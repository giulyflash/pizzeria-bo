package gui;

import java.io.IOException;

import model.graph.Graph;

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
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

/**
 * Glowne okno aplikacji
 * @author Michal‚ Nowak
 * @version 1
 *
 */
public class MainApp {
	private final static String TITLE = "Pizzeria";
	private Shell shell;
	private Graph graph;
	private Canvas canvas;
	
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
		
		center(shell);
		
		initUI();
		
		shell.open();
		shell.setSize(800, 600);
		
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
		paramGroup.setLayout(new RowLayout(SWT.VERTICAL));
		
		
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
		
		// PSO.compute
		Button psoBtn = new Button(psoGroup, SWT.PUSH);
		psoBtn.setText("Compute!");
		psoBtn.setLocation(100, 120);
		psoBtn.pack();
		
		// GA
		// TODO: parametry
		Group gaGroup = new Group(paramGroup, SWT.SHADOW_ETCHED_IN);
		gaGroup.setText("Genetyczny");
		gaGroup.setLocation(100, 100);
		
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
					graph = GraphConverter.convert(fileDialog.open());
					MyPaintListener.addGraph(graph);
					System.out.println("Loded");
					canvas.redraw();
				} catch (ArrayIndexOutOfBoundsException | IOException e1) {
					MessageBox msgBox = new MessageBox(shell, SWT.ICON_ERROR);
					msgBox.setMessage("Bad file format!");
					msgBox.open();
				} catch(NullPointerException e2) {
					
				}
			}
		});
		
		canvas.addPaintListener(new MyPaintListener());
		
		aboutItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
				messageBox.setMessage("Maks Kusak\nMichaĹ‚ Nowak\nĹ�ukasz SÄ™kalski\nGrzegorz LegieĹ„\nDominik Gawlik\nAlbert KuĹşma\nPaweĹ‚ Batko\nGrzesiek Mrozu\nMarcin Orzechowski\nDamian Goik");
				messageBox.open();
			}
		});
		
		// TODO w razie czego mozna wypierdolic bo i tak ladniej jak miasto sie miesci 
		// na jednym ekranie
		final Point origin = new Point(0, 0);
		
		canvas.getHorizontalBar().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int hSelection = canvas.getHorizontalBar().getSelection();
				MyPaintListener.scrollX(2*(-hSelection - origin.x));
				origin.x = -hSelection;
				canvas.redraw();
			}
		});
		
		canvas.getVerticalBar().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				int vSelection = canvas.getVerticalBar().getSelection();
				MyPaintListener.scrollY(2*(-vSelection - origin.y));
				origin.y = -vSelection;
				canvas.redraw();
			}
		});
	}
	
	/**
	 * WyĹ›rodkowuje okno
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
		Display display = new Display();
		
		new MainApp(display);
		display.dispose();
	}

}
