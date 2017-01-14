import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Boatlane extends Actor
{
    private int yAs;
    private int direction;
    
    public Boatlane(int y, int dir)
    {
        yAs = y;
        direction = dir;
    }
    
    public int getyAs()
    {
        return yAs;
    }
    
    public int getDirection()
    {
        return direction;
    }
        
}


