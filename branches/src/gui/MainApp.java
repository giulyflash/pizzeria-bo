package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.graph.Graph;
import model.graph.Vertex;
import model.pizzeria.Result;
import model.pizzeria.DeliveryBoy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Glowne okno aplikacji
 * @author Michal Nowak & Maks Kusak
 * @version 1.1
 *
 */
public class MainApp {
	private final static String TITLE = "Pizzeria";
	private static Shell shell;
	private static Graph graph;
	private static Canvas canvas;
	private static Result wynik;
	private static ProgressBar bar;
	private static Display display; 
	private static Watek rysownik;
	private static int delay=800;
	private Label psoLabel5;
	private Spinner psoSpinner5;
	private Spinner optSpinner1;
	private Button optbutton1;
	private Button optbutton2;
	

	
	/*static class Watek extends Thread{
		private int i,j;
		public Watek(int i, int j){
			this.i=i;
			this.j=j;
		}
		
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
			System.out.println(i + " "+ j);
			//ResultsPaintListener.zmienD(i);   
			//ResultsPaintListener.zmienV(j);
			ResultsPaintListener.rysuj(graph, wynik, (i+1), (j+1));  
			canvas.redraw();	
		}
	}*/
	
	/**
	 * Watek uzywany do rysowania sciezek.
	 * 
	 */
	static class Watek extends Thread{
		private int i,j;
		
