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
public class ResultsPaintListener implements PaintListener {
	private final static int SIZE = 20;
	private static Graph graph;
	private static int SCROLL_X, SCROLL_Y; 
	
	public ResultsPaintListener() {
		graph = null;
		SCROLL_X = 0;
		SCROLL_Y = 0;
	}
	
	public static void scrollX(int x) {
		SCROLL_X += x;
	}
	
	public static void scrollY(int y) {
		SCROLL_Y += y;
	}
	
	/**
	 * Ustala aktualnie rysowany graf
	 * @param g - graf
	 */
	public static void addGraph(Graph g) {
		graph = g;
	}
	
	/**
	 * Rysowanie
	 */
	@Override
	public void paintControl(PaintEvent e) {
	    
		if(graph != null) {
			e.gc.setBackground(new Color(e.display, 33, 200, 100));
			ArrayList<Vertex> list = (ArrayList<Vertex>) graph.getVertexList();
			
			for(Vertex v : list) {
				for(Edge ed : v.getEdgeList()) {
					e.gc.drawLine((int)v.getCoordinate().x+(SIZE/2)+SCROLL_X, (int)v.getCoordinate().y+(SIZE/2)+SCROLL_Y, 
							(int)ed.getEnd().getCoordinate().x+(SIZE/2)+SCROLL_X, 
							(int)ed.getEnd().getCoordinate().y+(SIZE/2)+SCROLL_Y);
				}
			}
			// TODO: drugi raz to samo zeby wezly byly na wierzchu. DO POPRAWY ?
			
			for(Vertex v : list) {
				e.gc.fillOval((int)v.getCoordinate().x+SCROLL_X, (int)v.getCoordinate().y+SCROLL_Y, SIZE, SIZE);
				
				// cyferki na wezlach
				e.gc.drawText("" + v.getNumber(), (int)v.getCoordinate().x+(SIZE/4), 
						(int)v.getCoordinate().y+(SIZE/16), true);
			}
			
			
			
		}
	}

}
