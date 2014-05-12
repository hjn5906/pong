
package pong;

import java.awt.*;

public class Ball implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1943323373497012056L;
	// instance variables
    private int xLoc = Pong.WIDTH / 2;
    private int yLoc = Pong.HEIGHT / 2;
    private int diamater = 10;
    private int ballX = -3;
    private int ballY = 3;
    private int pLeftScore;
    private int pRightScore;
    
    

    

    public void paint(Graphics g) {
    	
    	g.setColor(Color.white);
    	g.drawString("Blue team scored: "+pLeftScore, 10, Pong.HEIGHT - 40);
        g.drawString("Green team scored: "+pRightScore, Pong.WIDTH - 130, Pong.HEIGHT - 40);
        
        g.fillOval(xLoc, yLoc, diamater, diamater);

    }
    
    public void tick() {
    	xLoc += ballX;
        yLoc += ballY;

        if (yLoc + diamater < 5) {
        	// resets position of ball
        	pRightScore++;
        	ballY = 3;
            xLoc = Pong.WIDTH / 2;
            yLoc = Pong.HEIGHT / 2;
            try {
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } else if (yLoc + diamater > Pong.HEIGHT - 15) {
        	// resets position of ball
        	pLeftScore++;
        	ballY = -ballY;
            xLoc = Pong.WIDTH / 2;
            yLoc = Pong.HEIGHT / 2;
            try {
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        
        if (xLoc < 0) {
            ballX = 3;
            pRightScore++;
            // resets position of ball
            xLoc = Pong.WIDTH / 2;
            yLoc = Pong.HEIGHT / 2;
            try {
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        } else if (xLoc + diamater > Pong.WIDTH - 6) {
            ballX = -ballX;
            pLeftScore++;
            xLoc = Pong.WIDTH / 2;
            yLoc = Pong.HEIGHT / 2;
            try {
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        
    }

    
    
    

    public void hitSomething(PaddleLeft player,PaddleRight player2, PaddleBottom player3, PaddleTop player4) {
        if(paddleLeftHitbox(player)){
        	ballX = -ballX;
        }
        if(paddleRightHitbox(player2)){
        	ballX = -ballX;
        }
        
        if(paddleBottomHitbox(player3)) {
        	ballY = -ballY;
        }
        
        if(paddleTopHitbox(player4)) {
        	ballY = -ballY;
        }
    }
    
    public boolean paddleLeftHitbox(PaddleLeft pLeft) {
    	int hitboxX = pLeft.getWidth() + pLeft.getXLoc();
    	int hitboxY = pLeft.getHeight() + pLeft.getYLoc();
    	
    	if(xLoc < hitboxX && yLoc < hitboxY && xLoc > pLeft.getXLoc() && yLoc > pLeft.getYLoc()) {
    		return true;
    	}
    	
		return false;
    }
    
    public boolean paddleRightHitbox(PaddleRight pRight) {
    	int hitboxX = pRight.getWidth() + pRight.getXLoc();
    	int hitboxY = pRight.getHeight() + pRight.getYLoc();
    	
    	if(xLoc < hitboxX && yLoc < hitboxY && xLoc > pRight.getXLoc() && yLoc > pRight.getYLoc()) {
    		return true;
    	}
    	
		return false;
    }

    public boolean paddleBottomHitbox(PaddleBottom pBottom) {
    	
    	int hitboxX = pBottom.getHeight() + pBottom.getXLoc();
    	int hitboxY =  pBottom.getYLoc() - pBottom.getWidth() ;
    	
        if (yLoc > hitboxY && xLoc < hitboxX && xLoc > pBottom.getXLoc() &&   yLoc < pBottom.getYLoc() ) {
            return true;
        }
        
        return false;
    }
    
	public boolean paddleTopHitbox(PaddleTop pTop) {
	    	
	    	int hitboxX = pTop.getHeight() + pTop.getXLoc();
	    	int hitboxY =  pTop.getYLoc() + pTop.getWidth() ;
	    	
	        if (yLoc < hitboxY && xLoc < hitboxX && xLoc > pTop.getXLoc() &&   yLoc > pTop.getYLoc() ) {
	            return true;
	        }
	        
	        return false;
	    }

    
    public int getX() {
        return xLoc;
    }

    public int getY() {
        return yLoc;
    }


}
