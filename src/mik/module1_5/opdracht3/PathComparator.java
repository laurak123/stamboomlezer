package mik.module1_5.opdracht3;

import java.util.Comparator;
import java.util.Map;
import weiss.BinaryNode;

/** Comparator for comparing names with respect to their path in an Ahnentafel.
 * Shorter paths are 'greater' than longer ones. 
 * @author Rosan Prins(10964711) & Laura Keemink(10912797)*/
class PathComparator implements Comparator<String>
{
    private Map<String, BinaryNode<Person>> persons;

    public PathComparator(Map<String, BinaryNode<Person>> persons)
    {
        this.persons = persons; 
    }

    @Override
    public int compare(String name1, String name2)
    {
        if (name1 == null && name2 == null)
        {
            return 0;
        }
        if (name1 == null)
        {
            return 1;
        }
        if (name2 == null)
        {
            return -1;
        }

        return persons.get(name2).getPath().getCode() - persons.get(name1).getPath().getCode();
    }
}
