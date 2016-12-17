import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldMinigame3 extends World
{
    public TrashPit trashPit;
    public int ScoreToReach = 1;    
    public WorldMinigame3(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        addObject(new ConveyorBelt(), 600, 280);
        trashPit = new TrashPit();
        addObject(trashPit, 1000, 300);
    }
}
