package mik.module1_5.opdracht3;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** Main program: enables the user to process ahnentafel files
 * @author Rosan Prins(10964711) & Laura Keemink(10912797) */
public class Main extends JPanel
{    
    public static JTextArea textArea;
    
    public static void main(String[] args)
    {
        // TODO Opdracht 3c - onderstaande code vervangen
        //maakt een frame aan
        JFrame frame = new JFrame("Kwartierstaten");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //maakt buttons aan
        JPanel panel = new JPanel();
        frame.add(panel);
        //maakt open button aan
        JButton open = new JButton("Open bestand");
        panel.add(open);
        open.addActionListener(new Action1());
        //maakt close button aan
        JButton closeButton = new JButton("Sluiten");
        panel.add(closeButton);
        closeButton.addActionListener(new Action2());
        //maakt textarea aan
        textArea = new JTextArea(10,40);
        JScrollPane scroll = new JScrollPane(textArea);
        //maakt scrollbar aan
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        
        frame.setVisible(true); 
    }

    /**
     * Geeft de acties indien er op de open bestand button is geklikt.
     */
    private static class Action1 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton open = new JButton("Open bestand");
            JFileChooser fileChooser = new JFileChooser();
            if(fileChooser.showOpenDialog(open) == fileChooser.APPROVE_OPTION)
            {
                System.out.println("Je hebt dit bestand geopend: " + fileChooser.getSelectedFile().getName());
                Ahnentafel tafel = new Ahnentafel();
                AhnentafelReader reader = new AhnentafelReader();
                //de tree wordt opgehaald
                try {
                    tafel = reader.fileRead(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                //de tree wordt geprint
                tafel.print();
                /** Code gebruikt voor antwoord opdracht 3e verslag, zal niet werken i.v.m. ander pad, daarom in commentaar.
                try{
                Ahnentafel tafelLeonie = new Ahnentafel();
                tafelLeonie = reader.fileRead("C:\\Users\\Laura\\Documents\\MIK\\1.6 Medische basisprincipes\\Startcode\\leonie.txt");
                System.out.println(tafel.relationshipCoefficient(tafelLeonie));
                } catch(Exception f) {}
                */
            }
        }
    }
    
    /**
     * Geeft de actie indien op de close button is geklikt.
     */
    private static class Action2 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton closeButton = new JButton("Sluiten");
            System.exit(0);
        }
    }
    //
}
