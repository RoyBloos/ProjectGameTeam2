import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldMinigame3 extends World
{
    public WorldMinigame3(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        addObject(new ButtonPause(), 100, 100);
    }
}
