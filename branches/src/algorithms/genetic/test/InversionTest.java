package algorithms.genetic.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import algorithms.genetic.PopulationGenerator;
import algorithms.genetic.operation.mutation.Mutation;
import algorithms.genetic.structures.Genome;

public class InversionTest {
	Mutation mutation;
	List<Genome> population;
	List<Genome> newPopulation;
	double mutationProbability = 0.01;
	int populationSize = 100;
	
	@Before public void init(){
		 
		int genomeLength = 10;
		mutation = new Mutation(mutationProbability, genomeLength);
		population = PopulationGenerator.newGenerator(
				genomeLength, populationSize).generate();
		
	}
	
	@Test public void shouldMutatePopulation(){
		newPopulation = mutation.acceptPopulation(population).generateNewPopulation();
		
		Assert.assertTrue(newPopulation.size()==populationSize);
	}
	
	
}
