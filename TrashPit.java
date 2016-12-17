import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TrashPit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrashPit extends ActorsMinigame3
{
    /**
     * Act - do whatever the TrashPit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
     public void act() 
    {
        burn();
            
    }
    
    public void burn()
    {
        Actor removePresent = getOneIntersectingObject(Present.class);
                
        if(removePresent != null)
        {
            getWorld().removeObject(removePresent);
        }
    }      
}
