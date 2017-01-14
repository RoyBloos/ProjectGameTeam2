import greenfoot.*;

public class DeliveryPackage extends ActorMinigame3
{
    WorldMinigame3 parentWorld;
    ActorMinigame3 destination;
    int directionX;
    int directionY;
    int speed;    
    public DeliveryPackage(WorldMinigame3 parentWorld)
    {
        this.parentWorld = parentWorld;
        destination = parentWorld.deliveryTruck;
    }
    public void act()
    {
        turnTowards(directionX, directionY);
        move(speed);
        if (intersects(destination))
        {            
            parentWorld.removeObject(this);
        }
    }
}
