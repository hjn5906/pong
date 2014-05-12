package pong;
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
   private JButton jbSend,jbPM;
   private JPanel jpServerWest;
   private JMenuBar jmb;                                       // the menu bar
   private JMenu jmFile, jmHelp;                               // the menus
   private JMenuItem jmiExit, jmiStart,jmiLogin, jmiAbout, jmiRule,  
           jmiRestart;     // the menu items
           
   private static Pong instance = null;
           
       
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
      jmiLogin.setMnemonic(KeyEvent.VK_L);
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
       jpServerWest = new JPanel(new GridLayout(0,1));
      JPanel jpServerEast = new JPanel(new BorderLayout());
      JPanel jpServerEast2 = new JPanel(new BorderLayout());
      JPanel jpServerSouth = new JPanel(new GridLayout(2,0));
      
      
      
      jpServerWest.add(Pong.getInstance());
      add(jpServerWest,BorderLayout.CENTER);
    
      
      //jtaAreaWest.append("Pong game is here");
      
      //EAST BORDER SIDE FOR CLIENT CHAT
      jtaAreaEast = new JTextArea("Chat here:\n",30,10);
      
      jtaAreaEast.setEditable(false);
      
      jtSend = new JTextField("Say something", 15);
      jtSend.selectAll();
      jbSend = new JButton("Send");
      jbSend.addActionListener(this); 
      
      jbPM = new JButton("Private");
      jbPM.addActionListener(this); 
      
      jpServerEast.add(new JScrollPane(jtaAreaEast));
      jpServerEast2.add(jtSend);
      jpServerEast2.add(jbSend,"East");
      jpServerEast2.add(jbPM,"South");
      jpServerEast.add(jpServerEast2, "South");
   
      add(jpServerEast,BorderLayout.EAST);
      
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
      
      setLocation(500,100);
      setSize(800,500);
      setVisible(true);
      setResizable(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      
      
      
   }
   
 
   
   
   public static synchronized Pong getInstance() {
        if (instance == null) {
            instance = new Pong();
        }
        return instance;
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
            JOptionPane.showMessageDialog(null, "Server Quits\nGood-Bye", "No more connection", JOptionPane.INFORMATION_MESSAGE);
         //System.out.println("Error with input/output: " + ioe.getMessage());
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
         catch(NullPointerException npe)
         {
         //SSystem.out.println("You have no connections");
            JOptionPane.showMessageDialog(null, "Wrong ip address\nGood-Bye!", "Never connected", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
         }
         catch(IOException ioe){
            JOptionPane.showMessageDialog(null, "Server Quits\nGood-Bye", "No more connection", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Error with input/output: " + ioe.getMessage());
            System.exit(0);
         
         }
         
         try{
             
             
             oos.writeObject(username);
          }
          catch(IOException ioe){
             JOptionPane.showMessageDialog(null, "Sorry, server went down. please comeback soon!", "Server went down", JOptionPane.INFORMATION_MESSAGE);
             System.out.println("IO error: " + ioe.getMessage());
          
          }
      
         new ClientThread().start();
      
      }
      
      if(choice == jbSend) {
         
        
         try{
            String message = jtSend.getText().trim();
            jtSend.setText(" ");
           
            oos.writeObject(username+ ": "+message);
         }
         catch(IOException ioe){
            JOptionPane.showMessageDialog(null, "Sorry, server went down. please comeback soon!", "Server went down", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("IO error: " + ioe.getMessage());
         
         }
         
      }
      
      if(choice == jbPM) {
          
    	  ArrayList<String> list = new ArrayList<String>();
    	  String pmUser;
          
          try{
             String message = jtSend.getText().trim();
             jtSend.setText(" ");
             pmUser = JOptionPane.showInputDialog("Who would you like to private message?");
             list.add(pmUser);
             list.add(username+ ": "+message);
             oos.writeObject(list);
          }
          catch(IOException ioe){
             JOptionPane.showMessageDialog(null, "Sorry, server went down. please comeback soon!", "Server went down", JOptionPane.INFORMATION_MESSAGE);
             System.out.println("IO error: " + ioe.getMessage());
          
          }
          
       }
      if(choice.equals(jmiAbout))
      {
         JOptionPane.showMessageDialog(null,"121 Final Project: Pong" +
            "\n" + "\nDeveloped by:\n\t Hassan Ndow, Kevin Whetstone,\n\t Aleksey Zurkowski, and Abdullah Alam", "Pong", JOptionPane.INFORMATION_MESSAGE);
      }
      if(choice.equals(jmiRule))
      {
    	  JOptionPane.showMessageDialog(null,"To start this game press the 'G' key.\nEach player has control " +
    	  		"of two paddles. \nPlayer 1 has control of the left paddle [moves vertically with the 'W' and 'S' keys] and the " +
    	  		"top paddle [moves horizontally with the 'A' and 'D' paddle].\nPlayer 2 has control of the right paddle [moves veritically" +
    	  		" with the 'UP' and 'DOWN' keys] and the bottom paddle [moves horizontally with the 'LEFT' and 'RIGHT' keys." +
    	  		"]", "Pong", JOptionPane.INFORMATION_MESSAGE);
      }
   }
   
   //Client thread
   class ClientThread extends Thread {
      
     
      String message;
      Pong game;
      //run method
      public void run() {
      
         
         while(true){
            
            try{  
               Object next = ois.readObject();
               if(next instanceof String){
                  message = (String)next;

                  jtaAreaEast.append(message + "\n");
               }
               
               if(next instanceof Pong){
                  game = (Pong) next;
                  System.out.println("yes");
        
              jpServerWest.repaint();
               jpServerWest.add(game);
               
            }
            }
            catch(IOException ioe){
               System.out.println("Error with input/output: " + ioe.getMessage());
               JOptionPane.showMessageDialog(null, "Server Quits\nGood-Bye", "No more connection", JOptionPane.INFORMATION_MESSAGE);
               System.exit(0);
            
            }
            catch(ClassNotFoundException cnfe){
               System.out.println("Class could not be found: " + cnfe.getMessage());
               break;
            }
            
            
           
            
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
   
   
   //main method
   public static void main(String[] args)
   { 
      JTextArea jtaAreaEast = new JTextArea();
      String address = JOptionPane.showInputDialog("Please enter an address to connect to:");
      Client c = new Client(address, 1500);
     
   
   }
   
}
