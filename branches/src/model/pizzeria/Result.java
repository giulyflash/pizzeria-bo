package model.pizzeria;

import java.util.ArrayList;

/**
 * Klasa zwracana przez KA¯DY z algorytmów - zawiera listê dostawców Z PRZYDZIELONYMI TRASAMI, oraz dodatkowe dane dla zespo³u GUI do sporz¹dzenia wykresów
 * @author DoomThrower
 *
 */
public class Result {
	private ArrayList<DeliveryBoy> _deliveryBoys;
	private ArrayList<Double> _iterationResults;
	
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
