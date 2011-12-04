package gui;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import model.graph.Vertex;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Order;
import model.pizzeria.Result;
import model.pizzeria.Route;

/**
 * Klasa do testow rysowania trasy
 * @author Maks Kusak
 *
 */

public class Test {
	private ArrayList<DeliveryBoy> deliveryBoys;
	private Result wynik;
	private ArrayList<Double> iterationResults;
	private Route trasa;
	private final int CZAS = 10;
	private List<Vertex> vertices;
	private List<Order> orders;
	private DeliveryBoy boy;
	
	
	Result stworz(){
		wynik = new Result(); 
		deliveryBoys = new ArrayList<DeliveryBoy>();
		
		boy = new DeliveryBoy("boy1",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(1, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices,null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		boy = new DeliveryBoy("boy2",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(60,200)));
		vertices.add(new Vertex(3, new Point2D.Double(60,140)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices,null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		boy = new DeliveryBoy("boy3",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(250,60)));
		vertices.add(new Vertex(4, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		boy = new DeliveryBoy("boy4",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(250,60)));
		vertices.add(new Vertex(4, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		
		boy = new DeliveryBoy("boy5",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		boy = new DeliveryBoy("boy6",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(1, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices,null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		
		boy = new DeliveryBoy("boy7",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		boy = new DeliveryBoy("boy8",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(60,200)));
		vertices.add(new Vertex(3, new Point2D.Double(60,140)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices,null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
		wynik.setDeliveryBoys(deliveryBoys);
		
		return wynik;
		
	}
}
