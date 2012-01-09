package gui;

import java.io.IOException;
import java.util.ArrayList;

import model.graph.Graph;
import model.graph.GraphMatrix;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Order;
import model.pizzeria.Result;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import algorithms.genetic.GeneticAlgorithmRunner;
import algorithms.pso.PSOAlgorithm;

/**
 * Glowne okno aplikacji
 * @author Michal Nowak & Maks Kusak
 * @version 1.5
 *
 */

/**
 * UWAGA, do testów u¿ywaæ grafu:
10,20,2
120,70,1,3,4
80,40,1
250,60,2
60,140,0
60,200,4
200,140,3,1
 */

public class MainApp {
	private final static String TITLE = "Pizzeria";
	private static Shell shell;
	private static Graph graph;
	private static Canvas canvas;
	private static Display display; 
	private static Watek rysownik;
	private static int delay=800;
	private Label psoLabel5;
	
	private Spinner generalSpinner1;
	private Spinner generalSpinner2;
	private Spinner generalSpinner3;
	private Spinner generalSpinner4;
	
	private Spinner psoSpinner1;
	private Spinner psoSpinner2;
	private Spinner psoSpinner3;
	private Spinner psoSpinner4;
	private Spinner psoSpinner5;
	
	private Spinner gaSpinner1;
	private Spinner gaSpinner2;
	private Spinner gaSpinner3;
	private Spinner gaSpinner4;
	
	private Spinner optSpinner1;
	
	private Button optbutton1;
	private Button optbutton2;
	private Button optbutton3;
	private Button optbutton4;
	
	private Result wynik = null;

	
	/**
	 * Watek uzywany do rysowania sciezek.
	 * 
	 */
	static class Watek extends Thread{
		private int i,j;
		private Result re;
		
		public Watek(Result r) {
			re = r;
		}
		
