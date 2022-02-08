import java.io.*;
import java.util.Scanner;

public class AnalyseDocumentation {

    public static void main(String[] args) throws IOException {

        Scanner consoleReader = new Scanner(System.in);
        System.out.println("Veuillez donner le chemin d'accès d'un dossier qui contient du code Java:");
        String path = consoleReader.nextLine();
        consoleReader.close();
        ParseClass(path);

    }

    public static void ParseClass(String path) throws IOException {

        int loc=0,
                cloc=0;
        float dc=0;
        boolean commentBlock=false,
                commentLine=false;
        File javaFile = new File(path);
        Scanner reader = new Scanner(javaFile);
        String line;

        while(reader.hasNextLine()) {
            line=reader.nextLine();
            System.out.println(line);
            if(line.equals("\n")) continue;

            if (line.contains("//")) commentLine = true;
            if (line.contains("/*") && !commentBlock) commentBlock = true;
            if (commentBlock) commentLine = true;
            if (line.contains("*/") && commentBlock) commentBlock = false;

            loc++;
            if(commentLine) cloc++;
        }

        reader.close();
        System.out.println(loc);

    }

}
