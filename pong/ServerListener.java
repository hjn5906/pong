package pong;
import java.util.Vector;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ServerListener implements ActionListener,KeyListener {

 PaddleLeft paddleLeft = new PaddleLeft();
   PaddleRight paddleRight = new PaddleRight();
   //PaddleUp paddleUp = new PaddleUp();
   //PaddleDown paddleDown = new PaddleDown();
   Ball pongBall = new Ball();

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
      
		// down is pressed
      else if (pressed == KeyEvent.VK_DOWN) {
         if (paddleRight.getYLoc() + Paddle.HEIGHT >= Pong.HEIGHT) {
            paddleRight.setSpeed(0);
         }
         paddleRight.setSpeed(10);
      }
      else if (pressed == KeyEvent.VK_A) {
      Timer time = new Timer(50, this);
      time.start();
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

   }


@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	//Do nothing
	
}

public void actionPerformed(ActionEvent ae)
   {
   }
}
