package Graph;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuje wierzcholek grafu.
 * Wierzcholek jest przedstawiony jako punkt oraz krawędzie z niego wychodzące.
 * @author Marcin Skiba
 * @author Marcin Orzechowski - dostosowanie
 * @version 1.1
 */
public class Vertex{
	private int number;	//numer porzadkowy wierzcholka.
	private Point2D.Double coordinate;	//wspolrzedne wierzcholka
	private List<Edge> edges = new ArrayList<Edge>();	//lista krawędzi wychodzacych z wierzcholka
														
	
	/**
	 * @param i numer porzadkowy wierzcholka
	 * @param c wsprzolrzedna wierzcholka
	 */
	public Vertex(int i, Point2D.Double c){
		number = i;
		coordinate = c;
	}
	
	public String toString(){
		if(edges.size() == 0)
			return "";
			
		String out = new String();
		for(Edge e : edges)
			out = out + e.toString() + "\n";
		return out;
	}
	
	/**
	 * Zwraca numer porzadkowy wierzcholka
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * Zwraca wspolrzedna wierzcholka
	 */
	public Point2D.Double getCoordinate(){
		return coordinate;
	}
	
	/**
	 * Zwraca <b>kopie</b> listy krawedzi wychodzacych z wierzcholka
	 */
	public List<Edge> getEdgeList(){
		return new ArrayList<Edge>(edges);
	}

	
	/**
	 * Dodaje nowa krawedz do wierzcholka
	 * @param e dodawana krawedz
	 */
	public void addEdge(Edge e){
		edges.add(e);
	}
	
	/**
	 * Zwraca krawedz biegnaca do wierzcholka o numerze porzadkowym n (jezeli istnieje)
	 * @param n numer wierzcholka do ktorego biegnie szukana krawedz
	 */
	public Edge getEdge(int n){
		for(Edge e : edges)
		if(e.getEnd().getNumber() == n)
			return e;
		return null;
	} 
	
	/**
	 * Zwraca n-ta w kolejnosci krawedz wychodzaca z tego wierzcholka (jezeli istnieje)
	 * @param n numer szukanej krawedzi
	 */
	public Edge getEdgeAt(int n){
		if(n>=0 && n<edges.size())
			return edges.get(n);
		else
			return null;
	}
}
