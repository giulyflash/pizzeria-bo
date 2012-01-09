package model.graph;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuje graf nieskierowany wa¿ony.
 * @version 1.1
 */
public class Graph{
	private List<Vertex> vertices = new ArrayList<Vertex>();	//lista wierzcholkow grafu			

	public String toString(){
		String out = new String();
		for(Vertex v : vertices)
			if(v != null && v.toString() != "")
				out = out + v.toString() + "\n";
		return out;
	}
	
	/**
	 * Dodaje wierzcholek do grafu
	 * @param v wierzcholek, ktory jest dodawany do grafu
	 */
	public void addVertex(Vertex v){
		vertices.add(v);
	}
	
	/**
	 * Dodaje do grafu krawedz rozpieta miedzy wierzcholkami o liczbach porzadkowych <b>b</b> i <b>e</b>.
	 * Je¿eli wprowadzone numery wierzcholkow wskazuja na elementy nieistniejace to krawedz nie zostaje utworzona.
	 * @param b numer porzadkowy poczatkowego wierzcholka
	 * @param e numer porzadkowy wierzcholka koncowego
	 */
	public void addEdge(int b, int e){
		if( b>=0 && b<=vertices.size() && e>=0 && e<=vertices.size()){
			double weight = vertices.get(b).getCoordinate().distance(vertices.get(e).getCoordinate());
			vertices.get(e).addEdge(new Edge(vertices.get(b), weight));
			vertices.get(b).addEdge(new Edge(vertices.get(e), weight));
		} 
	}
	
	/**
	 * Zwraca wierzcholek o liczbie porzadkowej n
	 * @param n liczba porzadkowa szukanego wierzcholka
	 */
	public Vertex getVertex(int n){
		return vertices.get(n);
	}
	
	/**
	 * Zwraca <b>kopie</b> listy wierzcholkow tego grafu
	 */
	public List<Vertex> getVertexList(){
		return new ArrayList<Vertex>(vertices);
	}
	
	/**
	 * Zwraca wage krawedzi rozpietej miedzy dwoma wierzcho³kami.
	 * Jeœli miedzy wierzcho³kami nie ma krawêdzie lub a i b wskazuj¹ ten sam wierzcho³ek, metoda zwraca 0.
	 * @param a numer wierzcho³ka w grafie
	 * @param b numer wierzcho³ka w grafie
	 * @return waga krawedzi
	 */
	public double getWeight(int a, int b){
		return getWeight(vertices.get(a), vertices.get(b));
	}
	
	/**
	 * Zwraca wage krawedzi rozpietej miedzy dwoma wierzcho³kami.
	 * Jeœli miedzy wierzcho³kami nie ma krawêdzie lub a i b wskazuj¹ ten sam wierzcho³ek, metoda zwraca 0.
	 * @param a numer wierzcho³ka w grafie
	 * @param b numer wierzcho³ka w grafie
	 * @return waga krawedzi
	 */
	public double getWeight(Vertex a, Vertex b){
		for(Edge ed : a.getEdgeList())
			if(ed.getEnd() == b)
				return ed.getWeight();
		return 0.0;
	}
	
	public boolean areConnected(Vertex a, Vertex b){
		for(Edge e : a.getEdgeList())
			if(e.getEnd().getNumber() == b.getNumber())
				return true;
		return false;
	}
}

