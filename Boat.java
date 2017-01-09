import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Boat extends Actor
{
    int counter = 0;
    public Loods selectedLoods;
    public boolean heeftLoods;
    public Haven toegewezenHaven;
    public boolean isDocked;
    public int aantalContainers;
    public Boatlane assignedBoatlane;
    private boolean reachedEntrypoint;
    private int entryPointYOFfset = 150;
    private Random rand;
    public boolean magHavenVerlaten;
    public int occupiedCounter;
    public boolean isPaused;
    
    public Boat()
    {
        rand = new Random();
    }
    
    public void act() 
    {
        if(!isPaused)
        {
            HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
            if(Greenfoot.mouseClicked(this) && world.SelectedLoods != null)
            {
                world.SelectedLoods.SelectedBoat = this;
                selectedLoods = world.SelectedLoods;
                world.SelectedLoods = null;
            }
            
            if(toegewezenHaven == null && heeftLoods)
            {
                wijsHavenToe();
            }
            
            if(occupiedCounter > 0)
            {
                occupiedCounter -=1;
            }
            
            moveBoat();
        }
    }
    
    public void UnloadContainers(int containers)
    {
        if(aantalContainers > containers)
        {
            HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
            if(containers <= aantalContainers)
            {
                world.AddPoints(containers);
            } else
            {
                world.AddPoints(aantalContainers);
            }
        }
        aantalContainers = aantalContainers - containers;

        if(aantalContainers <= 0)
        {
            aantalContainers = 0;
            reachedEntrypoint = false;
        }
        
        setImage("Vrachtschip" + aantalContainers + ".png");
    }
    
    private void moveBoat()
    {
        if(toegewezenHaven != null && (!isDocked || magHavenVerlaten))
        {
            //Gaat richting het entrypoint van de haven
            if(!reachedEntrypoint)
            {
                moveTowardsHarborEntrypoint();
            } else if(aantalContainers > 0)
            {
                setRotation(getAngle(toegewezenHaven.getX(), toegewezenHaven.getY())+180 );
                turnTowards(toegewezenHaven.getX(), toegewezenHaven.getY());
            } else
            {
                setRotation(getAngle(0, getY())+180 );
                turnTowards(0, getY());
            }
        } 
        if(counter % 2 == 0 && (!isDocked || magHavenVerlaten))
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
    
    private void moveTowardsHarborEntrypoint()
    {
        setRotation(getAngle(toegewezenHaven.getX(), toegewezenHaven.getY() + entryPointYOFfset)+180 );
        turnTowards(toegewezenHaven.getX(), toegewezenHaven.getY() + entryPointYOFfset);
        if(getX() == toegewezenHaven.getX() && getY() == toegewezenHaven.getY() + entryPointYOFfset)
        {
            reachedEntrypoint = true;
        }
    }
    
    
    private void wijsHavenToe()
    {
        for(Haven haven : getWorld().getObjects(Haven.class)){
            if(!haven.IsBezet){
                toegewezenHaven = haven;
                haven.ZetBoat(this);
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
        aantalContainers = randInt(1,21);
        setRotation(assignedBoatlane.Direction);
        setImage("Vrachtschip" + aantalContainers + ".png");
    }
    
    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
