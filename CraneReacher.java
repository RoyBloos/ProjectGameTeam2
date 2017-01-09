import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class KraanGrijper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CraneReacher extends Actor
{
    public Crane Crane;
    public ContainerMG2 CurrentContainer = null;
    public ContainerMG2 ContainerToPickUp = null;
    public TruckMg2 TargetTruck = null;
    public BoatMg2 Boat;
    public boolean IsPaused;
    public boolean IsPlayerCraneReacher;
    
    public CraneReacher(Crane crane, BoatMg2 boat, boolean isPlayerCraneReacher)
    {
        this.Crane = crane;
        this.Boat = boat;
        IsPlayerCraneReacher = isPlayerCraneReacher;
    }
   
    public void act() 
    {
        if(!IsPaused)
        {
            if(IsPlayerCraneReacher)
            {
                playerAct();
            } else{
                cpuAct();
            }
        }
    } 
    
    private void playerAct()
    {
        if(Greenfoot.isKeyDown("a"))           
        {
            setLocation(getX() - 1, getY());
            
        } else if(Greenfoot.isKeyDown("d"))           
        {
            setLocation(getX() + 1, getY());
        }else if(Greenfoot.isKeyDown("q") && CurrentContainer == null)           
        {
            GrijpContainer();
        } else if(Greenfoot.isKeyDown("e") && CurrentContainer != null)
        {
            DropContainer();
        }
    }
    
    private void cpuAct()
    {
        WorldMinigame2 world = (WorldMinigame2)getWorld();

        if(CurrentContainer == null)
        {
            if(ContainerToPickUp == null)
            {
                ContainerToPickUp = world.CpuBoat.GetCpuContainer();
            } 
            else 
            {
                if(getX() != ContainerToPickUp.getX() || getY() != ContainerToPickUp.getY())
                {
                    turnTowards(ContainerToPickUp.getX(), ContainerToPickUp.getY());    
                } else 
                {
                    GrijpContainer();
                }
            }
            move(1);
        }
        else if(TargetTruck == null)
        {
            if(ContainerToPickUp == null)
            {
                CurrentContainer = null;
            } 
            else
            {
                TargetTruck = world.GetTruck(ContainerToPickUp.Color);
                ContainerToPickUp = null; 
            }
        }
        else if (TargetTruck.getWorld() != null)
        {
            int yOffset;
            
            if(TargetTruck.Containers.size() == 0)
            {
                yOffset = -8;
            } else
            {
                yOffset = 25;
            }
            
            if(CurrentContainer.getX() == TargetTruck.getX() && CurrentContainer.getY() == TargetTruck.getY() + yOffset)
            {
                DropContainer();
            } else
            {
                turnTowards(TargetTruck.getX(), TargetTruck.getY() + yOffset);                    
            }
            move(1);
        }
        
        
    }
    
    public void MatchYWithCrane()
    {
        int limit, minX, maxX;
        int x = getX();
        if(IsPlayerCraneReacher)
        {
            minX = 665;
            maxX = 1100;
        } 
        else
        {
            minX = 180;
            maxX = 615;
        }
        
        if (getX() < minX)
        {
            x = minX;
        } 
        else if  (getX() > maxX)
        {
            x = maxX;
        }
        
        setLocation(x, Crane.getY());
    }
    
    private void DropContainer()
    {
        for(BoatMg2 boat : getIntersectingObjects(BoatMg2.class))
        {
            if(boat.IsCloseEnough(getX(),getY()))
            {
                DropContainerOnBoat(boat);
            }
        }
        
        if(IsPlayerCraneReacher)
        {
            if(getIntersectingObjects(TruckMg2.class).size() == 1)
            {
                DropContainerOnTruck(getIntersectingObjects(TruckMg2.class).get(0));
            }    
        }
        else
        {
            for(TruckMg2 truck : getIntersectingObjects(TruckMg2.class))
            {
                if(truck == TargetTruck)
                {
                    DropContainerOnTruck(truck);
                }
            }
        }
    }
    
    private void DropContainerOnBoat(BoatMg2 boat)
    {
        List<ContainerMG2> containers = getObjectsInRange(20, ContainerMG2.class);
        int index = containers.indexOf(CurrentContainer);
        if(containers.size() > index)
        {
            containers.remove(index);
        }
        
        if(containers.isEmpty())
        {
            CurrentContainer.getImage().scale(23,23);
            CurrentContainer.Boat = boat;
            CurrentContainer.CraneReacher = null;
            CurrentContainer.SetOffsets(boat);
            Boat.AddContainer(CurrentContainer);
            CurrentContainer = null;
            TargetTruck = null;
            ContainerToPickUp = null;
            ContainerToPickUp = null;
        }
    }
    
    private void DropContainerOnTruck(TruckMg2 truck)
    {
        if(truck.CanStoreContainer(CurrentContainer))
        {
            CurrentContainer.getImage().scale(23,23);
            truck.AddContainer(CurrentContainer);
            CurrentContainer.CraneReacher = null;
            CurrentContainer = null;
            TargetTruck = null;
        }
    }
    
    private void GrijpContainer()
    {
        if(CurrentContainer == null)
        {
            List<TruckMg2> trucks = getIntersectingObjects(TruckMg2.class);
            if(trucks.size() == 1)
            {
                CurrentContainer = trucks.get(0).GetContainer();
                if(CurrentContainer != null)
                {
                    CurrentContainer.CraneReacher = this;
                    CurrentContainer.getImage().scale(26,26);
                }
            }
            
            List<BoatMg2> boats = getIntersectingObjects(BoatMg2.class);
            if(boats.size() == 1)
            {
                CurrentContainer = boats.get(0).GetContainer(getX(), getY());
                if(CurrentContainer != null)
                {
                    boats.get(0).RemoveContainer(CurrentContainer);
                    CurrentContainer.CraneReacher = this;
                    CurrentContainer.getImage().scale(26,26);
                }
            }
        }
        
    }
}
