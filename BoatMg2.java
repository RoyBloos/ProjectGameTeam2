import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoatMg2 extends PausableActor
{
    private boolean isCpuBoat = false;
    private boolean isDocked = false;
    private boolean hasCargo = true;
    private ArrayList<ContainerMG2> containers;
    private WorldMinigame2 parentWorld;
    private int xMiddle = -25;
    private double maxWeightOneSide = 15000;
    private boolean isOutOfBalance = false;
    private int rows = 8;
    private Random rand;
    private BalanceBar balanceBar;

    public BoatMg2(WorldMinigame2 parentWorld, boolean isCpuBoat)
    {
        this.isCpuBoat = isCpuBoat;
        containers = new ArrayList<>();
        this.parentWorld = parentWorld;
        rand = new Random();
    }
    
    public boolean getIsOutOfBalance()
    {
        return isOutOfBalance;
    }
    
    public void setIsOutOfBalance(boolean balance)
    {
        isOutOfBalance = balance;
    }
    
    public double getMaxWeightOneSide()
    {
        return maxWeightOneSide;
    }
    
    public int getxMiddle()
    {
        return xMiddle;
    }
    
    public boolean getIsCpuBoat()
    {
        return isCpuBoat;
    }
    
    public WorldMinigame2 getParentWorld()
    {
        return parentWorld;
    }
    
    public void act() 
    {
        if(!getIsPaused())
        {
            if (!isDocked)
            {
                // IsDocked = false? dan moet de boot varen
                moveBoat();
            }

            for(ContainerMG2 container : containers)
            {
                container.SetLocation();
            }

            if(balanceBar == null && (getY() + 200) <= 768)
            {
                balanceBar = new BalanceBar(this);
                parentWorld.addObject(balanceBar, xMiddle, getY() + 200);
            }
        }
    }
    
    public int getRows()
    {
        return rows;
    }
    
    public List<ContainerMG2> getContainers()
    {
        return containers;
    }
    
    public boolean IsCloseEnough(int x, int y)
    {
        int xInRange = getX() - x;
        int yInRange = getY() - y;
        return xInRange >= -6 && xInRange <= 52 && yInRange >= -81 && yInRange <= 130;
    }    

    private void moveBoat()
    {
        if (hasCargo)
        {
            // Heeft de boot lading dan komt de boot het veld binnen en moet varen tot Y 380
            setRotation(getAngle(getX(), 380));
        } else
        {
            // Heeft de boot geen laden dan vaart het de haven uit (richting Y = 0
            setRotation(getAngle(getX(), 0));
        }

        move(1);
        if(getY() <= 380 && hasCargo)
        {
            // Is de boot op Y 380 of lager EN heeft het nog lading dan IsDocked = true (IsDocked word bij het uitladen van de laatste container weer op false gezet zodat de boot weer vertrekt)
            isDocked = true;
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
        ArrayList<String> kleuren = new ArrayList<>();
        for(int i = 1; i <= rows; i++)
        {
            kleuren.add("Blauw");
            kleuren.add("Groen");
            kleuren.add("Grijs");

            int randomIndex = randInt(0, kleuren.size() - 1);
            String kleur1 = kleuren.get(randomIndex);
            kleuren.remove(kleur1);

            randomIndex = randInt(0, kleuren.size() - 1);
            String kleur2 = kleuren.get(randomIndex);
            kleuren.remove(kleur2);

            randomIndex = randInt(0, kleuren.size() - 1);
            String kleur3 = kleuren.get(randomIndex);
            kleuren.remove(kleur3);

            int y = 80 - ((i-1)*30);
            containers.add(new ContainerMG2(25, y , this, 1, 100, kleur1));
            containers.add(new ContainerMG2(0, y, this, 1, 100, kleur2));
            containers.add(new ContainerMG2(-25, y, this, 1, 100, kleur3));
        }
    }

    public void RemoveContainer(ContainerMG2 container)
    {
        containers.remove(container);
    }

    public void AddContainer(ContainerMG2 container)
    {
        containers.add(container);
    }

    public ContainerMG2 GetContainer(int x, int y)
    {
        List<ContainerMG2> conts = getObjectsAtOffset(x - getX(), y - getY(), ContainerMG2.class);
        if(!conts.isEmpty())
        {
            ContainerMG2 container = conts.get(0);
            containers.remove(container);
            container.setBoat(null);
            return container;
        }
        return null;
    }

    public ContainerMG2 GetCpuContainer()
    {
        while (containers != null && !containers.isEmpty() && parentWorld.getCpuTrucks() != null && parentWorld.getCpuTrucks().size() > 0)
        {
            int randomIndex = randInt(0, containers.size() -1);
            ContainerMG2[] containerArray = containers.toArray(new ContainerMG2[containers.size()]);
            for(TruckMg2 cpuTruck : parentWorld.getCpuTrucks())
            {
                if(containerArray[randomIndex].getColor() == cpuTruck.getColor())
                {
                    return containerArray[randomIndex];
                }
            }
        }

        return null;
    }

    private int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
