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
          
    private int speed = 5;
    private Random rand;
    public TruckMg3 truck;
    public TrashPit trashpit;
    
    public Present()
    {
        rand = new Random();
        setRotation(90);
    }
    
    public void act() 
    {
        movePresent();
    }
   
    public void movePresent()
    {
        if(truck != null)
        {
            turnTowards(truck.getX(), truck.getY());

        } else if (trashpit != null)
        {
            turnTowards(trashpit.getX(), trashpit.getY());
        }
        
        move(speed);
    }
               
}