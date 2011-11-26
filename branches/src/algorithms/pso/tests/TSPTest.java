package algorithms.pso.tests;
import model.graph.Group; 
import algorithms.pso.PSOAlgorithm;
import java.util.List;
import java.util.ArrayList;
import model.pizzeria.Result;

public class TSPTest {
	
	public static void main(String[] args){
	
	double[][] matrix = {
					{0,2.8,16,2.0,6.7},
					{2.8,0,11.3,5.5,3.2},
					{16,11.3,0,9.4,3.6},
					{2.0,5.5,9.4,0,5.1},
					{6.7,3.2,3.6,5.1,0}
					
		};
	
	Group g = new Group(matrix,1,6);
	
	List<List<Integer>> lista = g.group();
	System.out.println(lista.get(0).size());
	PSOAlgorithm x = new PSOAlgorithm();
	Object r1 = x.solveTSP(matrix,lista.get(0));
	System.out.println();
	//Object r2 = x.solveTSP(matrix,lista.get(1));
	
	
	}

}
