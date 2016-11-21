import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boatlane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boatlane extends Actor
{
    public int Yas;
    public int Direction;
    public boolean IsOccupied;
    private int counter;
    
    public Boatlane(int y, int dir)
    {
        Yas = y;
        Direction = dir;
    }
    
    public void act() 
    {
        if(counter > 0)
        {
            counter -= 1;
            IsOccupied = true;
        } else
        {
            IsOccupied = false;
        }
    }
    
    public void OccupyLane()
    {
        counter = 1000;
    }
}


