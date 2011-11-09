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

	private int crossoverProbability;

	private List<Genome> oldPopulation;

	@Override
	public void acceptPopulation(List<Genome> population) {
		this.oldPopulation = population;
	}

	@Override
	public List<Genome> generateNewPopulation() {
		List<Genome> populationPartForCrossover = new ArrayList<>();
		for (Genome genome : oldPopulation) {
			if (GeneralUtils.getBoolean(crossoverProbability)) {
				populationPartForCrossover.add(genome);
			}
		}
		oldPopulation.removeAll(populationPartForCrossover);

		if (populationPartForCrossover.size() % 2 != 0) {
			int chosenIndex;
			Genome tmp;
			if (GeneralUtils.getBoolean(0.5)) { // remove
				chosenIndex = GeneralUtils
						.nextRandomIntModulo(populationPartForCrossover.size());
				tmp = populationPartForCrossover.remove(chosenIndex);
				oldPopulation.add(tmp);
			} else { // add
				chosenIndex = GeneralUtils.nextRandomIntModulo(oldPopulation
						.size());
				tmp = oldPopulation.remove(chosenIndex);
				populationPartForCrossover.add(tmp);
			}
		}

		List<Genome> newGeneration = new ArrayList<>();
		for (int i = 0; i < populationPartForCrossover.size(); i++) {
			Genome parent1 = populationPartForCrossover.get(i);
			Genome parent2 = populationPartForCrossover.get(i + 1);
			newGeneration.addAll(doEdgeRecombinationOperator(parent1, parent2));
			i++;
		}
		oldPopulation.addAll(newGeneration);
		return new ArrayList<>(oldPopulation);
	}

	public void setCrossoverProbability(int crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	private List<Genome> doEdgeRecombinationOperator(Genome parent1,
			Genome parent2) {
		List<Genome> children = new ArrayList<>();
		int[] path1 = parent1.getPath();
		int[] path2 = parent2.getPath();
		int[] childPath1 = generateOneChild(path1, path2);
		int[] childPath2 = generateOneChild(path2, path1);
		Genome child1 = new Genome(childPath1);
		Genome child2 = new Genome(childPath2);
		children.add(child1);
		children.add(child2);
		return children;
	}

	private int[] generateOneChild(int[] path1, int[] path2) {
		int pathLen = path2.length;
		int[] childPath = new int[pathLen];
		Map<Integer, Set<Integer>> edgeMap = getCityEdgesMap(path1, path2);
		List<Integer> notYetChosenCities = getCitiesList(pathLen);

		int currentCity = path1[0];

		int currentCityIndex = 0;
		childPath[0] = currentCity;
		notYetChosenCities.remove(currentCity);

		do {

			Set<Integer> neighbours = edgeMap.get(currentCity);
			removeCurrentCityFromEdgesMap(edgeMap, currentCity);

			int nextCity = getNextCity(edgeMap, neighbours, notYetChosenCities);
			currentCityIndex++;
			currentCity = nextCity;
			childPath[currentCityIndex] = currentCity;
			notYetChosenCities.remove(currentCity);

		} while (!edgeMap.isEmpty());

		return childPath;
	}

	private List<Integer> getCitiesList(int pathLen) {
		List<Integer> notYetChosenCities = new ArrayList<>();
		for (int i = 0; i < pathLen; i++) {
			notYetChosenCities.add(i + 1);
		}
		return notYetChosenCities;
	}

	private int getNextCity(Map<Integer, Set<Integer>> edgeMap,
			Set<Integer> neighbours, List<Integer> notYetChosenCities) {
		if (neighbours.size() == 0) {
			Collections.shuffle(notYetChosenCities);
			return notYetChosenCities.get(0);
		}
		int minimalNumberOfNeighbours = 5;
		int tmpNeighbourEgdeNumber;
		int candidates[] = new int[4];
		int i = 0;
		for (Integer neighbourCity : neighbours) {
			tmpNeighbourEgdeNumber = edgeMap.get(neighbourCity).size();
			if (tmpNeighbourEgdeNumber < minimalNumberOfNeighbours) {
				i = 0;
				minimalNumberOfNeighbours = tmpNeighbourEgdeNumber;
			}
			if (tmpNeighbourEgdeNumber == minimalNumberOfNeighbours) {
				candidates[i] = neighbourCity;
				i++;
			} else {
				// pass
			}
		}
		int chosenOne;
		int numberOfCandidates = i;
		if (i == 1) {
			chosenOne = candidates[0];
		} else {
			chosenOne = candidates[GeneralUtils
					.nextRandomIntModulo(numberOfCandidates)];
		}
		return chosenOne;
	}

	private void removeCurrentCityFromEdgesMap(
			Map<Integer, Set<Integer>> edgeMap, int initialCity) {
		Set<Integer> tmp;
		for (int i : edgeMap.keySet()) {
			tmp = edgeMap.get(i);
			tmp.remove(initialCity);
			if (tmp.isEmpty()) {
				edgeMap.remove(tmp);
			}

		}
		edgeMap.remove(initialCity);
	}

	private Map<Integer, Set<Integer>> getCityEdgesMap(int[] path1, int[] path2) {
		Map<Integer, Set<Integer>> egdeList = new LinkedHashMap<>();
		updateEdgeMapBasedOnPath(path1, egdeList);
		updateEdgeMapBasedOnPath(path2, egdeList);

		return egdeList;
	}

	private void updateEdgeMapBasedOnPath(int[] path1,
			Map<Integer, Set<Integer>> egdeList) {
		for (int i = 0; i < path1.length; i++) {
			Set<Integer> edgesOfSpecificCity = egdeList.get(i);
			if (edgesOfSpecificCity == null) {
				edgesOfSpecificCity = new HashSet<>();
				egdeList.put(path1[i], edgesOfSpecificCity);
			}
			if ((i + 1) != path1.length) {
				edgesOfSpecificCity.add(path1[i + 1]);
			} else if (i != 0) {
				edgesOfSpecificCity.add(path1[i - 1]);
			}
		}
	}

}
