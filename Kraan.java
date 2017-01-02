import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kraan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kraan extends Actor
{
    public KraanGrijper Grijper;
    public boolean IsPaused;
    
    public void act() 
    {
        if(!IsPaused)
        {
            if(Greenfoot.isKeyDown("w"))           
            {
                setLocation(getX(), getY() - 1);
            } else if(Greenfoot.isKeyDown("s"))           
            {
                setLocation(getX(), getY() + 1);
            }
           Grijper.MatchYWithCrane();
        }
    }    
}
