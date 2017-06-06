package mik.module1_5.opdracht3;

import weiss.Visitor;

// TODO Opdracht 3d

/** Visitor for printing an ahnentafel 
 * @author Rosan Prins(10964711) & Laura Keemink(10912797) */
class AhnentafelPrinter implements Visitor<Person> 
{   
    public AhnentafelPrinter()
    {
        Main.textArea.setText("");
    }
    
    /**
     * Zet de regels in de textarea.
     * @param element de persoon
     * @param level de level
     */
    @Override
    public void visit(Person element, int level){
        for(int i = 0; i < level; i++)
            {
            Main.textArea.append("|");
            }
        Main.textArea.append(element.getName() + " " + element.getSex() + "\n");
    }
}
//