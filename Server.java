import java.util.*;
import java.net.*;
import java.io.*;

/*
 * A multi threaded server that receives login info from client, messages, and sends them back to all clients
 * */
public class Server {
   
   //instance variables
   private Vector<ServerThread> clients;
   private int port;
   private static int id;
   
   //constructor
   public Server(int port) {
      this.port = port;
      clients = new Vector<ServerThread>();
      try{
         ServerSocket ss = new ServerSocket(port);
         System.out.println("Accepting clients at port: " + port);
         
         while(true){
            Socket cs = ss.accept();
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