package gui;

import java.util.ArrayList;

import model.graph.GraphMatrix;
import model.pizzeria.Algorithm;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Result;
import algorithms.pso.PSOAlgorithm;

public class PSOComputer {
	public PSOComputer(double w, double c1, double c2, int part, int iter, 
			int dboys, int cap, int lrange, int rrange, GraphMatrix gm) {
		
		Algorithm al = new PSOAlgorithm();
		ArrayList<Float> params = new ArrayList<Float>();
		
		params.add(new Float(w));
		params.add(new Float(c1));
		params.add(new Float(c2));
		params.add(new Float(part));
		params.add(new Float(iter));
		
		ArrayList<DeliveryBoy> boys = new ArrayList<DeliveryBoy>();
		for(int i = 0 ; i < dboys; i++) 
			boys.add(new DeliveryBoy("Boy " + i, cap));
		
		Result r = al.execute(gm, boys, params);
		
		paintThisShit(r);
	}
	
	private void paintThisShit(Result r) {
		// TODO pewnie wywolanie jakiejs statycznej metody na MyPaintListener
		System.out.println("Obliczono");
	}
}
