import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;

/**
 * Write a description of class TruckMg2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TruckMg2 extends Actor
{
    public String Color;
    private Random rand;
    public boolean IsLoaded = false;
    public ArrayList<ContainerMG2> Containers;
    public boolean IsPaused;
    public boolean IsPlayerTruck;
    private int startingX;
    
    public TruckMg2(boolean isPlayerTruck, String color)
    {
        IsPlayerTruck = isPlayerTruck;
        Containers = new ArrayList<ContainerMG2>();
        rand = new Random();
        int number = randInt(1,3);
        Color = color;
        setImage("TruckMg2" + Color + ".png");
        setRotation(270);
    }
    
    public void act() 
    {
        if(!IsPaused)
        {
            // Wanneer de truck nog niet volgeladen is en er staat geen andere truck voor hem en hij heeft x 180 nog niet bereikt dan mag deze truck blijven rijden.
            if(((!IsLoaded && getY() > 180)))
            {
                moveInDirectionWithObstacleChecking(270, 0, -50);
            }
            
            // Wanneer de truck volledig geladen is dan moet deze truck draaien naar de vrije baan en rijden tot het daar is en daarna de weg volgen het beeld uit.
            if(IsLoaded)
            {
                if(IsPlayerTruck && getX() < startingX + 50)
                {
                    // Trucks van de speler moeten rechts uitvoegen en trucks
                    moveInDirection(315, 25, -25);
                }
                else
                {
                    // Andere baan bereikt dan weer naar het noorden wijzen en rijden
                    moveInDirection(270, 0, -50);
                }
                
                if(!IsPlayerTruck && getX() > startingX -50)
                {
                    // Trucks van de speler moeten rechts uitvoegen en trucks
                    moveInDirection(225, -25, -25);
                }              
                else
                {
                    // Andere baan bereikt dan weer naar het noorden wijzen en rijden
                    moveInDirection(270, 0, -50);
                }
            }
            
            // Wanneer de truck de rand van het spel haalt dan moet deze verwijdert worden.
            if(isAtEdge())
            {
                WorldMinigame2 world = (WorldMinigame2)getWorld();
                
                for(ContainerMG2 cont : Containers)
                {
                    cont.Boat = null;
                    cont.Truck = null;
                    cont.CraneReacher = null;
                    world.removeObject(cont);
                }
                world.RemoveTruck(this);
            }
        }
    }  
    
    private void moveInDirection(int rotation, int xOffset, int yOffset)
    {
        turnTowards(getX() + xOffset, getY() + yOffset);
        for(ContainerMG2 cont : Containers)
        {
            cont.turnTowards(getX() + xOffset, getY() + yOffset);
        }
        move(1);
    }
    
    private void moveInDirectionWithObstacleChecking(int rotation, int xOffset, int yOffset)
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
        int xInRange = (getX() - x );
        int yInRange = (getY() - y );
        return xInRange >= -7 && xInRange <= 7 && yInRange >= -7 && yInRange <= 7;
    } 
    
    public boolean CanStoreContainer(ContainerMG2 container)
    {
        return Containers.isEmpty() || (Containers.size() == 1 && container.Size == 1);
    }
    
    public void AddContainer(ContainerMG2 container)
    {
        if(container.Color == this.Color)
        {
            Containers.add(container);
            container.Truck = this;
            container.CraneReacher = null;
            container.SetOffsets(this);
            
            IsLoaded = Containers.size() >= 2;
                
        }
    }
    
    public ContainerMG2 GetContainer()
    {
        if(!Containers.isEmpty() && !IsLoaded)
        {
            ContainerMG2 container = Containers.get(0);
            Containers.remove(container);
            container.Truck = null;
            return container;
        }
        return null;
    }
    
    protected void addedToWorld(World world)
    {
        startingX = getX();
    }
}
