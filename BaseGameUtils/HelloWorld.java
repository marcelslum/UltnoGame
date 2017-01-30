//*******************************************************************
// NOTE: please read the 'More Info' tab to the right for shortcuts.
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class

// one class needs to have a main() method
public class HelloWorld
{
  

  
  
  
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
    
    
    Ball ball1 = new Ball();
		ball1.dvx = 2f;
		ball1.dvy = 3f;
    	ball1.positionX = 2f;
		ball1.positionY = 2f;
		ball1.mass = 1f;
		
		Ball ball2 = new Ball();
		ball2.dvx = -2f;
		ball2.dvy = -2f;
        ball2.positionX = 1f;
		ball2.positionY = 1f;
		ball2.mass = 1f;
		
		float mass = ball1.mass;
		
		double theta = -Math.atan2(ball2.positionY - ball1.positionY, ball2.positionX - ball1.positionX);
    
    
    	double a1 = Math.atan2(ball1.dvy, ball1.dvx);
    	double a2 = Math.atan2(ball2.dvy, ball2.dvx);
    
    
    	System.out.println("theta "+theta);
    	System.out.println("a1 "+Math.toDegrees(a1));
    	System.out.println("a1 "+Math.toDegrees(a2));
                    
        double v1x = Utils.getXRotatedFromRad(ball1.dvx, ball1.dvy, theta);
    	//System.out.println("v1x "+v1x);
        double v1y = Utils.getYRotatedFromRad(ball1.dvx, ball1.dvy, theta);
    	//System.out.println("v1y "+v1y);
        double v2x = Utils.getXRotatedFromRad(ball2.dvx, ball2.dvy, theta);
    	//System.out.println("v2x "+v2x);	
        double v2y = Utils.getYRotatedFromRad(ball2.dvx, ball2.dvy, theta);
    	//System.out.println("v2y "+v2y);
        

        double f1x = v1x * (mass - ball2.mass)/(mass + ball2.mass) + v2x * 2 * ball2.mass/(mass + ball2.mass);
    	//System.out.println("f1x "+f1x);
        double f1y = v1y;
    	//System.out.println("f1y "+f1y);
        
        double f2x = v2x * (ball2.mass - mass)/(mass + ball2.mass) + v1x * 2 * mass/(mass + ball2.mass);
    	//System.out.println("f2x "+f2x);
        double f2y = v2y;
    	//System.out.println("f2y "+f2y);
        
        double dvx = Utils.getXRotatedFromRad(f1x, f1y, -theta);
        double dvy = Utils.getYRotatedFromRad(f1x, f1y, -theta);
        double otherBallDvx = Utils.getXRotatedFromRad(f2x, f2y, -theta);
		double otherBallDvy = Utils.getYRotatedFromRad(f2x, f2y, -theta);
    
    
    	//System.out.println(dvx);
        //System.out.println(dvy);
        //System.out.println(otherBallDvx);
        //System.out.println(otherBallDvy);
    
        double af1 = Math.atan2(dvy, dvx);
    	double af2 = Math.atan2(otherBallDvy, otherBallDvx);
    
    	System.out.println("af1 "+Math.toDegrees(af1));
    	System.out.println("af2 "+Math.toDegrees(af2));
    
    
    
    	
    
  }
}

// you can add other public classes to this editor in any order
class Ball{
  public float dvx;
  public float dvy;
  public float mass;
  public float positionY;
  public float positionX;
  public Ball(){

  }

}


class Utils{
  
     public static double getXRotatedFromRad(double x, double y, double angle){
        return x * Math.cos(angle) - y * Math.sin(angle);
    }

    public static double getYRotatedFromRad(double x, double y, double angle){
        return x * Math.sin(angle) + y * Math.cos(angle);
	}
}
  
  
  