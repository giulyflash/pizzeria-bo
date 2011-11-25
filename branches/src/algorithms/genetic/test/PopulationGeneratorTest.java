package algorithms.genetic.test;

import static junit.framework.Assert.*;

import java.util.List;

import org.junit.Test;

import algorithms.genetic.PopulationGenerator;
import algorithms.genetic.structures.Genome;

public class PopulationGeneratorTest {
	
	List<Genome> population;
	
	@Test public void shouldGeneratePopulation(){
		int genomeLength = 10;
		int populationSize = 100;
		initPopulation(genomeLength, populationSize);
		
		makeAssertions(genomeLength, populationSize);
		
	}
	
	@Test public void shouldGenerateMinimalPopulation(){
		int genomeLength = 10;
		int populationSize = 2;
		initPopulation(genomeLength, populationSize);
		makeAssertions(genomeLength, populationSize);
		
	}
	
	@Test public void shouldFailAllNumbersAssertion(){
		int genomeLength = 10;
		int populationSize = 10;
		initPopulation(genomeLength, populationSize);
		population.get(1).getPath()[0]=1;
		population.get(1).getPath()[1]=1;
		assertTrue(population.size()==populationSize);
		boolean failed = false;
		for (Genome genome : population) {
			assertTrue(genome.getPath().length==genomeLength);
			failed = failed || doesNotHaveAllNumbers(genomeLength, genome);
		}
		assertTrue(failed);
	}

	private boolean doesNotHaveAllNumbers(int genomeLength, Genome genome) {
		return !haveAllNumbersFromZeroTo(genomeLength,genome);
	}
	
	@Test public void shouldGenerateEmptyPopulation(){
		int genomeLength = 10;
		int populationSize = 0;
		initPopulation(genomeLength, populationSize);
		assertTrue(population.size()==0);
		
	}
	
	
	private void makeAssertions(int genomeLength, int populationSize) {
		assertTrue(population.size()==populationSize);
		for (Genome genome : population) {
			assertTrue(genome.getPath().length==genomeLength);
			assertTrue(haveAllNumbersFromZeroTo(genomeLength,genome));
		}
	}
	private boolean haveAllNumbersFromZeroTo(int limit, Genome genome){
		for (int number = 0; number < limit;number++) {
			if(!haveNumber(number,genome.getPath())){
				return false;
			}
		}
		return true;
	}
	
	private boolean haveNumber(int number,int[] path){
		for (int i = 0; i < path.length; i++) {
			if(path[i]==number){
				return true;
			}
		}
		return false;
	}
	
	private void initPopulation(int genomeLength, int populationSize){
		population = PopulationGenerator.newGenerator(
				genomeLength, populationSize).generate();
	}
}
