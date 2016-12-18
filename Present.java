import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Present here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Present extends Actor
{
    /**
     * Act - do whatever the Present wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
          
    private int speed = 10;
    public int pickPresent = 0;
    private Random rand;
    
    public Present()
    {
        rand = new Random();
    }
    
    public void act() 
    {
        pickPresent();
        fall();
        
       
              
    }
   
    public void pickPresent()
    {
        Actor movePresent = getOneIntersectingObject(TargetMg3.class);
         
        pickPresent++;
        if(movePresent != null)
        {
            if (Greenfoot.isKeyDown("right"))
            {
                setLocation(1200, 600);
            }
            
            if (Greenfoot.isKeyDown("left"))
            {
                setLocation(100, 600);
            }
            if (Greenfoot.isKeyDown("d"))
            {
                move(5);
            }
            if (Greenfoot.isKeyDown("a"))
            {
                move(-5);
            
            }
        }
    }
      
    
    
    public void fall()
    {
        setLocation ( getX(), getY() + speed);
    }
               
}
