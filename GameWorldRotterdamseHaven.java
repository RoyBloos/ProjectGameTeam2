import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorldRotterdamseHaven here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorldRotterdamseHaven extends World
{

    /**
     * Constructor for objects of class GameWorldRotterdamseHaven.
     * 
     */
    public GameWorldRotterdamseHaven()
    {    
        // Create a new world with 1268x768 cells with a cell size of 1x1 pixels.
        super(1268, 768, 1);
        addObject(new HavenmeesterButton(),50,50);
    }
    
    public void Act()
    {
        int x = 1;
    }
}
