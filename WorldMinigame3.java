import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class WorldMinigame3 extends World
{
    public TrashPit trashPit;
    public int ScoreToReach = 1; 
    public int presentCounter;
    public ArrayList<Present> NewPresents;
    
    public WorldMinigame3(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        addObject(new ConveyorBelt(), 600, 280);
        trashPit = new TrashPit();
        addObject(trashPit, 1000, 600);
        
        addObject( new Present(), 600, 0);
        
        addObject(new TargetMg3(), 688, 493);
        
        presentCounter = 0;
       NewPresents = new ArrayList<Present>();
       
       addObject(new Water(), 600, 768);
       addObject( new TruckMg3(), 100, 600);
       
       setPaintOrder(Present.class, TargetMg3.class);
    }
     public void act()
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
