package algorithms.genetic.operation.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import algorithms.genetic.operation.GeneticTransformer;
import algorithms.genetic.structures.Genome;
import algorithms.genetic.utils.GeneralUtils;

public class Crossover implements GeneticTransformer {

	private double crossoverProbability;

	private List<Genome> oldPopulation;

	private List<Genome> crossoverPopulation;

	private int genomeLength;

	public Crossover(double crossoverProbability, int genomeLength) {
		this.crossoverProbability = crossoverProbability;
		this.genomeLength = genomeLength;
	}

	@Override
	public Crossover acceptPopulation(List<Genome> population) {
		if (population.size() < 2) {
			throw new RuntimeException(
					"GeneticAlgorithm:Crossover: population.size()<2 exception");
		}
		this.oldPopulation = new ArrayList<>(population);
		return this;
	}

	@Override
	public List<Genome> generateNewPopulation() {
		preparePopulationForCrossover();

		for (int i = 0; i < crossoverPopulation.size(); i += 2) {
			Genome parent1 = crossoverPopulation.get(i);
			Genome parent2 = crossoverPopulation.get(i + 1);
			doEdgeRecombination(parent1, parent2);
		}
		return oldPopulation;
	}

	private void preparePopulationForCrossover() {
		crossoverPopulation = new ArrayList<>();
		for (Genome genome : oldPopulation) {
			if (GeneralUtils.generateBoolean(crossoverProbability)) {
				crossoverPopulation.add(genome);
			}
		}
		oldPopulation.removeAll(crossoverPopulation);
		adjustCrossoverPopulationToEvenLength();
	}

	private void adjustCrossoverPopulationToEvenLength() {
		if (isPopulationForCrossoverOdd()) {
			if (GeneralUtils.generateBoolean(0.5)) {
				removeOneGenomeFromCrossoverPopulation();
			} else {
				addOneGenomeToCrossoverPopulation();
			}
		}
	}

	private void addOneGenomeToCrossoverPopulation() {
		int chosenIndex;
		Genome tmp;
		chosenIndex = GeneralUtils.nextRandomIntModulo(oldPopulation.size());
		tmp = oldPopulation.remove(chosenIndex);
		crossoverPopulation.add(tmp);
	}

	private void removeOneGenomeFromCrossoverPopulation() {
		int chosenIndex;
		Genome tmp;
		chosenIndex = GeneralUtils.nextRandomIntModulo(crossoverPopulation
				.size());
		tmp = crossoverPopulation.remove(chosenIndex);
		oldPopulation.add(tmp);
	}

	private boolean isPopulationForCrossoverOdd() {
		return crossoverPopulation.size() % 2 != 0;
	}

	public void setCrossoverProbability(int crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	private void doEdgeRecombination(Genome parent1, Genome parent2) {
		int[] path1 = parent1.getPath();
		int[] path2 = parent2.getPath();
		int[] childPath1 = generateChild(path1, path2);
		int[] childPath2 = generateChild(path2, path1);
		Genome child1 = new Genome(childPath1);
		Genome child2 = new Genome(childPath2);
		oldPopulation.add(child1);
		oldPopulation.add(child2);
	}

	private int[] generateChild(int[] path1, int[] path2) {
		int[] newPath = new int[genomeLength];
		Map<Integer, Set<Integer>> edgesOfVertexesMap = getEdgesOfVertexMap(
				path1, path2);
//		System.out.println("EdgesOfVertexes_Map: " + edgesOfVertexesMap);
		List<Integer> vertexesLeft = GeneralUtils
				.getIntegerFromZeroTo(genomeLength);

		int currentVertex = path1[0];

		int vertexIndex = 0;

		do {
//			System.out.println("Current vertex: " + currentVertex + " left: " + vertexesLeft);
			newPath[vertexIndex++] = currentVertex;
			Set<Integer> neighbours = edgesOfVertexesMap.get(currentVertex);
			removeCurrentCityFromEdgesMap(edgesOfVertexesMap, currentVertex);
			vertexesLeft.remove((Object)currentVertex);
			if(vertexesLeft.size()==0){
				break;
			}
			int nextCity = getNextVertex(edgesOfVertexesMap, neighbours,
					vertexesLeft);
			currentVertex = nextCity;
	//		System.out.println("");
		} while (!edgesOfVertexesMap.isEmpty());

		return newPath;
	}

	private int getNextVertex(Map<Integer, Set<Integer>> edgeMap,
			Set<Integer> neighbours, List<Integer> vertexesLeft) {
		int nextVertex;
//		System.out.println(edgeMap);
		if (neighbours == null || neighbours.size() == 0) {
			Collections.shuffle(vertexesLeft);
			nextVertex = vertexesLeft.get(0);
		} else {
			int minimalNumberOfNeighbours = 5;
			int numberOfNeighbours;
			int candidates[] = new int[4];
			int numberOfCandidates = 0;
			for (Integer vertexWithNeighbours : neighbours) {
				numberOfNeighbours = edgeMap.get(vertexWithNeighbours).size();
				if (numberOfNeighbours < minimalNumberOfNeighbours) {
					numberOfCandidates = 0;
					minimalNumberOfNeighbours = numberOfNeighbours;
				}
				if (numberOfNeighbours == minimalNumberOfNeighbours) {
					candidates[numberOfCandidates] = vertexWithNeighbours;
					numberOfCandidates++;
				} 
			}
			if (numberOfCandidates == 1) {
				nextVertex = candidates[0];
			} else {
				nextVertex = candidates[GeneralUtils
						.nextRandomIntModulo(numberOfCandidates)];
			}
		}
		return nextVertex;
	}

	private void removeCurrentCityFromEdgesMap(
			Map<Integer, Set<Integer>> edgeMap, int cityToRemove) {
		Set<Integer> tmp;
		for (int i : edgeMap.keySet()) {
			tmp = edgeMap.get(i);
			tmp.remove(cityToRemove);
			if (tmp.isEmpty()) {
				edgeMap.remove(tmp);
			}

		}
		edgeMap.remove(cityToRemove);
	}

	private Map<Integer, Set<Integer>> getEdgesOfVertexMap(int[] path1,
			int[] path2) {
		Map<Integer, Set<Integer>> neighbouringVertexesPerVertex = new LinkedHashMap<>();
		updateEdgeMapBasedOnPath(path1, neighbouringVertexesPerVertex);
		updateEdgeMapBasedOnPath(path2, neighbouringVertexesPerVertex);
		return neighbouringVertexesPerVertex;
	}

	private void updateEdgeMapBasedOnPath(int[] path,
			Map<Integer, Set<Integer>> edgeList) {
		for (int i = 0; i < genomeLength; i++) {
			
			Set<Integer> edgesOfVertex = edgeList.get(path[i]);
			if (edgesOfVertex == null) {
				edgesOfVertex = new HashSet<>();
				edgeList.put(path[i], edgesOfVertex);
			}
			edgesOfVertex.add(path[(i + 1) % genomeLength]);
			edgesOfVertex.add(path[(genomeLength + i - 1) % genomeLength]);
		}
	}

}
