package algorithms.genetic.operation.selection;

import java.util.List;

import algorithms.genetic.operation.GeneticTransformer;
import algorithms.genetic.structures.Genome;

public class Selection implements GeneticTransformer{

	@Override
	public Selection acceptPopulation(List<Genome> population) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public List<Genome> generateNewPopulation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Selection acceptEvaluation(List<Double> evaluation){
		//TODO
		return this;
	}



}
