import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldMinigame3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldMinigame3 extends World
{

    public WorldMinigame3(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        addObject(new ConveyorBelt(), 600, 280);
    }
}
