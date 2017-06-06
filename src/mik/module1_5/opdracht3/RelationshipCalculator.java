package mik.module1_5.opdracht3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import weiss.BinaryNode;

/** Calculates the coefficient of relationship 
    @author Rosan Prins(10964711) & Laura Keemink(10912797) */
class RelationshipCalculator
{
    private Map<String, BinaryNode<Person>> persons;
            
    /** Creates a new RelationshipCalculator      
     * @param thePersons  persons from an ahnentafel whose proband will be the 
     * first subject of the calculation */
    RelationshipCalculator(Map<String, BinaryNode<Person>> thePersons)
    {
        persons = thePersons;
    }
    
    /** Returns the coefficient of relationship between the proband supplied with 
     * the constructor ahnentafel and the other one (otherPersons). 
     * @param otherPersons de andere tafel
     * @return de verwantschapscoëfficiënt
     */
    double relationshipCoefficient(Map<String, BinaryNode<Person>> otherPersons)
    {
        String[] intersection = intersection(otherPersons);

        // TODO Opdracht 3e
        double coëfficiënt = 0;
        for(String overeenkomst: intersection)
        {
            //vraagt de lengte van het pad aan voor alle overeenkomende personen
            int pad1 = persons.get(overeenkomst).getPath().size();
            int pad2 = otherPersons.get(overeenkomst).getPath().size();
            int totaleLengte = pad1 + pad2;
            coëfficiënt = coëfficiënt + Math.pow(0.5, totaleLengte);
        }
        return coëfficiënt;
        //
    }
    
    /** Returns the names that are present in both this ahnentafel and the other
     * one. Irrelevant ones are omitted, i.e., once a name is included, none of 
     * the ancestors will be included. */
    String[] intersection(Map<String, BinaryNode<Person>> otherPersons)
    {
        // Full intersection
        List<String> intersection = new ArrayList<>();
        for (String key : persons.keySet())
        {
            if (otherPersons.containsKey(key))
            {
                intersection.add(key);
            }
        }

        if (intersection.isEmpty()) return new String[0];
                        
        // Mark redundant ancestors: this tree 
        String[] intersectionArray = intersection.toArray(new String[0]);        
        markRedundantAncerstors(intersectionArray, persons);
             
        // Mark redundant ancestors: other tree         
        int count = markRedundantAncerstors(intersectionArray, otherPersons);
        if (intersectionArray[intersectionArray.length - 1] != null) count++;
        
        return removeRedundantAncestors(intersectionArray, count);
    }
    
    private int markRedundantAncerstors(String[] intersectionArray, Map<String, BinaryNode<Person>> aPersons) 
    {
        Arrays.sort(intersectionArray, new PathComparator(aPersons));
        int count = 0;
        for (int i = 0; i + 1 < intersectionArray.length; i++)
        {
            if (intersectionArray[i] != null)
            {
                if (prefix(i, intersectionArray, aPersons))
                {
                    intersectionArray[i] = null;
                } else
                {
                    count++;
                }
            }
        }
        return count;
    }

    private String[] removeRedundantAncestors(String[] intersectionArray, int count)
    {
        String[] result = new String[count];
        int i = 0;
        int j = 0;
        while (i < intersectionArray.length)
        {
            if (intersectionArray[i] != null)
            {
                result[j] = intersectionArray[i];
                j++;
            }
            i++;
        }
        return result;
    }

    /** Returns true if the path of item is a prefix of any of the paths of the names in the intersectionArray */
    private boolean prefix(int item, String[] intersectionArray, Map<String, BinaryNode<Person>> aPersons)
    {
        String target = aPersons.get(intersectionArray[item]).getPath().getBinary();

        for (int i = item + 1; i < intersectionArray.length; i++)
        {
            if (intersectionArray[i] != null && target.startsWith(aPersons.get(intersectionArray[i]).getPath().getBinary()))
            {
                return true;
            }
        }
        return false;
    }    
}
