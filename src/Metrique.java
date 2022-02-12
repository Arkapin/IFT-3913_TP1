
/**
 * Object to hold information regarding a class/package metrics
 * */
public class Metrique {

	public String path = "";
	public String name = "";
	public int LOC = 0;
	public int CLOC = 0;
	public int weighted = 0; // double or int?
	
	/**
	 * Computes and returns the density level of comments in the package
	 * @return -1 if LOC is equal to 0, otherwise, DC value
	 * */
	public float getDC() {
		
		return LOC != 0 ? (float) CLOC / LOC : -1;
	}

	/**
	 * Computes and returns the level of documentation of the package
	 * @return -1 if wcp is equal to 0, otherwise, BC value
	 * */
	public double getBC() {
		
		return weighted != 0 ? (float) getDC() / weighted : -1;
	}
	

	/**
	 * Constructor
	 * */
	public Metrique() {}
	
	/**
	 * Constructor
     * @param path {@link String} containing the path of the class/package
     * @param name {@link String} of the name of the class/package
     * @param loc {@link Integer} containing the number of lines of code of the class/package
     * @param cloc {@link Integer} containing the number of lines of code and lines of comments of the class/package
     * @param weight {@link Integer} containing the weight of the class/package
	 * */
	public Metrique(String path, String name, int loc, int cloc, int weighted) {
		this.path = path;
		this.name = name;
		this.LOC = loc;
		this.CLOC = cloc;
		this.weighted = weighted;
	}
}
