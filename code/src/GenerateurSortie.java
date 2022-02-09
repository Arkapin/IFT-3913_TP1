import java.io.*;
import java.util.*;

/** */
public class GenerateurSortie {

	/** Generates a csv file from an ArrayList of MetriqueClasse */
	public static void GenererFichierClasses(ArrayList<Metrique> infoClasses) {
		
		try {
			PrintWriter writer = new PrintWriter("classes.csv", "UTF-8");
			
			// define the headers for the csv file
			String baseHeaders = "chemin, class, classe_LOC, classe_CLOC, classe_DC";
			String additionalHeaders = ", WMC, classe_BC";
			writer.println( baseHeaders + additionalHeaders );
			
			for (Iterator<Metrique> i = infoClasses.iterator(); i.hasNext();) {
				Metrique c = i.next();				

				// For every package, add its data on a new line
				writer.println( c.path + ", " + c.name + ", " + c.LOC + ", " + c.CLOC + ", " + c.getDC() + ", " + c.weighted+ ", " + c.getBC());
			}
			
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/** Generates a csv file from an ArrayList of MetriquePaquet */
	public static void GenererFichierPaquets(ArrayList<Metrique> infoPaquets) {

		try {
			PrintWriter writer = new PrintWriter("paquets.csv", "UTF-8");
			
			// define the headers for the csv file
			String baseHeaders = "chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC";
			String additionalHeaders = ", WCP, paquet_BC";
			
			// add headers to file
			writer.println( baseHeaders + additionalHeaders );
			
			for (Iterator<Metrique> i = infoPaquets.iterator(); i.hasNext();) {
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
