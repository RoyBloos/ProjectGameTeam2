import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ButtonSelectMinigame extends Actor
{
    public World World;
    public ButtonSelectMinigame(World world, String imageName)
    {
        this.World = world;
        setImage(imageName);
    }    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(World);
        }
    }
}
