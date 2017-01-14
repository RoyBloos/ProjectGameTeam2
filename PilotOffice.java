import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PilotOffice extends PausableActor
{
    private Pilot pilot;

    public PilotOffice(Pilot pilot)
    {
        this.pilot = pilot;
    }

    public void act() 
    {
        if(!getIsPaused() && Greenfoot.mouseClicked(this))
        {
            HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
            world.setSelectedPilot(pilot);
        }
    }    
}
