import java.io.*;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AnalyseDocumentation {
    
    //TEST
	private static java.util.ArrayList<Metrique> infoClasses = new java.util.ArrayList<Metrique>();
    //TEST

    public static void main(String[] args) throws IOException {
    	getPath();
        Scanner consoleReader = new Scanner(System.in);
        System.out.println("Veuillez donner le chemin d'accès d'un dossier qui contient du code Java:");
        String path = consoleReader.nextLine();
        consoleReader.close();
        
        testTemporaire();
        
        ParseClass(path);

    }
    
    public static String getPath() {
    	
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Java & text files", "java", "txt");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	if(chooser.getSelectedFile().isFile())
        		System.out.println("You chose to open this file : " + chooser.getSelectedFile().getName());
        	if(chooser.getSelectedFile().isDirectory())
        		System.out.println("isDirectory : " + chooser.getSelectedFile().isDirectory());
        	
            System.out.println("Path : " + chooser.getSelectedFile().getAbsolutePath());
        }
    	
    	return null;
    }

    public static void ParseClass(String path) throws IOException {

        int loc = 0, cloc = 0;
        float dc;
        boolean commentBlock=false,
                commentLine;
        File javaFile = new File(path);
        Scanner reader = new Scanner(javaFile);
        String line;

        while(reader.hasNextLine()) {
            commentLine = false;
            line = reader.nextLine();
            System.out.println(line);
            if(line.equals("\n")) continue;

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
    	infoClasses.add(new Metrique(path, path, loc, cloc, -1));    	
        GenerateurSortie.GenererFichierClasses(infoClasses);
        // TEST
        
        System.out.println(loc);
        System.out.println(cloc);
        System.out.println(dc);

    }
    
    private static void testTemporaire() {
    	java.util.ArrayList<Metrique> classes = new java.util.ArrayList<Metrique>();

    	classes.add(new Metrique() {{path = "testPath1"; name = "testNom1"; LOC = 10; CLOC = 2; weighted = 1;}});
    	classes.add(new Metrique() {{path = "testPath2"; name = "testNom2"; LOC = 10; CLOC = 4; weighted = -1;}});
    	classes.add(new Metrique() {{path = "testPath3"; name = "testNom3"; LOC = 10; CLOC = 6; weighted = 2;}});
    	classes.add(new Metrique() {{path = "testPath4"; name = "testNom4"; LOC = 10; CLOC = 8; weighted = -2;}});
    	
    	GenerateurSortie.GenererFichierClasses(classes);
    }

}
