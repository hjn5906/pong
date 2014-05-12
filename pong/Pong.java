
package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Pong extends JPanel implements ActionListener, KeyListener, java.io.Serializable {
   private static final long serialVersionUID = 1L;
   int def = 0;
   int count = 50;
    public final static int WIDTH = 500;
    public final static int HEIGHT = 300;
   
   //instance variables
   PaddleLeft paddleLeft = new PaddleLeft();
   PaddleRight paddleRight = new PaddleRight();
   PaddleTop paddleTop = new PaddleTop();
   PaddleBottom paddleBottom = new PaddleBottom();
   Ball pongBall = new Ball();
   
	/* Constructor that updates the game every 50 milliseconds */
   public Pong() {
      //default 50
      
      
      this.addKeyListener(this);
      this.setFocusable(true);
   }
   
   private static Pong instance = null;
   public static synchronized Pong getInstance() {
        if (instance == null) {
            instance = new Pong();
        }
        return instance;
    }


	/* Updates the location and action of the game components */
   public void actionPerformed(ActionEvent ae) {
      tick();
	  hitBox();
      repaint();
   }
   
   
	/* Updates the status of the active game components */
   private void tick() {
	  paddleRight.tick();
      paddleLeft.tick();
      paddleTop.tick();
      paddleBottom.tick();
      pongBall.tick();

   }
   
   /* Checks to see if the pongBall has hit any solid objects */
   private void hitBox() {
		pongBall.hitSomething(paddleLeft, paddleRight, paddleBottom, paddleTop);   
   }
	
	/* paints the game onto the frame */
   public void paintComponent(Graphics g) {
	   
	   //paint background for game
      g.setColor(Color.black);
      g.fillRect(0, 0, Pong.WIDTH, Pong.HEIGHT);

	  //paint the game components 
	  
	  paddleLeft.paint(g);
      pongBall.paint(g);
      paddleRight.paint(g);
      paddleTop.paint(g);
      paddleBottom.paint(g);

   } // end of paintComponent

	
	
   /* Performs actions based on what key is pressed */
   public void keyPressed(KeyEvent ke) {
	   int pressed = ke.getKeyCode();
	   
	   // up is pressed
      if (pressed == KeyEvent.VK_UP) {
         if (paddleRight.getYLoc() < 5) {
            paddleRight.setSpeed(0);
         }
         
         paddleRight.setSpeed(-10);
         
      }
      
	  // w is pressed
      else if (pressed == KeyEvent.VK_W) {
    	  if (paddleLeft.getYLoc() < 5) {
              paddleLeft.setSpeed(0);
           }
         paddleLeft.setSpeed(-10); 
      }
      
      // a is pressed
      else if (pressed == KeyEvent.VK_A) {
    	  if (paddleTop.getXLoc() < 0) {
              paddleTop.setSpeed(0);
           }
         paddleTop.setSpeed(-10); 
      }
      
      // D is pressed
      else if (pressed == KeyEvent.VK_D) {
    	  if (paddleTop.getXLoc() > Pong.WIDTH) {
              paddleTop.setSpeed(0);
           }
         paddleTop.setSpeed(10); 
      }
      
      // left is pressed
      else if (pressed == KeyEvent.VK_LEFT) {
    	  if (paddleBottom.getXLoc() < 0) {
    		  paddleBottom.setSpeed(0);
           }
    	  paddleBottom.setSpeed(-10); 
      }
      
      // right is pressed
      else if (pressed == KeyEvent.VK_RIGHT) {
    	  if (paddleBottom.getXLoc() > Pong.WIDTH) {
    		  paddleBottom.setSpeed(0);
           }
    	  paddleBottom.setSpeed(10); 
      }


      
		// down is pressed
      else if (pressed == KeyEvent.VK_DOWN) {
         if (paddleRight.getYLoc() + Paddle.HEIGHT >= Pong.HEIGHT) {
            paddleRight.setSpeed(0);
         }
         paddleRight.setSpeed(10);
      }
      
      
      else if (pressed == KeyEvent.VK_G) {
      while(def <1){
      def = 1;
      Timer time = new Timer(30, this);
      time.start();
      System.out.println("Yes " + def);
      }
      
      
      
      
      }
      
	  // s is pressed
      else if (pressed == KeyEvent.VK_S) {
         if (paddleLeft.getYLoc() + Paddle.HEIGHT >= Pong.HEIGHT) {
            paddleLeft.setSpeed(0);
         }
         paddleLeft.setSpeed(10);
      }
      
      // x is pressed
      else if (pressed == KeyEvent.VK_X) {
         paddleLeft.setGrowth();
      }
   } // end of keyPressed method
   
   
	/* Performs actions based on which key is released */
   public void keyReleased(KeyEvent ke) {
      int released = ke.getKeyCode();
	  
      if (released == KeyEvent.VK_UP) {
         paddleRight.setSpeed(0);
      
      }
	  
	  else if (released == KeyEvent.VK_W) {
         paddleLeft.setSpeed(0);
      
      }
	  
	  else if (released == KeyEvent.VK_DOWN) {
         paddleRight.setSpeed(0);
      
      }
	  
	  else if (released == KeyEvent.VK_S) {
         paddleLeft.setSpeed(0);
      
      }
      
      else if (released == KeyEvent.VK_A) {
         paddleTop.setSpeed(0);
      
      }
      
      else if (released == KeyEvent.VK_D) {
         paddleTop.setSpeed(0);
      
      }
      
      else if (released == KeyEvent.VK_LEFT) {
          paddleBottom.setSpeed(0);
       
       }
       
       else if (released == KeyEvent.VK_RIGHT) {
    	   paddleBottom.setSpeed(0);
       
       }

   }


@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	//Do nothing
	
}

}
	

	
