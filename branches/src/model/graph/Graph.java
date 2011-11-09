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
		if( b>=0 && b<=vertices.size() && e>=0 && e<=vertices.size() && vertices.get(b)!=null && vertices.get(e)!=null){
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
	 * Zwraca wage krawedzi rozpietej miedzy dwoma wierzcho³kami
	 * @param a numer wierzcho³ka w grafie
	 * @param b numer wierzcho³ka w grafie
	 * @return waga krawedzi
	 */
	public double getWeight(int a, int b){
		List<Edge> edges = vertices.get(a).getEdgeList();
		for(Edge ed : edges)
			if(ed.getEnd().getNumber() == b)
				return ed.getWeight();
		
		return 0.0;
	}
}

