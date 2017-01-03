import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    public boolean IsPaused;
    
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
        if(!IsPaused)
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
                
                if(getIntersectingObjects(TruckMg2.class).size() == 1)
                {
                    DropContainerOnTruck(getIntersectingObjects(TruckMg2.class).get(0));
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
            HuidigeContainer.getImage().scale(23,23);
            HuidigeContainer.Boat = boat;
            HuidigeContainer.Grijper = null;
            HuidigeContainer.SetOffsets(boat);
            Boat.AddContainer(HuidigeContainer);
            HuidigeContainer = null;
        }
    }
    
    private void DropContainerOnTruck(TruckMg2 truck)
    {
        if(truck.CanStoreContainer(HuidigeContainer))
        {
            HuidigeContainer.getImage().scale(23,23);
            truck.AddContainer(HuidigeContainer);
            HuidigeContainer = null;
        }
    }
    
    private void GrijpContainer()
    {
        if(HuidigeContainer == null)
        {
            List<TruckMg2> trucks = getIntersectingObjects(TruckMg2.class);
            if(trucks.size() == 1)
            {
                HuidigeContainer = trucks.get(0).GetContainer();
                if(HuidigeContainer != null)
                {
                    HuidigeContainer.Grijper = this;
                    HuidigeContainer.getImage().scale(26,26);
                }
            }
            
            List<BoatMg2> boats = getIntersectingObjects(BoatMg2.class);
            if(boats.size() == 1)
            {
                HuidigeContainer = boats.get(0).GetContainer(getX(), getY());
                if(HuidigeContainer != null)
                {
                    boats.get(0).RemoveContainer(HuidigeContainer);
                    HuidigeContainer.Grijper = this;
                    HuidigeContainer.getImage().scale(26,26);
                }
            }
        }
        
    }
}
