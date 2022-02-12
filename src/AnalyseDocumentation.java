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
        System.out.println("Give the path towards a folder that contains Java code");
    	PathInfo pInfo = getPath();

        if(pInfo!=null)
        {
            if(pInfo.isFile())
                ParseClass(pInfo.getPath());
            else if(pInfo.isDirectory()) {
                // TODO Add logic to handle recursive search of files
                File f = new File(pInfo.getPath());
                SearchDirectory(f);
            }
    	}
        else System.out.println("No valid path was given");

    	
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

    public static void SearchDirectory(File f) throws IOException {
        File[] directory = f.listFiles();
        assert directory != null;
        for (File file: directory)
        {
            if(file.isDirectory()) {
                SearchDirectory(file);
            }
            else {
                ParseClass(file.getAbsolutePath());
            }
        }
    }

    public static void ParseClass(String path) throws IOException {

        if(!path.endsWith(".java")) return;

        int loc = 0, cloc = 0, weight=-1;
        boolean commentBlock=false,
                commentLine;
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
                packagePath=line.substring(7).trim();
                classPackage = packagePath.split("\\.");
            }

            if (line.contains("//")) commentLine = true;
            if (line.contains("/*")) commentBlock = true;
            if (commentBlock) commentLine = true;
            if (line.contains("*/")) commentBlock = false;

            if(line.contains("{")) weight++;

            loc++;
            if(commentLine) cloc++;
        }

        reader.close();        

        // TEST
    	infoClasses.add(new Metrique(path, name, loc, cloc, weight));
        // TEST

        if(classPackage!=null) ParsePackage(path,classPackage,loc,cloc,weight);

    }

    public static void ParsePackage(String path, String[] classPackage, int loc, int cloc, int weight)
    {
        String packagePath;
        int index;
        Metrique pack;
        for(String packName: classPackage) {
            index = path.lastIndexOf(packName);
            packagePath = path.substring(0,index+packName.length());
            pack = null;

            for(int i=0;i < infoPaquets.size(); i++)
            {
                if(infoPaquets.get(i).path.equals(packagePath))
                {
                    pack = infoPaquets.get(i);
                    pack.LOC+=loc;
                    pack.CLOC+=cloc;
                    pack.weighted+=weight;
                    infoPaquets.set(i,pack);
                }
            }
            if(pack==null)
            {
                pack = new Metrique(packagePath, packName, loc, cloc, weight);
                infoPaquets.add(pack);
            }
        }
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
