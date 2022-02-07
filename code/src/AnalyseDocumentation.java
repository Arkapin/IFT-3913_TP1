public class AnalyseDocumentation {

    public static void main(String[] args) {

        System.out.println("Veuillez donner le chemin d'accès d'un dossier qui contient du code Java:");

    }

    public void ParseLine(String path) {

        int loc=0,
                cloc=0;
        float dc=0;
        boolean commentBlock=false,
                commentLine=false;
        String line="\n";

        if(line.contains("//")) commentLine=true;
        if (line.contains("/*") && !commentBlock) commentBlock = true;
        if (commentBlock) commentLine=true;
        if (line.contains("*/") && commentBlock) commentBlock = false;

        if(commentLine) cloc++;

        dc = cloc/loc;
    }

}
