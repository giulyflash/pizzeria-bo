package algorithms.genetic.utils;

import java.util.ArrayList;
import java.util.List;

import model.graph.GraphMatrix;
import model.graph.Group;
import algorithms.genetic.structures.GeneticGraph;
import algorithms.genetic.structures.Genome;

public class GeneralMatrixToGeneticMatricConverter {

	private List<List<Integer>> listOfPlacesToVisitForEachDeliveryBoy;
	
	private List<GeneticGraph> listOfGeneticGraph;

	public GeneralMatrixToGeneticMatricConverter(GraphMatrix matrix,
			int numberOfDeliveryBoys, int deliveryBoyCapacity) {
		listOfGeneticGraph = new ArrayList<>();
		double[][] matrix1 = matrix.getMatrix();
		Group g = new Group(matrix1, numberOfDeliveryBoys, deliveryBoyCapacity);
		listOfPlacesToVisitForEachDeliveryBoy = g.group();
		for (List<Integer> path : listOfPlacesToVisitForEachDeliveryBoy) {
			double[][] subMatrix= new double[path.size()][path.size()];
			for (int i = 0; i < subMatrix.length; i++) {
				for (int j = 0; j < subMatrix.length; j++) {
					int a1 = path.get(i);
					int a2 = path.get(j);
					subMatrix[i][j]=matrix1[a1][a2];
				}
			}
			
			GeneticGraph geneticGraph = new GeneticGraph(subMatrix, subMatrix.length);
			listOfGeneticGraph.add(geneticGraph);
			geneticGraph.setId(listOfGeneticGraph.indexOf(geneticGraph));
		}
//		int i =0;
//		for (List<Integer> path : listOfPlacesToVisitForEachDeliveryBoy) {
//			System.out.println(path);
//			GeneticGraph gg = listOfGeneticGraph.get(i);
//			i++;
//			System.out.println(gg.toString());
//			System.out.println(":::::::");
//			
//		}
		
	}

	
	public List<Integer> convertPath(Genome genome, int geneticGraphId){
		int[] path = new int[genome.getPath().length];
		int[] genomePath = genome.getPath();
		List<Integer> placesToVisitForEachDeliveryBoy = listOfPlacesToVisitForEachDeliveryBoy.get(geneticGraphId);
		//GeneticGraph geneticGraph = listOfGeneticGraph.get(geneticGraphId);
		for (int i = 0; i < path.length; i++) {
			path[i] = placesToVisitForEachDeliveryBoy.get(genomePath[i]);
		}
		List<Integer> returnValue = new ArrayList<>();
		for (Integer integer : path) {
			returnValue.add(integer);
		}
		return 
				returnValue;		
	}
//	public static void main(String[] args) {
//		GeneralMatrixToGeneticMatricConverter g = new GeneralMatrixToGeneticMatricConverter(
//				null, 2, 2);
//		
//		int[] p = {0,1};
//		Genome genome = new Genome(p);
//		for (Integer i : g.convertPath(genome, 0)) {
//			System.out.println(i);
//		}
//	}


	public List<List<Integer>> getListOfPlacesToVisitForEachDeliveryBoy() {
		return listOfPlacesToVisitForEachDeliveryBoy;
	}


	public List<GeneticGraph> getListOfGeneticGraph() {
		return listOfGeneticGraph;
	}



}
