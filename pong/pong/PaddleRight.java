
package pong;

import java.awt.*;


public class PaddleRight implements Paddle {

   
   private int x = 5;
   private int y = YLOC;
   private int speed = 0;
   private int height = 0;

   public PaddleRight() {
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
       g.setColor(Color.green);
       g.fillRect(Pong.WIDTH - 16, y, WIDTH, getHeight());
   }

  
   public int getXLoc() {
       return Pong.WIDTH - 16 - WIDTH;
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

    


