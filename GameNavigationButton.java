import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        if(PausableWorld.class.isInstance(parentWorld))
        {
            PausableWorld pausableWorld = (PausableWorld)parentWorld;
            if(!pausableWorld.getIsPaused())
            {
                pausableWorld.PauseWorld(false);
            }
        } else if(WorldMinigame2.class.isInstance(parentWorld))
        {
            if(!((WorldMinigame2)parentWorld).IsPaused)
            {
                ((WorldMinigame2)parentWorld).PauseWorld(false);
            }
        }
    }

    private void ResumeWorld()
    {
        if(PausableWorld.class.isInstance(parentWorld))
        {
            PausableWorld pausableWorld = (PausableWorld)parentWorld;
            if(pausableWorld.getIsPaused())
            {
                pausableWorld.ResumeWorld();
            }
        } else if(WorldMinigame2.class.isInstance(parentWorld))
        {
            if(((WorldMinigame2)parentWorld).IsPaused)
            {
                ((WorldMinigame2)parentWorld).ResumeWorld();
            }
        }
    }

    private void RestartWorld()
    {
        if(PausableWorld.class.isInstance(parentWorld))
        {
            ((PausableWorld)parentWorld).RestartWorld();
        } else if(WorldMinigame2.class.isInstance(parentWorld))
        {
            ((WorldMinigame2)parentWorld).RestartWorld();
        }
    }

    private void StopWorld()
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
