import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ContainerMG2 extends PausableActor
{
    private int xPositie;
    private int yPositie;
    private int xOffset;
    private int yOffset;
    private Boolean isAddedToWorld;
    private BoatMg2 boat;
    private TruckMg2 truck;
    private CraneReacher craneReacher;
    private String color;
    private int size = 1;
    private double balanceWeight;
    private double weight;

    public ContainerMG2(int xOffset, int yOffset, BoatMg2 boat, int layer, int weight, String color)
    {
        isAddedToWorld = false;
        this.xOffset = boat.getxMiddle() + xOffset;
        this.yOffset = yOffset;
        this.boat = boat;
        xPositie = boat.getX() + xOffset;
        yPositie = boat.getY() + yOffset;
        this.weight = weight;
        this.color = color;

        setImage("MG2Container" + color + ".png");
    }

    public void setBoat(BoatMg2 boat)
    {
        this.boat = boat;
    }
    
    public void setTruck(TruckMg2 truck)
    {
        this.truck = truck;
    }
    
    public void setCraneReacher(CraneReacher craneReacher)
    {
        this.craneReacher = craneReacher;
    }
    
    public String getColor()
    {
        return color;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public double getBalanceWeight()
    {
        return balanceWeight;
    }
    
    public void act() 
    {
        if(!getIsPaused())
        {
            if(boat != null)
            {
                balanceWeight = (getX() - boat.getX() - boat.getxMiddle()) * weight;
            }
            SetLocation();
        }
    }

    public void SetOffsets(Actor actor)
    {
        int actorX = actor.getX();
        int containerX = getX();
        xOffset = -(actorX - containerX);
        int actorY = actor.getY();
        int containerY = getY();
        yOffset = -(actorY - containerY);
    }

    public void SetLocation()
    {
        if(boat != null)
        {
            xPositie = boat.getX() + xOffset;
            yPositie = boat.getY() + yOffset;
        }

        if(truck != null)
        {
            xPositie = truck.getX() + xOffset;
            yPositie = truck.getY() + yOffset;
        }

        if(craneReacher != null)
        {
            xPositie = craneReacher.getX();
            yPositie = craneReacher.getY();
        }
        setLocation(xPositie, yPositie);
        if (!isAddedToWorld && yPositie <= 768)
        {
            boat.getParentWorld().addObject(this, xPositie, yPositie);
            isAddedToWorld = true;
        }
    }

    public void SetScale(int pixels)
    {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() + pixels, image.getHeight() + pixels);
    }
}
