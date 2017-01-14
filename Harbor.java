import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Harbor extends PausableActor
{
    private boolean isOccupied;
    private Boat assignedBoat;
    private int counter = 1;
    private int dockedXLocation;
    private HarborStoplight stoplicht;

    public Harbor(int dockedXLocation)
    {
        this.dockedXLocation = dockedXLocation;
        stoplicht = new HarborStoplight(this, "Groen");

    }

    public boolean getIsOccupied()
    {
        return isOccupied;
    }

    protected void addedToWorld(World world)
    {
        getWorld().addObject(stoplicht, dockedXLocation - 32, 350);
    }

    public void act() 
    {
        if(!getIsPaused())
        {
            putBoatInDockIfCloseEnough();
            unloadContainerIfDocked();
            setTrafficlightToOrangeWhenBoatUnloaded();
        }
    }

    private void putBoatInDockIfCloseEnough()
    {
        if(assignedBoat != null && isSelectedBoatCloseEnough()){
            assignedBoat.setIsDocked(true);
            assignedBoat.setLocation(dockedXLocation, 320);
            assignedBoat.turnTowards(dockedXLocation, 325);
        }
    }

    public void SetBoat(Boat boat)
    {
        isOccupied = true;
        assignedBoat = boat;
        stoplicht.ZetStoplicht("Rood");
    }

    public void ReleaseBoat()
    {
        assignedBoat.setReleasedFromHarbor(true);
        assignedBoat = null;
        isOccupied = false;
    }

    private boolean isSelectedBoatCloseEnough()
    {  
        for(Boat boat : getObjectsInRange(20, Boat.class)){
            if(boat == assignedBoat){
                return true;
            }
        }
        return false;
    }
    
    private void unloadContainerIfDocked()
    {
        if(assignedBoat != null && assignedBoat.getIsDocked()){
            if(counter % 25 == 0){
                assignedBoat.UnloadContainers(1);
            }
            counter++;
        }
    }

    private void setTrafficlightToOrangeWhenBoatUnloaded()
    {
        if(assignedBoat != null && assignedBoat.getNumberOfContainers() <= 0)
        {
            stoplicht.ZetStoplicht("Oranje");
        }
    }
}
