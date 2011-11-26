package algorithms.genetic.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithms.genetic.structures.Genome;

public class PopulationGenerator {
	
	private int genomeLength;
	
	private int populationSize;
	
	private List<Integer> vertexesIds;
	
	private PopulationGenerator(int genomeLength, int populationSize) {
		super();
		this.genomeLength = genomeLength;
		this.populationSize = populationSize;
		this.vertexesIds=GeneralUtils.getIntegerFromZeroTo(genomeLength);
	}

	public List<Genome> generate(){
		List<Genome> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(getRandomGenome());
		}
		return population;
	}

	private Genome getRandomGenome() {
		Genome g =  new Genome(genomeLength);
		int[] genomeInnards = getNewRandomGenomePath();
		g.setPath(genomeInnards);
		return g;
	}

	private int[] getNewRandomGenomePath() {
		Collections.shuffle(vertexesIds);
		int[] genomeInnards=new int[genomeLength];
		for (int i = 0; i < genomeInnards.length; i++) {
			genomeInnards[i]=vertexesIds.get(i);
		}
		return genomeInnards;
	}
	
	public static PopulationGenerator newGenerator(int genomeLength, int populationSize){
			return new PopulationGenerator(genomeLength, populationSize);
	}
	

}
