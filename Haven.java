import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Haven here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Haven extends Actor
{
    public boolean IsBezet;
    public Boat ToegewezenBoat;
    private int counter = 1;
    private int dockedXLocation;
        
    public Haven(int dockedXLocation)
    {
        this.dockedXLocation = dockedXLocation;
    }
    
    /**
     * Act - do whatever the Haven wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(ToegewezenBoat != null){
            if(selectedBoatIsCloseEnough()){
                ToegewezenBoat.IsDocked = true;
                ToegewezenBoat.setLocation(dockedXLocation, 320);
                ToegewezenBoat.turnTowards(dockedXLocation, 325);
            }
            
            if(ToegewezenBoat.IsDocked){
                if(counter % 25 == 0){
                    ToegewezenBoat.UnloadContainers(1);
                }
                counter++;
            }
            if(ToegewezenBoat.AantalContainers <= 0)
            {
                IsBezet = false;
                ToegewezenBoat = null;
            }
        }
    }
    
    private boolean selectedBoatIsCloseEnough()
    {  
        for(Boat boat : getObjectsInRange(20, Boat.class)){
            if(boat == ToegewezenBoat){
                return true;
            }
        }
        return false;
    }
    
}
