package model.pizzeria;

import java.util.ArrayList;

import model.graph.Vertex;

/**
 * Klasa przedstawiaj¹ca trasê, która rozwoziæ pizzê bêdzie dany dostawca.
 * @author DoomThrower
 *
 */
public class Route {
	private int _timeNeededToFinish;
	private ArrayList<Vertex> _vertices;
	private ArrayList<Order> _orders;

	public Route(int timeNeededToFinish, ArrayList<Vertex> vertices, ArrayList<Order> orders) {
		_timeNeededToFinish = timeNeededToFinish;
		_vertices = vertices;
		_orders = orders;
	}
	
	public int getTimeNeededToFinish() {
		return _timeNeededToFinish;
	}
	public void setTimeNeededToFinish(int timeNeededToFinish) {
		_timeNeededToFinish = timeNeededToFinish;
	}

	public ArrayList<Vertex> getVertices() {
		return _vertices;
	}
	public void setVertices(ArrayList<Vertex> vertices) {
		_vertices = vertices;
	}
	
	public ArrayList<Order> getOrders() {
		return _orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		_orders = orders;
	}
	
	public void addOrder(Order order) {
		_orders.add(order);
	}
}
