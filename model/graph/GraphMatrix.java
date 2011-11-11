package model.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import model.pizzeria.Order;

/**
 * Klasa zawierajaca macierzow�(macierz s�siedztwa) reprezentacje grafu zbudowanego tylko z wierzcho�k�w wyr�nionych.
 * W wierzcho�kach wyr�nionych znajduj� si� punkty do kt�rych nale�y dostarczy� pizze i punkt pocz�tkowy - pizzeria.
 * Pizzeria zawsze znajduje si� w wierzcho�ku pod indeksem <b>0</b>
 * @version 1.0
 */
public class GraphMatrix {
	//zawiera wierzcho�ki z oryginalnego grafu
	private Vertex[] vertexTranslator;
	//macierz trojkatna
	private PathData[][] data;
	private Graph cityMap;
	
	/**
	 * Zwraca now� instancje klasy, zawierajac� macierzow� reprezentacj� wyr�nionego grafu.
	 * @param pizzeriaVertex wierzcho�ek w kt�rym znajduje si� pizzeria
	 * @param oreders lista z zam�wieniami, kt�re zostan� uwzgl�dnione w grafie
	 * @param cityMap graf reprezentuj�cy miasto
	 */
	public GraphMatrix(Vertex pizzeriaVertex, List<Order> orders, Graph _cityMap){
		cityMap = _cityMap; 
		
		//wyeliminowanie ewentualnych duplikat�w
		Set<Vertex> vertexSet = new HashSet<Vertex>();
		for(Order order : orders)
			vertexSet.add(order.getVertex());
		
		vertexTranslator = new Vertex[vertexSet.size()+1];
		vertexTranslator[0] = pizzeriaVertex;
		
		int l = 1;
		for(Vertex vertex : vertexSet)
			vertexTranslator[l++] = vertex;
		
		data = new PathData[vertexTranslator.length][];
		for(int i = 1; i < vertexTranslator.length; i++)
			data[i] = new PathData[i];

		for(int i = 1; i < data.length; i++){
			VertexData[] vertexData = makePath(vertexTranslator[i]);
			for(int k = 0; k < data[i].length; k++){
				data[i][k] = getPathData(vertexData, vertexTranslator[i], vertexTranslator[k]);
			}
		}
				
	}

	//Klasa zawierajaca dane potrzebne do szukania najkr�tszych �cie�ek w grafie
	private class VertexData implements Comparable<VertexData>{
		Vertex vertex;
		Vertex parentVertex;
		double pathLength;
		
		@Override
		public int compareTo(VertexData arg0) {
			return (int)(this.pathLength - arg0.pathLength);
		}
	}
	
	//Klasa zawierajca d�ugo�� najkr�tszej �cie�ki i kolejne wierzcho�ki na tej �cie�ce(��cznie z pocz�tkowym i ko�cowym
	private class PathData{
		double pathLength;
		//zawiera kolejne wierzcholki w najkrotszej scie�ce
		List<Vertex> vertices = new ArrayList<Vertex>();
	}
	
	//zwraca tablice indeksowan� numerami z oryginalnego grafu
	private VertexData[] makePath(Vertex begin) throws IndexOutOfBoundsException{
		List<Vertex> vertices = cityMap.getVertexList();
		VertexData[] verticesData = new VertexData[vertices.size()];
		for(Vertex ver : vertices){
			verticesData[ver.getNumber()] = new VertexData();
			verticesData[ver.getNumber()].vertex = ver;
			verticesData[ver.getNumber()].parentVertex = null;
			verticesData[ver.getNumber()].pathLength = Double.MAX_VALUE;
		}
		
		verticesData[begin.getNumber()].pathLength = 0.0;
		Queue<VertexData> vertexQueue = new PriorityQueue<VertexData>(verticesData.length);
		for(VertexData ver : verticesData)
			vertexQueue.add(ver);
		
		//Dijkstra
		while(!vertexQueue.isEmpty()){
			VertexData tmp = vertexQueue.poll();
			List<Edge> edgeList = tmp.vertex.getEdgeList();
			for(Edge ed : edgeList){
				relax(tmp, verticesData[ed.getEnd().getNumber()], ed.getWeight());
			}
		}
		
		return verticesData;
	}
	
	private void relax(VertexData a, VertexData b, double weight){
		if(b.pathLength > a.pathLength + weight){
			b.pathLength = a.pathLength + weight;
			b.parentVertex = a.vertex;
		}
	}
	
