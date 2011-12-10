package model.pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zwracana przez KA�DY z algorytm�w - zawiera list� dostawc�w Z
 * PRZYDZIELONYMI TRASAMI, oraz dodatkowe dane dla zespo�u GUI do sporz�dzenia
 * wykres�w
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
	 * @param deliveryBoys dostawcy - b>UWAGA</b> Umieszcza� tylko tych dostawc�w, kt�rym <b>USTAWIONO</b> �cie�ki
	 * @param iterationResults - wyniki wszystkich iteracji algorytmu, ��cznie z ostatni� - potrzebne do stworzenia wykresu 
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
	 * Ustawia dostawc�w, kt�rym przypisano �cie�ki. <b>UWAGA</b> Umieszcza�
	 * tylko tych dostawc�w, kt�rym <b>USTAWIONO</b> �cie�ki.
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
