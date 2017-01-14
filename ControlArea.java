import greenfoot.*;

public class ControlArea extends ActorMinigame3
{
    WorldMinigame3 parentWorld;
    DeliveryPackage deliveryPackage;
    ActorMinigame3 packageDestination;
    public ControlArea(WorldMinigame3 parentWorld)
    {
        this.parentWorld = parentWorld;
    }
    public void act() 
    {
        deliveryPackage = parentWorld.deliveryPackage;
        packageDestination = deliveryPackage.destination;
        if (intersects(deliveryPackage) && packageDestination == parentWorld.deliveryTruck)
        {
            //highlightArea();
            if (Greenfoot.isKeyDown("space"))
            {
                deliveryPackage.destination = parentWorld.trashCan;
            }            
        }
    }
    private void highlightArea()
    {
    }
}
