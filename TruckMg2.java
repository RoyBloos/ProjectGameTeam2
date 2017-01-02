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
    public boolean IsLoaded = false;;
    public ArrayList<ContainerMG2> containers;
    public boolean IsPaused;
    
    public TruckMg2()
    {
        containers = new ArrayList<ContainerMG2>();
        rand = new Random();
        int number = randInt(1,3);
        if(number == 1)
        {
            Color = "Blauw";
        } else if (number == 2)
        {
            Color = "Groen";
        } else
        {
            Color = "Grijs";
        }
        setImage("TruckMg2" + Color + ".png");
        setRotation(270);
    }
    
    public void act() 
    {
        if(!IsPaused)
        {
            if(((!IsLoaded && getY() > 180) || IsLoaded) && getObjectsAtOffset(0, -50, TruckMg2.class).size() == 0)
            {
                move(1);
            }
            
            if(isAtEdge())
            {
                World world = getWorld();
                for(ContainerMG2 cont : containers)
                {
                    cont.Boat = null;
                    cont.Truck = null;
                    cont.Grijper = null;
                    world.removeObject(cont);
                }
                world.removeObject(this);
            }
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
        return containers.size() == 0 || (containers.size() == 1 && container.Size == 1);
    }
    
    public void AddContainer(ContainerMG2 container)
    {
        if(container.Color == this.Color)
        {
            containers.add(container);
            container.Truck = this;
            container.Grijper = null;
            container.SetOffsets(this);
            
            int lading = 0;
            for(ContainerMG2 cont : containers)
            {
                lading += cont.Size;
            }
            IsLoaded = lading == 2;
                
        }
    }
    
    public ContainerMG2 GetContainer()
    {
        if(containers.size() > 0 && !IsLoaded)
        {
            ContainerMG2 container = containers.get(0);
            containers.remove(container);
            container.Truck = null;
            return container;
        }
        return null;
    }
}
