import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
/**
 * Write a description of class BoatMg2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoatMg2 extends Actor
{
    public boolean IsCpuBoat = false;
    public boolean IsDocked = false;
    public boolean HasCargo = true;
    private ArrayList<ContainerMG2> containers;
    public WorldMinigame2 ParentWorld;
    
    public BoatMg2(WorldMinigame2 parentWorld, boolean isCpuBoat)
    {
        IsCpuBoat = isCpuBoat;
        containers = new ArrayList<ContainerMG2>();
        ParentWorld = parentWorld;
    }
    
    public void act() 
    {
        if (!IsDocked)
        {
            // IsDocked = false? dan moet de boot varen
            moveBoat();
        }
        
        for(ContainerMG2 container : containers)
        {
            container.SetLocation();
        }
    }
    
    public boolean IsCloseEnough(int x, int y)
    {
        int xInRange = (getX() - x );
        int yInRange = (getY() - y );
        return xInRange >= -6 && xInRange <= 52 && yInRange >= -81 && yInRange <= 130;
    }    
    
    private void moveBoat()
    {
        if (HasCargo)
        {
                // Heeft de boot lading dan komt de boot het veld binnen en moet varen tot Y 380
                setRotation(getAngle(getX(), 380));
        } else
        {
                // Heeft de boot geen laden dan vaart het de haven uit (richting Y = 0
                setRotation(getAngle(getX(), 0));
        }
            
        move(1);
        if(getY() <= 380 && HasCargo)
        {
            // Is de boot op Y 380 of lager EN heeft het nog lading dan IsDocked = true (IsDocked word bij het uitladen van de laatste container weer op false gezet zodat de boot weer vertrekt)
            IsDocked = true;
        }
    }
    
    
     public int getAngle(int targetX, int targetY) 
     {
        int angle = (int) Math.toDegrees(Math.atan2(targetY - getY(), targetX - getX()));
    
        if(angle < 0){
            angle += 360;
        }
    
        return angle;
    }
    
    protected void addedToWorld(World world)
    {
        containers.add(new ContainerMG2(0, 80, this, 1));
        containers.add(new ContainerMG2(-25, 80, this, 1));
        containers.add(new ContainerMG2(-50, 80, this, 1));
        
        containers.add(new ContainerMG2(0, 50, this, 1));
        containers.add(new ContainerMG2(-25, 50, this, 1));
        containers.add(new ContainerMG2(-50, 50, this, 1));
        
        containers.add(new ContainerMG2(0, 20, this, 1));
        containers.add(new ContainerMG2(-25, 20, this, 1));
        containers.add(new ContainerMG2(-50, 20, this, 1));
        
        containers.add(new ContainerMG2(0, -10, this, 1));
        containers.add(new ContainerMG2(-25, -10, this, 1));
        containers.add(new ContainerMG2(-50, -10, this, 1));
        
        containers.add(new ContainerMG2(0, -40, this, 1));
        containers.add(new ContainerMG2(-25, -40, this, 1));
        containers.add(new ContainerMG2(-50, -40, this, 1));
        
        containers.add(new ContainerMG2(0, -70, this, 1));
        containers.add(new ContainerMG2(-25, -70, this, 1));
        containers.add(new ContainerMG2(-50, -70, this, 1));
        
        containers.add(new ContainerMG2(0, -100, this, 1));
        containers.add(new ContainerMG2(-25, -100, this, 1));
        containers.add(new ContainerMG2(-50, -100, this, 1));
        
        containers.add(new ContainerMG2(0, -130, this, 1));
        containers.add(new ContainerMG2(-25, -130, this, 1));
        containers.add(new ContainerMG2(-50, -130, this, 1));
    }
    
    public void RemoveContainer(ContainerMG2 container)
    {
        containers.remove(containers.indexOf(container));
    }
    
    public void AddContainer(ContainerMG2 container)
    {
        containers.add(container);
    }
    
     public ContainerMG2 GetContainer(int x, int y)
    {
        List<ContainerMG2> containers = getObjectsAtOffset(x - getX(), y - getY(), ContainerMG2.class);
        if(containers.size() > 0)
        {
            ContainerMG2 container = containers.get(0);
            containers.remove(container);
            container.Boat = null;
            return container;
        }
        return null;
    }
}
