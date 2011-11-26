package algorithms.genetic.test;

import org.junit.Test;

import algorithms.genetic.GeneticAlghorithm;
import algorithms.genetic.PopulationGenerator;
import algorithms.genetic.structures.GeneticGraph;

public class AlgorithmTest {

	GeneticAlghorithm algorithm;
	double graphMatrix[][] = {{0,4,7,3,872,23,46,1},
			{2,0,8,324,6,23,75,23},
			{334,3,0,67,23,2,4,3},
			{4,3,6,0,7,83,234,7},
			{5,23,6,23,0,3,25,7},
			{6,23,7,23,7,0,2,23},
			{7,12,1,532,123,6,0,6},
			{7,12,1,532,123,6,1,0}};
	int genomeSize=graphMatrix.length;
	
	
	@Test public void shouldSolveProblem(){
		genomeSize=50;
		
		double mutationProbability = 0.30;
		double crossoverProbability = 0.5;
		int numberOfIterations = 100;
		int populationSize = 1000;
		init(mutationProbability, crossoverProbability, numberOfIterations, populationSize);
		
		algorithm.doIt();
	}
	
	private void init(double mutationProbability,
			double crossoverProbability,
			int numberOfIterations,
			int populationSize){
		GeneticGraph graph=GeneticGraph.getRandomGraph(genomeSize);
		//GeneticGraph graph = new GeneticGraph(graphMatrix, genomeSize);
		
		algorithm=new GeneticAlghorithm(graph, mutationProbability, crossoverProbability, 
				numberOfIterations, populationSize, genomeSize);
	}
}
