package model.graph;
import java.util.Collections;
import java.util.Random; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Iterator;

/* Klasa ktorej obiekty maja za zadanie przeksztalcic  macierz grafu w liste list.
 * Lista ma tyle elementow co dostawcow, a listy list tyle elementow klasy Iteger 
 * ile pizz zostalo przyporzadkowanych do poszczegolnych dostawcow. Na wypadek gdyby
 * dostawcy mieli mniej miejsca niz zostalo przydzielonych pizz rzucany jest wyjatek
 * Klasa przyjmuje macierz pochodzaca z meto getMatrix i redukuje ja o pierwszy wiersz i
 * kolumne (w grupowaniu niepotrzebna jest pizzeria) */
public class Group {
	
	/*
	 * Klasa potrzebna do sortowania krawedzi wzgledem ich dlugosci, wiaze nr wierzcholokow
	 * z odlegloscia
	 */
	
	private class ListObject implements Comparable<ListObject>{
		
		public int dist; 
		public int nr1; 
		public int nr2; 
		public ListObject(int dist, int nr1, int nr2){
			this.nr1 = nr1;
			this.nr2 = nr2; 
			this.dist = dist; 
			
		}
		
		public int compareTo(ListObject lo){
			
			if(dist < lo.dist)
				return -1; 
			if(dist == lo.dist)
				return 0; 
			return 1; 
				
			
		}
		
	
		
	}
	
	
	
	private int maxCapacity; //maksymalna pojemonosc dostawcy 
	private int numOfSuppliers; //ilosc wolnych dostawcow
	private int[][] matrix; //macierz bez pizzeri!
	private int pizza;  //ilosc pizz do dostarczenia
	
	
	
	
	public Group(int[][] matrix, int numOfSuppliers, int maxCapacity){
		
		this.matrix = new int[matrix.length-1][];
		for(int i=0;i<matrix.length-1;i++)
			this.matrix[i] = new int[matrix.length-1];
		for(int i=0;i<matrix.length-1;i++)
			for(int j=0;j<matrix.length-1;j++)
				this.matrix[i][j]=matrix[i+1][j+1];
				
		this.numOfSuppliers = numOfSuppliers; 
		this.maxCapacity = maxCapacity;
		pizza = matrix.length-1;
		
		
	}
	
	//dane losowe do testow
	
	public void prepareData(){
		
		
		Random r = new Random();
		for(int i = 0;i< pizza; i++)
			for(int j = i+1;j<pizza; j++)
				matrix[i][j]=r.nextInt(100)+1;
		
		
	}
	
	//metoda do grupowania
	
