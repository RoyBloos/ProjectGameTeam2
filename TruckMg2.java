import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class TruckMg2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TruckMg2 extends Actor
{
    public String Color;
    private Random rand;
    public boolean IsLoaded = false;;
    
    public TruckMg2()
    {
        rand = new Random();
        int number = randInt(1,3);
        if(number == 1)
        {
            Color = "Blauw";
        } else if (number == 2)
        {
            Color = "Groen";
        } else
        {
            Color = "Grijs";
        }
        setImage("TruckMg2" + Color + ".png");
        setRotation(270);
    }
    
    public void act() 
    {
        if(!IsLoaded && getY() > 180 && getObjectsAtOffset(0, -50, TruckMg2.class).size() == 0) 
        {
            move(1);
        }
    }  
    
     public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    
    public boolean IsCloseEnough(int x, int y)
    {
        int xInRange = (getX() - x );
        int yInRange = (getY() - y );
        return xInRange >= -7 && xInRange <= 7 && yInRange >= -7 && yInRange <= 7;
    }    
}
