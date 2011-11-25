package algorithms.genetic.structures;


public class Genome {
	
	private final int genomLen;
	
	private int[] path;
	
	public Genome(int genomLen) {
		this.genomLen = genomLen;
	}
	
	public Genome(int[] path) {
		this.genomLen = path.length;
		this.path=path;
	}

	public int getGenomeLength(){
		return genomLen;
	}
	
	public TSPEdge getIthPathEdge(int i){
		if(i>getGenomeLength() || i<0){
			throw new IndexOutOfBoundsException("Cannot access egde #" + i);
		}
		return new TSPEdge(path[i], path[(i+1) % genomLen]);
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
	
	@Override
	public Genome clone(){
		try {
			return (Genome)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
