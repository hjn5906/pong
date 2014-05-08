
package pong;

import java.awt.*;

public class Ball {
	
	// instance variables
    private int xLoc = Pong.WIDTH / 2;
    private int yLoc = Pong.HEIGHT / 2;
    private int diamater = 10;
    private int ballX = -3;
    private int ballY = 3;
    
    

    

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(xLoc, yLoc, diamater, diamater);

    }
    
    public void tick() {
    	xLoc += ballX;
        yLoc += ballY;

        if (yLoc < 0) {
            ballY = 3;
        } else if (yLoc + diamater > Pong.HEIGHT - 35) {
            ballY = -ballY;
        }
        
        if (xLoc < 0) {
            ballX = 3;
            
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

    
    
    

    public void hitSomething(PaddleLeft player,PaddleRight player2) {
        if(paddleLeftHitbox(player)){
        	ballX = -ballX;
        }
        if(paddleRightHitbox(player2)){
        	ballX = -ballX;
        }
        
        if(borderHitbox()) {
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

    public boolean borderHitbox() {
        if (yLoc < 5) {
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
