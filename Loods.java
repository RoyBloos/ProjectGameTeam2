import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Loods extends PausableActor
{
    private Boat selectedBoat;
    private int xStartPositie;
    private int yStartPositie;

    public void setSelectedBoat(Boat boat)
    {
        selectedBoat = boat;
    }
    
    public void act() 
    {
        if(!getIsPaused())
        {
            if(Greenfoot.mouseClicked(this))
            {
                HavenmeesterWorld world = (HavenmeesterWorld)getWorld();
                world.setSelectedLoods(this);
            }

            if(selectedBoat != null)
            {
                setRotation(getAngle(selectedBoat.getX(), selectedBoat.getY())+180 );
                turnTowards(selectedBoat.getX(), selectedBoat.getY());
                move(1);
                if(selectedBoatIsCloseEnough())
                {
                    selectedBoat.setHasLoods(true);
                    selectedBoat = null;
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
            if(boat == selectedBoat){
                return true;
            }
        }
        return false;
    }
}
