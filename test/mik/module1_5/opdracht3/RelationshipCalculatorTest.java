package mik.module1_5.opdracht3;

import java.util.Map;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import weiss.BinaryNode;

public class RelationshipCalculatorTest
{    	
        private RelationshipCalculator treeCalc, tree2Calc, t1Calc, t2Calc, t3Calc;
        private Map<String, BinaryNode<Person>> treeMap, tree2Map, t1Map, t2Map, t3Map;
	
	@Before
	public void setUp() throws Exception 
	{               
	    Ahnentafel tree = new Ahnentafel(new Person("1"));
	    Ahnentafel tree2 = new Ahnentafel(new Person("a"));
	    
            tree.addParent(new Person("2"), "1");
            tree.addParent(new Person("3", Person.FEMALE), "1");

            tree.addParent(new Person("4"), "2");
            tree.addParent(new Person("5", Person.FEMALE), "2");

            tree.addParent(new Person("6"), "3");
            tree.addParent(new Person("7", Person.FEMALE), "3");	

            tree.addParent(new Person("9", Person.FEMALE), "4");  

            tree.addParent(new Person("12"), "6");
            tree.addParent(new Person("13", Person.FEMALE), "6");	

            tree2.addParent(new Person("b"), "a");
            tree2.addParent(new Person("c", Person.FEMALE), "a");
        
            tree2.addParent(new Person("12"), "b");
            tree2.addParent(new Person("d", Person.FEMALE), "b");
        
            tree2.addParent(new Person("e"), "12");
            
            tree2.addParent(new Person("4"), "c");
            tree2.addParent(new Person("5", Person.FEMALE), "c");

            tree2.addParent(new Person("9", Person.FEMALE), "4");
        
            Ahnentafel t1 = new Ahnentafel(new Person("a"));
            t1.addParent(new Person("b"), "a");
            t1.addParent(new Person("c", Person.FEMALE), "a");

            Ahnentafel t2 = new Ahnentafel(new Person("A"));
            t2.addParent(new Person("B"), "A");
            t2.addParent(new Person("c", Person.FEMALE), "A");

            Ahnentafel t3 = new Ahnentafel(new Person("B"));
            
            treeCalc = new RelationshipCalculator(tree.getPersonsMap());
            tree2Calc = new RelationshipCalculator(tree2.getPersonsMap());
            t1Calc = new RelationshipCalculator(t1.getPersonsMap());
            t2Calc = new RelationshipCalculator(t2.getPersonsMap());
            t3Calc = new RelationshipCalculator(t3.getPersonsMap());                  
            
            treeMap = tree.getPersonsMap();
            tree2Map = tree2.getPersonsMap();
            t1Map = t1.getPersonsMap();
            t2Map = t2.getPersonsMap();
            t3Map = t3.getPersonsMap();            
	}
		
	@Test
	public void intersect()
	{
	    assertArrayEquals(new String[] { "5", "4", "12" }, treeCalc.intersection(tree2Map));
	    assertArrayEquals(new String[] { "12", "5", "4" }, tree2Calc.intersection(treeMap));
	    
            assertArrayEquals(new String[] { "a" }, t1Calc.intersection(t1Map));

            assertArrayEquals(new String[] { "c" }, t1Calc.intersection(t2Map));
            assertArrayEquals(new String[] { "c" }, t2Calc.intersection(t1Map));
	}
	
	@Test
	public void relationshipCoefficient()
	{
            assertEquals(0.0, t1Calc.relationshipCoefficient(t3Map), 1E-10);
            assertEquals(0.0, t3Calc.relationshipCoefficient(t1Map), 1E-10);

	    assertEquals(0.25, t1Calc.relationshipCoefficient(t2Map), 1E-10);
            assertEquals(0.25, t2Calc.relationshipCoefficient(t1Map), 1E-10);

            assertEquals(0.5, t2Calc.relationshipCoefficient(t3Map), 1E-10);
            assertEquals(0.5, t3Calc.relationshipCoefficient(t2Map), 1E-10);
        
	    assertEquals(2 * 1/16.0 + 1/32.0, treeCalc.relationshipCoefficient(tree2Map), 1E-10);
	    assertEquals(2 * 1/16.0 + 1/32.0, tree2Calc.relationshipCoefficient(treeMap), 1E-10);	     
            
            assertEquals(1.0, treeCalc.relationshipCoefficient(treeMap), 1E-10);            
            assertEquals(1.0, t3Calc.relationshipCoefficient(t3Map), 1E-10);    
	}
}
