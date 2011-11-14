package model.pizzeria;

import java.util.List;
import java.util.Queue;

import model.graph.GraphMatrix;

/**
 * Interfejs, kt�ry maj� implementowa� OBIE klasy algorytm�w.
 * @author DoomThrower
 *
 */
public interface Algorithm {
	public Result execute(GraphMatrix graphMatrix, List<DeliveryBoy> availableDeliveryBoys, List<Float> parameters);
}