		public void run() {
			//Test test = new Test();
			// CHANGE
			//wynik = test.stworz();	
			int iDostawcow=re.getDeliveryBoys().size();
			ArrayList<DeliveryBoy> boys = (ArrayList<DeliveryBoy>)re.getDeliveryBoys();
			for(i=0; i<iDostawcow; i++){
				int iVertex = boys.get(i).getCurrentRoute().getVertices().size();
				for(j=0; j<iVertex;j++){
					display.syncExec(new Runnable() {
				        public void run() {
				        	ResultsPaintListener.rysuj(graph, re, (i+1), (j+1));  
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
	 * watek do tworzenia nowego okna z wynikami
	 */
	
	static class OknoWynik extends Thread{
		private Image obraz;
		private String nazwa;
		
		OknoWynik(Image obraz, String nazwa){
			this.obraz = obraz;
			this.nazwa = nazwa;
		}
		
		public void run() {
					display.asyncExec(new Runnable() {
				        public void run() {
				        	new ResultsWindow(display, obraz,nazwa);
				        }});
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
		
		shell.setSize(800, 700);
		center(shell);

		initUI();
		
		shell.open();
		
		try {
			graph = GraphConverter.convert("miasto.txt");
			ResultsPaintListener.rysuj(graph,-1,-1);
			canvas.redraw();
			System.out.println("Loaded default city");
		} catch(Exception e) {
			//
		}
		
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
		generalSpinner1 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner1.setLocation(120, 20);
		generalSpinner1.setSize(80, 20);
		
		// GENERAL.deliveryCap
		Label generalLabel2 = new Label(generalGroup, SWT.LEFT);
		generalLabel2.setText("Delivery capacity");
		generalLabel2.setLocation(5, 45);
		generalLabel2.pack();
		// spinner
		generalSpinner2 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner2.setLocation(120, 45);
		generalSpinner2.setSize(80, 20);
		
		// GENERAL.deliveryRange
		Label generalLabel3 = new Label(generalGroup, SWT.LEFT);
		generalLabel3.setText("No. of orders\nbetween");
		generalLabel3.setLocation(5, 70);
		generalLabel3.pack();
		// spinner1
		generalSpinner3 = new Spinner(generalGroup, SWT.WRAP);
		generalSpinner3.setLocation(100, 75);
		generalSpinner3.setSize(50, 20);
		// spinner2
		generalSpinner4 = new Spinner(generalGroup, SWT.WRAP);
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
		psoSpinner1 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner1.setLocation(100, 20);
		psoSpinner1.setDigits(2);
		psoSpinner1.setSize(100, 20);
		
		// PSO.learning rates
		Label psoLabel2 = new Label(psoGroup, SWT.LEFT);
		psoLabel2.setText("c1");
		psoLabel2.setLocation(5, 45);
		psoLabel2.pack();
		// spinner
		psoSpinner2 = new Spinner(psoGroup, SWT.WRAP);
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
		psoSpinner3 = new Spinner(psoGroup, SWT.WRAP);
		psoSpinner3.setLocation(100, 70);
		psoSpinner3.setDigits(2);
		psoSpinner3.setSize(100, 20);
		
		// PSO.particles
		Label psoLabel4 = new Label(psoGroup, SWT.LEFT);
		psoLabel4.setText("Particles");
		psoLabel4.setLocation(5, 95);
		psoLabel4.pack();
		// spinner
		psoSpinner4 = new Spinner(psoGroup, SWT.WRAP);
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
		Group gaGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		gaGroup.setText("GA");
		gaGroup.setLocation(100, 100);
		
		// GA.iterations
		Label gaLabel1 = new Label(gaGroup, SWT.SHADOW_ETCHED_IN);
		gaLabel1.setText("Iterations");
		gaLabel1.setLocation(5, 20);
		gaLabel1.pack();
		// spinner
		gaSpinner1 = new Spinner(gaGroup, SWT.WRAP);
		gaSpinner1.setLocation(100, 20);
		gaSpinner1.setMaximum(100000);
		gaSpinner1.setSize(100, 20);
		
		// GA.population
		Label gaLabel2 = new Label(gaGroup, SWT.SHADOW_ETCHED_IN);
		gaLabel2.setText("Population");
		gaLabel2.setLocation(5, 45);
		gaLabel2.pack();
		// spinner
		gaSpinner2 = new Spinner(gaGroup, SWT.WRAP);
		gaSpinner2.setLocation(100, 45);
		gaSpinner2.setMaximum(100000);
		gaSpinner2.setSize(100, 20);
		
		// GA.crossover
		Label gaLabel3 = new Label(gaGroup, SWT.SHADOW_ETCHED_IN);
		gaLabel3.setText("Crossover");
		gaLabel3.setLocation(5, 70);
		gaLabel3.pack();
		// spinner
		gaSpinner3 = new Spinner(gaGroup, SWT.WRAP);
		gaSpinner3.setLocation(100, 70);
		gaSpinner3.setSize(100, 20);
		gaSpinner3.setDigits(2);
		
		// GA.mutacja
		Label gaLabel4 = new Label(gaGroup, SWT.SHADOW_ETCHED_IN);
		gaLabel4.setText("Mutation");
		gaLabel4.setLocation(5, 95);
		gaLabel4.pack();
		// spinner
		gaSpinner4 = new Spinner(gaGroup, SWT.WRAP);
		gaSpinner4.setLocation(100, 95);
		gaSpinner4.setSize(100, 20);
		gaSpinner4.setDigits(2);
		
		// GA.compute
		Button gaBtn = new Button(gaGroup, SWT.PUSH);
		gaBtn.setText("Compute!");
		gaBtn.setLocation(100, 120);
		gaBtn.pack();
		
		// RYSOWANIE
		Group optGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		optGroup.setText("Drawing options");
		optGroup.setLocation(100, 100);
		
		// PSO.inertia
		Label optLabel1 = new Label(optGroup, SWT.LEFT);
		optLabel1.setText("Speed (ms)");
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
		optbutton1.setText("Show all routes");
		optbutton1.setLocation(5, 40);
		optbutton1.pack();
		
		optbutton2 = new Button(optGroup, SWT.CHECK);
		optbutton2.setText("Show verticles numbers");
		optbutton2.setLocation(5, 60);
		optbutton2.setSelection(true);
		optbutton2.pack();
		
		optbutton3 = new Button(optGroup, SWT.PUSH);
		optbutton3.setText("Repaint");
		optbutton3.setLocation(5, 80);
		optbutton3.pack();
		
		optbutton4 = new Button(optGroup, SWT.PUSH);
		optbutton4.setText("Show Results");
		optbutton4.setLocation(70, 80);
		optbutton4.pack();
		
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
		
		helpItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Runtime.getRuntime().exec("hh.exe BOhelp.chm");
				} catch (IOException e1) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
					messageBox.setMessage("Can't open help file!");
					messageBox.open();
				}
			}
		});
		
		optSpinner1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				delay = Integer.parseInt(optSpinner1.getText());
				//System.out.println(delay);
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
		
		optbutton3.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if(wynik!=null)
				{
					try {
						rysownik.interrupt();
					} catch (Exception a) {
						System.out.println("Problem z przerwaniem");
					}
					rysownik = new Watek(wynik);
					rysownik.start();
					canvas.redraw();
				}
			}
		});
		
