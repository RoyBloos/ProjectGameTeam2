import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

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
    public BoatMg2 PlayerBoat = null;
    public BoatMg2 CPUBoat = null;
    public ArrayList<TruckMg2> PlayerTrucks;
    public int truckCounter;
    
    public WorldMinigame2(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        PlayerTrucks = new ArrayList<TruckMg2>();
        spawnBoats();
        KraanSpeler = new Kraan();
        KraanGrijperSpeler = new KraanGrijper(KraanSpeler, PlayerBoat);
        KraanSpeler.Grijper = KraanGrijperSpeler;
        truckCounter = 0;
        addObject(KraanGrijperSpeler, 1100, 300);
        addObject(KraanSpeler,900,300);
        
        setPaintOrder(Kraan.class, KraanGrijper.class, ContainerMG2.class, BoatMg2.class);
    }
    
    public void act()
    {
        truckCounter += 1;
        if(PlayerTrucks.size() < 7 && truckCounter % 150 == 0)
        {
            TruckMg2 newTruck = new TruckMg2();
            addObject(newTruck, 967, 768);
            PlayerTrucks.add(newTruck);
            truckCounter = 0;
        }
            
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
            CPUBoat = new BoatMg2(this, true);
            addObject(CPUBoat, 560, getWidth());
        }
        
        if (!hasPlayerBoat)
        {
            PlayerBoat = new BoatMg2(this, false);
            addObject(PlayerBoat, 760, getWidth());
        }
    }
}
