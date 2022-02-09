

public class MetriqueClasse {

	public String chemin = "";
	public String nomClass = "";
	public int classe_LOC = 0;
	public int classe_CLOC = 0;
	public int wmc = 0; // double or int?
	
	/** Computes and returns the density level of comments in the class
	 * Returns -1 if classe_LOC is equal to 0*/
	public float classe_DC() {
		
		if(classe_LOC == 0) return -1;
		return (float) classe_CLOC / classe_LOC;
	}
	
	/** Computes and returns the level of documentation of the class
	 * Returns -1 if wmc is equal to 0*/
	public float classe_BC() {
		
		if(wmc == 0) return -1;
		return (float) classe_DC() / wmc;
	}
}
