

public class MetriquePaquet {

	public String chemin = "";
	public String nomPaquet = "";
	public int paquet_LOC = 0;
	public int paquet_CLOC = 0;
	public int wcp = 0; // double or int?
	
	/** Computes and returns the density level of comments in the package
	 * Returns -1 if paquet_LOC is equal to 0*/
	public float paquet_DC() {
		
		if(paquet_LOC == 0) return -1;
		return (float) paquet_CLOC / paquet_LOC;
	}
	
	/** Computes and returns the level of documentation of the package
	 * Returns -1 if wcp is equal to 0*/
	public double paquet_BC() {
		
		if(wcp == 0) return -1;
		return (float) paquet_DC() / wcp;
	}
	
}
