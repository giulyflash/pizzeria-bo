package model.pizzeria;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import model.graph.Vertex;

/**
 * Klasa przedstawiaj¹ca trasê, która rozwoziæ pizzê bêdzie dany dostawca.
 * @author DoomThrower
 * @author orzech
 */
public class Route {
	private int _timeNeededToFinish;
	private List<Vertex> _vertices;
	private List<Order> _orders;

	/**
	 * @deprecated Bezpieczniej jest uzyc konstruktora w ktorym timeNeededToFinish jest doublem 
	 * @param timeNeededToFinish
	 * @param vertices
	 * @param orders
	 */
	public Route(int timeNeededToFinish, List<Vertex> vertices, ArrayList<Order> orders) {
		_timeNeededToFinish = timeNeededToFinish;
		_vertices = vertices;
		_orders = orders;
	}
	
	public Route(double timeNeededToFinish, List<Vertex> vertices, ArrayList<Order> orders){
		MathContext context = new MathContext(1, RoundingMode.UP);
		BigDecimal decimal = new BigDecimal(timeNeededToFinish, context);
		_timeNeededToFinish = decimal.intValue();
		_vertices = vertices;
		_orders = orders;
	}
	
	public int getTimeNeededToFinish() {
		return _timeNeededToFinish;
	}
	
	/**
	 * @deprecated Bezpieczniej jest uzyc setera w ktorym timeNeededToFinish jest doublem 
	 * @param timeNeededToFinish
	 */
	public void setTimeNeededToFinish(int timeNeededToFinish) {
		_timeNeededToFinish = timeNeededToFinish;
	}
	
	public void setTimeNeededToFinish(double timeNeedeToFinish){
		MathContext context = new MathContext(1, RoundingMode.UP);
		BigDecimal decimal = new BigDecimal(timeNeedeToFinish, context);
		_timeNeededToFinish = decimal.intValue();
	}
	
	public List<Vertex> getVertices() {
		return _vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		_vertices = vertices;
	}
	
	public List<Order> getOrders() {
		return _orders;
	}
	
	public void setOrders(List<Order> orders) {
		_orders = orders;
	}
	
	public void addOrder(Order order) {
		_orders.add(order);
	}
}
