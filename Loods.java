import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Loods here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Loods extends Actor
{
    public Boat SelectedBoat;
    private int xStartPositie;
    private int yStartPositie;
    public boolean IsPaused;
    /**
     * Act - do whatever the Loods wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!IsPaused)
        {
            if(Greenfoot.mouseClicked(this))
            {
                HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
                world.SelectedLoods = this;
            }
            
            if(SelectedBoat != null)
            {
                setRotation(getAngle(SelectedBoat.getX(), SelectedBoat.getY())+180 );
                turnTowards(SelectedBoat.getX(), SelectedBoat.getY());
                move(1);
                if(selectedBoatIsCloseEnough())
                {
                    SelectedBoat.HeeftLoods = true;
                    SelectedBoat = null;
                }
            } else if (xStartPositie != getX() && yStartPositie != getY())
            {
                setRotation(getAngle(xStartPositie, yStartPositie)+180 );
                turnTowards(xStartPositie, yStartPositie);
                move(1);
            } else
            {
                setRotation(0);
            }
        }
    }    
    
     protected void addedToWorld(World world)
    {
        setRotation(90);
        xStartPositie = getX();
        yStartPositie = getY();
    }
    
    public int getAngle(int targetX, int targetY) {
        int angle = (int) Math.toDegrees(Math.atan2(targetY - getY(), targetX - getX()));
    
        if(angle < 0){
            angle += 360;
        }
    
        return angle;
    }
    
    private boolean selectedBoatIsCloseEnough()
    {  
        for(Boat boat : getObjectsInRange(30, Boat.class)){
            if(boat == SelectedBoat){
                return true;
            }
        }
        return false;
    }
}
