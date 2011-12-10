package model.pizzeria;

import java.util.ArrayList;

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
	}
	
	/**
	 * Ustawia dostawców, którym przypisano œcie¿ki. <b>UWAGA</b> Umieszczaæ
	 * tylko tych dostawców, którym <b>USTAWIONO</b> œcie¿ki.
	 * 
	 * @param deliveryBoys
	 */
	public void setDeliveryBoys(ArrayList<DeliveryBoy> deliveryBoys) {
		_deliveryBoys = deliveryBoys;
	}

	public ArrayList<DeliveryBoy> getDeliveryBoys() {
		return _deliveryBoys;
	}

	public void setIterationResults(ArrayList<Double> iterationResults) {
		_iterationResults = iterationResults;
	}

	public ArrayList<Double> getIterationResults() {
		return _iterationResults;
	}
}
