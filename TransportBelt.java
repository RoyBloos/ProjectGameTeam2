import greenfoot.*;

public class TransportBelt extends ActorMinigame3
{
    WorldMinigame3 parentWorld;
    DeliveryPackage deliveryPackage;
    ActorMinigame3 packageDestination;
    TrashCan destinationCan;
    DeliveryTruck destinationTruck;
    int difficultyLevel;
    public TransportBelt(WorldMinigame3 parentWorld)
    {
        this.parentWorld = parentWorld;       
        difficultyLevel = 1;
    }
    public void act()
    {
        deliveryPackage = parentWorld.deliveryPackage;
        packageDestination = deliveryPackage.destination;
        destinationCan = parentWorld.trashCan;
        destinationTruck = parentWorld.deliveryTruck;        
        if (packageDestination == destinationCan)
        {
            deliveryPackage.directionX = destinationCan.getX();
            deliveryPackage.directionY = destinationCan.getY();
        }
        else if (packageDestination == destinationTruck)
        {
            if (deliveryPackage.getY() < destinationTruck.getY())
            {
                deliveryPackage.directionX = deliveryPackage.getX();
            }
            else 
            {
                deliveryPackage.directionX = destinationTruck.getX();
            }
            deliveryPackage.directionY = destinationTruck.getY();
        }
        deliveryPackage.speed = difficultyLevel;
    }
}
