package pong;

import java.awt.*;

/**
 * Team: Night Owl <br>
 * @author Hassan Ndow <br>
 * Date: May 2, 2014 <br>
 *         Purpose: This program creates and defines the left paddle
 * 
 */
public class PaddleLeft extends Paddle {

	// instance variables
	private static final long serialVersionUID = -406087425592932940L;
	private int x = 5;
	private int y = YLOC;
	private int speed = 0;
	private int height = 0;
	
	/**
	 * Constructor
	 */
	public PaddleLeft() {
	}
	
	/**
	 * Updates the paddle's properties
	 */
	public void tick() {

		if (y > 0) {
			y += speed;
		} else {
			y = 10;
		}

		if (y + HEIGHT >= Pong.HEIGHT - 20) {
			y = Pong.HEIGHT - (HEIGHT + 20);
		}

	}

	/**
	 * Paints the paddle onto the game
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, getYLoc(), getWidth(), getHeight());
	}

	/**
	 * Sets the speed of the paddle
	 * 
	 * @param v
	 */
	public void setSpeed(int v) {
		speed = v;
	}

	/**
	 * Gets the speed of the paddle
	 * 
	 * @return
	 */
	public int getSpeed() {
		return speed;

	}

	/**
	 * Gets the width of the paddle
	 * 
	 * @return
	 */
	public int getWidth() {
		return WIDTH;
	}

	/**
	 * Gets the height of the paddle
	 * 
	 * @return
	 */
	public int getHeight() {
		return HEIGHT;
	}

	/**
	 * Gets the x location of the paddle
	 * 
	 * @return
	 */
	public int getXLoc() {
		return XLOC;

	}

	/**
	 * Gets the y location of the paddle
	 * 
	 * @return
	 */
	public int getYLoc() {
		return y;

	}

}
