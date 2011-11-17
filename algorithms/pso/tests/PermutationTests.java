package algorithms.pso.tests;
import algorithms.pso.permutations.Particle;
import algorithms.pso.permutations.SetOfTranspositions;

public class PermutationTests {
	
	


	    public static void main(String[] args) {

	        Particle p1 = new Particle(6, new int[]{12,2,0,4,7,6});
	        Particle p2 = new Particle(6, new int[]{2,0,6,4,7,12});
	        Particle p3 = new Particle(3,new int[]{0,5,6});
	        Particle p4 = new Particle(3, new int[]{5,6,0});
	        Particle p5 = new Particle(2, new int[]{0,1});
	        Particle p6 = new Particle(2, new int[]{0,1});
	        p1.printPermutation();
	        p2.printPermutation();
	        p3.printPermutation();
	        p4.printPermutation();
	        p5.printPermutation();
	        p6.printPermutation(); 
	        
	        SetOfTranspositions sot1 = null;
	        SetOfTranspositions sot2 = null;
	        SetOfTranspositions sot3 = null;
	        try{
	        sot1 = new SetOfTranspositions(p1, p2);
	        sot2 = new SetOfTranspositions(p3, p4);
	        sot3 = new SetOfTranspositions(p5, p6);
	        
	        }
	        catch(Exception e) {}
	        
	        sot1.printTranpsositions();
	        sot2.printTranpsositions();
	        sot3.printTranpsositions();
	        
	        Particle p7 = new Particle(6, new int[]{12,2,0,4,7,6});
	        Particle p8 = new Particle(6, new int[]{2,0,6,4,7,12});
	        SetOfTranspositions sot4 = null;
	        try{
		        sot4 = new SetOfTranspositions(p7, p8);}

	        catch(Exception e) {}
	        System.out.println("------------");
	        p7.printPermutation();
	        p8.printPermutation(); 
	        sot4.printTranpsositions();
	        p8.permute(sot4);
	        System.out.println("------------");
	        p7.printPermutation();
	        p8.printPermutation();
	        SetOfTranspositions sot5 = null;
	        try{
		        sot5 = new SetOfTranspositions(p7, p8);}

	        catch(Exception e) {}
	        sot5.printTranpsositions();

	    }

	

}
