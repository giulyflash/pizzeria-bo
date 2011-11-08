package algorithms.genetic.utils;

import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;

public class SolutionEvaluator {
	
	private GeneticGraph graph;
	
	public SolutionEvaluator(GeneticGraph graph) {
		this.graph = graph;
	}

	public double eval(Genome sampleSolution){
		double result=0;
		
		for (int i = 0; i < sampleSolution.getEdgesNumber(); i++) {
			result+=graph.evalTSPEgde(sampleSolution.getIthPathEdge(i));
		}
		return result;
	}

}
