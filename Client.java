import java.util.*;
import java.net.*;
import java.io.*;

/*
 * A client that sends messages to the server
 * To run the client type below (local address automatically set to local host name, default port set to 1500)
 * > java Client
 * */
public class Client {
   
   
   //instance variables
   private int port;
   private String address;
   private ObjectInputStream ois;
   private ObjectOutputStream oos;
   private Socket socket;
   
   
   //constructor
   public Client(String address, int port){
      this.port = port;
      this.address = address;
      
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
            
            System.out.println("Server sent: " + message);
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
   public static void main(String[] args){
      try{
         Client c = new Client(InetAddress.getLocalHost().getHostAddress(), 1500);
      
      
         Scanner in = new Scanner(System.in);
      
         while(true){
            System.out.print("> ");
            String message = in.nextLine();
            try{
               c.oos.writeObject(message);
            }
            catch(IOException ioe){
               System.out.println("IO error: " + ioe.getMessage());
               break;
            }
         
         }
      }
      catch(UnknownHostException uhe){
      }
   }
   
}