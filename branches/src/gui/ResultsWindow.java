package gui;

import java.util.ArrayList;
import java.util.LinkedList;

import model.graph.Graph;
import model.graph.Vertex;
import model.pizzeria.DeliveryBoy;
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
import org.eclipse.swt.widgets.Text;

public class ResultsWindow {

	private Display display;
	private Shell shell;
	private Result result;
	
	public ResultsWindow (Display display, Image obraz, String nazwa, Result result) {
		this.display = display;
		this.result = result;
		shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setLayout(new FillLayout());
		shell.setText(nazwa);
		shell.setSize(860, 700);

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
	    TabItem one = new TabItem(tabFolder, SWT.NONE);
	    one.setText("Chart");
	    one.setControl(getTabOneControl(tabFolder, obraz));;
	
	    Text text = new Text(tabFolder, SWT.MULTI | SWT.BORDER  | SWT.V_SCROLL);
	    text.setBounds(0, 0, 200, 200);
	    ArrayList<DeliveryBoy> boysi = result.getDeliveryBoys(); 
	    String abba="";
	    
	    for(int i=0; i<boysi.size(); i++)
	    {
	    	abba += "DOSTAWCA "+ i + "\n Wierzcholki: ";
	    	LinkedList<Vertex> vlist = (LinkedList<Vertex>)boysi.get(i).getCurrentRoute().getVertices();
	    	int szerokosc = 14;
	    	for(Vertex v : vlist) 
	    	{
		    	if(szerokosc + Integer.toString(i).length() +1 >134) 
		    	{
		    		abba +=  "\n";
		    		szerokosc = 0;
		    	}
				abba += v.getNumber() + ", ";
				szerokosc += Integer.toString(v.getNumber()).length()+1; 
			}
	    	abba += "\n";
	    	
	    }
	    
	    text.setText(abba);
	    text.setEditable(false);
	    
	    
	    TabItem two = new TabItem(tabFolder, SWT.NONE);
	    two.setText("Results");
	    two.setControl(text);
		
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
	
	
