import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class TruckMg2 extends PausableActor
{
    private String color;
    private Random rand;
    private boolean isLoaded = false;
    private ArrayList<ContainerMG2> containers;
    private boolean isPlayerTruck;
    private int startingX;
    private boolean isUitgevoegd;

    public TruckMg2(boolean isPlayerTruck, String color)
    {
        this.isPlayerTruck = isPlayerTruck;
        containers = new ArrayList<>();
        rand = new Random();
        this.color = color;
        setImage("TruckMg2" + color + ".png");
        setRotation(270);
    }

    public String getColor()
    {
        return color;
    }

    public boolean getIsLoaded()
    {
        return isLoaded;
    }

    public List<ContainerMG2> getContainers()
    {
        return containers;
    }

    public boolean getIsPlayerTruck()
    {
        return isPlayerTruck;
    }

    public void act() 
    {
        if(!getIsPaused())
        {
            if(!isLoaded && getY() > 180)
            {
                moveInDirectionWithObstacleChecking( 0, -50);
            }

            if(isLoaded)
            {
                if(!isUitgevoegd)
                {
                    truckUitvoegen();
                } else {
                    moveInDirectionWithObstacleChecking( 0, -50);
                }
            }

            removeTruckWhenAtEdge();

        }
    }  

    private void removeTruckWhenAtEdge()
    {
        if(isAtEdge())
        {
            WorldMinigame2 world = (WorldMinigame2)getWorld();

            for(ContainerMG2 cont : containers)
            {
                cont.setBoat(null);
                cont.setTruck(null);
                cont.setCraneReacher(null);
                world.removeObject(cont);
            }
            world.RemoveTruck(this);
        }
    }

    private void truckUitvoegen()
    {
        if(isPlayerTruck)
        {
            isUitgevoegd = getX() >= startingX + 50;
            moveInDirection(50, -50);
        }
        else
        {
            isUitgevoegd = getX() <= startingX - 50;
            moveInDirection(-50, -50);
        }
    }

    private void moveInDirection(int xOffset, int yOffset)
    {
        turnTowards(getX() + xOffset, getY() + yOffset);
        for(ContainerMG2 cont : containers)
        {
            cont.turnTowards(getX() + xOffset, getY() + yOffset);
        }
        move(1);
    }

    private void moveInDirectionWithObstacleChecking(int xOffset, int yOffset)
    {
        turnTowards(getX() + xOffset, getY() + yOffset);
        if(getObjectsAtOffset(xOffset, yOffset, TruckMg2.class).size() == 0)
        {
            move(1);
        }
    }

    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public boolean IsCloseEnough(int x, int y)
    {
        int xInRange = getX() - x;
        int yInRange = getY() - y;
        return xInRange >= -7 && xInRange <= 7 && yInRange >= -7 && yInRange <= 7;
    } 

    public boolean CanStoreContainer(ContainerMG2 container)
    {
        return containers.isEmpty() || (containers.size() == 1 && container.getSize() == 1);
    }

    public void AddContainer(ContainerMG2 container)
    {
        if(container.getColor() == color)
        {
            containers.add(container);
            container.setTruck(this);
            container.setCraneReacher(null);
            container.SetOffsets(this);

            isLoaded = containers.size() >= 2;

        }
    }

    public ContainerMG2 GetContainer()
    {
        if(!containers.isEmpty() && !isLoaded)
        {
            ContainerMG2 container = containers.get(0);
            containers.remove(container);
            container.setTruck(null);
            return container;
        }
        return null;
    }

    protected void addedToWorld(World world)
    {
        startingX = getX();
    }
}
