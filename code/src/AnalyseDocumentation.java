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

}
