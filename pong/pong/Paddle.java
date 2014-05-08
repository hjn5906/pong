package pong;

import java.awt.*;

public interface Paddle {
	
	// instance variables
	static final int WIDTH = 10;
	static final int HEIGHT = 50;
	static final int YLOC = Pong.HEIGHT / 2;
	static final int XLOC = 5;
	static final int ICE = 0;
	static final int QUICK = 2;
	
	
	/* Gets the x location of the paddle*/
	public int getXLoc();
	
	
	/* Gets the y location of the paddle*/
	public int getYLoc();
	
	
	/* Gets the width of the paddle*/
	public int getWidth();
	
		
	/* Gets the width of the paddle*/
	public int getHeight();
	
	/* Sets the speed of the paddle*/
	public void setSpeed(int v);
	
	/* Gets the speed of the paddle*/
	public int getSpeed();
	
	/* Sets the increased height of the paddle*/
	public void setGrowth();
	
	/* Sets the paddle into a frozen state*/
	public void setFreeze();
	
	/* Sets the paddle into a faster speed */
	public void setQuickness();
	
	/* Paints the paddle onto the game */
	public void paint(Graphics g);
	
	/* Updates the paddle's properties */
	public void tick();		
}