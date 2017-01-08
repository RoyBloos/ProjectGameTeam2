import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kraan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Crane extends Actor
{
    public CraneReacher CraneReacher;
    public boolean IsPaused;
    
    public Crane()
    {
    }
    
    public void act() 
    {
        if(!IsPaused)
        {
            if(CraneReacher.IsPlayerCraneReacher)
            {
                if(Greenfoot.isKeyDown("w"))           
                {
                    setLocation(getX(), getY() - 1);
                } else if(Greenfoot.isKeyDown("s"))           
                {
                    setLocation(getX(), getY() + 1);
                }
                CraneReacher.MatchYWithCrane();
            }
            else
            {
                setLocation(getX(), CraneReacher.getY());
            }
           
        }
    }    
}
