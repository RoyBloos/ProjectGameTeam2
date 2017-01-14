import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class CraneReacher extends PausableActor
{
    private Crane crane;
    private ContainerMG2 currentContainer = null;
    private ContainerMG2 containerToPickUp = null;
    private TruckMg2 targetTruck = null;
    private boolean isPlayerCraneReacher;

    public CraneReacher(Crane crane, BoatMg2 boat, boolean isPlayerCraneReacher)
    {
        this.crane = crane;
        this.isPlayerCraneReacher = isPlayerCraneReacher;
    }

    public boolean getIsPlayerCraneReacher()
    {
        return isPlayerCraneReacher;
    }

    public void act() 
    {
        if(!getIsPaused())
        {
            if(isPlayerCraneReacher)
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
        }else if(Greenfoot.isKeyDown("q") && currentContainer == null)           
        {
            grijpContainer();
        } else if(Greenfoot.isKeyDown("e") && currentContainer != null)
        {
            dropContainer();
        }
    }

     public void MatchYWithCrane()
    {
        int minX;
        int maxX;

        int x = getX();
        if(isPlayerCraneReacher)
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

        setLocation(x, crane.getY());
    }
    
    private void cpuAct()
    {
        WorldMinigame2 world = (WorldMinigame2)getWorld();
        if(currentContainer == null)
        {
            if(containerToPickUp == null)
            {
                containerToPickUp = world.getCpuBoat().GetCpuContainer();
            } 
            else 
            {
                moveTowardsContainerAndPickUpWhenReached();
            }
            move(1);
        }
        else if(targetTruck == null)
        {
            getRandomTruck();
        }
        else if (targetTruck.getWorld() != null)
        {
            moveToTruckAndDropContainerWhenReached();

        }

    }

    private void moveToTruckAndDropContainerWhenReached()
    {
        int yOffset;

        if(targetTruck.getContainers().size() == 0)
        {
            yOffset = -8;
        } else
        {
            yOffset = 25;
        }

        if(currentContainer.getX() == targetTruck.getX() && currentContainer.getY() == targetTruck.getY() + yOffset)
        {
            dropContainer();
        } else
        {
            turnTowards(targetTruck.getX(), targetTruck.getY() + yOffset);                    
        }
        move(1);
    }

    private void getRandomTruck()
    {
        if(containerToPickUp == null)
        {
            currentContainer = null;
        } 
        else
        {
            WorldMinigame2 world = (WorldMinigame2)getWorld();
            targetTruck = world.GetTruck(containerToPickUp.getColor());
            containerToPickUp = null; 
        }
    }

    private void moveTowardsContainerAndPickUpWhenReached()
    {
        if(getX() != containerToPickUp.getX() || getY() != containerToPickUp.getY())
        {
            turnTowards(containerToPickUp.getX(), containerToPickUp.getY());    
        } else 
        {
            grijpContainer();
        }
    }

    private void dropContainer()
    {
        for(BoatMg2 curboat : getIntersectingObjects(BoatMg2.class))
        {
            if(curboat.IsCloseEnough(getX(),getY()))
            {
                dropContainerOnBoat(curboat);
            }
        }

        if(isPlayerCraneReacher)
        {
            if(getIntersectingObjects(TruckMg2.class).size() == 1)
            {
                dropContainerOnTruck(getIntersectingObjects(TruckMg2.class).get(0));
            }    
        }
        else
        {
            for(TruckMg2 truck : getIntersectingObjects(TruckMg2.class))
            {
                if(truck == targetTruck)
                {
                    dropContainerOnTruck(truck);
                }
            }
        }
    }

    private void dropContainerOnBoat(BoatMg2 boat)
    {
        List<ContainerMG2> containers = getObjectsInRange(20, ContainerMG2.class);
        int index = containers.indexOf(currentContainer);
        if(containers.size() > index)
        {
            containers.remove(index);
        }

        if(containers.isEmpty())
        {
            currentContainer.getImage().scale(23,23);
            currentContainer.setBoat(boat);
            currentContainer.setCraneReacher(null);
            currentContainer.SetOffsets(boat);
            boat.AddContainer(currentContainer);
            currentContainer = null;
            targetTruck = null;
            containerToPickUp = null;
        }
    }

    private void dropContainerOnTruck(TruckMg2 truck)
    {
        if(truck.CanStoreContainer(currentContainer))
        {
            currentContainer.getImage().scale(23,23);
            truck.AddContainer(currentContainer);
            currentContainer.setCraneReacher(null);
            currentContainer = null;
            targetTruck = null;
        }
    }

    private void grijpContainer()
    {
        if(currentContainer == null)
        {
            List<TruckMg2> trucks = getIntersectingObjects(TruckMg2.class);
            if(trucks.size() == 1)
            {
                currentContainer = trucks.get(0).GetContainer();
                if(currentContainer != null)
                {
                    currentContainer.setCraneReacher(this);
                    currentContainer.getImage().scale(26,26);
                }
            }

            List<BoatMg2> boats = getIntersectingObjects(BoatMg2.class);
            if(boats.size() == 1)
            {
                currentContainer = boats.get(0).GetContainer(getX(), getY());
                if(currentContainer != null)
                {
                    boats.get(0).RemoveContainer(currentContainer);
                    currentContainer.setCraneReacher(this);
                    currentContainer.getImage().scale(26,26);
                }
            }
        }
    }
}
