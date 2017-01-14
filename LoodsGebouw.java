import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class LoodsGebouw extends PausableActor
{
    public Loods loods;
    
    public LoodsGebouw(Loods loods)
    {
        this.loods = loods;
    }

    public void act() 
    {
        if(!getIsPaused())
        {
             if(Greenfoot.mouseClicked(this))
            {
                HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
                world.setSelectedLoods(loods);
            }
        }
    }    
}
