import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class WorldMinigame2 extends World
{
    public WorldMinigame2(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1);
        addObject(new ButtonPause(), 100, 100);
    }
    
    public void act()
    {
        spawnBoats();
    }
    
    private void spawnBoats()
    {
        boolean hasCpuBoat = false;
        boolean hasPlayerBoat = false;
        for(BoatMg2 boat : getObjects(BoatMg2.class))
        {
            if(boat.IsCpuBoat)
            {
                hasCpuBoat = true;
            } else
            {
                hasPlayerBoat = true;
            }
        }
        
        if (!hasCpuBoat)
        {
            addObject(new BoatMg2(true), 560, getWidth());
        }
        
        if (!hasPlayerBoat)
        {
            addObject(new BoatMg2(false), 760, getWidth());
        }
    }
}
