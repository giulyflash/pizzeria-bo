package algorithms.genetic.operation.selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.genetic.SolutionEvaluator;
import algorithms.genetic.operation.GeneticTransformer;
import algorithms.genetic.structures.Genome;
import algorithms.genetic.utils.GeneralUtils;

public class Selection implements GeneticTransformer{

	private double[] evalList;
	
	private List<Double> cumulativeDistribution;
		
	private List<Genome> oldPopulation;
	
	private List<Genome> newPopulation;
		
	private int populationSize;
	
	private SolutionEvaluator evaluator;				
	
	private double totalEval;
	
	private int genomeLength;
	
	private double temporaryEvalListMaxValue;
	
	public Selection(SolutionEvaluator evaluator, int genomeLength) {
		this.evaluator=evaluator;
		this.genomeLength=genomeLength;
	}
	
	@Override
	public Selection acceptPopulation(List<Genome> population) {
		oldPopulation = new ArrayList<>(population);
		populationSize = population.size();
		cumulativeDistribution=new ArrayList<>();
		evalList=new double[populationSize];
		return this;
	}

	@Override
	public List<Genome> generateNewPopulation() {
		calculateEvaluationParameters();
		calculateProbabilityParameters();
		//System.out.println("eval array: " + Arrays.toString(evalList));
		//System.out.println("dystrybuanta: " + cumulativeDistribution);
		newPopulation = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			newPopulation.add(selectGenome());
		}
		return newPopulation;
	}
	
	private Genome selectGenome(){
		double probability = GeneralUtils.nextRandomDouble(0, 1);
		int index = Collections.binarySearch(cumulativeDistribution, probability);
		if(index<0){
			index=-index-1;
		}
		//System.out.println("probability: " + probability +
		//		", index: " + index);
		Genome g =  oldPopulation.get(index);
		return g.copy();
	}

	private void calculateProbabilityParameters() {
		double prevProbability=0.0;
		double ithCumulativeProbability;
		for (int i = 0; i < populationSize; i++) {
			ithCumulativeProbability=getProbability(i)+prevProbability;
			cumulativeDistribution.add(i,ithCumulativeProbability);
			prevProbability=ithCumulativeProbability;
		}
	}

	private double getProbability(int i) {
		return evalList[i]/totalEval;
	}

	private void calculateEvaluationParameters() {
		
		temporaryEvalListMaxValue=0.0;
		double tmpEval;
		for (int i = 0; i < populationSize; i++) {
			tmpEval = evaluator.eval(oldPopulation.get(i));
			if(tmpEval>temporaryEvalListMaxValue){
				temporaryEvalListMaxValue=tmpEval;
			}
			
			evalList[i]=tmpEval;
		}
		totalEval=0.0;
		for (int i = 0; i < populationSize; i++) {
			evalList[i]=temporaryEvalListMaxValue-evalList[i];
			totalEval+=evalList[i];
		}
	}
	
}