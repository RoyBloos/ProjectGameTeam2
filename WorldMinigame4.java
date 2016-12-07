import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldMinigame4 extends World
{
    public WorldMinigame4(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        addObject(new ButtonPause(), 100, 100);
    }
}
