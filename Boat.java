import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.List;
import java.util.Random;

/**
 * Write a description of class Boat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boat extends Actor
{
    int counter = 0;
    public Loods SelectedLoods;
    public boolean HeeftLoods;
    public Haven ToegewezenHaven;
    public boolean IsDocked;
    public int AantalContainers;
    public int StartAantalContainers;
    private Boatlane assignedBoatlane;
    private boolean reachedEntrypoint;
    private int entryPointYOFfset = 150;
    private Random rand;
    
    public Boat(Boatlane boatlane)
    {
        assignedBoatlane = boatlane;
        assignedBoatlane.OccupyLane();
        rand = new Random();
    }
    
    public void act() 
    {
        HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
        if(Greenfoot.mouseClicked(this) && world.SelectedLoods != null)
        {
            world.SelectedLoods.SelectedBoat = this;
            SelectedLoods = world.SelectedLoods;
            world.SelectedLoods = null;
        }
        
        if(ToegewezenHaven == null && HeeftLoods)
        {
            wijsHavenToe();
        }
        moveBoat();
    }
    
    public void UnloadContainers(int containers)
    {
        AantalContainers = AantalContainers - containers;
        if(AantalContainers < 0)
        {
            AantalContainers = 0;
        }
        
        if(AantalContainers == 0)
        {
            reachedEntrypoint = false;
            IsDocked = false;
        }
        
        setImage("Vrachtschip" + AantalContainers + ".png");
    }
    
    private void moveBoat()
    {
        if(ToegewezenHaven != null && !IsDocked)
        {
            if(!reachedEntrypoint)
            {
                setRotation(getAngle(ToegewezenHaven.getX(), ToegewezenHaven.getY() + entryPointYOFfset)+180 );
                turnTowards(ToegewezenHaven.getX(), ToegewezenHaven.getY() + entryPointYOFfset);
                if(getX() == ToegewezenHaven.getX() && getY() == ToegewezenHaven.getY() + entryPointYOFfset)
                {
                    reachedEntrypoint = true;
                }
            } else if(AantalContainers > 0)
            {
                setRotation(getAngle(ToegewezenHaven.getX(), ToegewezenHaven.getY())+180 );
                turnTowards(ToegewezenHaven.getX(), ToegewezenHaven.getY());
            } else
            {
                setRotation(getAngle(0, getY())+180 );
                turnTowards(0, getY());
            }
        } 
        if(counter % 2 == 0 && !IsDocked)
        {
            move(1);
        }
        counter++;
        
        if(isAtEdge())
        {
            HavenmeesterWorld havenmeesterWorld =  (HavenmeesterWorld)getWorld();
            havenmeesterWorld.RemoveBoat(this);
        }
    }
    
    private void wijsHavenToe()
    {
        for(Haven haven : getWorld().getObjects(Haven.class)){
            if(!haven.IsBezet){
                ToegewezenHaven = haven;
                haven.IsBezet = true;
                haven.ToegewezenBoat = this;
                return;
            }
        }
    }
    
    public int getAngle(int targetX, int targetY) {
        int angle = (int) Math.toDegrees(Math.atan2(targetY - getY(), targetX - getX()));
    
        if(angle < 0){
            angle += 360;
        }
    
        return angle;
    }
    
    protected void addedToWorld(World world)
    {
        StartAantalContainers = randInt(1,21);
        AantalContainers = StartAantalContainers;
        setRotation(assignedBoatlane.Direction);
        setImage("Vrachtschip" + AantalContainers + ".png");
    }
    
     public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    
    
       
  
}