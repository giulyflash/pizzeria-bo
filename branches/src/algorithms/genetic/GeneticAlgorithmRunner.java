package algorithms.genetic;

import java.util.ArrayList;
import java.util.List;

import model.graph.GraphMatrix;
import model.graph.Vertex;
import model.pizzeria.Algorithm;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Order;
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
		
		//obsluga ewentualnych parametrow
		if (parameters != null && parameters.size() == 4) {
			getAlgorithmParametersFronEnigmaticList(parameters);
		}
		
		//sprawdzenie czy jest chociaz jeden deliveryBoy
		int numberOfDeliveryBoys = availableDeliveryBoys.size();
		if (numberOfDeliveryBoys == 0) {
			return null;
		}
		
		//stworzenie konwertera.
		//jest on potrzebny bo do algorytm genetyczny (GeneticAlgorithm.solve())
		//obsluguje male macierze ktore maja tyle wierzcholkow ile chce odwiedzic
		//
		//chodzi o to, ze majac graphMatrix - pelny graf
		//uzywam grupowania do uzyskania wierzcholkow ktore mam odwiedziec,
		//np. uzyskalem wierzcholki 0,5,45,225,1000.
		//na ich podstawie tworze mala macierz dla alg. genetycznego
		//ktora jest tablica 5x5, 
		//algortym genetyczny zwraca rozwiazanie, np. 2,0,1,3,4
		//teraz te numery przkestlacem na wierzcho�ki duzej macierzy, czyli
		// 2 -> 45
		// 0 -> 0
		// 1 -> 5
		// 3 -> 225
		// 4 -> 1000
		// czyli rozwiazaniem jest trasa 45,0,5,225,1000
		int maxCapacity = availableDeliveryBoys.get(0).getLoadCapacity();
		GeneralMatrixToGeneticMatricConverter converter = new GeneralMatrixToGeneticMatricConverter(
				graphMatrix, availableDeliveryBoys.size(), maxCapacity);

		//tworze instancje Result ktora po uzupelnieniu zwr�ce
		Result result = new Result();
		int deliveryBoyNumber = 0;
		
		//iteracja po ma�ych macierzach, ktory uzyskalem z grupowania
		for (GeneticGraph geneticGraph : converter.getListOfGeneticGraph()) {
			int genomeLength = geneticGraph.getSize();
			
			//tworze nowa instacje algorytmu z odpowiednimi parametrami
			algorithm = new GeneticAlghorithm(geneticGraph,
					mutationProbability, crossoverProbability,
					numberOfIterations, populationSize, genomeLength);
			//rozwiazuje, dostaje obiekt GeneticSolutionPack kt�ry agreguje rozne informacje o rozwiazaniu
			GeneticSolutionPack pack = algorithm.solve();
			
			//wybieram najlepsze rozwiazanie, w postacji [2,0,1,3,4]
			Genome bestGenome = pack.getBestGenome();
			
			//convertuje to najlepsze rozwiazanie z postaci [2,0,1,3,4] do [45,0,5,225,1000]
			List<Integer> path = converter.convertPath(bestGenome,
					geneticGraph.getId());
			
			// kazali wywo�ac to wywoluje.
			// to chyba jest przeksztalcenie [45,0,5,225,1000] z grafu pelnego
			// do listy wierzcholkow z grafu niepelnego, prawda?
			List<Vertex> vertices = graphMatrix
					.translateToFullVerticesList(path);
			
			//pobieram deliveryBoya
			DeliveryBoy db = availableDeliveryBoys.get(deliveryBoyNumber);
			deliveryBoyNumber++;
			
			//pobieram czas najlepszego rozwiazania
			double timeNeededToFinish = pack.getBestValue();

			
			//pobieram wszystkie zamowieania, zakladam, ze na i-tym w miejscu na tej tablicy
			//sa zam�wienia do i-tego wierzcho�ka - ALE z ktorego grafu??? 
			// zakladam ze indeksy tablicy allOrders zgadza sie z numeracja obiektow
			// klasy Vertex
			Order[] allOrders = graphMatrix.getOrders();
			
			
			// TODO why ArrayList?? w konstruktorze Route()
			// tutaj wykorzystuje powyzsze zalozenie
			ArrayList<Order> currentOrders = new ArrayList<>();
			for (Vertex vertex : vertices) {
				currentOrders.add(allOrders[vertex.getNumber()]);
			}

			//tworze nowy route
			Route route = new Route(timeNeededToFinish, vertices, currentOrders);
			
			//wsadzam go do deliveryBoya
			db.setCurrentRoute(route);
			
			//deliveryBoya wsadzam do resulta razem z lista wynikow dla kazdej iteracji,
			//n-ty element na tej liscie to najlepszey wynik n-tej iteracji
			result.setDeliveryBoyAndResults(db, pack.getBestResultOfIteration());
		}

		return result;
	}

	private void getAlgorithmParametersFronEnigmaticList(List<Float> parameters) {
		this.numberOfIterations = parameters.get(0).intValue();
		this.populationSize = parameters.get(1).intValue();
		this.crossoverProbability = parameters.get(2);
		this.mutationProbability = parameters.get(3);
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
