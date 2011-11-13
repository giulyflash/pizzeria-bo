package model.pizzeria;

import java.util.ArrayList;

/**
 * Klasa zwracana przez KA�DY z algorytm�w - zawiera list� dostawc�w Z PRZYDZIELONYMI TRASAMI, oraz dodatkowe dane dla zespo�u GUI do sporz�dzenia wykres�w
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
