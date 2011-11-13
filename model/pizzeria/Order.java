package model.pizzeria;

import model.graph.Vertex;

/**
 * Klasa z danymi zamówienia
 */
public class Order {
	/**
	 * Wierzcho³ek do którego nale¿y dostarczyæ zamówienie.
	 */
	private Vertex _vertex;
	/**
	 * Iloœæ pizz w zamówieniu (UNUSED).
	 */
	private int _amountOfPizzas;
	
	/**
	 * Ogólniejszy konstruktor
	 * @param vertex
	 * @param amountOfPizzas
	 */
	public Order(Vertex vertex, int amountOfPizzas) {
		_vertex = vertex;
		_amountOfPizzas = amountOfPizzas;
	}
	
	/**
	 * Zwraca wierzcho³ek do którego nale¿y dostarczyæ zamówienie.
	 * @return wierzcho³ek
	 */
	public Vertex getVertex(){
		return _vertex;
	}
	
	public void setVertex(Vertex vertex){
		_vertex = vertex;
	}
	
	/**
	 * Zwraca iloœæ pizz w zamówieniu (UNUSED)
	 * @return int
	 */
	public int getAmountOfPizzas(){
		return _amountOfPizzas;
	}
	
	public void setAmountOfPizzas(int amountOfPizzas){
		_amountOfPizzas = amountOfPizzas;
	}
}
