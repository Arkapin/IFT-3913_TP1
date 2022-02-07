public class AnalyseDocumentation {

    public static void main(String[] args) {

        System.out.println("Veuillez donner le chemin d'accès d'un dossier qui contient du code Java:");

    }

    public void ParseLine(String line) {

        int loc=0,
                cloc=0;
        float dc=0;
        if(line.contains("//")) loc++; cloc++;
        if(line.contains("/*")&&line.contains("*/")) loc++; cloc++;

        dc = cloc/loc;
    }

}