		public void run() {
			Test test = new Test();
			wynik = test.stworz();	
			int iDostawcow=wynik.getDeliveryBoys().size();
			ArrayList<DeliveryBoy> boys = (ArrayList<DeliveryBoy>)wynik.getDeliveryBoys();
			for(i=0; i<iDostawcow; i++){
				int iVertex = boys.get(i).getCurrentRoute().getVertices().size();
				for(j=0; j<iVertex;j++){
					display.syncExec(new Runnable() {
				        public void run() {
				        	ResultsPaintListener.rysuj(graph, wynik, (i+1), (j+1));  
				        	canvas.redraw();	
				        }});
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						ResultsPaintListener.rysuj(graph,-1,-1);
						canvas.redraw();
						System.out.println("Interrupted.");
					}
				}	
			}
			System.out.println("Narysowane");
		}
	}
	
	/**
	 * Zwraca aktualnie zaladowane miasto
	 * @return graf miasta
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * Glowne okno
	 * @param display
	 */
	public MainApp(Display display) {
		graph = null;
		
		shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setText(TITLE);
		canvas = new Canvas(shell, SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		
		shell.setToolTipText("This is tooltip");
		
		shell.setSize(800, 600);
		center(shell);
		
		initUI();
		
		shell.open();
		
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	
	/**
	 * Inicjalizacja interfejsu. Layouty, przyciski etc
	 */
	public void initUI() {
		// LAYOUT
		// TODO layout ladniejszy	??
		RowLayout shellLayout = new RowLayout();
		shellLayout.wrap = false;
		shellLayout.fill = true;
		
		shell.setLayout(shellLayout);
		
		// CANVAS
		canvas.setLayoutData(new RowData(550, 500));
		
		// GROUP
		// PARAMS
		Group paramGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
		paramGroup.setText("Parameters");
		paramGroup.setLayout(new RowLayout(SWT.VERTICAL));
		

		
		// GENERAL
		Group generalGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		generalGroup.setText("General");
		generalGroup.setLocation(100, 100);
		

		
		// GENERAL.delivery
		Label generalLabel1 = new Label(generalGroup, SWT.LEFT);
		generalLabel1.setText("Delivery boys");
		generalLabel1.setLocation(5, 20);
		generalLabel1.pack();
		// spinner
		Spinner generalSpinner1 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner1.setLocation(120, 20);
		generalSpinner1.setSize(80, 20);
		
		// GENERAL.deliveryCap
		Label generalLabel2 = new Label(generalGroup, SWT.LEFT);
		generalLabel2.setText("Delivery capacity");
		generalLabel2.setLocation(5, 45);
		generalLabel2.pack();
		// spinner
		Spinner generalSpinner2 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner2.setLocation(120, 45);
		generalSpinner2.setSize(80, 20);
		
		// GENERAL.deliveryRange
		Label generalLabel3 = new Label(generalGroup, SWT.LEFT);
		generalLabel3.setText("No. of orders\nbetween");
		generalLabel3.setLocation(5, 70);
		generalLabel3.pack();
		// spinner1
		Spinner generalSpinner3 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner3.setLocation(100, 75);
		generalSpinner3.setSize(50, 20);
		// spinner2
		Spinner generalSpinner4 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner4.setLocation(150, 75);
		generalSpinner4.setSize(50, 20);
		
		// PSO
		Group psoGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		psoGroup.setText("PSO");
		psoGroup.setLocation(100, 100);
		
		// PSO.inertia
		Label psoLabel1 = new Label(psoGroup, SWT.LEFT);
		psoLabel1.setText("w (inertia)");
		psoLabel1.setLocation(5, 20);
		psoLabel1.pack();
		// spinner
		Spinner psoSpinner1 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner1.setLocation(100, 20);
		psoSpinner1.setDigits(2);
		psoSpinner1.setSize(100, 20);
		
		// PSO.learning rates
		Label psoLabel2 = new Label(psoGroup, SWT.LEFT);		// TODO: zrobic scroolowanie
		psoLabel2.setText("c1");
		psoLabel2.setLocation(5, 45);
		psoLabel2.pack();
		// spinner
		Spinner psoSpinner2 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner2.setLocation(100, 45);
		psoSpinner2.setDigits(2);
		psoSpinner2.setSize(100, 20);
		psoSpinner2.setSize(100, 20);
		


		// PSO.learning rates 2
		Label psoLabel3 = new Label(psoGroup, SWT.LEFT);
		psoLabel3.setText("c2");
		psoLabel3.setLocation(5, 70);
		psoLabel3.pack();
		// spinner
		Spinner psoSpinner3 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner3.setLocation(100, 70);
		psoSpinner3.setDigits(2);
		psoSpinner3.setSize(100, 20);
		
		// PSO.particles
		Label psoLabel4 = new Label(psoGroup, SWT.LEFT);
		psoLabel4.setText("Particles");
		psoLabel4.setLocation(5, 95);
		psoLabel4.pack();
		// spinner
		Spinner psoSpinner4 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner4.setLocation(100, 95);
		psoSpinner4.setMaximum(1000);
		psoSpinner4.setSize(100, 20);
		
		// PSO.iterations
		psoLabel5 = new Label(psoGroup, SWT.LEFT);
		psoLabel5.setText("Iterations");
		psoLabel5.setLocation(5, 120);
		psoLabel5.pack();
		// spinner
		psoSpinner5 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner5.setLocation(100, 120);
		psoSpinner5.setMaximum(100000);
		psoSpinner5.setSize(100, 20);
		
		// PSO.compute
		Button psoBtn = new Button(psoGroup, SWT.PUSH);
		psoBtn.setText("Compute!");
		psoBtn.setLocation(100, 145);
		psoBtn.pack();
		
		// GA
		// TODO: parametry
		Group gaGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		gaGroup.setText("Genetyczny");
		gaGroup.setLocation(100, 100);
		
		
		Group optGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		psoGroup.setText("Opcje");
		psoGroup.setLocation(100, 100);
		
		// PSO.inertia
		Label optLabel1 = new Label(optGroup, SWT.LEFT);
		optLabel1.setText("Szybkosc (ms)");
		optLabel1.setLocation(5, 20);
		optLabel1.pack();
		// spinner
		optSpinner1 = new Spinner(optGroup, SWT.WRAP);
		optSpinner1.setLocation(100, 20);
		optSpinner1.setMaximum(3000);
		optSpinner1.setSelection(800);
		optSpinner1.setMinimum(100);
		optSpinner1.setIncrement(100);
		optSpinner1.setSize(100, 20);
		
		optbutton1 = new Button(optGroup, SWT.CHECK);
		optbutton1.setText("Pokaz wszystkie trasy");
		optbutton1.setLocation(5, 40);
		optbutton1.pack();
		
		optbutton2 = new Button(optGroup, SWT.CHECK);
		optbutton2.setText("Pokaz nr wierzcholkow");
		optbutton2.setLocation(5, 60);
		optbutton2.pack();
		
		//bar = new ProgressBar (paramGroup, SWT.NULL);
		//bar.setLocation(0,0);
		//bar.setSize(100, 20);
		
		// MENU
		// TODO zapisywanie wynikow
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");
		
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);
		
		MenuItem loadItem = new MenuItem(fileMenu, SWT.PUSH);
		loadItem.setText("&Open city");
		
		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
		
		MenuItem cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeHelpMenu.setText("&Help");
		
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeHelpMenu.setMenu(helpMenu);
		
		MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
		helpItem.setText("&Show help");
		
		MenuItem aboutItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutItem.setText("&Authors");
		
		shell.setMenuBar(menuBar);
		
		// LISTENERY
		exitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.getDisplay().dispose();
				System.exit(0);
			}
		});
		
		optSpinner1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				delay = Integer.parseInt(optSpinner1.getText());
				System.out.println(delay);
			}
		});
		
		optbutton1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				ResultsPaintListener.setWszyscy(optbutton1.getSelection());
				canvas.redraw();
			}
		});
		
		optbutton2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				ResultsPaintListener.setVertexNrVisible(optbutton2.getSelection());
				canvas.redraw();
			}
		});
		
	
		// tworzy osobny watek, ktory rysuje sciezki
		psoBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				try {
				rysownik.interrupt();
				} catch (Exception a){}
				Test test = new Test();
				wynik = test.stworz();	
				delay = Integer.parseInt(optSpinner1.getText());
				System.out.println(delay);
			/*	display.asyncExec( new Watek(1,1));
				int iDostawcow=wynik.getDeliveryBoys().size();
				ArrayList<DeliveryBoy> boys = (ArrayList<DeliveryBoy>)wynik.getDeliveryBoys();
				for(int i=0; i<iDostawcow; i++){
					int iVertex = boys.get(i).getCurrentRoute().getVertices().size();
					for(int j=0; j<iVertex;j++){
						display.asyncExec( new Watek(i,j));
	
					}	
				} */
				System.out.println("Narysowane");
				rysownik = new Watek();
				rysownik.start();
			}
		});
		
		// OPEN CITY
		loadItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
				
				String [] filterNames = new String[] {"Text files", "All files (*)"};
				String [] filterExtensions = new String [] { "*.txt", "*"};
				
				fileDialog.setFilterNames(filterNames);
				fileDialog.setFilterExtensions(filterExtensions);
				
				try {
					rysownik.interrupt();
				} catch (Exception a) {
					System.out.println("Problem z przerwaniem");
				}
				
				try {
					graph = GraphConverter.convert(fileDialog.open());
					ResultsPaintListener.rysuj(graph,-1,-1);
					System.out.println("Loaded");
					canvas.redraw();
				} catch (ArrayIndexOutOfBoundsException | IOException e1) {
					MessageBox msgBox = new MessageBox(shell, SWT.ICON_ERROR);
					msgBox.setMessage("Bad file format!");
					msgBox.open();
				} catch(NullPointerException e2) {
					
				}
			}
		});
		
	//	canvas.addPaintListener(new MyPaintListener());
		canvas.addPaintListener(new ResultsPaintListener());
		
		aboutItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
				messageBox.setMessage("Maks Kusak\nMichal Nowak\nLukasz Sekalski\nGrzegorz Legien\nDominik Gawlik\nAlbert Kuzma\nPawel Batko\nGrzesiek Mrozu\nMarcin Orzechowski\nDamian Goik");
				messageBox.open();
			}
		});
		
		// SCROLLOWANIE
		// TODO w razie czego mozna wypierdolic bo i tak ladniej jak miasto sie miesci 
		// na jednym ekranie
		final Point origin = new Point(0, 0);
		
		canvas.getHorizontalBar().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int hSelection = canvas.getHorizontalBar().getSelection();
			//	MyPaintListener.scrollX(2*(-hSelection - origin.x));
				ResultsPaintListener.scrollX(2*(-hSelection - origin.x));
				origin.x = -hSelection;
				canvas.redraw();
			}
		});
		
		canvas.getVerticalBar().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int vSelection = canvas.getVerticalBar().getSelection();
			//	MyPaintListener.scrollY(2*(-vSelection - origin.y));
				ResultsPaintListener.scrollY(2*(-vSelection - origin.y));
				origin.y = -vSelection;
				canvas.redraw();
			}
		});
	}
	
	/**
	 * Wyºrodkowuje okno
	 * @param shell
	 */
	private void center(Shell shell) {
		Rectangle bds = shell.getDisplay().getBounds();
		
		Point p = shell.getSize();
		
		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;
		
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	/**
	 * Main ?
	 * @param args
	 */
	public static void main(String[] args) {
		display = new Display();
		
		new MainApp(display);
		
		display.dispose();
	}

}
