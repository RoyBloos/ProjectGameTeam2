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
    public ArrayList<ContainerMG2> Containers;
    public WorldMinigame2 ParentWorld;
    public boolean IsPaused;
    public int XMiddle = -25;
    public double MaxWeightOneSide = 15000;
    public boolean IsOutOfBalance = false;
    
    private BalanceBar BalanceBar;
    
    public BoatMg2(WorldMinigame2 parentWorld, boolean isCpuBoat)
    {
        IsCpuBoat = isCpuBoat;
        Containers = new ArrayList<ContainerMG2>();
        ParentWorld = parentWorld;
    }
    
    public void act() 
    {
        if(!IsPaused)
        {
            if (!IsDocked)
            {
                // IsDocked = false? dan moet de boot varen
                moveBoat();
            }
            
            for(ContainerMG2 container : Containers)
            {
                container.SetLocation();
            }
            
            if(BalanceBar == null && (getY() + 200) <= 768)
            {
                BalanceBar = new BalanceBar(this);
                ParentWorld.addObject(BalanceBar, XMiddle, getY() + 200);
            }
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
        int rows = 8;
        for(int i = 1; i <= 8; i++)
        {
            int y = 80 - ((i-1)*30);
            Containers.add(new ContainerMG2(25, y , this, 1, 100));
            Containers.add(new ContainerMG2(0, y, this, 1, 100));
            Containers.add(new ContainerMG2(-25, y, this, 1, 100));
        }
    }
    
    public void RemoveContainer(ContainerMG2 container)
    {
        Containers.remove(Containers.indexOf(container));
    }
    
    public void AddContainer(ContainerMG2 container)
    {
        Containers.add(container);
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
