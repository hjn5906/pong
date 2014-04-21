import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * A multi threaded server that receives login info from client, messages, and sends them back to all clients
 * To run the server type below (default port set to 1500)
 * > java Server
 * */
public class Server extends JFrame 
   implements ActionListener{
   
   //instance variables
   private Vector<ServerThread> clients;
   private int port;
   private static int id;
   private JTextArea jtaAreaEast, jtaAreaWest;
   private JLabel jlCount, jlWins;
   private JTextField jtCount, jtWins;
   
    private JMenuBar jmb;                                       // the menu bar
   private JMenu jmFile, jmHelp;                               // the menus
   private JMenuItem jmiExit, jmiStart, jmiAbout, jmiRule,  jmiRestart;     // the menu items

   
   
   //constructor
   public Server(int port) {
      this.port = port;
      clients = new Vector<ServerThread>();
      
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
      
      // //Adding ActionListener
      jmiStart.addActionListener(this);
      jmiRestart.addActionListener(this);
      jmiExit.addActionListener(this); 
      jmiAbout.addActionListener(this);
      jmiRule.addActionListener(this);
      

      
      
      //WEST BORDER SIDE FOR PONG GAME
      JPanel jpServerWest = new JPanel(new GridLayout(0,1));
      JPanel jpServerEast = new JPanel(new GridLayout(0,1));
      JPanel jpServerSouth = new JPanel(new GridLayout(2,0));
      
      jtaAreaWest = new JTextArea("Pong Game",20,40);
      jtaAreaWest.setEditable(true);
      
      
      jpServerWest.add(new JScrollPane(jtaAreaWest));
      add(jpServerWest,"West");
      
      //jtaAreaWest.append("Pong game is here");
      
      //EAST BORDER SIDE FOR CLIENT CHAT
		jtaAreaEast = new JTextArea(20,30);
		jtaAreaEast.setEditable(false);
	   
      
		jpServerEast.add(new JScrollPane(jtaAreaEast));
      add(jpServerEast,"East");
      
      
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
      
      try{
         ServerSocket ss = new ServerSocket(port);
         jtaAreaEast.append("Welcom to the Pong game. This port is: " + port);
         jtaAreaEast.append("\n");
         while(true){
            
            Socket cs = ss.accept();
            jtaAreaEast.append("Accepting clients at port: " + port + "\n");
            ServerThread st = new ServerThread(cs);
            clients.add(st);
            st.start();
            
         }
      }
      catch(IOException ioe){
         System.out.println("Error with input/output: " + ioe.getMessage());
      }
      
   }
   
   //ActionListener
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
               String name = "Player 4 only to play Pong Game\n";
               numPlayers = 4;
               System.out.println(numPlayers);
               jtaAreaEast.append(name);
               break;
         }
      }
      
      //Testing to see if it is working, after choosing player a server allows clients to join the game and starts the game.
      System.out.println("Last message " + numPlayers);
      

   }
   
   //end ActionListener
   //Server thread
   class ServerThread extends Thread {
   
      //instance variables
      Socket socket;
      ObjectOutputStream oos;
      ObjectInputStream ois;
      String message;
      int identification;
      
      
      //constructor
      ServerThread(Socket socket) {
         this.socket = socket;
         identification = id++;
         
         try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
         }
         catch(IOException ioe){
            System.out.println("IO Error: " + ioe.getMessage());
         }
      }
      
      //run method
      public void run() {
         while(true) {
            try { 
               message = (String) ois.readObject();
               jtaAreaEast.append("\n"+message);
            }
            catch(IOException ioe) {
               System.out.println("Client disconnected");
               break;
            }
            catch(ClassNotFoundException cnfe){
               System.out.println("Class could not be found: " + cnfe.getMessage());
               break;
            }
            
            System.out.println("Client sent: " + message.toUpperCase());
               
            for(int i = clients.size(); --i >= 0;) {
               ServerThread c = clients.get(i);
               if(c.socket.isConnected() == false){
                  try{
                     c.oos.close();
                     c.ois.close();
                     c.socket.close();
                  }
                  catch(IOException ioe) {
                     
                  }
                  clients.remove(i);
               }
                  
               else{
                  try{
                     c.oos.writeObject(message.toUpperCase());
                  }
                  catch(IOException ioe){
                     System.out.println("error");
                  
                  }
                     
               }
            }            
         }
         
         
         //remove and close the client
         for(int i = 0; i < clients.size(); ++i) {
            ServerThread client = clients.get(i);
            if(client.identification == identification) {
               clients.remove(i);
               return;
            }
         }
         
         try{
            oos.close();
            ois.close();
            socket.close();
         }                  
         catch(IOException ioe) {
         
         }
         
      
      }
   }
   
   
   //main method
   public static void main (String[] args) {
      Server s = new Server(1500);
   }
}
