package pong;

import java.awt.*;

/**
 * Team: Night Owl <br>
 * @author Hassan Ndow
 * Date:  May 2, 2014 <br>
 * Purpose: This program sets the interface for the four paddle to implement
 *
 */
public abstract class Paddle implements java.io.Serializable {
	
	// instance variables
	private static final long serialVersionUID = 1382504345135840812L;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 50;
	public static final int YLOC = Pong.HEIGHT / 2;
	public static final int XLOC = 5;
	public static final int ICE = 0;
	public static final int QUICK = 2;
	
	
	/**
	 * Gets the x location of the paddle
	 * @return
	 */
	abstract int getXLoc();
	
	
	
	/**
	 * Gets the y location of the paddle
	 * @return
	 */
	abstract int getYLoc();
	
	
	
	/**
	 * Gets the width of the paddle
	 * @return
	 */
	abstract int getWidth();
	
		
	
	/**
	 * Gets the height of the paddle
	 * @return
	 */
	abstract int getHeight();
	
	
	/**
	 * Sets the speed of the paddle
	 * @param v
	 */
	abstract void setSpeed(int v);
	
	
	/**
	 * Gets the speed of the paddle
	 * @return
	 */
	abstract int getSpeed();
	
	
	
	
	/**
	 * Paints the paddle onto the game
	 * @param g
	 */
	abstract void paint(Graphics g);
	
	
	/**
	 * Updates the paddle's properties
	 */
	abstract void tick();		
}