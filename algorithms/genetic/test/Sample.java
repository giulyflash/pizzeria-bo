package algorithms.genetic.test;

import java.util.List;

import algorithms.genetic.PopulationGenerator;
import algorithms.genetic.SolutionEvaluator;
import algorithms.genetic.operation.GeneticTransformer;
import algorithms.genetic.operation.crossover.Crossover;
import algorithms.genetic.operation.mutation.Mutation;
import algorithms.genetic.operation.selection.Selection;
import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;

public class Sample {
	
	public static void main(String[] args) {
		System.out.println("Ala123");
	}

	private static void test() {
//		int size = 100;
//		GeneticGraph gg = GeneticGraph.getRandomGraph(size);
//		int genomeLength = 10; // equivalent to number of vertices on path
//		int populationSize = 100;
//		List<Genome> population = PopulationGenerator.newGenerator(
//				genomeLength, populationSize).generate();
//		{
//			SolutionEvaluator se = new SolutionEvaluator(gg);
//			for (Genome genome : population) {
//				se.eval(genome);
//			}
//		}
//		GeneticTransformer mutator = new Mutation();
//		GeneticTransformer selector = new Selection(ev);
//		GeneticTransformer reproductor = new Crossover(2,genomeLength);
//		{
//			selector.acceptPopulation(population);
//			population = selector.generateNewPopulation();
//		}
//		{
//			reproductor.acceptPopulation(population);
//			population = reproductor.generateNewPopulation();
//		}
//		{
//			mutator.acceptPopulation(population);
//			population = mutator.generateNewPopulation();
//		}
	}
}
