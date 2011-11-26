package algorithms.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithms.genetic.structures.Genome;

public class GeneticSolutionPack {
	
	private double mutationProbability;

	private double crossoverProbability;

	private int numberOfIterations;

	private int populationSize;
	
	private SolutionEvaluator evaluator;
	
	private List<List<Genome>> solutions;
	
	private List<List<Double>> solutionEvals;
	
	private List<Double> bestResultOfIteration;
	
	
	
	
	public GeneticSolutionPack(double mutationProbability,
			double crossoverProbability, int numberOfIterations,
			int populationSize, SolutionEvaluator evaluator) {
		super();
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.numberOfIterations = numberOfIterations;
		this.populationSize = populationSize;
		this.evaluator = evaluator;
		solutionEvals=new ArrayList<>();
		solutions=new ArrayList<>();
		bestResultOfIteration=new ArrayList<>();
	}

	public void addNextSolution(List<Genome> solution){
		solutions.add(solution);
		List<Double> currentSolutionEval = new ArrayList<>();
		for (Genome genome : solution) {
			currentSolutionEval.add(evaluator.eval(genome));
		}
		bestResultOfIteration.add(Collections.min(currentSolutionEval));
	}
	
	public double getBestValue(){
		return Collections.min(bestResultOfIteration);
	}
	
	public double getBestValue(int iteration){
		return bestResultOfIteration.get(iteration);
	}

	public int getBestValueIterationNumber(){
		double bestSolution = getBestValue();
		return bestResultOfIteration.indexOf((Object)bestSolution);
	}
	
	
	
	public List<Genome> getIthItearionPopulation(int i){
		return solutions.get(i);
	}
	
	

	public double getMutationProbability() {
		return mutationProbability;
	}

	public double getCrossoverProbability() {
		return crossoverProbability;
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public int getPopulationSize() {
		return populationSize;
	}
	
	
	
	

}
