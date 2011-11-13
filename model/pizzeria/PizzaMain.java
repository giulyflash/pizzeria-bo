package model.pizzeria;

import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;
import java.util.Random;

import model.graph.Graph;
/**
 * G��wna klasa odpowiadaj�ca za proces ustalania oprymalnych tras, na podstawie zawartych w niej danych.
 * @author DoomThrower
 *
 */
public class PizzaMain {
	/**
	 * Minimalna ilo�� zam�wie� dodawanych co interwa� 30 minut.
	 */
	private int _minNewOrders;
	/**
	 * Maksymalna ilo�� zam�wie� dodawanych co interwa� 30 minut.
	 */
	private int _maxNewOrders;
	/**
	 * Liczba informuj�ca o ilo�ci dostawc�w (potrzebna w metodzie init()).
	 */
	private int _numberOfDeliveryBoys;
	private int _defaultLoadCapacityOfDeliveryBoy;
	/**
	 * Graf przedstawiaj�cy map� miasta (wierzcho�ek 0 to pizzeria).
	 */
	private Graph _cityMap;
	/**
	 * Lista wszystkich dostawc�w (niezale�nie od tego czy s� obecnie w trasie czy nie).
	 */
	private ArrayList<DeliveryBoy> _allDeliveryBoys;
	/**
	 * Kolejka FIFO zam�wie�.
	 */
	private Queue<Order> _orderQueue;
	/**
	 * Lista oczekuj�cych w pizzerii dostawc�w.
	 */
	private ArrayList<DeliveryBoy> _availableDeliveryBoys;
	/**
	 * Algorytm, kt�rym rozwi�zywany b�dzie problem.
	 */
	private Algorithm _algorithm;
	/**
	 * (UNUSED) Informacja o czasie - na ewentualno�� zmiany dormy reprezentacji Route.timeNeededToFinish.
	 */
	private Date _currentTime;
	/**
	 * Lista parametr�w potrzebnych dla algorytm�w.
	 */
	private ArrayList<Float> _parametersForAlgorithm;
	
	/**
	 * Konstruktor
	 * @param minNewOrders
	 * @param maxNewOrders
	 * @param numberOfDeliveryBoys
	 * @param defaultLoadCapacityOfDeliveryBoy
	 * @param cityMap
	 * @param allDeliveryBoys
	 * @param orderQueue
	 * @param availableDeliveryBoys
	 * @param algorithm
	 * @param currentTime
	 * @param parametersForAlgorithm
	 */
	public PizzaMain(int minNewOrders, int maxNewOrders, int numberOfDeliveryBoys, int defaultLoadCapacityOfDeliveryBoy, Graph cityMap, ArrayList<DeliveryBoy> allDeliveryBoys, Queue<Order> orderQueue, ArrayList<DeliveryBoy> availableDeliveryBoys, Algorithm algorithm, Date currentTime, ArrayList<Float> parametersForAlgorithm) {
		_minNewOrders = minNewOrders;
		_maxNewOrders = maxNewOrders;
		_numberOfDeliveryBoys = numberOfDeliveryBoys;
		_defaultLoadCapacityOfDeliveryBoy = defaultLoadCapacityOfDeliveryBoy;
		_cityMap = cityMap;
		_allDeliveryBoys = allDeliveryBoys;
		_orderQueue = orderQueue;
		_availableDeliveryBoys = availableDeliveryBoys;
		setAlgorithm(algorithm);
		_currentTime = currentTime;
		_parametersForAlgorithm = parametersForAlgorithm;
	}
	
	public ArrayList<DeliveryBoy> getAllDeliveryBoys() {
		return _allDeliveryBoys;
	}
	public void addDeliveryBoy(DeliveryBoy deliveryBoy) {
		_allDeliveryBoys.add(deliveryBoy);
	}
	public Queue<Order> getOrderQueue() {
		return _orderQueue;
	}
	public void addOrder(Order order) {
		_orderQueue.add(order);
	}
	public ArrayList<DeliveryBoy> getAvailableDeliveryBoys() {
		return _availableDeliveryBoys;
	}
	
