import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PausableWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PausableWorld extends World
{

    private boolean isPaused;

    public PausableWorld(int width, int height)
    {    
        super(width, height, 1); 
    }

    public boolean getIsPaused()
    {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused)
    {
        this.isPaused = isPaused;
    }

    public abstract void PauseWorld(boolean isGameOver);

    public abstract void ResumeWorld();

    public abstract void RestartWorld();

    public abstract void StopWorld();

}
