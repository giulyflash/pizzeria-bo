package model.pizzeria;

import model.graph.Vertex;

/**
 * Klasa z danymi zamówienia
 */
public class Order {
	private Vertex vertex;
	
	/**
	 * Zwraca wierzchołek do którego należy dostarczyć zamówienie.
	 * @return wierzchołek 
	 */
	public Vertex getVertex(){
		return vertex;
	}
}
