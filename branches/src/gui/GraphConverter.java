package gui;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.graph.Graph;
import model.graph.Vertex;
/**
 * Klasa do konwersji grafu w postaci pliku tekstowego
 * na klasę Graph. Postać pliku: x,y,[nr wierzcholkow]
 * @author Michał Nowak
 * @version 1
 */
public class GraphConverter {
	
	/**
	 * Konwertuje zawartosc pliku tekstowego na Graph
	 * @param fileName - nazwa pliku z danymi
	 * @return graf
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static Graph convert(String fileName) throws IOException, ArrayIndexOutOfBoundsException {
		BufferedReader  in = new BufferedReader(new FileReader(fileName));
		Graph graph = new Graph();
		
		String line;
		
		// struktura pliku: x, y, [nr polaczen]
		int i = 0;
		while ( (line = in.readLine()) != null) {
			String [] tmp = line.split(",");
			if (tmp.length < 2)
				throw new ArrayIndexOutOfBoundsException();
			
			graph.addVertex(new Vertex(i++, new Point2D.Double(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]))));
		}
		in.close();
		in = new BufferedReader(new FileReader(fileName));
		i = 0;
		while ( (line = in.readLine()) != null) {
			String [] tmp = line.split(",");
			if (tmp.length < 2)
				throw new ArrayIndexOutOfBoundsException();
			
			for(int j = 2 ; j < tmp.length ; j++) {
				graph.addEdge(i, Integer.parseInt(tmp[j]));
			}
			i++;
		}
		in.close();
		
		return graph;
	}
	
}
