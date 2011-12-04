package gui;

import java.util.ArrayList;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Vertex;
import model.pizzeria.Result;
import model.pizzeria.DeliveryBoy;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;

/**
 * Klasa do rysowania tras przejazdow
 * @author Maks Kusak
 *
 */
public class ResultsPaintListener implements PaintListener {
	private final static int SIZE = 30;
	private final static int SIZEK = 20;
	private static Graph graph;
	private static int SCROLL_X, SCROLL_Y; 
	private static Result wynik;
	private static int dostawca;
	private static int vertex;
	
	public ResultsPaintListener() {
		graph = null;
		wynik = null;
		SCROLL_X = 0;
		SCROLL_Y = 0;
		dostawca = 0;
		vertex = 0;
		
	}
	
	public static void zmienV(int x) {
		SCROLL_X += x;
	}
	
	public static void zmienD(int x) {
		dostawca=x;
	}
	
	public static void incV(int x) {
		vertex=x;
	}
	
	
	public static void scrollY(int y) {
		SCROLL_Y += y;
	}
	
	public static void scrollX(int x) {
		SCROLL_X += x;
	}
	
	/**
	 * Ustala aktualnie rysowany graf
	 * i trase
	 */
	public static void rysuj(Graph g, Result w, int i, int j) {
		graph = g;
		wynik = w;
		dostawca= i;
		vertex = j;
	}
	
	public static void rysuj(Graph g, int i, int j) {
		dostawca= i;
		vertex = j;
		graph=g;
	}
	
	public int min(int x, int y){
		if(x<y) return x; else return y;
	}
	
	/**
	 * Rysowanie
	 */
	@Override
	public void paintControl(PaintEvent e) {
		if (dostawca==-1){
			if(graph != null) {
				e.gc.setLineWidth(2);
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
				//	e.gc.drawText("" + v.getNumber(), (int)v.getCoordinate().x+(SIZE/4)+SCROLL_X, 
					//		(int)v.getCoordinate().y+(SIZE/16)+SCROLL_Y, true);
				}
		}
		}
			else
		{
		if(graph != null) {
			e.gc.setLineWidth(2);
			e.gc.setBackground(new Color(e.display, 33, 200, 100));
			e.gc.setForeground(new Color(e.display, 0, 0, 0));
		ArrayList<Vertex> list = (ArrayList<Vertex>) graph.getVertexList();
						
			for(Vertex v : list) {
				for(Edge ed : v.getEdgeList()) {
					e.gc.drawLine((int)v.getCoordinate().x+(SIZE/2)+SCROLL_X, (int)v.getCoordinate().y+(SIZE/2)+SCROLL_Y, 
							(int)ed.getEnd().getCoordinate().x+(SIZE/2)+SCROLL_X, 
							(int)ed.getEnd().getCoordinate().y+(SIZE/2)+SCROLL_Y);
				}
			} 
			e.gc.setLineWidth(3);
			// rysuje trasy dla kazdego dostawcy
			int odchylenie = 2;  //o ile przesunieta bedzie linia wzgledem œroda (zeby sie nie zlewaly)
			int zmiana = 1;
			int k=1;
			System.out.println(dostawca + " "+ vertex);
			ArrayList<DeliveryBoy> boys = (ArrayList<DeliveryBoy>)wynik.getDeliveryBoys();
	/*		for(int i=0; i<dostawca-1; i++){
				for(int j=0;j<4;j++){
					e.gc.drawLine(i*20,i*50,j*50,j*70);
				}
			}
			
			
			for(int i=0; i<dostawca; i++){
				for(int j=0;j<vertex;j++){
					e.gc.drawLine(i*20,i*50,j*50,j*70);
				}
			}*/
			e.gc.setLineWidth(3);
			for (int i=0; i<dostawca-1; i++){		
				e.gc.setForeground(e.display.getSystemColor(k+3));
			//	System.out.println(odchylenie);
				
				ArrayList<Vertex> vlist = (ArrayList<Vertex>)boys.get(i).getCurrentRoute().getVertices();  
				for (int j=0; j<vlist.size()-1 ; j++){
					e.gc.drawLine((int)vlist.get(j).getCoordinate().x+(SIZE/2)+SCROLL_X+zmiana*odchylenie, (int)vlist.get(j).getCoordinate().y+(SIZE/2)+SCROLL_Y+zmiana*odchylenie, 
							(int)vlist.get(j+1).getCoordinate().x+(SIZE/2)+SCROLL_X+zmiana*odchylenie, (int)vlist.get(j+1).getCoordinate().y+(SIZE/2)+SCROLL_Y+zmiana*odchylenie);
				}
				if(k % 2 == 0) odchylenie += 2; 
				zmiana = -zmiana;
				k++;
			}
			
		//	odchylenie = 2;  //o ile przesunieta bedzie linia wzgledem œroda (zeby sie nie zlewaly)
		//	zmiana = 1;
		//	k=1;
			
			e.gc.setLineWidth(3);	
			e.gc.setForeground(e.display.getSystemColor(k+3));
			

			ArrayList<Vertex> vlist = (ArrayList<Vertex>)boys.get(dostawca-1).getCurrentRoute().getVertices();  
			int min = min(vlist.size(),vertex);
			for (int j=0; j<min-1 ; j++){
				e.gc.drawLine((int)vlist.get(j).getCoordinate().x+(SIZE/2)+SCROLL_X+zmiana*odchylenie, (int)vlist.get(j).getCoordinate().y+(SIZE/2)+SCROLL_Y+zmiana*odchylenie, 
						(int)vlist.get(j+1).getCoordinate().x+(SIZE/2)+SCROLL_X+zmiana*odchylenie, (int)vlist.get(j+1).getCoordinate().y+(SIZE/2)+SCROLL_Y+zmiana*odchylenie);
				
			}

			
			e.gc.setForeground(new Color(e.display, 0, 0, 0));
			e.gc.setBackground(new Color(e.display, 33, 200, 100));
			for(Vertex v : list) {
				e.gc.fillOval((int)v.getCoordinate().x+SCROLL_X, (int)v.getCoordinate().y+SCROLL_Y, SIZE, SIZE);
			//	e.gc.drawText("" + v.getNumber(), (int)v.getCoordinate().x+(SIZE/4)+SCROLL_X, 
				//		(int)v.getCoordinate().y+(SIZE/16)+SCROLL_Y, true);
			}
			e.gc.setBackground(new Color(e.display, 250, 250, 250));
			e.gc.fillOval((int)vlist.get(min-1).getCoordinate().x+SCROLL_X+(SIZE - SIZEK)/2, (int)vlist.get(min-1).getCoordinate().y+SCROLL_Y+(SIZE - SIZEK)/2, SIZEK, SIZEK);
			e.gc.drawText(""+(dostawca), (int)vlist.get(min-1).getCoordinate().x+SCROLL_X+12, (int)vlist.get(min-1).getCoordinate().y+SCROLL_Y+8, true);
		}	
	}
	}

}
