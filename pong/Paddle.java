package pong;

import java.awt.*;

public abstract class Paddle implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1382504345135840812L;
	// instance variables
	public static final int WIDTH = 10;
	public static final int HEIGHT = 50;
	public static final int YLOC = Pong.HEIGHT / 2;
	public static final int XLOC = 5;
	public static final int ICE = 0;
	public static final int QUICK = 2;
	
	
	/* Gets the x location of the paddle*/
	abstract int getXLoc();
	
	
	/* Gets the y location of the paddle*/
	abstract int getYLoc();
	
	
	/* Gets the width of the paddle*/
	abstract int getWidth();
	
		
	/* Gets the width of the paddle*/
	abstract int getHeight();
	
	/* Sets the speed of the paddle*/
	abstract void setSpeed(int v);
	
	/* Gets the speed of the paddle*/
	abstract int getSpeed();
	
	/* Sets the increased height of the paddle*/
	abstract void setGrowth();
	
	/* Sets the paddle into a frozen state*/
	abstract void setFreeze();
	
	/* Sets the paddle into a faster speed */
	abstract void setQuickness();
	
	/* Paints the paddle onto the game */
	abstract void paint(Graphics g);
	
	/* Updates the paddle's properties */
	abstract void tick();		
}