package algorithms.genetic;

import java.util.List;

import algorithms.genetic.structures.GeneticGraph;

import model.graph.GraphMatrix;
import model.pizzeria.Algorithm;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Result;

public class GeneticAlgorithmRunner implements Algorithm {

	private int numberOfIterations;
	
	private int populationSize;
	
	private double crossoverProbability;
	
	private double mutationProbability;
	
	private GeneticAlghorithm algorithm;
	

	public GeneticAlgorithmRunner(int numberOfIterations, int populationSize,
			double crossoverProbability, double mutationProbability) {
		this.numberOfIterations = numberOfIterations;
		this.populationSize = populationSize;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
	}



	@Override
	public Result execute(GraphMatrix graphMatrix,
			List<DeliveryBoy> availableDeliveryBoys, List<Float> parameters) {
		//TODO replace random graph with with real graph
		GeneticGraph graph = GeneticGraph.getRandomGraph(10);
		int genomeLength=graph.getSize();
		algorithm=new GeneticAlghorithm(graph, mutationProbability, crossoverProbability, 
				numberOfIterations, populationSize, genomeLength);
		return null;
	}



	public int getNumberOfIterations() {
		return numberOfIterations;
	}



	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}



	public int getPopulationSize() {
		return populationSize;
	}



	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}



	public double getCrossoverProbability() {
		return crossoverProbability;
	}



	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}



	public double getMutationProbability() {
		return mutationProbability;
	}



	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

}
