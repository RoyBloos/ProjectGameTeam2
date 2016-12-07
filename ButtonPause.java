import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ButtonPause extends Actor
{
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new WorldPauseMenu(1280, 768, getWorld()));
        }    
    }
}
