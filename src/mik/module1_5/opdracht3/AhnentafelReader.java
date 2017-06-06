package mik.module1_5.opdracht3;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Constructs Ahnentafel objects from file. 
 * @author Rosan Prins(10964711) & Laura Keemink(10912797)*/
public class AhnentafelReader
{   
    /**
     * Reads an ahnentafel from file and constructs de corresponding Ahnentafel object
     * @param filename de filenaam
     * @return de tree
     * @throws IOException 
     */
    public static Ahnentafel fileRead(String filename) throws IOException
    {                
        Ahnentafel newTree = new Ahnentafel();
        // TODO Opdracht 3c
        FileReader reader = new FileReader(filename);
        Scanner scanner = new Scanner(reader);
        //regex voor de regels
        String patternString1 = "([A-Za-z\\-\\s]+)\\s+@\\s([MF])\\s+([A-Za-z\\-\\s]+)";
        Pattern pattern = Pattern.compile(patternString1);
        //doorloopt de file
        while(scanner.hasNextLine())
        {
            String regel = scanner.nextLine();
            Matcher matcher = pattern.matcher(regel);
            //controleert of de file regels overeenkomen met de regex 
            if(matcher.matches())
            {
                String naam = matcher.group(1).trim();
                char geslacht = matcher.group(2).charAt(0);
                String voorouder = matcher.group(3).trim();
                Person persoon = new Person(naam, geslacht);
                //voegt een persoon toe aan de tree
                newTree.addParent(persoon, voorouder);
            }          
        }
        //
        return newTree;        
    }
}