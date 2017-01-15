import greenfoot.*;

public class WorldMinigame3 extends World
{
    WorldMainMenu parentWorld;
    TransportBelt transportBelt;
    ControlArea controlArea;
    DeliveryTruck deliveryTruck;
    TrashCan trashCan;    
    DeliveryPackage deliveryPackage;
    public WorldMinigame3(WorldMainMenu parentWorld, int gameWidth, int gameHeight)
    {    
        super(gameWidth, gameHeight, 1);
        String className = this.getClass().getName();
        GreenfootImage defaultImage = new GreenfootImage("images/Minigame3/" + className + "_default.png");
        setBackground(defaultImage);
        this.parentWorld = parentWorld;
        createStaticObjects(gameWidth, gameHeight);  
    }
    public void act()
    {
        createDynamicObjects();
    }
    private void createStaticObjects(int gameWidth, int gameHeight)
    {
        transportBelt = new TransportBelt(this);
        addObject(transportBelt, gameWidth / 16 * 11, gameHeight / 8 * 3);
        
        controlArea = new ControlArea(this);
        addObject(controlArea, transportBelt.getX() + 87, transportBelt.getY() + 95);
        
        deliveryTruck = new DeliveryTruck(this);
        addObject(deliveryTruck, transportBelt.getX() - 511, transportBelt.getY() + 227);
        
        trashCan = new TrashCan(this);
        addObject(trashCan, transportBelt.getX() + 275, transportBelt.getY() + 162);
    }
    private void createDynamicObjects()
    {
        if (getObjects(DeliveryPackage.class).isEmpty())
        {
            deliveryPackage = new DeliveryPackage(this);
            addObject(deliveryPackage, controlArea.getX(), 1);
        }
    }
}

