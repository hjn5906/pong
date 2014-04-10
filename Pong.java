import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pong extends JFrame implements ActionListener
{
   private JMenuBar jmb;                                       // the menu bar
   private JMenu jmFile, jmHelp;                               // the menus
   private JMenuItem jmiExit, jmiStart, jmiAbout, jmiRule,  
           jmiRestart;     // the menu items
   
   public Pong()
   {
      //JMenuBar objects
      jmb = new JMenuBar(); 
      jmFile = new JMenu("File");
      jmHelp = new JMenu("Help");
      jmiStart = new JMenuItem("Start game");
      jmiRestart = new JMenuItem("Restart");
      jmiExit = new JMenuItem("Exit");
      jmiAbout = new JMenuItem("About");
      jmiRule = new JMenuItem("Rules");
      
      //adding JMenuBar objects to the JFrame
      jmFile.add(jmiStart);
      jmFile.add(jmiRestart);
      jmFile.add(jmiExit); 
      jmHelp.add(jmiAbout); 
      jmHelp.add(jmiRule); 
      jmb.add(jmFile); 
      jmb.add(jmHelp); 
      setJMenuBar(jmb);
      
      //Mnemonic objects
      jmFile.setMnemonic(KeyEvent.VK_F);
      jmHelp.setMnemonic(KeyEvent.VK_H);
      jmiExit.setMnemonic(KeyEvent.VK_X);
      jmiAbout.setMnemonic(KeyEvent.VK_A);
      jmiRule.setMnemonic(KeyEvent.VK_R);
      jmiStart.setMnemonic(KeyEvent.VK_S);
      jmiRestart.setMnemonic(KeyEvent.VK_R);
      
      //Adding ActionListener
      jmiStart.addActionListener(this);
      jmiRestart.addActionListener(this);
      jmiExit.addActionListener(this); 
      jmiAbout.addActionListener(this);
      jmiRule.addActionListener(this);

   }
   
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
      
      if(choice.equals(jmiExit))
      {
         System.exit(0);
      }
      if(choice.equals(jmiRestart))
      {
      }
      if(choice.equals(jmiStart))
      {
      }
      if(choice.equals(jmiAbout))
      {
         JOptionPane.showMessageDialog(null,"121 Final Project: Pong" +
            "\n" + "\nDeveloped By Hassan Ndow, Kevin Whetstone, Aleksey Zurkowski, & Abdullah Alam", "Pong", JOptionPane.INFORMATION_MESSAGE);
      }
      if(choice.equals(jmiRule))
      {
        
      }
   }     
   
   public static void main (String [] args)
   {
      //creates a Pong object
      Pong p = new Pong();
      
      //Sets the JFrame
      p.setTitle("Pong");
      p.setSize(700,700);
      p.setLocationRelativeTo(null);
      p.setVisible(true);
      p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
