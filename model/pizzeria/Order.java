package model.pizzeria;

import model.graph.Vertex;

/**
 * Klasa z danymi zam�wienia
 */
public class Order {
	/**
	 * Wierzcho�ek do kt�rego nale�y dostarczy� zam�wienie.
	 */
	private Vertex _vertex;
	/**
	 * Ilo�� pizz w zam�wieniu (UNUSED).
	 */
	private int _amountOfPizzas;
	
	/**
	 * Og�lniejszy konstruktor
	 * @param vertex
	 * @param amountOfPizzas
	 */
	public Order(Vertex vertex, int amountOfPizzas) {
		_vertex = vertex;
		_amountOfPizzas = amountOfPizzas;
	}
	
	/**
	 * Zwraca wierzcho�ek do kt�rego nale�y dostarczy� zam�wienie.
	 * @return wierzcho�ek
	 */
	public Vertex getVertex(){
		return _vertex;
	}
	
	public void setVertex(Vertex vertex){
		_vertex = vertex;
	}
	
	/**
	 * Zwraca ilo�� pizz w zam�wieniu (UNUSED)
	 * @return int
	 */
	public int getAmountOfPizzas(){
		return _amountOfPizzas;
	}
	
	public void setAmountOfPizzas(int amountOfPizzas){
		_amountOfPizzas = amountOfPizzas;
	}
}
