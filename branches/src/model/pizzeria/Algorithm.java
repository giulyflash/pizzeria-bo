package model.pizzeria;

import java.util.List;
import java.util.Queue;

import model.graph.GraphMatrix;

/**
 * Interfejs, który maj¹ implementowaæ OBIE klasy algorytmów.
 * @author DoomThrower
 *
 */
public interface Algorithm {
	public Result execute(GraphMatrix graphMatrix, List<DeliveryBoy> availableDeliveryBoys, Queue<Order> orderQueue, List<Float> parameters);
}
