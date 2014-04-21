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
public class Server extends JFrame {
   
   //instance variables
   private Vector<ServerThread> clients;
   private int port;
   private static int id;
   private JTextArea jtaAreaEast, jtaAreaWest;
   private JLabel jlCount, jlWins;
   private JTextField jtCount, jtWins;
   //constructor
   public Server(int port) {
      this.port = port;
      clients = new Vector<ServerThread>();
      
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
            jtaAreaEast.append("Accepting clients at port: " + port);
            ServerThread st = new ServerThread(cs);
            clients.add(st);
            st.start();
            
         }
      }
      catch(IOException ioe){
         System.out.println("Error with input/output: " + ioe.getMessage());
      }
      
   }
   
   
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