	public List<List<Integer>> group() throws Exception{
		
		
		if(pizza>numOfSuppliers*maxCapacity) throw new Exception("dostawcy nie moga zaladowac tyle pizz");
		List<List<Integer>> results;
		results = new ArrayList<List<Integer>>();
		 
		//przypadek gdy kazdy dostawca ma dostarczyc co najwyzej jedna pizze
		
		if(numOfSuppliers >= pizza){
			List<Integer> l; 
			for(int i=0;i<pizza;i++){
				l = new ArrayList<Integer>();
				l.add(i);
				results.add(l);
			}
			return results;
		}
		//przypadek gdy jeden dostawca ma dostarczyc wszystkie pizze
		if(numOfSuppliers==1){
			List<Integer> l; 
			
				l = new ArrayList<Integer>();
				results.add(l);
				for(int i=0;i<pizza;i++)
					l.add(i);
			
			return results;
		}
		
		//wlasciwe grupowania
		/* najpierw wybierane sa punkty - poczatki swoich grup, po jednym punkcie na 
		 * kazdego dostawce. Majac pewien zbior punktow poczatkowych kolejnym punkt poczatkowy
		 * wybieram szukaj wsrod reszty punktow takiego, ktorego najkrotsza odleglosc od 
		 * wszystkich pozostalych punktow poczatkowych jest najwieksza (funkcja pomocnica
		 * maxDistanceVertex). Majac punkty poczatkowe dolaczam reszte przechodzac przez liste
		 * uporzadkowanych rosnaca krawedzi w grafie. Jesli dana odleglosc jest pomiedzy 
		 * punktem poczatkowym i niedodanym do zadnego zbioru, i dodatkowo zbior punktu pocza
		 * tkowego nie jest wiekszy od maxCapacity to punkt zostaje dolaczony */
		
		List<Integer> centres = new ArrayList<Integer>();
		boolean[] added = new boolean[pizza];
		for(int i = 0; i<pizza; i++){
			 
			
			added[i]=false;
			
		}
		List<ListObject> distancesList = new ArrayList<ListObject>();
		for(int i = 0;i< pizza; i++)
			for(int j = i+1;j<pizza; j++)
				distancesList.add(new ListObject(matrix[i][j],i,j));
		Collections.sort(distancesList);
		
		
		
		
		ListObject lo; 
		lo = distancesList.get(distancesList.size()-1);
		centres.add(lo.nr1);
		centres.add(lo.nr2);
		
		added[lo.nr1]=true;
		added[lo.nr2]=true;
		
		
		for(int i=0;i<pizza;i++)
			results.add(new ArrayList<Integer>());
		results.get(lo.nr1).add(lo.nr1);
		results.get(lo.nr2).add(lo.nr2);
		int newVertex; 
		
			
		for(int j=2;j<numOfSuppliers;j++){
			
			newVertex = maxDistanceVertex(centres);
			results.get(newVertex).add(newVertex);
			added[newVertex]=true; 
			centres.add(newVertex);
			
		}
		
		
		
		for(Iterator i = distancesList.iterator(); i.hasNext(); ){
			
			lo = (ListObject)i.next();
			if(!added[lo.nr2] && results.get(lo.nr1).size()<maxCapacity && centres.contains(lo.nr1)){
				added[lo.nr2]=true; 
				results.get(lo.nr1).add(lo.nr2);
				
			}
			
			else if(!added[lo.nr1] && results.get(lo.nr2).size()<maxCapacity && centres.contains(lo.nr2)){
				added[lo.nr1]=true;
				results.get(lo.nr2).add(lo.nr1);
				
			}
			
			 
		}	
		List <Integer> finalList;   
		List<List<Integer>> finalResults = new ArrayList< List<Integer> >();
		for(List<Integer> l : results)
			if(l.size()>0) {
				finalList = new ArrayList<Integer>();
				for(Iterator I = l.iterator();I.hasNext(); )
					  finalList.add((Integer)I.next()+1);
				finalList.add(0);
				finalResults.add(finalList);
				
			}
			
		return finalResults; 
		
		
		
		
		
	}
	//funkcja pomocnicza do grupowania
	public int maxDistanceVertex(List<Integer> v){
		
		int minDistance = 1000000000;
		int maxDistance = -1; 
		int v2=-1;
		int v3=-1; 
		int k = 1;
		int i; 
		int j;
		for(int n = 0; n < matrix.length ;n++){
			
			if(v.contains(n)) continue;
			
			minDistance = 10000000;
			i=n;
			j=n;
			
		while(i - k > -1){
			
			if(v.contains(i-k) && (matrix[i-k][n] < minDistance)){
				minDistance=matrix[i-k][n];
				v2 = n; 
			}
			k++; 
		}
		k = 1;
		while(j + k < matrix.length){
			
			
			if(v.contains(j+k) && (matrix[n][j+k] < minDistance)){
				minDistance=matrix[n][j+k];
				v2 = n; 
			}
			k++; 
		}
		if(minDistance > maxDistance){
			v3=v2; 
			maxDistance = minDistance; 
		}
			
	}
		
		
		return v3; 
		
		
		
	}
	
		
	
	
	
	

}

