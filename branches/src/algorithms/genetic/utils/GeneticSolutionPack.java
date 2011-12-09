package algorithms.genetic.utils;

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
	
	private List<Genome> bestSolutionsOfIteration;
	
	private List<Double> bestResultOfIteration;
	
	
	
	
	public GeneticSolutionPack(double mutationProbability,
			double crossoverProbability, int numberOfIterations,
			int populationSize, SolutionEvaluator evaluator) {
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.numberOfIterations = numberOfIterations;
		this.populationSize = populationSize;
		this.evaluator = evaluator;
		bestSolutionsOfIteration=new ArrayList<>();
		bestResultOfIteration=new ArrayList<>();
	}

	public void addNextSolution(List<Genome> solution){
		Genome bestGenome=null;
		Genome currentGenome;
		double bestValue=Double.MAX_VALUE;
		double currentValue;
		for (int i = 0; i < solution.size(); i++) {
			currentGenome = solution.get(i);
			currentValue = evaluator.eval(currentGenome);
			if(currentValue <= bestValue){
				bestValue=currentValue;
				bestGenome = currentGenome;
			}
		}
		bestSolutionsOfIteration.add(bestGenome);
		bestResultOfIteration.add(bestValue);
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
	
	public Genome getBestGenome(){
		return bestSolutionsOfIteration.get(getBestValueIterationNumber());
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
