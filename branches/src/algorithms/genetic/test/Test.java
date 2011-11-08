package algorithms.genetic.test;

import java.util.List;

import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;
import algorithms.genetic.utils.PopulationGenerator;
import algorithms.genetic.utils.SolutionEvaluator;

public class Test {
	public static void main(String[] args) {
		int size=100;
		GeneticGraph gg = GeneticGraph.getRandomGraph(size);
		int genomeLength=10; //equivalent to number of vertices on path
		int populationSize = 100;
		List<Genome> population = PopulationGenerator.newGenerator(genomeLength, populationSize).generate();
		SolutionEvaluator se = new SolutionEvaluator(gg);
		for (Genome genome : population) {
			se.eval(genome);
		}
	}
}
