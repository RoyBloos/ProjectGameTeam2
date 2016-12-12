import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class KraanGrijper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KraanGrijper extends Actor
{
    public Kraan Kraan;
    public ContainerMG2 HuidigeContainer = null;
    public BoatMg2 Boat;
    public KraanGrijper(Kraan kraan, BoatMg2 boat)
    {
        this.Kraan = kraan;
        this.Boat = boat;
    }
    /**
     * Act - do whatever the KraanGrijper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("a"))           
        {
            setLocation(getX() - 1, getY());
            
        } else if(Greenfoot.isKeyDown("d"))           
        {
            setLocation(getX() + 1, getY());
        }else if(Greenfoot.isKeyDown("q") && HuidigeContainer == null)           
        {
            GrijpContainer();
        } else if(Greenfoot.isKeyDown("e") && HuidigeContainer != null)
        {
            for(BoatMg2 boat : getIntersectingObjects(BoatMg2.class))
            {
                if(!boat.IsCpuBoat && boat.IsCloseEnough(getX(),getY()))
                {
                    DropContainerOnBoat(boat);
                }
            }
            
            for(TruckMg2 truck : getObjectsInRange(20, TruckMg2.class))
            {
                if(truck.IsCloseEnough(getX(),getY()) && truck.Color == HuidigeContainer.Color)
                {
                    DropContainerOnTruck(truck);
                }
            }
        }
    } 
    
    public void MatchYWithCrane()
    {
        int x = getX();
        if (x < 665)
        {
            x = 665;
        }
        setLocation(x, Kraan.getY());
    }
    
    private void DropContainerOnBoat(BoatMg2 boat)
    {
        List<ContainerMG2> containers = getObjectsInRange(20, ContainerMG2.class);
        int index = containers.indexOf(HuidigeContainer);
        if(containers.size() > index)
        {
            containers.remove(index);
        }
        
        if(containers.size() == 0)
        {
            HuidigeContainer.Boat = boat;
            HuidigeContainer.SetScale(-15);
            HuidigeContainer.Grijper = null;
            HuidigeContainer.SetOffsets(boat);
            Boat.AddContainer(HuidigeContainer);
            HuidigeContainer = null;
        }
    }
    
     private void DropContainerOnTruck(TruckMg2 truck)
    {
        List<ContainerMG2> containers = getObjectsInRange(0, ContainerMG2.class);
        int index = containers.indexOf(HuidigeContainer);
        if(containers.size() > index)
        {
            containers.remove(index);
        }
        
        if(containers.size() == 0)
        {
            HuidigeContainer.Truck = truck;
            HuidigeContainer.SetScale(-15);
            HuidigeContainer.Grijper = null;
            HuidigeContainer.SetOffsets(truck);
            Boat.AddContainer(HuidigeContainer);
            HuidigeContainer = null;
        }
    }
    
    private void GrijpContainer()
    {
        int bovensteContainer = 0;
        List<ContainerMG2> containers = getObjectsInRange(10, ContainerMG2.class);
        
        for(ContainerMG2 container : containers)
        {
            if(container.Laag > bovensteContainer)
            {
                bovensteContainer = container.Laag;
            }
        }
        
        if(bovensteContainer > 0)
        {
            for(ContainerMG2 container : containers)
            {
                if(container.Laag == bovensteContainer)
                {
                    HuidigeContainer = container;
                    Boat.RemoveContainer(container);
                    HuidigeContainer.Boat = null;
                    HuidigeContainer.Grijper = this;
                    HuidigeContainer.SetScale(15);
                }
            }
        }
    }
}
