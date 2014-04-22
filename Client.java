import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * A client that sends messages to the server
 * To run the client type below (local address automatically set to local host name, default port set to 1500)
 * > java Client
 * */
public class Client extends JFrame
   implements ActionListener {
   
   
   //instance variables
   private int port;
   private String address;
   private ObjectInputStream ois;
   private ObjectOutputStream oos;
   private Socket socket;
   private JTextArea jtaAreaEast, jtaAreaWest;
   private JLabel jlCount, jlWins;
   private JTextField jtCount, jtWins,jtSend;
   private JButton jbSend;
   
   private JMenuBar jmb;                                       // the menu bar
   private JMenu jmFile, jmHelp;                               // the menus
   private JMenuItem jmiExit, jmiStart,jmiLogin, jmiAbout, jmiRule,  
           jmiRestart;     // the menu items
           
   String username;
   //constructor
   public Client(String address, int port){
      this.port = port;
      this.address = address;
      username = JOptionPane.showInputDialog("Please enter a username");

//////////////////////////////////////////////////////////////////////////
      
      //JMenuBar objects
      jmb = new JMenuBar(); 
      jmFile = new JMenu("File");
      jmHelp = new JMenu("Help");
      // jmiStart = new JMenuItem("Start game");
//       jmiRestart = new JMenuItem("Restart");
      jmiExit = new JMenuItem("Exit");
      jmiAbout = new JMenuItem("About");
      jmiRule = new JMenuItem("Rules");
      jmiLogin = new JMenuItem("Login");
      
      //adding JMenuBar objects to the JFrame
      // jmFile.add(jmiStart);
//       jmFile.add(jmiRestart);
      jmFile.add(jmiLogin); 
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
      // jmiStart.setMnemonic(KeyEvent.VK_S);
//       jmiRestart.setMnemonic(KeyEvent.VK_R);
      
      // //Adding ActionListener
      // jmiStart.addActionListener(this);
//       jmiRestart.addActionListener(this);
      jmiExit.addActionListener(this); 
      jmiAbout.addActionListener(this);
      jmiRule.addActionListener(this);
      jmiLogin.addActionListener(this);
      
      
      
      
      
      
      /////////////////////////////////////////////////////////////////////////////////
      
      //WEST BORDER SIDE FOR PONG GAME
      JPanel jpServerWest = new JPanel(new GridLayout(0,1));
      JPanel jpServerEast = new JPanel(new BorderLayout());
      JPanel jpServerEast2 = new JPanel(new BorderLayout());
      JPanel jpServerSouth = new JPanel(new GridLayout(2,0));
      
      jtaAreaWest = new JTextArea("Pong Game",10,40);
      jtaAreaWest.setEditable(true);
      
      
      jpServerWest.add(new JScrollPane(jtaAreaWest));
      add(jpServerWest,BorderLayout.CENTER);
      
      //jtaAreaWest.append("Pong game is here");
      
      //EAST BORDER SIDE FOR CLIENT CHAT
		jtaAreaEast = new JTextArea("Chat here:\n",30,10);
      
		jtaAreaEast.setEditable(true);
	   
      jtSend = new JTextField("Say something", 15);
      jbSend = new JButton("Send");
      jbSend.addActionListener(this); 
      
		jpServerEast.add(new JScrollPane(jtaAreaEast));
      jpServerEast2.add(jtSend);
      jpServerEast2.add(jbSend,"East");
      jpServerEast.add(jpServerEast2, "South");
      //JPanel total = new JPanel(new GridLayout(2,0));
      //total.add(jpServerEast);
     // total.add(jpServerEast2);
      add(jpServerEast,BorderLayout.EAST);
      //add(jpServerEast2,"East");
      
      //SOUTH BORDER SIDE FOR LABELS FOR COUNTS OF WINS AND ON
      jlCount = new JLabel("Counts of game: ");
      jtCount = new JTextField(10);
      jlCount.setHorizontalAlignment(JLabel.RIGHT);
      jtCount.setHorizontalAlignment(JTextField.CENTER);
      //jlCount.setLabelFor(jtCount);
      
      jlWins = new JLabel("Number of Wins");
      jtWins = new JTextField(5);
      jlWins.setHorizontalAlignment(JLabel.RIGHT);
      jtWins.setHorizontalAlignment(JTextField.CENTER);
      
      
      jpServerSouth.add(jlCount);
      jpServerSouth.add(jtCount);
      jpServerSouth.add(jlWins);
      jpServerSouth.add(jtWins);
      add(jpServerSouth, "South");
      
      setSize(800,500);
		setVisible(true);
      setResizable(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
      //Client cant controll for restart and start t
      if(choice.equals(jmiExit))
      {
         System.exit(0);
      }
      
      if(choice == jmiLogin) {
         try{
         socket = new Socket(address,port);
      }
      catch(UnknownHostException uhe){
         System.out.println("Unknown host: " + uhe.getMessage());
      }
      catch(IOException ioe){
         System.out.println("Error with input/output: " + ioe.getMessage());
      }
      
      try{
         System.out.println("Client connected to" + socket.getInetAddress() + "/" + socket.getPort());
      }
      catch(NullPointerException npe){
         System.out.println("No server to connect to.");
      }
      
      try{
         ois = new ObjectInputStream(socket.getInputStream());
         oos = new ObjectOutputStream(socket.getOutputStream());
      }
      catch(IOException ioe){
         System.out.println("Error with input/output: " + ioe.getMessage());
         
      }
      
      new ClientThread().start();
      }
      
      if(choice == jbSend) {
         String message = jtSend.getText().trim();
        
         try{
               oos.writeObject(username+ ": "+message);
            }
            catch(IOException ioe){
               System.out.println("IO error: " + ioe.getMessage());
        
            }
         
      }
      // if(choice.equals(jmiRestart))
//       {
//       }
//       if(choice.equals(jmiStart))
//       {
//          pickingPlayers();
//       }
      if(choice.equals(jmiAbout))
      {
         JOptionPane.showMessageDialog(null,"121 Final Project: Pong" +
            "\n" + "\nDeveloped by:\n\t Hassan Ndow, Kevin Whetstone,\n\t Aleksey Zurkowski, and Abdullah Alam", "Pong", JOptionPane.INFORMATION_MESSAGE);
      }
      if(choice.equals(jmiRule))
      {
        
      }
   }
   
   // public void pickingPlayers()
//    {
//       int numPlayers = 0;
//       
//       
//       String[] palyers = new String[] {"Player 1", "Player 2", "Player 3", "Player 4"};
//       
//       String[] choices = {"Player 1", "Player 2", "Player 3", "Player 4"};
//    
//       //Input dialog box for asking for how many players to join before games actually starts.
//       String playerPick = (String)JOptionPane.showInputDialog(null, "How many player do you wish to play with?"
//              , "Welcome to PONG game", JOptionPane.QUESTION_MESSAGE
//              , null, choices, choices[0]);
//    
//       //When player doesnt pick, it will display error.
//       //example would be, cancel and red x button
//       if (playerPick == null)
//       {
//          JOptionPane.showMessageDialog(null, "You didn't picked a player!");
//          System.exit(0);
//       }
//       else //executes when a player is picking number of players.
//       {
//          //Attributes
//          String funfact = null;
//          numPlayers = 0;
//          
//          switch (playerPick)
//          {
//             case "Player 1":
//                System.out.println("Player 1 only to play Pong Game");
//                numPlayers = 1;
//                System.out.println(numPlayers);
//                break;
//             case "Player 2":
//                System.out.println("Player 2 only to play Pong Game");
//                numPlayers = 2;
//                System.out.println(numPlayers);
//                break;
//             case "Player 3":
//                System.out.println("Player 3 only to play Pong Game");
//                numPlayers = 3;
//                System.out.println(numPlayers);
//                break;
//             case "Player 4":
//                String name = "Player 4 only to play Pong Game\n";
//                numPlayers = 4;
//                System.out.println(numPlayers);
//                jtaAreaEast.append(name);
//                break;
//          }
//       }
//       
//       //Testing to see if it is working, after choosing player a server allows clients to join the game and starts the game.
//       System.out.println("Last message " + numPlayers);
//       
// 
//    }
    
   
   //Client thread
   class ClientThread extends Thread {
      
      String message;
      
      //run method
      public void run() {
         while(true){
            try{
               message = (String)ois.readObject();
            }
            catch(IOException ioe){
               System.out.println("Error with input/output: " + ioe.getMessage());
               break;
            }
            catch(ClassNotFoundException cnfe){
               System.out.println("Class could not be found: " + cnfe.getMessage());
               break;
            }
            
            jtaAreaEast.append(message + "\n");
         }
         
         try{
            ois.close(); 
            oos.close();
            socket.close(); 
         }
         catch(IOException ioe){
            System.out.println("Error with input/output: " + ioe.getMessage());
         } 
      }
   } // end of ClientThread
   
   ////////////////////////////////////
  //  public void actionPerformed(ActionEvent ae)
//    {
//       Object choice = ae.getSource();
//       
//       if(choice.equals(jmiExit))
//       {
//          System.exit(0);
//       }
//       if(choice.equals(jmiRestart))
//       {
//       }
//       if(choice.equals(jmiStart))
//       {
//          pickingPlayers();
//       }
//       if(choice.equals(jmiAbout))
//       {
//          JOptionPane.showMessageDialog(null,"121 Final Project: Pong" +
//             "\n" + "\nDeveloped by:\n\t Hassan Ndow, Kevin Whetstone,\n\t Aleksey Zurkowski, and Abdullah Alam", "Pong", JOptionPane.INFORMATION_MESSAGE);
//       }
//       if(choice.equals(jmiRule))
//       {
//         
//       }
//    }
//    
   
   
   
   //main method
   public static void main(String[] args)
   { 
      JTextArea jtaAreaEast = new JTextArea();
      try{
         Client c = new Client(InetAddress.getLocalHost().getHostAddress(), 1500);
      
     
      }
      catch(UnknownHostException uhe){
      }
   }
   
}
