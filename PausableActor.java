import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class PausableActor extends Actor
{
    private boolean isPaused;
    public boolean getIsPaused()
    {
        return isPaused;
    }
    
    public void setIsPaused(boolean pause)
    {
        isPaused = pause;
    }
}
