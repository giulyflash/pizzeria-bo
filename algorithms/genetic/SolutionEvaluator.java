package algorithms.genetic;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Double> evalPopulation(List<Genome> population){
		List<Double> evaluation = new ArrayList<>();
		for (Genome g : population) {
			evaluation.add(eval(g));
		}
		return evaluation;
	}

}
