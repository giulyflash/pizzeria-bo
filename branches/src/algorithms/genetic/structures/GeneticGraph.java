package algorithms.genetic.structures;

import algorithms.genetic.utils.GeneralUtils;

public class GeneticGraph {

	private double[][] graph;

	private int size;

	public GeneticGraph(double[][] graph, int size) {
		this.graph = graph;
		this.size = size;
	}

	public double evalTSPEgde(TSPEdge e) {
		return graph[e.getV1()][e.getV2()];
	}

	public static GeneticGraph getRandomGraph(int size) {
		double[][] graphInnards = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				graphInnards[i][j]=GeneralUtils.nextDouble(10.0, 1000.0);
			}
		}
		return new GeneticGraph(graphInnards, size);
	}

}
