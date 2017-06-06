package mik.module1_5.opdracht3;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import weiss.BinaryNode;

/** Represents an ahnentafel, which can be constructed, inspected, and
 * printed. 
 * @author Rosan Prins(10964711) & Laura Keemink(10912797) 
 */
public class Ahnentafel
{
    private BinaryNode<Person> tree;
    private Map<String, BinaryNode<Person>> persons;
    

    /** Creates an empty ahnentafel */
    Ahnentafel()
    {
        this(null);
    }

    /** Creates an ahnentafel with a proband */
    public Ahnentafel(Person proband)
    {
        persons = new HashMap<>();   

        if (proband == null)
        {
            tree = new BinaryNode<>();
        } else
        {
            tree = new BinaryNode<>(proband);
            addPerson(proband.getName(), tree);
        }
    }

    /** Adds an ancestor of a person to this ahnentafel.     
     * @param ancestor the ancestor (or the proband)
     * @param personName name of the person whose ancestor is to be added (or
     * '-' in case of the proband) */
    public void addParent(Person ancestor, String personName)
    {
        if (personName.equals("-")) // ancestor is proband
        {
            tree = new BinaryNode<>(ancestor);          
            addPerson(ancestor.getName(), tree);
        } else
        {
            BinaryNode<Person> ancestorTree = insertInBinaryTree(ancestor, personName);
            addPerson(ancestor.getName(), ancestorTree);
        }
        
    }
    
   /** Adds a person to the map of all known persons */
   private void addPerson(String name, BinaryNode<Person> personNode)
   {
       persons.put(name, personNode);
   }

    /** 
     * Returns tree with new ancestor node as root
     * @param ancestor de voorouder
     * @param personName naam van de persoon
     * @return de ancestor node
     */
    private BinaryNode<Person> insertInBinaryTree(Person ancestor, String personName)
    {        
        BinaryNode<Person> personNode = persons.get(personName);
        if (personNode == null)
        {
            throw new NoSuchElementException("'" + personName + "'");
        }
        // TODO Opdracht 3b
        if (persons.get(ancestor.getName()) != null) 
        {
            throw new IllegalArgumentException();
        }
        //
        
        BinaryNode<Person> ancestorNode;
        if (ancestor.isMale())
        {
            ancestorNode = personNode.insertRight(ancestor);
        } else
        {
            ancestorNode = personNode.insertLeft(ancestor);
        }
        return ancestorNode;
    }

    /** Returns the proband of this ahnentafel */
    public Person getProband()
    {
        return tree.getElement();
    }
    
    /** Returns the father of the given person */
    public Person getFather(String name)
    {
        return getAncestor(name, true);
    }

    /** Returns the mother of the given person */
    public Person getMother(String name)
    {
        return getAncestor(name, false);
    }
    
    /**
     * Geeft de voorouder terug.
     * @param name de naam
     * @param father of het de vader is
     * @return de voorouder
     */
    private Person getAncestor(String name, boolean father)
    {        
        BinaryNode<Person> node = persons.get(name);
        if (node == null)
        {
            return null;
        }

        BinaryNode<Person> ancestor = null;
        if (father)
        {
            // TODO Opdracht 3a
            ancestor = node.getRight();
            //
        } else
        {
            // TODO Opdracht 3a
            ancestor = node.getLeft();
            //
        }
        return ancestor == null ? null : ancestor.getElement();
    }
    
    /** Prints this ahnentafel */
    public void print()
    {
        tree.traverse(new AhnentafelPrinter()); // TODO Opdracht 3d - commentaar weghalen
    }
    
    /** Returns the coefficient of relationship between the proband of this
     * ahnentafel and the other one. */
    public double relationshipCoefficient(Ahnentafel other)
    {
        RelationshipCalculator calculator = new RelationshipCalculator(persons);
        return calculator.relationshipCoefficient(other.persons);
    }
    
    /** For testing */
    Map<String, BinaryNode<Person>> getPersonsMap()
    {
        return persons;
    }
    
}