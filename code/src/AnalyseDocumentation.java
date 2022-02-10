import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AnalyseDocumentation {
    
    //TEST
	private static ArrayList<Metrique> infoClasses = new ArrayList<Metrique>();
	private static ArrayList<Metrique> infoPaquets = new ArrayList<Metrique>();
    //TEST

    public static void main(String[] args) throws IOException {
    	PathInfo pInfo = getPath();

    	if(pInfo.isFile())
    		ParseClass(pInfo.getPath());
    	else if(pInfo.isDirectory()) {
    		// TODO Add logic to handle recursive search of files
    	}

    	
    	// Output
    	if(!infoClasses.isEmpty())
            GenerateurSortie.GenererFichier(infoClasses, true);
    	if(!infoPaquets.isEmpty())
            GenerateurSortie.GenererFichier(infoPaquets, false);

//      Scanner consoleReader = new Scanner(System.in);
//      System.out.println("Veuillez donner le chemin d'accès d'un dossier qui contient du code Java:");
//      String path = consoleReader.nextLine();
//      consoleReader.close();
        
//      testTemporaire();

//      TEST
//      if(path!=null) ParseClass(path);
//      TEST

    }
    
    /** Returns the path selected by the user, or null if operation was canceled */
    public static PathInfo getPath() {
    	
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java & text files", "java", "txt");
        
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(filter);
        
        return chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ? new PathInfo(chooser.getSelectedFile()) : null;
    }

    public static void ParseClass(String path) throws IOException {

        int loc = 0, cloc = 0;
        float dc;
        boolean commentBlock=false,
                commentLine;
        File javaFile = new File(path);
        String name = javaFile.getName();
        System.out.println(javaFile.getPath());

        String[] classPackage=null;
        String packagePath;

        Scanner reader = new Scanner(javaFile);
        String line;

        while(reader.hasNextLine()) {
            commentLine = false;
            line = reader.nextLine();
            System.out.println(line);
            if(line.trim().equals("")) continue;

            if(line.startsWith("package")) {
                System.out.println("package trouvé");
                packagePath=line.substring(7).trim();
                classPackage = packagePath.split("\\.");
                packagePath=packagePath.replace('.','\\');
                System.out.println(packagePath);
                for(String pack: classPackage) {

                }
            }

            if (line.contains("//")) commentLine = true;
            if (line.contains("/*")) commentBlock = true;
            if (commentBlock) commentLine = true;
            if (line.contains("*/")) commentBlock = false;

            loc++;
            if(commentLine) cloc++;
        }

        dc = (float) cloc/loc;

        reader.close();        

        // TEST
    	infoClasses.add(new Metrique(path, name, loc, cloc, -1));
        // TEST
        
        System.out.println(loc);
        System.out.println(cloc);
        System.out.println(dc);

        if(classPackage!=null) ParsePackage(path,classPackage,loc,cloc,-1);

    }

    public static void ParsePackage(String path, String[] classPackage, int loc, int doc, int weight)
    {

    }
    
//    private static void testTemporaire() {
//    	ArrayList<Metrique> classes = new ArrayList<Metrique>();
//
//    	classes.add(new Metrique() {{path = "testPath1"; name = "testNom1"; LOC = 10; CLOC = 2; weighted = 1;}});
//    	classes.add(new Metrique() {{path = "testPath2"; name = "testNom2"; LOC = 10; CLOC = 4; weighted = -1;}});
//    	classes.add(new Metrique() {{path = "testPath3"; name = "testNom3"; LOC = 10; CLOC = 6; weighted = 2;}});
//    	classes.add(new Metrique() {{path = "testPath4"; name = "testNom4"; LOC = 10; CLOC = 8; weighted = -2;}});
//    	
//    	GenerateurSortie.GenererFichier(classes, true);
//    }

}
