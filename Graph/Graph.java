package Graph;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentuje graf skierowany wazony/niewazony.
 * @version 1.1
 */
public class Graph{
	private List<Vertex> vertices = new ArrayList<Vertex>();	//lista wierzcholkow grafu
	private int vertexCount;			//wskazuje ile wierzcholkow ma graf (null to tez wierzcholek, tyle ze pusty. 
										//Co za tym idzie - vertexCount nigdy nie zmaleje, nawet po usunieciu wszystkich wierzcholkow, gdyz usuwanie
										//to nic innego jak zastapienie istniejacego wierzcholka w liscie przez null)
										
	public Graph(){
		vertexCount = 0;
	}
	
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
		vertexCount++;
	}
	
	/**
	 * Dodaje do grafu krawedz o wadze w rozpieta miedzy wierzcholkami o liczbach porzadkowych b --> e.
	 * Jeżeli wprowadzone numery wierzcholkow wskazuja na elementy nieistniejace to krawedz nie zostaje utworzona.
	 * @param b numer porzadkowy poczatkowego wierzcholka
	 * @param e numer porzadkowy wierzcholka koncowego
	 */
	public void addEdge(int b, int e){
		if( b>=0 && b<=vertexCount && e>=0 && e<=vertexCount && vertices.get(b)!=null && vertices.get(e)!=null){
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
}

