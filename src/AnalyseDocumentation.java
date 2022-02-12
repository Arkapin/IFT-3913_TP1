import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/** 
 * This class provides tools used to compute metrics on Java programs
 * @author Guillaume Gagnon  20191696
 * @author Pierre-Paul Hamon 20160518
 * */
public class AnalyseDocumentation {

	/**
	 * {@link java.util.ArrayList} contains {@link Metrique} objects for every class parsed
	 * */
	private static ArrayList<Metrique> infoClasses = new ArrayList<Metrique>();

	/**
	 * {@link java.util.ArrayList} contains {@link Metrique} objects for every package parsed
	 * */
	private static ArrayList<Metrique> infoPaquets = new ArrayList<Metrique>();
	
	/**
	 * Entry point of the program, asks the user for the path, and then start the analysis on the code. Outputs the metrics in csv files.
	 * @param args The command line arguments.
	 * @throws java.io.IOException if we can't read/write a file
	 **/
    public static void main(String[] args) throws IOException {
        System.out.println("Give the path towards a folder that contains Java code");
    	PathInfo pInfo = getPath();

        if(pInfo != null)
        {
            if(pInfo.isFile())
                parseClass(pInfo.getPath());
            else if(pInfo.isDirectory()) {
                File f = new File(pInfo.getPath());
                searchDirectory(f);
            }
    	}
        else {
            System.out.println("No valid path was given");
            return;
        }

    	
    	// Output
    	if(!infoClasses.isEmpty())
            GenerateurSortie.GenererFichier(infoClasses, true);
    	if(!infoPaquets.isEmpty())
            GenerateurSortie.GenererFichier(infoPaquets, false);

        System.out.println("The analysis of the folder is complete");

    }
    
    /** 
     * Prompts user to select a file or directory to analyze
     * @return a {@link PathInfo PathInfo} object containing the path selected by the user, or null if operation was canceled 
     * */
    public static PathInfo getPath() {
    	
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java files", "java");
        
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Choisir le fichier/dossier à analyser");
        
        return chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ? new PathInfo(chooser.getSelectedFile()) : null;
    }

    /** 
     * Recursively searches a file's directory to find other java files in sub-folders
     * @param f {@link java.io.File} from which the search will start
	 * @throws java.io.IOException if we can't read/write a file
     * */
    public static void searchDirectory(File f) throws IOException {
        File[] directory = f.listFiles();
        assert directory != null;
        for (File file: directory)
        {
            if(file.isDirectory()) {
                searchDirectory(file);
            }
            else {
            	parseClass(file.getAbsolutePath());
            }
        }
    }
    
    /** 
     * Parses a class file and computes metrics on it. Updates global list of metrics for classes
     * @param path {@link String} containing the path of the class
	 * @throws java.io.IOException if we can't read/write a file
     * */
    public static void parseClass(String path) throws IOException {

        if(!path.endsWith(".java")) return;

        int loc = 0, cloc = 0, weight = -1;
        boolean commentBlock = false, commentLine;
        File javaFile = new File(path);
        String name = javaFile.getName();

        String[] classPackage=null;
        String packagePath;

        Scanner reader = new Scanner(javaFile);
        String line;

        while(reader.hasNextLine()) {
            commentLine = false;
            line = reader.nextLine();
            if(line.trim().equals("")) continue;

            if(line.startsWith("package")) {
                packagePath = line.substring(7).trim();
                classPackage = packagePath.split("\\.");
            }

            if (line.contains("//")) commentLine = true;
            if (line.contains("/*")) commentBlock = true;
            if (commentBlock) commentLine = true;
            if (line.contains("*/")) commentBlock = false;

            if(line.contains("{")&&!commentBlock) weight++;

            loc++;
            if(commentLine) cloc++;
        }

        reader.close();

        if(weight<=0) weight=1;

    	infoClasses.add(new Metrique(path, name, loc, cloc, weight));

        if(classPackage!=null) parsePackage(path, classPackage, loc, cloc, weight);

    }
    
    /** 
     * Parses a package and computes metrics on it. Updates global list of metrics for packages
     * @param path {@link String} containing the path of the class
     * @param classPackage {@link String String[]} containing the classes of the package
     * @param loc {@link Integer} containing the number of lines of code of the package so far
     * @param cloc {@link Integer} containing the number of lines of code and lines of comments of the package so far
     * @param weight {@link Integer} containing the weight of the package
     * */
    public static void parsePackage(String path, String[] classPackage, int loc, int cloc, int weight)
    {
        String packagePath;
        int index;
        Metrique pack;
        for(String packName: classPackage) {
            index = path.lastIndexOf(packName);
            packagePath = path.substring(0, index + packName.length());
            pack = null;

            for(int i = 0; i < infoPaquets.size(); i++)
            {
                if(infoPaquets.get(i).path.equals(packagePath))
                {
                    pack = infoPaquets.get(i);
                    pack.LOC += loc;
                    pack.CLOC += cloc;
                    pack.weighted += weight;
                    infoPaquets.set(i,pack);
                }
            }
            if(pack == null)
            {
                pack = new Metrique(packagePath, packName, loc, cloc, weight);
                infoPaquets.add(pack);
            }
        }
    }
}
