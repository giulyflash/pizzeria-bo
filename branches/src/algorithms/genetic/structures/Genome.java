package algorithms.genetic.structures;


public class Genome {
	
	private final int genomLen;
	
	private int[] path;
	
	public Genome(int genomLen) {
		this.genomLen = genomLen;
	}

	public int getEdgesNumber(){
		return getGenomeLength()-1;
	}
	
	public int getGenomeLength(){
		return genomLen;
	}
	
	public TSPEdge getIthPathEdge(int i){
		if(i>=getEdgesNumber()){
			throw new IndexOutOfBoundsException("Cannot access egde #" + i);
		}
		return new TSPEdge(path[i], path[i+1]);
	}

	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		if(path.length!=genomLen){
			throw new RuntimeException("path length does not match genom length");
		}
		this.path = path;
	}
	
	
}
