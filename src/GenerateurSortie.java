import java.io.*;
import java.util.*;

/** Handles the program outputs */
public class GenerateurSortie {
	private static String encodage = "UTF-8";
	private static String[] valeursClasse = new String[] {"classes.csv", "chemin, class, classe_LOC, classe_CLOC, classe_DC, WMC, classe_BC"};
	private static String[] valeursPaquet = new String[] {"paquets.csv", "chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC, WCP, paquet_BC"};
	
	/** Generates a csv file from an ArrayList of Metrique objects, need to specify if class, if not, its considered to be a package */
	public static void GenererFichier(ArrayList<Metrique> metriques, boolean isClass) {

		try {
			String fileName = (isClass ? valeursClasse : valeursPaquet)[0];
			PrintWriter writer = new PrintWriter(fileName, encodage);
			
			// define the headers for the csv file
			String headers = (isClass ? valeursClasse : valeursPaquet)[1];
			
			// add headers to file
			writer.println(headers);
			
			for (Iterator<Metrique> i = metriques.iterator(); i.hasNext();) {
				Metrique p = i.next();

				// For every package, add its data on a new line
				writer.println( p.path + ", " + p.name + ", " + p.LOC + ", " + p.CLOC + ", " + p.getDC() + ", " + p.weighted + ", " + p.getBC());
			}
			
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
