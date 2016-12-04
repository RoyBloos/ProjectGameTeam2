import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoatMg2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoatMg2 extends Actor
{
    public boolean IsCpuBoat = false;
    public boolean IsDocked = false;
    public boolean HasCargo = true;
    
    public BoatMg2(boolean isCpuBoat)
    {
        IsCpuBoat = isCpuBoat;
    }
    
    public void act() 
    {
        if (!IsDocked)
        {
            // IsDocked = false? dan moet de boot varen
            moveBoat();
        }
    }
    
    private void moveBoat()
    {
        if (HasCargo)
        {
                // Heeft de boot lading dan komt de boot het veld binnen en moet varen tot Y 380
                setRotation(getAngle(getX(), 380));
        } else
        {
                // Heeft de boot geen laden dan vaart het de haven uit (richting Y = 0
                setRotation(getAngle(getX(), 0));
        }
            
        move(1);
        if(getY() <= 380 && HasCargo)
        {
            // Is de boot op Y 380 of lager EN heeft het nog lading dan IsDocked = true (IsDocked word bij het uitladen van de laatste container weer op false gezet zodat de boot weer vertrekt)
            IsDocked = true;
        }
    }
    
    
     public int getAngle(int targetX, int targetY) {
        int angle = (int) Math.toDegrees(Math.atan2(targetY - getY(), targetX - getX()));
    
        if(angle < 0){
            angle += 360;
        }
    
        return angle;
    }
}
