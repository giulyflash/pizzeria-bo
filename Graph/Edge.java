package Graph;

/**
 * Klasa reprezentuje krawedz grafu.
 * @author Marcin Skiba
 * @author Marcin Orzechowski - dostosowanie
 * @version 1.1
 */
public class Edge{
	private	Vertex end;	//wskazuje wierzcholek do ktorego prowadzi krawedz
	private double weight;	//waga krawedzi
	
	/**
	 * @param e wierzcholek koncowy
	 * @param w waga krawedzi
	 */
	public Edge(Vertex e, double w){
		end = e;
		weight = w;
	}
	
	/**
	 * Zwraca wierzcholek koncowy krawedzi
	 */
	public Vertex getEnd(){
		return end;
	}
	
	/**
	 * Zwraca wage krawedzi
	 */
	public double getWeight(){
		return weight;
	}
}
