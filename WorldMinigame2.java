import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class WorldMinigame2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldMinigame2 extends World
{
    public Kraan KraanSpeler;
    public KraanGrijper KraanGrijperSpeler;
    public int ScoreToReach = 1;
    
    public WorldMinigame2(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        spawnBoats();
        KraanSpeler = new Kraan();
        KraanGrijperSpeler = new KraanGrijper(KraanSpeler);
        KraanSpeler.Grijper = KraanGrijperSpeler;
        
        addObject(KraanGrijperSpeler, 1100, 300);
        addObject(KraanSpeler,900,300);
        
        setPaintOrder(Kraan.class, KraanGrijper.class, ContainerMG2.class, BoatMg2.class);
    }
    
    public void act()
    {
        
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
            addObject(new BoatMg2(this, true), 560, getWidth());
        }
        
        if (!hasPlayerBoat)
        {
            addObject(new BoatMg2(this, false), 760, getWidth());
        }
    }
}
