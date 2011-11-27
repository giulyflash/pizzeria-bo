package algorithms.genetic.operation.mutation;

import java.util.ArrayList;
import java.util.List;

import algorithms.genetic.operation.GeneticTransformer;
import algorithms.genetic.structures.Genome;
import algorithms.genetic.utils.GeneralUtils;

public class Mutation implements GeneticTransformer{

	private List<Genome> population;
	
	private double mutationProbability;
	
	private int genomeSize;
	
	public Mutation(double mutationProbability, int genomeSize) {
		this.mutationProbability=mutationProbability;
		this.genomeSize=genomeSize;
	}
	@Override
	public Mutation acceptPopulation(List<Genome> population) {
		this.population=new ArrayList<>(population);
		return this;
	}

	@Override
	public List<Genome> generateNewPopulation() {
		for (Genome genome : population) {
			if(GeneralUtils.generateBoolean(mutationProbability)){
				int firstCutPoint = GeneralUtils.nextRandomIntModulo(genomeSize);
				int secondCutPoint = GeneralUtils.nextRandomIntModulo(genomeSize);
				secondCutPoint=(firstCutPoint+secondCutPoint)%genomeSize;
				if(firstCutPoint>secondCutPoint){
					doInversion(genome,secondCutPoint,firstCutPoint);
				} else if(secondCutPoint>firstCutPoint){
					doInversion(genome,firstCutPoint,secondCutPoint);
				}
			}
		}
		return population;
	}
	
	private void doInversion(Genome genome,int first,int second){
		int j;
		int i;
		int[] path = genome.getPath();
		int tmp;
		//System.out.println("Inverse from: " + first + ", to : " + second);
		//System.out.println(Arrays.toString(path));
		for (i = first, j= second; j>i; i++,j--) {
			tmp=path[i];
			path[i]=path[j];
			path[j]=tmp;
		}
		//System.out.println(Arrays.toString(path));
		
	}



}
