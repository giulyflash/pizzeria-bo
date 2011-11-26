package algorithms.pso.permutations;

import java.util.List; 
import java.util.ArrayList; 
import java.util.Random; 

public class SetOfTranspositions {


    private class PermutationTransformer {

        public void slide(int[] permutation, int stride){

            int[] copy = permutation.clone();
            for(int i=0;i<permutation.length;i++)
                permutation[i] = copy[(i+stride)%permutation.length];

        }

        public void revert(int[] permutation){
            int swap;
            for(int i = 0; i<permutation.length/2; i++){
                swap = permutation[i];
                permutation[i]=permutation[permutation.length-1-i];
                permutation[permutation.length-i-1] = swap;
            }

        }

              

    }
    
    class Transposition{
    	
    	    public int from; 
    		public int to; 
    		public float r; 
    		public int stride; 
    		public boolean revert; 
    	
    	public Transposition(int from, int to){
    		
    		this.from = from; 
    		this.to = to; 
    		
    		
    		r=1; 
    		
    	}
    	
    }

    private List<Transposition> transpositions;

    public SetOfTranspositions (){
    	transpositions = new ArrayList<Transposition>();
    }
    public SetOfTranspositions (int[] P, Particle X) throws Exception{
        if(P.length != X.getSize())
            throw new Exception("permutacje roznych dlugosci!");
        
         
        transpositions = new ArrayList<Transposition>();
         
        
        //int[] p = P.getPermutation();
        int[] x = X.getPermutation();
        int[] Xcopy;

        List<Transposition> currentTranspositions; 
        int currentBestNumberOfTranspositions = P.length + 1;
        int currentNumberOfTranspositions;
        PermutationTransformer pt = new PermutationTransformer();
        int swap;
        int k;
        
        for(int i=0;i<P.length*2; i++){
        	currentTranspositions = new ArrayList<Transposition>();
            Xcopy = x.clone();
            currentNumberOfTranspositions = 0;
            if(i%2==0) pt.revert(Xcopy);
            pt.slide(Xcopy, i/2);
            
            for(int j=0;j<X.getSize()-1;j++){

                swap = Xcopy[j];
                for(k = j;k<X.getSize();k++){
                    if(Xcopy[k]==P[j])
                        break;
                }
                Xcopy[j]=Xcopy[k];
                Xcopy[k]=swap;
                
                if(k!=j){
                	
                    currentTranspositions.add(new Transposition(k,j));
                    currentNumberOfTranspositions++;

                }



                
            }

            if(currentNumberOfTranspositions < currentBestNumberOfTranspositions){
                currentBestNumberOfTranspositions = currentNumberOfTranspositions;
                	transpositions.clear(); 
                    transpositions=currentTranspositions;
                    for(Transposition t : transpositions){
                    	t.stride = i/2; 
                    	t.revert = (i%2==0); 
                    	
                    }
                     
                
            }  
            else{
            	currentTranspositions.clear(); 
            }
            


        }



        
    }
    
        
    public void multByR(){
    	Random r = new Random(); 
    	List<Transposition> toRemove = new ArrayList<Transposition>();
    	for(Transposition t : transpositions){
    		t.r*=r.nextFloat(); 
    		if(t.r<0.5) toRemove.add(t);
    	}
    	for(Transposition t : toRemove){
    		transpositions.remove(t);
    	}
    }
    
    public void multByC(float c){ 
    	List<Transposition> toRemove = new ArrayList<Transposition>();
    	for(Transposition t : transpositions){
    		t.r*=c; 
    		if(t.r<0.5) toRemove.add(t);
    	}
    	for(Transposition t : toRemove){
    		transpositions.remove(t);
    	}
    }
    
    public void addSetsOfTranspositions(SetOfTranspositions set){
    	transpositions.addAll(set.getTranspositions());
    }
    
    public List<Transposition> getTranspositions(){
    	return transpositions; 
    }
    
    public int getSize(){
    	return transpositions.size(); 
    }

    public void printTranpsositions(){
        
        for(Transposition t : transpositions){
        	System.out.print(t.from);
        	System.out.print(" -> ");
        	System.out.print(t.to);
        	System.out.print(" (");
        	System.out.print(t.stride);
        	System.out.print("  ");
        	System.out.print(t.revert);
        	System.out.print(") ");
        	
        }
        System.out.println();
        
        System.out.println();
    }

}
