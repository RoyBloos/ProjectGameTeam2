import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldMinigame4 extends World
{
    private int scoreToReach = 1;
    
    public WorldMinigame4(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
    }
    
    public int getScoreToReach()
    {
        return scoreToReach;
    }
}
