package gui;

import java.util.ArrayList;

import model.graph.GraphMatrix;
import model.pizzeria.Algorithm;
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Result;

public class AlghoritmComputer {
	private Result r;
	public AlghoritmComputer(Algorithm al, double w, double c1, double c2, double part, double iter, 
			int dboys, int cap, int lrange, int rrange, GraphMatrix gm) {
		
		//Algorithm al = new PSOAlgorithm();
		ArrayList<Float> params = new ArrayList<Float>();
		
		// Dla genetycznego idzie po kolei (inaczej):
		// iteracje, rozmiar populacji, crossover [0-1], mutation [0-1]
		// ostatnie ustawic na 0 wtedy
		//
		// Boooze jakie to brzydkie :P
		
		params.add(new Float(w));
		params.add(new Float(c1));
		params.add(new Float(c2));
		params.add(new Float(part));
		if(iter != 0)
			params.add(new Float(iter));
		
		ArrayList<DeliveryBoy> boys = new ArrayList<DeliveryBoy>();
		for(int i = 0 ; i < dboys; i++) 
			boys.add(new DeliveryBoy("Boy " + i, cap));
		
		r = al.execute(gm, boys, params);
		
		//paintThisShit(r);
	}
	
	public Result getResult() {
		return r;
	}
	
	@Deprecated
	private void paintThisShit(Result r) {
		// TODO pewnie wywolanie jakiejs statycznej metody na MyPaintListener
		System.out.println("Obliczono");
	}
}
