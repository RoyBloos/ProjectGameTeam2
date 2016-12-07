import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoodsGebouw here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoodsGebouw extends Actor
{
    public boolean IsPaused;
    public Loods loods;
    
    public LoodsGebouw(Loods loods)
    {
        this.loods = loods;
    }
    /**
     * Act - do whatever the LoodsGebouw wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!IsPaused)
        {
             if(Greenfoot.mouseClicked(this))
            {
                HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
                world.SelectedLoods = loods;
            }
        }
    }    
}
