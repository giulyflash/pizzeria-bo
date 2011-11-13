package model.pizzeria;

import model.graph.Vertex;

/**
 * Klasa z danymi zamówienia
 */
public class Order {
	private Vertex vertex;
	
	/**
	 * Zwraca wierzcho³ek do którego nale¿y dostarczyæ zamówienie.
	 * @return wierzcho³ek s5
	 */
	public Vertex getVertex(){
		return vertex;
	}
}
