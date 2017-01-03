import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BalanceBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BalanceBar extends Actor
{
    private BoatMg2 Boat;
    private boolean IsAddedToWorld;
    private double balans;
    private BalanceIndicator balanceIndicator;
    public BalanceBar(BoatMg2 boat)
    {
        Boat = boat;
    }
    
    public void act() 
    {
       if(Boat != null)
       {
           setLocation(Boat.getX() - 25, Boat.getY() + 200);
       }
       SetBalance();       
    }
    
    private void SetBalance()
    {
        balans = 0;
        if(Boat != null)
        {
            for(ContainerMG2 container : Boat.Containers)
            {
                balans += container.BalanceWeight;
            }
        }
        int halfImageWidth = getImage().getWidth() / 2;
        double weightPerPixel = Boat.MaxWeightOneSide / halfImageWidth;
        int xOffset = (int)(balans / weightPerPixel);
        balanceIndicator.SetXOffset(xOffset);
        if((xOffset > 0 && xOffset >= halfImageWidth) || (xOffset < 0 && xOffset <= -halfImageWidth) )
        {
            Boat.IsOutOfBalance = true;
        }
    }
    
    protected void addedToWorld(World world)
    {
        balanceIndicator = new BalanceIndicator(this);
        world.addObject(balanceIndicator, getX(), getY());
    }
}
