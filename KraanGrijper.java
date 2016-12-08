import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KraanGrijper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KraanGrijper extends Actor
{
    public Kraan Kraan;
    public KraanGrijper(Kraan kraan)
    {
        this.Kraan = kraan;
    }
    /**
     * Act - do whatever the KraanGrijper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("a"))           
        {
            setLocation(getX() - 1, getY());
            
        } else if(Greenfoot.isKeyDown("d"))           
        {
            setLocation(getX() + 1, getY());
        }
    } 
    
    public void MatchYWithCrane()
    {
        setLocation(getX(), Kraan.getY());
    }
}
