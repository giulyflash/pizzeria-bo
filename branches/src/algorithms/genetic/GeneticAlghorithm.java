package algorithms.genetic;

import java.util.List;

import algorithms.genetic.operation.crossover.Crossover;
import algorithms.genetic.operation.mutation.Mutation;
import algorithms.genetic.operation.selection.Selection;
import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;

public class GeneticAlghorithm {

	private GeneticGraph graph;

	private int mutationProbability;

	private int crossoverProbability;

	private int numberOfIterations;

	private int populationSize;

	private Crossover crossover;

	private Mutation mutation;

	private Selection selection;

	private SolutionEvaluator evaluator;
	

	public GeneticAlghorithm() {
	}

	public GeneticAlghorithm(GeneticGraph graph, int mutationProbability,
			int crossoverProbability, int numberOfIterations, int populationSize, int genomeLength) {
		this.graph = graph;
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.numberOfIterations = numberOfIterations;
		this.populationSize = populationSize;
		this.crossover = new Crossover(crossoverProbability,genomeLength);
		this.mutation = new Mutation();
		this.evaluator = new SolutionEvaluator(graph);
		this.selection = new Selection(evaluator,genomeLength);
		
	}

	void doIt() {
		int numberOfCities = graph.getSize();
		int genomeLength = numberOfCities;
		List<Genome> population = PopulationGenerator.newGenerator(
				genomeLength, populationSize).generate();
		for (int i = 0; i < numberOfIterations; i++) {
			population = selection.acceptPopulation(population)
					.generateNewPopulation();
			
			population = crossover.acceptPopulation(population)
					.generateNewPopulation();
			
			population = mutation.acceptPopulation(population)
					.generateNewPopulation();
		}
	}

	public int getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(int mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public int getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setCrossoverProbability(int crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
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

	public GeneticGraph getGraph() {
		return graph;
	}

	public void setGraph(GeneticGraph graph) {
		this.graph = graph;
	}

}
