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
		
		for (int i = 0; i < sampleSolution.getGenomeLength(); i++) {
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
	
	public double evalPopulationWithReduce(List<Genome> population){
		double reducedEvaluation=0.0;
		for (double partialEval : evalPopulation(population)) {
			reducedEvaluation+=partialEval;
		}
		return reducedEvaluation;
	}
	
	public double getLowerEval(List<Genome> population){
		double min=-1.0;
		double tmp;
		for (Genome genome : population) {
			tmp=eval(genome);
			if(min<0 || tmp<min){
				min=tmp;
			}
		}
		return min;
	}

}
