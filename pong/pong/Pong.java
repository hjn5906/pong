
package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Pong extends JFrame {
	
	
	private static final long serialVersionUID = 1L;


	
    final static int WIDTH = 500;
    final static int HEIGHT = 300;
	
	
	// start of pong constructor
    public Pong() {
        setResizable(false);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game pongGame = new Game();
		add(pongGame);
        setVisible(true);
    } // end of pong constructor
	
	
	// start of Game class
	class Game extends JPanel implements ActionListener, KeyListener {

   //instance variables
   PaddleLeft paddleLeft = new PaddleLeft();
   PaddleRight paddleRight = new PaddleRight();
   //PaddleUp paddleUp = new PaddleUp();
   //PaddleDown paddleDown = new PaddleDown();
   Ball pongBall = new Ball();
   
	/* Constructor that updates the game every 50 milliseconds */
   public Game() {
      //default 50
      Timer time = new Timer(20, this);
      time.start();
   
      this.addKeyListener(this);
      this.setFocusable(true);
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
      pongBall.tick();

   }
   
   /* Checks to see if the pongBall has hit any solid objects */
   private void hitBox() {
		pongBall.hitSomething(paddleLeft, paddleRight);   
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
      
		// down is pressed
      else if (pressed == KeyEvent.VK_DOWN) {
         if (paddleRight.getYLoc() + Paddle.HEIGHT >= Pong.HEIGHT) {
            paddleRight.setSpeed(0);
         }
         paddleRight.setSpeed(10);
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

}
	/* Main method that start the entire game */
    public static void main(String[] args) {
        Pong pong = new Pong();
    }

	
}
