package pong;

import java.awt.*;

/**
 * Team: Night Owl <br>
 * @author Hassan Ndow <br>
 * Date: May 2, 2014 <br>
 *         Purpose: This program creates and defines the top paddle
 * 
 */
public class PaddleTop extends Paddle {

	private static final long serialVersionUID = 4588501951168499687L;
	private int y = 0;
	private int speed = 0;
	private int height = 0;
	private int x = Pong.WIDTH / 2;
	
	/**
	 * Constructor
	 */
	public PaddleTop() {
	}

	/**
	 * Updates the paddle's properties
	 */
	public void tick() {

		if (x > 0) {
			x += speed;
		} else {
			x = 1;
		}

		if (x + HEIGHT >= Pong.WIDTH) {
			x = Pong.WIDTH - HEIGHT;
		}

	}

	/**
	 * Paints the paddle onto the game
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, getHeight(), getWidth());
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
		return x;

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