	/**
	 * Metoda aktualizuj� list� dost�pnych dostawc�w na podstawie czasu pozosta�ego do ko�ca.
	 */
	public void checkDeliveryBoysAvailability() {
		_availableDeliveryBoys.clear();
		for(DeliveryBoy deliveryBoy : _allDeliveryBoys) {
			if(deliveryBoy.getCurrentRoute() == null || deliveryBoy.getCurrentRoute().getTimeNeededToFinish() <= 0) {
				deliveryBoy.setCurrentRoute(null);
				_availableDeliveryBoys.add(deliveryBoy);
			}
		}
	}
	/**
	 * Metoda aktualizuje stan problemu:
	 * 	-skraca czas potrzebny do powrotu z tras,
	 * 	-aktualizuje list� dost�pnych dostawc�w,
	 * 	-dodaje nowe losowe zam�wienia z zakresu <_minNewOrders, _maxNewOrders>,
	 */
	public void pizzaUpdate() {
		Route r;
		// Mija 30 minut - skracamy o nie czas potrzebny do powrotu z tras
		for(DeliveryBoy deliveryBoy : _allDeliveryBoys) {
			if(deliveryBoy.getCurrentRoute() != null) {
				r = deliveryBoy.getCurrentRoute();
				r.setTimeNeededToFinish(r.getTimeNeededToFinish()-30);
			}
		}
		// Aktualizujemy list� dost�pnych dostawc�w
		checkDeliveryBoysAvailability();
		// Dodajemy nowe losowe zam�wienia
		Random generator = new Random();
		int size = _cityMap.getVertexList().size();
		int ordersQuantity = _minNewOrders + generator.nextInt(_maxNewOrders);
		for(int i=0; i<ordersQuantity; i++) {
			_orderQueue.add(new Order(_cityMap.getVertex(generator.nextInt(size)+1), 1));
		}
	}
	
	/**
	 * Inicjalizacja (g��wnie listy dostawc�w).
	 */
	public void init() {
		String[] names = {"Luke", "Tom", "Bill", "Jack", "Will"};
		String[] surnames = {"Jackson", "O'Reilly", "Smith", "Wesson", "White"};
		
		Random generator = new Random();
		for(int i=0; i<_numberOfDeliveryBoys; i++) {
			addDeliveryBoy(new DeliveryBoy(names[generator.nextInt(5)]+" "+surnames[generator.nextInt(5)], _defaultLoadCapacityOfDeliveryBoy));
		}
		
		// Na pocz�tku wszyscy dostawcy s� w pizzerii - to dodaje ich do _availableDeliveryBoys
		checkDeliveryBoysAvailability();
	}
	
	//TODO
	public void execute() {
		
	}
	
	public void addParameterForAlgorithm(Float parameter) {
		_parametersForAlgorithm.add(parameter);
	}
	
	public void clearParameterForAlgorithmList() {
		_parametersForAlgorithm.clear();
	}

	public void setNumberOfDeliveryBoys(int numberOfDeliveryBoys) {
		_numberOfDeliveryBoys = numberOfDeliveryBoys;
	}

	public int getMinNewOrders() {
		return _minNewOrders;
	}

	public void setMinNewOrders(int minNewOrders) {
		_minNewOrders = minNewOrders;
	}

	public int getMaxNewOrders() {
		return _maxNewOrders;
	}

	public void setMaxNewOrders(int maxNewOrders) {
		_maxNewOrders = maxNewOrders;
	}

	public int getNumberOfDeliveryBoys() {
		return _numberOfDeliveryBoys;
	}

	public void setAlgorithm(Algorithm algorithm) {
		_algorithm = algorithm;
	}

	public Algorithm getAlgorithm() {
		return _algorithm;
	}

	public void setDefaultLoadCapacityOfDeliveryBoy(int defaultLoadCapacityOfDeliveryBoy) {
		_defaultLoadCapacityOfDeliveryBoy = defaultLoadCapacityOfDeliveryBoy;
	}

	public int getDefaultLoadCapacityOfDeliveryBoy() {
		return _defaultLoadCapacityOfDeliveryBoy;
	}
}
