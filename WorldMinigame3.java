import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class WorldMinigame3 extends World
{
    
    public int ScoreToReach = 1; 
    public int presentCounter;
    public ArrayList<Present> NewPresents;
    private TruckMg3 truck;
    private TrashPit trashpit;
    private TargetMg3 target;
    
    public WorldMinigame3(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        
        truck = new TruckMg3();
        trashpit = new TrashPit();
        target = new TargetMg3();
        
        addObject(new ConveyorBelt(), 600, 280);
        addObject(trashpit, 1000, 600);
        addObject(target, 600, 600);
        addObject(truck, 100, 600);
        
        addObject( new Present(), 600, 0);
        
        addObject(new TargetMg3(), 688, 493);
        
        presentCounter = 0;
        NewPresents = new ArrayList<Present>();
       
        addObject(new Water(), 600, 768);
        
       
        setPaintOrder(Present.class, TargetMg3.class);
    }
     public void act()
    {
        spawnPresents();
         if (Greenfoot.isKeyDown("right"))
            {
                // vind alle presents in target boxje
                for(Present p : target.GeefPresents())
                {
                    p.trashpit = trashpit;
                }
                // zet de pit van elke gevonden present
                
            }
            
            if (Greenfoot.isKeyDown("left"))
            {
                // vind alle presents in target boxje
                 // vind alle presents in target boxje
                for(Present p : target.GeefPresents())
                {
                    p.truck = truck;
                }
                // zet de truck van elke gevonden present
            }
            
            
    }
    private void spawnPresents()
    {
        presentCounter += 1;
        if(NewPresents.size() < 100 && presentCounter % 100 == 0)
        {
            Present newPresent = new Present();
            addObject(newPresent, 600, 0);
            NewPresents.add(newPresent);
            presentCounter = 0;
    
        }
    }
}
