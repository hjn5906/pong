
package pong;

import java.awt.*;


public class PaddleLeft implements Paddle {

   
   private int x = 5;
   private int y = YLOC;
   private int speed = 0;
   private int height = 0;

   public PaddleLeft() {
   }

   public void tick() {
        
      if(y >0){
         y += speed;
      }
      else{
         y = 10;
      }
       
      if(y + HEIGHT >= Pong.HEIGHT - 20)
      {
         y = Pong.HEIGHT- (HEIGHT + 20);
      }
        
   }

   public void paint(Graphics g) {
      g.setColor(Color.blue);
      g.fillRect(0, getYLoc(), getWidth(), getHeight());
   }

   
   @Override
   public void setSpeed(int v) {
   	// TODO Auto-generated method stub
	   speed = v;
   }
   
   @Override
   public int getSpeed() {
   	return speed;
   	// TODO Auto-generated method stub
   	
   }

 
   public int getWidth() {
      return WIDTH;
   }

   public int getHeight() {
      return HEIGHT;
   }
   
   @Override
   public int getXLoc() {
   	return XLOC;
   	// TODO Auto-generated method stub
   	
   }

   @Override
   public int getYLoc() {
   	return y;
   	// TODO Auto-generated method stub
   	
   }
    
   public void setGrowth() {
      height = HEIGHT *2;
   }


	@Override
	public void setFreeze() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setQuickness() {
		// TODO Auto-generated method stub
		
	}
}
