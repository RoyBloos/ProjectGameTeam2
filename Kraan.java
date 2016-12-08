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
    /**
     * Act - do whatever the Kraan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
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
