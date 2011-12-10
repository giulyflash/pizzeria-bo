package gui;

import model.graph.Graph;
import model.pizzeria.Result;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class ResultsWindow {

	private Display display;
	private Shell shell;
	
	public ResultsWindow (Display display, Image obraz, String nazwa) {
		this.display = display;
		shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText(nazwa);
		shell.setSize(860, 700);

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
	    TabItem one = new TabItem(tabFolder, SWT.NONE);
	    one.setText("Wykres");
	    one.setControl(getTabOneControl(tabFolder, obraz));;
	
	    
	    TabItem two = new TabItem(tabFolder, SWT.NONE);
	    two.setText("Wyniki");
		
		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Control getTabOneControl(TabFolder tabFolder, Image obraz) {
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(new FillLayout(SWT.VERTICAL));
	    Label lb1 = new Label(composite, SWT.BORDER);
	    lb1.setImage(obraz);
	    return composite;
	}
		
}
	
	
