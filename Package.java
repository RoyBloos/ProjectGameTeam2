import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Package extends ActorMinigame3
{
    WorldMinigame3 parentWorld;
    public Package(WorldMinigame3 parentWorld)
    {
        this.parentWorld = parentWorld;
        setRotation(90);
    }
    public void act()
    {
        moveForward();
        changeDirection();
    }
    private void moveForward()
    {
        move(1);
    }
    private void changeDirection()
    {
        if (this.getY() == parentWorld.conveyorBeltTarget.getY())
        {
            this.setRotation(180);
        }
    }
}
