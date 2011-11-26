package algorithms.genetic.test;

import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algorithms.genetic.operation.selection.Selection;
import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;
import algorithms.genetic.utils.PopulationGenerator;
import algorithms.genetic.utils.SolutionEvaluator;

public class SelectionTest {
	GeneticGraph graph;
	Selection selection;
	List<Genome> population;
	List<Genome> newPopulation;
	double crossoverProbability = 0.5;
	int populationSize = 10;
	SolutionEvaluator evaluator;
	double graphMatrix[][] = {{0,4,7,3,872,23,46,1},
			{2,0,8,324,6,23,75,23},
			{334,3,0,67,23,2,4,3},
			{4,3,6,0,7,83,234,7},
			{5,23,6,23,0,3,25,7},
			{6,23,7,23,7,0,2,23},
			{7,12,1,532,123,6,0,6},
			{7,12,1,532,123,6,1,0}};
	
	@Before public void init(){
		 
		graph = new GeneticGraph(graphMatrix, graphMatrix.length);
		int genomeLenght = graphMatrix.length;
		evaluator = new SolutionEvaluator(graph);
		selection = new Selection(evaluator, genomeLenght);
		int genomeLength = graph.getSize();
		population = PopulationGenerator.newGenerator(
				genomeLength, populationSize).generate();
		
	}
	
	@Test public void shouldUsuallySelectBetterSolutions(){
		newPopulation = selection.acceptPopulation(population).generateNewPopulation();
		
		
		double populationEval = evaluator.evalPopulationWithReduce(population);
		System.out.println("old cost" + populationEval);
		double newPopulationEval = evaluator.evalPopulationWithReduce(newPopulation);
		System.out.println("new cost" + newPopulationEval);
		
		
		assertTrue(newPopulation.size() == populationSize);
	}
	
	
}
