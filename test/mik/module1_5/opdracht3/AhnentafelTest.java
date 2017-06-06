package mik.module1_5.opdracht3;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class AhnentafelTest 
{
	private Ahnentafel tree, tree2, t1, t2;
	
	@Before
	public void setUp() throws Exception 
	{
	    tree = new Ahnentafel(new Person("1"));
	    tree2 = new Ahnentafel(new Person("a"));
	    
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
        
            t1 = new Ahnentafel(new Person("a"));
            t1.addParent(new Person("b"), "a");
            t1.addParent(new Person("c", Person.FEMALE), "a");

            t2 = new Ahnentafel(new Person("A"));
            t2.addParent(new Person("B"), "A");
            t2.addParent(new Person("c", Person.FEMALE), "A");
	}
        
        @Test(expected = IllegalArgumentException.class)
        public void testExceptions()
        {
            // TODO Opdracht 3b
            tree.addParent(new Person("9"), "2");
            //
        }
       
	@Test
	public void ancestors()
	{    
            // TODO Opdracht 3a
                assertEquals("6",tree.getFather("3").getName());
                assertEquals("12",tree2.getFather("b").getName());
                assertEquals("b",t1.getFather("a").getName());
                assertEquals("c",t2.getMother("A").getName());
            //
     	}
			
	// Not really a test: no assertions
	@Test
	public void printer()
	{
            //tree.print();
	}
}
