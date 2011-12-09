package algorithms.genetic;

import java.util.ArrayList;
import java.util.List;

import model.graph.GraphMatrix;
import model.graph.Vertex;
import model.pizzeria.Algorithm;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Result;
import model.pizzeria.Route;
import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;
import algorithms.genetic.utils.GeneralMatrixToGeneticMatricConverter;
import algorithms.genetic.utils.GeneticAlghorithm;
import algorithms.genetic.utils.GeneticSolutionPack;

public class GeneticAlgorithmRunner implements Algorithm {

	private int numberOfIterations;
	
	private int populationSize;
	
	private double crossoverProbability;
	
	private double mutationProbability;
	
	private GeneticAlghorithm algorithm;
	

	public GeneticAlgorithmRunner(int numberOfIterations, int populationSize,
			double crossoverProbability, double mutationProbability) {
		this.numberOfIterations = numberOfIterations;
		this.populationSize = populationSize;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
		
	}



	@Override
	public Result execute(GraphMatrix graphMatrix,
			List<DeliveryBoy> availableDeliveryBoys, List<Float> parameters) {
		int numberOfDeliveryBoys = availableDeliveryBoys.size();
		if(numberOfDeliveryBoys==0){
			return null;
		}
		int maxCapacity = availableDeliveryBoys.get(0).getLoadCapacity();
		GeneralMatrixToGeneticMatricConverter converter = 
				new GeneralMatrixToGeneticMatricConverter(
						graphMatrix, availableDeliveryBoys.size(), maxCapacity);
		
		
		Result result = new Result();
		int deliveryBoyNumber=0;
		for (GeneticGraph geneticGraph : converter.getListOfGeneticGraph()) {
			int genomeLength=geneticGraph.getSize();
			algorithm=new GeneticAlghorithm(
					geneticGraph, mutationProbability, crossoverProbability, 
					numberOfIterations, populationSize, genomeLength);
			GeneticSolutionPack pack = algorithm.solve();
			Genome bestGenome = pack.getBestGenome();
			List<Integer> path = converter.convertPath(bestGenome, geneticGraph.getId());
			List<Vertex> vertices = graphMatrix.translateToFullVerticesList(path);
			DeliveryBoy db = availableDeliveryBoys.get(deliveryBoyNumber);
			deliveryBoyNumber++;
			double timeNeededToFinish = pack.getBestValue();
			
			graphMatrix.getOrders();
			//Route route = new Route(timeNeededToFinish, vertices,WTF )); //wtf??
			//db.setCurrentRoute(route)
			
		}
		result.setDeliveryBoys(new ArrayList<>(availableDeliveryBoys));
		//result.setIterationResults(iterationResults); // wtf?
		return result;
	}



	public int getNumberOfIterations() {
		return numberOfIterations;
	}



	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}



	public int getPopulationSize() {
		return populationSize;
	}



	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}



	public double getCrossoverProbability() {
		return crossoverProbability;
	}



	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}



	public double getMutationProbability() {
		return mutationProbability;
	}



	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

}
