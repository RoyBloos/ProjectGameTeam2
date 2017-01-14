import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Crane extends PausableActor
{
    private CraneReacher craneReacher;
    
    public CraneReacher getCraneReacher()
    {
        return craneReacher;
    }
    
    public void setCraneReacher(CraneReacher craneReacher)
    {
        this.craneReacher = craneReacher;
    }
    
    public void act() 
    {
        if(!getIsPaused())
        {
            if(craneReacher.getIsPlayerCraneReacher())
            {
                if(Greenfoot.isKeyDown("w"))           
                {
                    setLocation(getX(), getY() - 1);
                } else if(Greenfoot.isKeyDown("s"))           
                {
                    setLocation(getX(), getY() + 1);
                }
                craneReacher.MatchYWithCrane();
            }
            else
            {
                setLocation(getX(), craneReacher.getY());
            }
           
        }
    }    
}
