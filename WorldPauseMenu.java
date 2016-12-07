import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldPauseMenu extends World
{
    World toWorld;
    public WorldPauseMenu(int gameHeight, int gameWidth, World fromWorld)
    {    
        super(gameHeight, gameWidth, 1);
        toWorld = fromWorld;
        addObject(new ButtonResume(toWorld), 400, 300);
        addObject(new ButtonRestart(toWorld), 500, 300);
        addObject(new ButtonStop(), 600, 300);
    }
}
