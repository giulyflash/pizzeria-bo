package gui;

import java.util.ArrayList;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Vertex;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;

/**
 * Klasa do rysowania miasta z zadanego grafu.
 * @author Michał Nowak
 *
 */
public class MyPaintListener implements PaintListener {
	private final static int SIZE = 20;
	private static Graph graph;
	
	public MyPaintListener() {
		graph = null;
	}
	
	/**
	 * Ustala aktualnie rysowany graf
	 * @param g - graf
	 */
	public static void addGraph(Graph g) {
		graph = g;
	}
	
	/**
	 * Rysowaniee
	 */
	@Override
	public void paintControl(PaintEvent e) {
	//	Rectangle rect = ((Canvas) e.widget).getBounds();
	//    e.gc.drawFocus(5, 5, rect.width - 10, rect.height - 10);
	    
		if(graph != null) {
			e.gc.setBackground(new Color(e.display, 33, 200, 100));
			ArrayList<Vertex> list = (ArrayList<Vertex>) graph.getVertexList();
			
			for(Vertex v : list) {
				for(Edge ed : v.getEdgeList()) {
					e.gc.drawLine((int)v.getCoordinate().x+(SIZE/2), (int)v.getCoordinate().y+(SIZE/2), 
							(int)ed.getEnd().getCoordinate().x+(SIZE/2), 
							(int)ed.getEnd().getCoordinate().y+(SIZE/2));
				}
			}
			// TODO: drugi raz to samo zeby wezly byly na wierzchu. DO POPRAWY
			
			for(Vertex v : list) {
				e.gc.fillOval((int)v.getCoordinate().x, (int)v.getCoordinate().y, SIZE, SIZE);
				
				e.gc.drawText("" + v.getNumber(), (int)v.getCoordinate().x+(SIZE/4), 
						(int)v.getCoordinate().y+(SIZE/16), true);
			}
			
		}
	}

}
