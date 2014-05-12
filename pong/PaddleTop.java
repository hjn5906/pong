
package pong;

import java.awt.*;


public class PaddleTop extends Paddle {

   
   
   private int y = 0;
   private int speed = 0;
   private int height = 0;
   private int x = Pong.WIDTH / 2;

   public PaddleTop() {
   }

   public void tick() {
        
      if(x >0){
         x += speed;
      }
      else{
         x = 1;
      }
       
      if(x + HEIGHT >= Pong.WIDTH)
      {
         x = Pong.WIDTH - HEIGHT;
      }
        
   }

   public void paint(Graphics g) {
      g.setColor(Color.blue);
      g.fillRect(x, y, getHeight(), getWidth());
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
   	return x;
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
