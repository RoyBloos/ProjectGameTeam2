import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameNavigationButton extends Actor
{
    private World parentWorld;
    private String knopType;
    
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

    public String getKnopType()
    {
        return knopType;
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            if(knopType == "Pause")
            {
                pauseWorld();
            } else if (knopType == "Resume")
            {
                resumeWorld();
            } else if (knopType == "Restart")
            {
                restartWorld();
            } else if (knopType == "Stop")
            {
                stopWorld();
            }
        }
    }

    private void pauseWorld()
    {
        if(PausableWorld.class.isInstance(parentWorld))
        {
            PausableWorld pausableWorld = (PausableWorld)parentWorld;
            if(!pausableWorld.getIsPaused())
            {
                pausableWorld.PauseWorld(false);
            }
        } 
    }

    private void resumeWorld()
    {
        if(PausableWorld.class.isInstance(parentWorld))
        {
            PausableWorld pausableWorld = (PausableWorld)parentWorld;
            if(pausableWorld.getIsPaused())
            {
                pausableWorld.ResumeWorld();
            }
        }
    }

    private void restartWorld()
    {
        if(PausableWorld.class.isInstance(parentWorld))
        {
            ((PausableWorld)parentWorld).RestartWorld();
        } else if(WorldMinigame2.class.isInstance(parentWorld))
        {
            ((WorldMinigame2)parentWorld).RestartWorld();
        }
    }

    private void stopWorld()
    {
        if(PausableWorld.class.isInstance(parentWorld))
        {
            ((PausableWorld)parentWorld).StopWorld();
        } else if(WorldMinigame2.class.isInstance(parentWorld))
        {
            ((WorldMinigame2)parentWorld).StopWorld();
        }
    }
}
