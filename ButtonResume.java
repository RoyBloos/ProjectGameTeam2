import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ButtonResume extends Actor
{
    World openWorld;
    public ButtonResume(World toWorld)
    {
        openWorld = toWorld;
    }
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(openWorld);
        }    
    }
}
