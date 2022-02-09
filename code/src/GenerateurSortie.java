import java.io.*;
import java.util.*;

/** */
public class GenerateurSortie {

	/** Generates a csv file from an ArrayList of MetriqueClasse */
	public static void GenererFichierClasses(ArrayList<MetriqueClasse> infoClasses) {
		
		try {
			PrintWriter writer = new PrintWriter("classes.csv", "UTF-8");
			
			// define the headers for the csv file
			String baseHeaders = "chemin, class, classe_LOC, classe_CLOC, classe_DC";
			String additionalHeaders = ", WMC, classe_BC";
			writer.println( baseHeaders + additionalHeaders );
			
			for (Iterator<MetriqueClasse> i = infoClasses.iterator(); i.hasNext();) {
				MetriqueClasse p = i.next();				

				// For every package, add its data on a new line
				writer.println( p.chemin + ", " + p.nomClass + ", " + p.classe_LOC + ", " + p.classe_CLOC + ", " + p.classe_DC() + ", " + p.wmc + ", " + p.classe_BC());
			}
			
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/** Generates a csv file from an ArrayList of MetriquePaquet */
	public static void GenererFichierPaquets(ArrayList<MetriquePaquet> infoPaquets) {

		try {
			PrintWriter writer = new PrintWriter("paquets.csv", "UTF-8");
			
			// define the headers for the csv file
			String baseHeaders = "chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC";
			String additionalHeaders = ", WCP, paquet_BC";
			
			// add headers to file
			writer.println( baseHeaders + additionalHeaders );
			
			for (Iterator<MetriquePaquet> i = infoPaquets.iterator(); i.hasNext();) {
				MetriquePaquet p = i.next();				

				// For every package, add its data on a new line
				writer.println( p.chemin + ", " + p.nomPaquet + ", " + p.paquet_LOC + ", " + p.paquet_CLOC + ", " + p.paquet_DC() + ", " + p.wcp + ", " + p.paquet_BC());
			}
			
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
