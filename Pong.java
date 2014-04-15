import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * ISTE 121 Programming Domain II
 * Names: Hassan Ndow, Kevin Whetstone, Abdulla Alam, and Aleksey Zurkowski
 * Team Project: 06
 * Due: May 17, 2014
 * Final Project: Pong Game
 * Description: The pong is a 1 to 4 player that actually play by blocking 
 *    the ball avoiding the ball pass your line border. 
 */
public class Pong extends JFrame implements ActionListener
{
   //Attributes
   private JMenuBar jmb;                                       // the menu bar
   private JMenu jmFile, jmHelp;                               // the menus
   private JMenuItem jmiExit, jmiStart, jmiAbout, jmiRule,  
           jmiRestart;     // the menu items
   
   //Default Constructor
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
   
   public void pickingPlayers()
   {
      int numPlayers = 0;
      
      
      String[] palyers = new String[] {"Player 1", "Player 2", "Player 3", "Player 4"};
      
      String[] choices = {"Player 1", "Player 2", "Player 3", "Player 4"};
   
      //Input dialog box for asking for how many players to join before games actually starts.
      String playerPick = (String)JOptionPane.showInputDialog(null, "How many player do you wish to play with?"
             , "Welcome to PONG game", JOptionPane.QUESTION_MESSAGE
             , null, choices, choices[0]);
   
      //When player doesnt pick, it will display error.
      //example would be, cancel and red x button
      if (playerPick == null)
      {
         JOptionPane.showMessageDialog(null, "You didn't picked a player!");
         System.exit(0);
      }
      else //executes when a player is picking number of players.
      {
         //Attributes
         String funfact = null;
         numPlayers = 0;
         
         switch (playerPick)
         {
            case "Player 1":
               System.out.println("Player 1 only to play Pong Game");
               numPlayers = 1;
               System.out.println(numPlayers);
               break;
            case "Player 2":
               System.out.println("Player 2 only to play Pong Game");
               numPlayers = 2;
               System.out.println(numPlayers);
               break;
            case "Player 3":
               System.out.println("Player 3 only to play Pong Game");
               numPlayers = 3;
               System.out.println(numPlayers);
               break;
            case "Player 4":
               System.out.println("Player 4 only to play Pong Game");
               numPlayers = 4;
               System.out.println(numPlayers);
               break;
         }
      }
      
      //Testing to see if it is working, after choosing player a server allows clients to join the game and starts the game.
      System.out.println("Last message " + numPlayers);
      

   }
   
   public void aboutMenu()
   {
     
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
         pickingPlayers();
      }
      if(choice.equals(jmiAbout))
      {
         JOptionPane.showMessageDialog(null,"121 Final Project: Pong" +
            "\n" + "\nDeveloped by:\n\t Hassan Ndow, Kevin Whetstone,\n\t Aleksey Zurkowski, and Abdullah Alam", "Pong", JOptionPane.INFORMATION_MESSAGE);
      }
      if(choice.equals(jmiRule))
      {
        
      }
   }     
   
   //main method
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
