import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameNavigationButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameNavigationButton extends Actor
{
    public World parentWorld;
    public String knopType;
    public GameNavigationButton(World parentWorld, String knopType)
    {
        this.parentWorld = parentWorld;
        this.knopType = knopType;
        if(knopType == "Pause")
        {
            setImage("PauseButton.png");
        } else if(knopType == "Resume")
        {
            setImage("ResumeButton.png");
        } else if(knopType == "Restart")
        {
            setImage("RestartButton.png");
        } else if(knopType == "Stop")
        {
            setImage("StopButton.png");
        }
    }
    /**
     * Act - do whatever the GameNavigationButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            if(knopType == "Pause")
            {
                PauseWorld();
            } else if (knopType == "Resume")
            {
                ResumeWorld();
            } else if (knopType == "Restart")
            {
                RestartWorld();
            } else if (knopType == "Stop")
            {
                StopWorld();
            }
        }
    }
    
    private void PauseWorld()
    {
        if(HavenmeesterWorld.class.isInstance(parentWorld))
        {
            if(!((HavenmeesterWorld)parentWorld).IsPaused)
            {
                ((HavenmeesterWorld)parentWorld).PauseWorld();
            }
        }
    }
    
    private void ResumeWorld()
    {
        if(HavenmeesterWorld.class.isInstance(parentWorld))
        {
            if(((HavenmeesterWorld)parentWorld).IsPaused)
            {
                ((HavenmeesterWorld)parentWorld).ResumeWorld();
            }
        }
    }
    
    private void RestartWorld()
    {
        if(HavenmeesterWorld.class.isInstance(parentWorld))
        {
            ((HavenmeesterWorld)parentWorld).RestartWorld();
        }
    }
    
    private void StopWorld()
    {
        if(HavenmeesterWorld.class.isInstance(parentWorld))
        {
            ((HavenmeesterWorld)parentWorld).StopWorld();
        }
    }
}
