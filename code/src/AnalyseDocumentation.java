import java.io.*;
import java.util.Scanner;

public class AnalyseDocumentation {

    public static void main(String[] args) throws IOException {

        Scanner consoleReader = new Scanner(System.in);
        System.out.println("Veuillez donner le chemin d'accès d'un dossier qui contient du code Java:");
        String path = consoleReader.nextLine();
        consoleReader.close();
        
        testTemporaire();
        
        ParseClass(path);

    }

    public static void ParseClass(String path) throws IOException {

        int loc=0,
                cloc=0;
        float dc;
        boolean commentBlock=false,
                commentLine;
        File javaFile = new File(path);
        Scanner reader = new Scanner(javaFile);
        String line;

        while(reader.hasNextLine()) {
            commentLine = false;
            line=reader.nextLine();
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
        System.out.println(loc);
        System.out.println(cloc);
        System.out.println(dc);

    }
    
    private static void testTemporaire() {
    	java.util.ArrayList<MetriqueClasse> classes = new java.util.ArrayList<MetriqueClasse>();

    	classes.add(new MetriqueClasse() {{chemin = "testPath1"; nomClass = "testNom1"; classe_LOC = 10; classe_CLOC = 2; wmc = 1;}});
    	classes.add(new MetriqueClasse() {{chemin = "testPath2"; nomClass = "testNom2"; classe_LOC = 10; classe_CLOC = 4; wmc = -1;}});
    	classes.add(new MetriqueClasse() {{chemin = "testPath3"; nomClass = "testNom3"; classe_LOC = 10; classe_CLOC = 6; wmc = 2;}});
    	classes.add(new MetriqueClasse() {{chemin = "testPath4"; nomClass = "testNom4"; classe_LOC = 10; classe_CLOC = 8; wmc = -2;}});
    	
    	GenerateurSortie.GenererFichierClasses(classes);
    }

}
