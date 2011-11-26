package algorithms.genetic.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.lowagie.text.pdf.ArabicLigaturizer;

import algorithms.genetic.operation.crossover.Crossover;
import algorithms.genetic.operation.mutation.Mutation;
import algorithms.genetic.operation.selection.Selection;
import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;

public class GeneticAlghorithm {

	private GeneticGraph graph;

	private double mutationProbability;

	private double crossoverProbability;

	private int numberOfIterations;

	private int populationSize;

	private Crossover crossover;

	private Mutation mutation;

	private Selection selection;

	private SolutionEvaluator evaluator;
	
	private List<Double> bestSolutions;

	private GeneticSolutionPack pack;

	private int genomeLength;
	

//	public GeneticAlghorithm() {
//	}

	public GeneticAlghorithm(GeneticGraph graph, double mutationProbability,
			double crossoverProbability, int numberOfIterations, int populationSize, int genomeLength) {
		this.graph = graph;
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.numberOfIterations = numberOfIterations;
		this.populationSize = populationSize;
		this.crossover = new Crossover(crossoverProbability,genomeLength);
		this.mutation = new Mutation(mutationProbability,genomeLength);
		this.evaluator = new SolutionEvaluator(graph);
		this.selection = new Selection(evaluator,genomeLength);
		this.genomeLength = graph.getSize();
		
	}

	public GeneticSolutionPack solve() {
		pack = new GeneticSolutionPack(mutationProbability, crossoverProbability, numberOfIterations, populationSize, evaluator);
		List<Genome> population = generateFreshPopulation(genomeLength);
		pack.addNextSolution(new ArrayList<>(population));
		for (int i = 0; i < numberOfIterations; i++) {
			
			population = doNextIteration(population);
			pack.addNextSolution(new ArrayList<>(population));
		}
		return pack;
	}

	private List<Genome> generateFreshPopulation(int genomeLength) {
		return PopulationGenerator.newGenerator(
				genomeLength, populationSize).generate();
	}

	private List<Genome> doNextIteration(List<Genome> population) {
		population = selection.acceptPopulation(population)
				.generateNewPopulation();
		
		population = crossover.acceptPopulation(population)
				.generateNewPopulation();
		
		population = mutation.acceptPopulation(population)
				.generateNewPopulation();
		return population;
	}

	public double getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(int mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public double getCrossoverProbability() {
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
