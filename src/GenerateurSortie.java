import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

/**
 * Handles the program outputs
 * */
public class GenerateurSortie {
	
	/**
	 * Encoding used for the output csv files
	 * */
	private static String encodage = "UTF-8";
	
	/**
	 * Array containing info used in the generation of the classes.csv file
	 * */
	private static String[] valeursClasse = new String[] {"classes.csv", "chemin, class, classe_LOC, classe_CLOC, classe_DC, WMC, classe_BC"};
	
	/**
	 * Array containing info used in the generation of the paquets.csv file
	 * */
	private static String[] valeursPaquet = new String[] {"paquets.csv", "chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC, WCP, paquet_BC"};
	
	/**
	 * {@link Integer} used to fetch the file name from the array
	 * */
	private static int csvName = 0;

	/**
	 * {@link Integer} used to fetch the file headers from the array
	 * */
	private static int csvHeaders = 1;
	
	/**
	 * Generates a csv file from an ArrayList of Metrique objects, need to specify if class, if not, its considered to be a package
	 * @param metriques {@link ArrayList} of {@link Metrique} objects to use for the csv
	 * @param isClass {@link Boolean} indicating whether to generate a class csv or a package csv
	 * */
	public static void GenererFichier(ArrayList<Metrique> metriques, boolean isClass) {

		try {
			PathInfo pInfo = getPath(isClass);
			String path = "";
			
			if(pInfo != null)
				path = pInfo.getPath();
			String fileName = path + "\\" + (isClass ? valeursClasse : valeursPaquet)[csvName];
			PrintWriter writer = new PrintWriter(fileName, encodage);
			
			// define the headers for the csv file
			String headers = (isClass ? valeursClasse : valeursPaquet)[csvHeaders];
			
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
    
    /** 
     * Prompts user to select a directory where to save the csv to
     * @return a {@link PathInfo PathInfo} object containing the path selected by the user, or null if operation was canceled
	 * @param isClass {@link Boolean} indicating whether to generate a class csv or a package csv
     * */
    private static PathInfo getPath(boolean isClass) {
    	
    	JFileChooser chooser = new JFileChooser();
        
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle((isClass ? valeursClasse : valeursPaquet)[csvName]);
        chooser.setApproveButtonText("Save");
        return chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ? new PathInfo(chooser.getSelectedFile()) : null;
    }
	
}
