package algorithms.pso.permutations;

import java.util.Random;
import algorithms.pso.permutations.SetOfTranspositions.Transposition; 
import algorithms.pso.permutations.SetOfTranspositions; 

public class Particle {

    private int[] permutationOfVertices;
    private int size;

    public Particle(int size, int[] numbersOfVertices){

       permutationOfVertices = new int[size];
       int[] permutation;
       permutation = new int[size];
       this.size = size;
       boolean[] b = new boolean[size];
       for(int i = 0; i< size; i++)
           b[i] = true;
       Random r = new Random();
       int n1;
       int n2;
       for(int i=0; i< size; i++){
           n1 = r.nextInt(size);
           n2 = n1;
           
           while(true){
               if(b[n1])
                   break;
               if(b[n2]){
                   n1 = n2;
                   break;
               }

               n1--;
               n2++;
               n2%=size;
               if(n1 < 0) n1+=size;
               }
           b[n1] = false;
           //n1++;
           permutation[i]=n1;
       }
       for(int i=0;i<size;i++)
    	   permutationOfVertices[i]=numbersOfVertices[permutation[i]];



    }

    public int[] getPermutation(){
        return permutationOfVertices.clone();
    }

    public int getSize(){
        return size;
    }

    public void printPermutation(){
        for(int i = 0 ; i< size; i++){
            System.out.print(permutationOfVertices[i]);
            System.out.print(" ");
        }
        System.out.println();
    }
    
    public void permute(SetOfTranspositions set){
    	int swap;
    	int i;
    	int j;
    	
    	
    	for(Transposition t : set.getTranspositions()){
    		i = t.from +t.stride;
    		if (i>size-1) i-=size;
    		j = t.to + t.stride;
    		if (j>size-1) j-=size; 
    		//i = (t.from + t.stride)%size; 
    		//j = (t.to + t.stride)%size;
    	
    		if(t.revert){
    			i=size-i-1;
    			j=size-j-1;
    		}
    		
    		swap = permutationOfVertices[i];
    		permutationOfVertices[i] = permutationOfVertices[j];
    		permutationOfVertices[j]=swap; 
    		
    	}
    
    }

}
