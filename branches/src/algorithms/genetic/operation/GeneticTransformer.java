package algorithms.genetic.operation;

import java.util.List;

import algorithms.genetic.structures.Genome;

public interface GeneticTransformer {
	
	GeneticTransformer acceptPopulation(List<Genome> population);
	
	List<Genome> generateNewPopulation();
	

}
