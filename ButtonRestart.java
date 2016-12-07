import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ButtonRestart extends Actor
{
    World openWorld;
    public ButtonRestart(World toWorld)
    {
        openWorld = toWorld;
    }
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            //Greenfoot.setWorld(new openWorld()); //Moet nog uitzoeken hoe. Maar er moet een nieuwe instantie van de desbetreffende World class worden gemaakt.
        }    
    }
}
