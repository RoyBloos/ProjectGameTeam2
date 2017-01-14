import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Boat extends PausableActor
{
    private Harbor assignedHarbor;
    private boolean isDocked;
    private int numberOfContainers;
    private Boatlane assignedBoatlane;
    private int moveCounter = 0;
    private int occupiedCounter;
    private boolean reachedEntrypoint;
    private int entryPointYOFfset = 150;
    private Random rand;
    private boolean hasLoods;
    private boolean releasedFromHarbor;

    public Boat()
    {
        rand = new Random();
    }

    public void setHasLoods(boolean hasLoods)
    {
        this.hasLoods = hasLoods;
    }

    public void setReleasedFromHarbor(boolean released)
    {
        releasedFromHarbor = released;
    }

    public int getOccupiedCountrer()
    {
        return occupiedCounter;
    }

    public void setOccupiedCountrer(int counterValue)
    {
        occupiedCounter = counterValue;
    }

    public void setAssignedBoatlane(Boatlane boatlane)
    {
        assignedBoatlane = boatlane;
    }

    public Boatlane getAssignedBoatlane()
    {
        return assignedBoatlane;
    }

    public void setIsDocked(boolean isDocked)
    {
        this.isDocked = isDocked;
    }

    public boolean getIsDocked()
    {
        return isDocked;
    }

    public int getNumberOfContainers()
    {
        return numberOfContainers;
    }

    public void act() 
    {
        if(!getIsPaused())
        {
            HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
            if(Greenfoot.mouseClicked(this) && world.getSelectedPilot() != null)
            {
                world.getSelectedPilot().setSelectedBoat(this);
                world.setSelectedPilot(null);
            }

            if(assignedHarbor == null && hasLoods)
            {
                wijsHavenToe();
            }

            if(occupiedCounter > 0)
            {
                occupiedCounter -=1;
            }

            moveBoat();
        }
    }

    public void UnloadContainers(int containers)
    {
        if(numberOfContainers > containers)
        {
            HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
            if(containers <= numberOfContainers)
            {
                world.AddPoints(containers);
            } else
            {
                world.AddPoints(numberOfContainers);
            }
        }
        numberOfContainers = numberOfContainers - containers;

        if(numberOfContainers <= 0)
        {
            numberOfContainers = 0;
            reachedEntrypoint = false;
        }

        setImage("Vrachtschip" + numberOfContainers + ".png");
    }

    private void moveBoat()
    {
        if(assignedHarbor != null && (!isDocked || releasedFromHarbor))
        {
            //Gaat richting het entrypoint van de haven
            if(!reachedEntrypoint)
            {
                moveTowardsHarborEntrypoint();
            } else if(numberOfContainers > 0)
            {
                setRotation(getAngle(assignedHarbor.getX(), assignedHarbor.getY())+180 );
                turnTowards(assignedHarbor.getX(), assignedHarbor.getY());
            } else
            {
                setRotation(getAngle(0, getY())+180 );
                turnTowards(0, getY());
            }
        } 
        if(moveCounter % 2 == 0 && (!isDocked || releasedFromHarbor))
        {
            move(1);
        }
        moveCounter++;

        if(isAtEdge())
        {
            HavenmeesterWorld havenmeesterWorld =  (HavenmeesterWorld)getWorld();
            havenmeesterWorld.RemoveBoat(this);
        }
    }

    private void moveTowardsHarborEntrypoint()
    {
        setRotation(getAngle(assignedHarbor.getX(), assignedHarbor.getY() + entryPointYOFfset)+180 );
        turnTowards(assignedHarbor.getX(), assignedHarbor.getY() + entryPointYOFfset);
        if(getX() == assignedHarbor.getX() && getY() == assignedHarbor.getY() + entryPointYOFfset)
        {
            reachedEntrypoint = true;
        }
    }

    private void wijsHavenToe()
    {
        for(Harbor harbor : getWorld().getObjects(Harbor.class)){
            if(!harbor.getIsOccupied()){
                assignedHarbor = harbor;
                harbor.SetBoat(this);
                return;
            }
        }
    }

    public int getAngle(int targetX, int targetY) {
        int angle = (int) Math.toDegrees(Math.atan2(targetY - getY(), targetX - getX()));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    protected void addedToWorld(World world)
    {
        numberOfContainers = randInt(1,21);
        setRotation(assignedBoatlane.getDirection());
        setImage("Vrachtschip" + numberOfContainers + ".png");
    }

    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
