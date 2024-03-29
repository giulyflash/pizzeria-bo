package gui;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private List<Double> wyniki;
	
	Result stworz(){
		wynik = new Result(); 
		deliveryBoys = new ArrayList<DeliveryBoy>();
		
		wyniki= new ArrayList<Double>();
		
		Random generator = new Random(System.currentTimeMillis());
		for(int i=0; i<100; i++){
			wyniki.add(generator.nextDouble()*1000%100);
		}
		
		//Chart wykres = new Chart(wyniki, "algorithm GEN/PSO");
		
//		wykres.saveChart("obrazek.jpg");

		for(double value: wyniki){
			System.out.print(value+" ");
		}
		
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
		
		boy = new DeliveryBoy("boy9",5);
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
		
		boy = new DeliveryBoy("boy10",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(200,140)));
		vertices.add(new Vertex(4, new Point2D.Double(250,60)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
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
		
		/*		boy = new DeliveryBoy("boy6",5);
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
		
		boy = new DeliveryBoy("boy9",5);
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
		
		boy = new DeliveryBoy("boy10",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(200,140)));
		vertices.add(new Vertex(4, new Point2D.Double(250,60)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);
		
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
		
		boy = new DeliveryBoy("boy9",5);
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
		
		boy = new DeliveryBoy("boy10",5);
		vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(0, new Point2D.Double(10,20)));
		vertices.add(new Vertex(1, new Point2D.Double(60,140)));
		vertices.add(new Vertex(2, new Point2D.Double(120,70)));
		vertices.add(new Vertex(3, new Point2D.Double(200,140)));
		vertices.add(new Vertex(4, new Point2D.Double(250,60)));
		vertices.add(new Vertex(1, new Point2D.Double(80,40)));
		vertices.add(new Vertex(4, new Point2D.Double(10,20)));
		trasa = new Route(CZAS, vertices, null);
		boy.setCurrentRoute(trasa);
		deliveryBoys.add(boy);*/
		
		wynik.setDeliveryBoys(deliveryBoys);
		
		return wynik;
		
	}
}
