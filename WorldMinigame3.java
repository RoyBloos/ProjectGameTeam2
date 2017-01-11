import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WorldMinigame3 extends World
{
    Package currentPackage;
    ConveyorBelt conveyorBelt;
    ConveyorBeltTarget conveyorBeltTarget;
    Truck truck;
    public WorldMinigame3(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1);
        
        conveyorBelt = new ConveyorBelt();
        conveyorBeltTarget = new ConveyorBeltTarget();
        truck = new Truck();
        
        addObject(conveyorBelt, 830, 290);
        addObject(conveyorBeltTarget, conveyorBelt.getX() + 87, conveyorBelt.getY() + 218);
        addObject(truck, conveyorBeltTarget.getY() - 597, conveyorBeltTarget.getY());
    }
    public void act()
    {
        spawnPackage();
    }
    private void spawnPackage()
    {
        if (currentPackage == null)
        {
            currentPackage = new Package(this);
            addObject(currentPackage, conveyorBeltTarget.getX(), 1);
        }
    }
}

