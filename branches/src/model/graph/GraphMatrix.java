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
 * Klasa zawierajaca macierzow¹(macierz s¹siedztwa) reprezentacje grafu zbudowanego tylko z wierzcho³ków wyró¿nionych.
 * W wierzcho³kach wyró¿nionych znajduj¹ siê punkty do których nale¿y dostarczyæ pizze i punkt pocz¹tkowy - pizzeria.
 * Pizzeria zawsze znajduje siê w wierzcho³ku pod indeksem <b>0</b>
 * @version 1.0
 */
public class GraphMatrix {
	//zawiera wierzcho³ki z oryginalnego grafu
	private Vertex[] vertexTranslator;
	//macierz trojkatna
	private PathData[][] data;
	private Graph cityMap;
	private ArrayList<ArrayList<Order>> ordersOfVertex;
	
	/**
	 * Zwraca now¹ instancje klasy, zawierajac¹ macierzow¹ reprezentacjê wyró¿nionego grafu.
	 * @param pizzeriaVertex wierzcho³ek w którym znajduje siê pizzeria
	 * @param oreders lista z zamówieniami, które zostan¹ uwzglêdnione w grafie
	 * @param cityMap graf reprezentuj¹cy miasto
	 */
	public GraphMatrix(Vertex pizzeriaVertex, List<Order> _orders, Graph _cityMap){
		cityMap = _cityMap;
		
		//wyeliminowanie ewentualnych duplikatów
		HashSet<Vertex> vertexSet = new HashSet<Vertex>();
		for(Order order : _orders)
			vertexSet.add(order.getVertex());
		
		vertexTranslator = new Vertex[vertexSet.size()+1];
		vertexTranslator[0] = pizzeriaVertex;
		ordersOfVertex = new ArrayList<ArrayList<Order>>();
		
		int l = 1;
		for(Vertex vertex : vertexSet)
			vertexTranslator[l++] = vertex;
		
		//inicjalizacja
		for(int i = 0; i < vertexTranslator.length; i++){
			ArrayList<Order> array = new ArrayList<>();
			ordersOfVertex.add(array);
		}
		
		for(int i = 0; i < vertexTranslator.length; i++){
			for(Order order : _orders){
				if(order.getVertex() == vertexTranslator[i])
					ordersOfVertex.get(i).add(order);
			}
		}
			
		
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

	//Klasa zawierajaca dane potrzebne do szukania najkrótszych œcie¿ek w grafie
	private class VertexData implements Comparable<VertexData>{
		Vertex vertex;
		Vertex parentVertex = null;
		double pathLength = Double.MAX_VALUE;
		
		@Override
		public int compareTo(VertexData arg0) {
			if(this.pathLength < arg0.pathLength)
				return -1;
			else{
				if(this.pathLength > arg0.pathLength)
					return 1;
				else
					return 0;
			}
		}
	}
	
	//Klasa zawierajca d³ugoœæ najkrótszej œcie¿ki i kolejne wierzcho³ki na tej œcie¿ce(³¹cznie z pocz¹tkowym i koñcowym
	private class PathData{
		double pathLength;
		//zawiera kolejne wierzcholki w najkrotszej scie¿ce
		List<Vertex> vertices;
	}
	
	//zwraca tablice indeksowan¹ numerami z oryginalnego grafu
	private VertexData[] makePath(Vertex begin) throws IndexOutOfBoundsException{
		List<Vertex> vertices = cityMap.getVertexList();
		VertexData[] verticesData = new VertexData[vertices.size()];
		for(Vertex ver : vertices){
			verticesData[ver.getNumber()] = new VertexData();
			verticesData[ver.getNumber()].vertex = ver;
		}
		
		verticesData[begin.getNumber()].pathLength = 0.0;
		Queue<VertexData> vertexQueue = new PriorityQueue<VertexData>(verticesData.length);
		for(VertexData ver : verticesData)
			vertexQueue.add(ver);
		
		//Dijkstra
		while(!vertexQueue.isEmpty()){
			VertexData tmp = vertexQueue.remove();
			List<Edge> edgeList = tmp.vertex.getEdgeList();
			for(Edge ed : edgeList){
				relax(tmp, verticesData[ed.getEnd().getNumber()], ed.getWeight());
			}
		}
		
		return verticesData;
	}
	
	private void relax(VertexData begin, VertexData end, double weight){
		if(end.pathLength > begin.pathLength + weight){
			end.pathLength = begin.pathLength + weight;
			end.parentVertex = begin.vertex;
		}
	}
	
	private PathData getPathData(VertexData[] data, Vertex begin, Vertex end){
		PathData result = new PathData();
		result.pathLength = data[end.getNumber()].pathLength;
		
		LinkedList<Vertex> vertices = new LinkedList<Vertex>();
		
		Vertex currentVertex = data[end.getNumber()].parentVertex;
		while(currentVertex != null){
			vertices.addFirst(currentVertex);
			currentVertex = data[currentVertex.getNumber()].parentVertex;
		}
		if(!vertices.isEmpty())
			vertices.removeFirst();

		result.vertices = vertices;
		return result;
	}
	
	/**
	 * Zwraca kwadratow¹ macierz adiacencji reprezentuj¹c¹ wyró¿niony graf.
	 * @return macierz reprezentuj¹ca graf
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
	 * Zwraca kopiê tablicy w której znajduj¹ sie wyró¿nione wierzcho³ki. 
	 * Indeks w tej tablicy, to id wierzcho³ka w macierzy adiacencji zwracanej przez <b>getGrapMatrix()</b>.
	 * @return tablica wierzcho³ków
	 */
	public Vertex[] getVertexTranslator(){
		return Arrays.copyOf(vertexTranslator, vertexTranslator.length);
	}
	
	/**
	 * T³umaczy liste wierzcho³ków do odwiedzania na reprezentacjê odpowiednia dla GUI.
	 * Rozwija drogi pomiedzy wierzcho³kami.
	 * @param vertices wierzcho³ki do przet³umaczenia
	 * @return lista wierzcho³ków dla GUI
	 */
	public List<Vertex> translateToFullVerticesList(List<Integer> vertices){
		int[] myVertices = new int[vertices.size()];
		int l = 0;
		for(Integer myInt : vertices)
			myVertices[l++] = myInt.intValue();
		
		return translateToFullVerticesList(myVertices);
	}
	
	/**
	 * T³umaczy liste wierzcho³ków do odwiedzania na reprezentacjê odpowiednia dla GUI.
	 * Rozwija drogi pomiedzy wierzcho³kami.
	 * @param vertices wierzcho³ki do przet³umaczenia
	 * @return lista wierzcho³ków dla GUI
	 */
	public List<Vertex> translateToFullVerticesList(int[] vertices){
		LinkedList<Vertex> result = new LinkedList<Vertex>();
		
		for(int i = 0; i < vertices.length-1; i++){
			result.add(vertexTranslator[vertices[i]]);
			if(vertices[i] == vertices[i+1]){
				//TODO jesli jest ten sam wierzcho³ek pod rz¹d to co wtedy?
			} else{
				if(vertices[i] > vertices[i+1]){
					if(!data[vertices[i]][vertices[i+1]].vertices.isEmpty()){
						result.addAll(data[vertices[i]][vertices[i+1]].vertices);
					}
				}
				else{
					if(!data[vertices[i+1]][vertices[i]].vertices.isEmpty()){
						//TODO w java 7 mozna ladniej
						LinkedList<Vertex> path = (LinkedList<Vertex>)data[vertices[i+1]][vertices[i]].vertices;
						if(path.size() > 0){
							ListIterator<Vertex> iterator = path.listIterator(path.size());
							while(iterator.hasPrevious())
								result.add(iterator.previous());
						}
					}
				}
			}
		}
		result.add(vertexTranslator[vertices[vertices.length-1]]);
		
		return result;
	}
	
	/**
	 * Zwraca liste z zamówienimi, które nale¿y obs³u¿yæ w bierz¹cym wywo³aniu algorytmu.
	 * Indeksy w zwracanej liscie s¹ zgodne z indeksami wierzcho³ków z macierzy adiacencji.
	 * Konsekwencj¹ tego jest, ¿e pod indeksem 0, który w macierzy reprezentuje pizzerie, 
	 * nie ma ¿adnych zamówieñ.
	 * Element zwracany jest list¹ poniewa¿ w jednym wierzcho³ku mo¿e znajdowaæ siê wiêcej 
	 * zamówieñ.
	 * @return lista z zamówieniami
	 */
	public ArrayList<ArrayList<Order>> getOrders(){
		return ordersOfVertex;
	}
}