	private PathData getPathData(VertexData[] data, Vertex begin, Vertex end){
		PathData result = new PathData();
		result.pathLength = data[end.getNumber()].pathLength;
		
		LinkedList<Vertex> vertices = new LinkedList<Vertex>();
		vertices.addFirst(end);
		
		Vertex currentVertex = data[end.getNumber()].parentVertex;
		while(currentVertex != null){
			vertices.addFirst(currentVertex);
			currentVertex = data[currentVertex.getNumber()].parentVertex;
		}
		
		result.vertices = vertices;
		return result;
	}
	
	/**
	 * Zwraca kwadratow� macierz adiacencji reprezentuj�c� wyr�niony graf.
	 * @return macierz reprezentuj�ca graf
	 */
	public double[][] getMatrix(){
		double[][] result = new double[data.length][];
		for(int i = 0; i < result.length; i++)
			result[i] = new double[data.length];
		
		for(int i = 0; i < result.length; i++){
			for(int k = 0; k < i; k++)
				result[i][k] = data[i][k].pathLength;
			
			result[i][i] = 0.0;
			
			for(int k = i+1; k < result[i].length; k++)
				result[i][k] = data[k][i].pathLength;
		}
		
		return result;
	}
	
	/**
	 * Zwraca kopi� tablicy w kt�rej znajduj� sie wyr�nione wierzcho�ki. 
	 * Indeks w tej tablicy, to id wierzcho�ka w macierzy adiacencji zwracanej przez <b>getGrapMatrix()</b>.
	 * @return tablica wierzcho�k�w
	 */
	public Vertex[] getVertexTranslator(){
		return Arrays.copyOf(vertexTranslator, vertexTranslator.length);
	}
	
	/**
	 * T�umaczy liste wierzcho�k�w do odwiedzania na reprezentacj� odpowiednia dla GUI.
	 * Rozwija drogi pomiedzy wierzcho�kami.
	 * @param vertices wierzcho�ki do przet�umaczenia
	 * @return lista wierzcho�k�w dla GUI
	 */
	public List<Vertex> translateToFullVerticesList(List<Integer> vertices){
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		
		int[] myVertices = new int[vertices.size()];
		int l = 0;
		for(Integer myInt : vertices)
			myVertices[l++] = myInt.intValue();
		
		for(int i = 0; i < myVertices.length-1; i++){
			result.add(vertexTranslator[myVertices[i]]);
			if(myVertices[i] == myVertices[i+1]){
				//TODO docelowo gdzie indziej niz System.err
				System.err.println("Blad algorytmow: ten sam wierzcholek 2x pod rzad, w liscie do odwiedzenia");
				i++;
			} else{
				if(myVertices[i] > myVertices[i+1])
					result.addAll(data[myVertices[i]][myVertices[i+1]].vertices);
				else{
					LinkedList<Vertex> path = (LinkedList<Vertex>)data[myVertices[i+1]][myVertices[i]].vertices;
					ListIterator<Vertex> iterator = path.listIterator();
					while(iterator.hasPrevious())
						result.add(iterator.previous());
				}
			}
		}
		result.add(vertexTranslator[myVertices[myVertices.length-1]]);
		
		return result;
	}
	
	/**
	 * T�umaczy liste wierzcho�k�w do odwiedzania na reprezentacj� odpowiednia dla GUI.
	 * Rozwija drogi pomiedzy wierzcho�kami.
	 * @param vertices wierzcho�ki do przet�umaczenia
	 * @return lista wierzcho�k�w dla GUI
	 */
	public List<Vertex> translateToFullVerticesList(int[] vertices){
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		
		for(int i = 0; i < vertices.length-1; i++){
			result.add(vertexTranslator[vertices[i]]);
			if(vertices[i] == vertices[i+1]){
				//TODO jesli jest ten sam wierzcho�ek pod rz�d to co wtedy?
			} else{
				if(vertices[i] > vertices[i+1])
					result.addAll(data[vertices[i]][vertices[i+1]].vertices);
				else{
					LinkedList<Vertex> path = (LinkedList<Vertex>)data[vertices[i+1]][vertices[i]].vertices;
					ListIterator<Vertex> iterator = path.listIterator();
					while(iterator.hasPrevious())
						result.add(iterator.previous());
				}
			}
		}
		result.add(vertexTranslator[vertices[vertices.length-1]]);
		
		return result;
	}
}