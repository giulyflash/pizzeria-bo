package model.pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zwracana przez KA¯DY z algorytmów - zawiera listê dostawców Z
 * PRZYDZIELONYMI TRASAMI, oraz dodatkowe dane dla zespo³u GUI do sporz¹dzenia
 * wykresów
 * 
 * @author DoomThrower
 * 
 */
public class Result {
	private ArrayList<DeliveryBoy> _deliveryBoys;
	private ArrayList<Double> _iterationResults;
	private ArrayList<List<Double>> _extendedIterationResults;

	/**
	 * Konstruktor
	 * @param deliveryBoys dostawcy - b>UWAGA</b> Umieszczaæ tylko tych dostawców, którym <b>USTAWIONO</b> œcie¿ki
	 * @param iterationResults - wyniki wszystkich iteracji algorytmu, ³¹cznie z ostatni¹ - potrzebne do stworzenia wykresu 
	 */
	public Result(ArrayList<DeliveryBoy> deliveryBoys, ArrayList<Double> iterationResults){
		_deliveryBoys = deliveryBoys;
		_iterationResults = iterationResults;
	}
	
	public Result(){
		_deliveryBoys = new ArrayList<>();
		_iterationResults = new ArrayList<>();
		_extendedIterationResults = new ArrayList<List<Double>>();
	}
	
	/**
	 * Dodaje dostawce i jego wyniki iteracji
	 * @param deliveryBoy dostawca
	 * @param iterationResults wyniki iteraci dla dostawcy
	 */
	public void setDeliveryBoyAndResults(DeliveryBoy deliveryBoy, List<Double> iterationResults){
		_deliveryBoys.add(deliveryBoy);
		_extendedIterationResults.add(iterationResults);
	}
	
	/**
	 * Zwraca kolekcje zawierajaca wyniki poszczegolnych iteracji dla kazdego dostawcy oddzielnie
	 * @return wyniki iteracji
	 */
	public ArrayList<List<Double>> getExtendedIterationResult(){
		return _extendedIterationResults;
	}
	
	/**
	 * Ustawia dostawców, którym przypisano œcie¿ki. <b>UWAGA</b> Umieszczaæ
	 * tylko tych dostawców, którym <b>USTAWIONO</b> œcie¿ki.
	 * @deprecated
	 * @param deliveryBoys
	 */
	public void setDeliveryBoys(ArrayList<DeliveryBoy> deliveryBoys) {
		_deliveryBoys = deliveryBoys;
	}

	public ArrayList<DeliveryBoy> getDeliveryBoys() {
		return _deliveryBoys;
	}

	/**
	 * @deprecated wyniki iteracji powinny byc oddzielne dla kazdego dostawcy 
	 * @param iterationResults wyniki iteracji dla wszystkich dostawcow lacznie
	 */
	public void setIterationResults(ArrayList<Double> iterationResults) {
		_iterationResults = iterationResults;
	}

	/**
	 * @deprecated wyniki iteracji powinny byc oddzielne dla kazdego dostawcy 
	 * @return iterationResults wyniki iteracji dla wszystkich dostawcow lacznie
	 */
	public ArrayList<Double> getIterationResults() {
		return _iterationResults;
	}
}
