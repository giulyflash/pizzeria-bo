package model.pizzeria;

import model.graph.Vertex;

/**
 * Klasa z danymi zam�wienia
 */
public class Order {
	private Vertex vertex;
	
	/**
	 * Zwraca wierzcho�ek do kt�rego nale�y dostarczy� zam�wienie.
	 * @return wierzcho�ek s5
	 */
	public Vertex getVertex(){
		return vertex;
	}
}
