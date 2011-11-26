package algorithms.pso;

import java.util.List;
import java.util.ArrayList; 

import model.graph.Group; 
import model.graph.GraphMatrix;
import model.pizzeria.Algorithm; 
import model.pizzeria.DeliveryBoy;
import model.pizzeria.Result;
import algorithms.pso.permutations.*; 

public class PSOAlgorithm implements Algorithm {
	
	int numberOfParticles=2; 
	int numberOfIterations=1; 
	float inertia=1.0f; 
	float c1=1.0f;
	float c2=1.0f; 
	int numberOfPizzas; 
	
	//klasa zwracajaca wynik dla jednego dostawcy
	
	private class SimpleResult{
		public List<Integer> route = new ArrayList<Integer>(); 
		public List<Double> costs = new ArrayList<Double>(); 
		
	}

	
	public Result execute(GraphMatrix graphMatrix, List<DeliveryBoy> availableDeliveryBoys, List<Float> parameters){
		
		numberOfParticles=(int)(float)parameters.get(3);
		numberOfIterations=(int)(float)parameters.get(4);
		inertia = parameters.get(0);
		c1 = parameters.get(1);
		c2 = parameters.get(2);
		numberOfPizzas = graphMatrix.getMatrix().length; 
		
		
		
		
		return new Result();
	}
	
	//funkcja zwaracajaca koszt przejscia po grafie wedlug permutacji wierzcholkow z czastki
	
		private double distance(double[][] matrix, Particle p){
			
			int[] permutation = p.getPermutation();
			double distance=0; 
			int size = p.getSize(); 
			for(int i=0;i<size-1;i++)
				distance+=matrix[permutation[i]][permutation[i+1]];
			
			return distance+matrix[permutation[size-1]][permutation[0]];
				
			
		}
	
	
	//metoda rozwiazujaca problem dla jednego dostawcy
	
	public SimpleResult solveTSP(double[][] matrix, List<Integer> numbersOfVertices){
		
		List<Particle> Particles = new ArrayList<Particle>();
		SimpleResult result = new SimpleResult(); 
		
		int[] startPermutation = new int[numbersOfVertices.size()];
		for(int i=0;i<numbersOfVertices.size();i++)
			startPermutation[i] = numbersOfVertices.get(i);
		
		Particle swarmLeader = new Particle(numbersOfVertices.size(),startPermutation); 
		double swarmLeaderDistance = distance(matrix, swarmLeader);
		swarmLeader.setBestPermutationDistance(swarmLeaderDistance);
		Particles.add(swarmLeader);
		
		Particle p; 
		double pDistance; 
		
		//stworzenie roju
		
		for(int i = 1; i<numberOfParticles ; i++){
			
			p = new Particle(numbersOfVertices.size(),startPermutation);
			
			pDistance = distance(matrix,p);
			p.setBestPermutationDistance(pDistance);
			if(pDistance < swarmLeaderDistance){
				swarmLeader = p; 
				swarmLeaderDistance = pDistance; 
				
			}
			Particles.add(p);
				
			
		}
		
		SetOfTranspositions lastSpeed = null; 
		SetOfTranspositions piSpeed = null; 
		SetOfTranspositions pgSpeed = null; 
		Particle newSwarmLeader=null;
		double newSwarmLeaderDistance=swarmLeaderDistance;
		double newDistance; 
		
		
		for(int i = 0; i<numberOfIterations ; i++){
			
			for(Particle P : Particles){
				
				lastSpeed = P.getSpeed();
				lastSpeed.multByC(inertia);
				try{
					piSpeed = new SetOfTranspositions(P.getBestPermutation(),P);
					pgSpeed = new SetOfTranspositions(swarmLeader.getBestPermutation(),P);
					
				}
				catch(Exception e) {}
				
				piSpeed.multByR(); 
				piSpeed.multByC(c1);
				pgSpeed.multByR();
				pgSpeed.multByC(c2);
				
				lastSpeed.addSetsOfTranspositions(piSpeed);
				lastSpeed.addSetsOfTranspositions(pgSpeed);
				
				
				P.permute(lastSpeed);
				 
				newDistance = distance(matrix, P);
				System.out.println("iter " + i + " " + newDistance);
				if(newDistance < P.getBestPermutationDistance()){
					P.setBestPermutationDistance(newDistance);
					P.setBestPermutation(P.getPermutation().clone());
				}
				
				if(P.getBestPermutationDistance()<=newSwarmLeaderDistance){
					newSwarmLeader = P; 
					newSwarmLeaderDistance = P.getBestPermutationDistance(); 
				}
				
				
				
				
			}
			swarmLeader = newSwarmLeader; 
			
			result.costs.add(newSwarmLeaderDistance);
			System.out.println("best " + newSwarmLeaderDistance);

		}
		
		result.route=new ArrayList<Integer>();
		
		int[] best = swarmLeader.getBestPermutation();
		int[] reorderedBest = new int[best.length]; 
		int i = 0; 
		while(best[i]!=0) i++;
		for(int j=0; j<best.length;j++){
			reorderedBest[j]=best[(j+i)%best.length];
		}
		
		for(i=0;i<best.length;i++){
			result.route.add(reorderedBest[i]);
			System.out.print(reorderedBest[i] + " ");
		}
		
		
		
		
		return new SimpleResult(); 
	}
	
	
}
