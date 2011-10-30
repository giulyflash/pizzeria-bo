package gui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import Graph.Graph;

/**
 * Główne okno aplikacji
 * @author Michał Nowak
 * @version 1
 *
 */
public class MainApp {
	private final static String TITLE = "Pizzeria";
	private Shell shell;
	private Graph graph;
	
	/**
	 * Zwraca aktualnie załadowane miasto
	 * @return graf miasta
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * Głowne okno
	 * @param display
	 */
	public MainApp(Display display) {
		graph = null;
		
		shell = new Shell(display);
		shell.setText(TITLE);
		
		shell.setToolTipText("This is tooltip");
		
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
//		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
//		rowLayout.marginTop = 200;
//		rowLayout.marginBottom = 200;
//		rowLayout.marginLeft = 100;
//		rowLayout.marginRight = 100;
//		rowLayout.spacing = 10;
//		shell.setLayout(rowLayout);
		
		// MENU
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&Plik");
		
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);
		
		MenuItem loadItem = new MenuItem(fileMenu, SWT.PUSH);
		loadItem.setText("&Otwórz miasto");
		
		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Wyjście");
		
		MenuItem cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeHelpMenu.setText("&Pomoc");
		
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeHelpMenu.setMenu(helpMenu);
		
		MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
		helpItem.setText("&Wyświetl pomoc");
		
		MenuItem aboutItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutItem.setText("&Autorzy");
		
		shell.setMenuBar(menuBar);
		
		// LISTENERY
		exitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.getDisplay().dispose();
				System.exit(0);
			}
		});
		
		loadItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
				
				String [] filterNames = new String[] {"Pliki tekstowe", "Wszystkie pliki (*)"};
				String [] filterExtensions = new String [] { "*.txt", "*"};
				
				fileDialog.setFilterNames(filterNames);
				fileDialog.setFilterExtensions(filterExtensions);
				
				try {
					graph = GraphConverter.convert(fileDialog.open());
					MyPaintListener.addGraph(graph);
					shell.redraw();
				} catch (ArrayIndexOutOfBoundsException | IOException e1) {
					MessageBox msgBox = new MessageBox(shell, SWT.ICON_ERROR);
					msgBox.setMessage("Błędny format pliku!");
					msgBox.open();
				} catch(NullPointerException e2) {
					
				}
			}
		});
		
		shell.addPaintListener(new MyPaintListener());
		
		aboutItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
				messageBox.setMessage("Maks Kusak\nMichał Nowak\nŁukasz Sękalski\nGrzegorz Legień\nDominik Gawlik\nAlbert Kuźma\nPaweł Batko\nGrzesiek Mrozu\nMarcin Orzechowski\nDamian Goik");
				messageBox.open();
			}
		});
	}
	
	/**
	 * Wyśrodkowuje okno
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