		optbutton4.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if(wynik!=null)
				{
					try {
						rysownik.interrupt();
					} catch (Exception a) {
						System.out.println("Problem z przerwaniem");
					}
					rysownik = new Watek(wynik);
					rysownik.start();
					canvas.redraw();
				}
			}
		});
		
		
	
		gaBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				try {
				rysownik.interrupt();
				} catch (Exception a){}
				//Test test = new Test();
				
				ArrayList<Order> orders = new ArrayList<Order>();
				
				int a = Integer.parseInt(generalSpinner3.getText());
				int b = Integer.parseInt(generalSpinner4.getText());
				
				if(a > b) {
					int c = b;
					b = a;
					a = c;
				}
				
				int range = (int)(Math.random() * (b-a) ) + a;

				if(graph!=null){
					ArrayList<Integer> used = new ArrayList<Integer>();
					int i = 0;

					while(i < range) {
						int v = (int) (Math.random() * (graph.getVertexList().size()-2)) + 1;
						if(used.contains(v))
							continue;
						
						used.add(v);
						System.out.println("increased ... (" + i + ")");
						i++;
						orders.add(new Order(graph.getVertex(v), 1));
						if (i >= graph.getVertexList().size() - 2)
							break;
					}
					
					GraphMatrix gm = new GraphMatrix(graph.getVertex(0), orders, graph);
					// Algorithm specific
//					System.out.println("Dane: " + Integer.parseInt(gaSpinner1.getText().replace(',', '.')) +
//							Integer.parseInt(gaSpinner2.getText().replace(',', '.')) +
//							Double.parseDouble(gaSpinner3.getText().replace(',', '.')) +
//							Double.parseDouble(gaSpinner4.getText().replace(',', '.')));
					
					AlghoritmComputer psoComputer = new AlghoritmComputer(
							new GeneticAlgorithmRunner(Integer.parseInt(gaSpinner1.getText().replace(',', '.')), 
									Integer.parseInt(gaSpinner2.getText().replace(',', '.')), 
									Double.parseDouble(gaSpinner3.getText().replace(',', '.')), 
									Double.parseDouble(gaSpinner4.getText().replace(',', '.'))),
									
							Integer.parseInt(gaSpinner1.getText().replace(',', '.')),
							Integer.parseInt(gaSpinner2.getText().replace(',', '.')),
							Double.parseDouble(gaSpinner3.getText().replace(',', '.')),
							Double.parseDouble(gaSpinner4.getText().replace(',', '.')),
							0,
							Integer.parseInt(generalSpinner1.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner2.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner3.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner4.getText().replace(',', '.')),
							gm);
					
					delay = Integer.parseInt(optSpinner1.getText());
					// tu mam przekazac result
					wynik = psoComputer.getResult();
					OknoWynik oknoWynik = new OknoWynik(new Image(display,"obrazek.jpg"), "algorithm GEN");
					oknoWynik.start();
					rysownik = new Watek(wynik);
					rysownik.start();
				}		
				
			//	System.out.println(delay);
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

			}
		});
		
		// tworzy osobny watek, ktory rysuje sciezki
		psoBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				try {
				rysownik.interrupt();
				} catch (Exception a){}
				//Test test = new Test();
				
				ArrayList<Order> orders = new ArrayList<Order>();
				
				int a = Integer.parseInt(generalSpinner3.getText());
				int b = Integer.parseInt(generalSpinner4.getText());
				
				if(a > b) {
					int c = b;
					b = a;
					a = c;
				}
				
				int range = (int)(Math.random() * (b-a) ) + a;

				if(graph!=null){
					ArrayList<Integer> used = new ArrayList<Integer>();
					int i = 0;
					//for (int i = 0; i < range ; i++) {
					while(i < range) {
						int v = (int) (Math.random() * (graph.getVertexList().size()-2)) + 1;
						if(used.contains(v))
							continue;
						
						used.add(v);
						System.out.println("increased ... (" + i + ")");
						i++;
						orders.add(new Order(graph.getVertex(v), 1));
						if (i >= graph.getVertexList().size() - 2)
							break;
					}
					
					GraphMatrix gm = new GraphMatrix(graph.getVertex(0), orders, graph);
					AlghoritmComputer psoComputer = new AlghoritmComputer(new PSOAlgorithm(), Double.parseDouble(psoSpinner1.getText().replace(',', '.')),
							Double.parseDouble(psoSpinner2.getText().replace(',', '.')),
							Double.parseDouble(psoSpinner3.getText().replace(',', '.')),
							Integer.parseInt(psoSpinner4.getText().replace(',', '.')),
							Integer.parseInt(psoSpinner5.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner1.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner2.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner3.getText().replace(',', '.')),
							Integer.parseInt(generalSpinner4.getText().replace(',', '.')),
							gm);
					
					delay = Integer.parseInt(optSpinner1.getText());
					// tu mam przekazac result
					wynik = psoComputer.getResult();
					OknoWynik oknoWynik = new OknoWynik(new Image(display,"obrazek.jpg"), "algorithm PSO");
					oknoWynik.start();
					rysownik = new Watek(wynik);
					rysownik.start();
				}		
				
			//	System.out.println(delay);
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
