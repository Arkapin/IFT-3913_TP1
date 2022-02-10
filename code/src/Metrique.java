
/** Object to hold information regarding a class/package metrics */
public class Metrique {

	public String path = "";
	public String name = "";
	public int LOC = 0;
	public int CLOC = 0;
	public int weighted = 0; // double or int?
	
	/** Computes and returns the density level of comments in the package
	 * Returns -1 if LOC is equal to 0*/
	public float getDC() {
		
		return LOC != 0 ? (float) CLOC / LOC : -1;
	}

	/** Computes and returns the level of documentation of the package
	 * Returns -1 if wcp is equal to 0*/
	public double getBC() {
		
		return weighted != 0 ? (float) getDC() / weighted : -1;
	}
	

	/** Constructor */
	public Metrique() {}
	
	/** Constructor */
	public Metrique(String path, String name, int loc, int cloc, int weighted) {
		this.path = path;
		this.name = name;
		this.LOC = loc;
		this.CLOC = cloc;
		this.weighted = weighted;
	}
}
