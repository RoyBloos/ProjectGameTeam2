import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BalanceBar extends Actor
{
    private BoatMg2 boat;
    private BalanceIndicator balanceIndicator;
    
    public BalanceBar(BoatMg2 boat)
    {
        this.boat = boat;
    }
    
    public void act() 
    {
       if(boat != null)
       {
           setLocation(boat.getX() - 25, boat.getY() + 200);
       }
       setBalance();       
    }
    
    private void setBalance()
    {
        double balans = 0;
        if(boat != null)
        {
            for(ContainerMG2 container : boat.getContainers())
            {
                balans += container.getBalanceWeight();
            }
        }
        int halfImageWidth = getImage().getWidth() / 2;
        double weightPerPixel = boat.getMaxWeightOneSide() / halfImageWidth;
        int xOffset = (int)(balans / weightPerPixel);
        balanceIndicator.SetXOffset(xOffset);
        if((xOffset > 0 && xOffset >= halfImageWidth) || (xOffset < 0 && xOffset <= -halfImageWidth) )
        {
            boat.setIsOutOfBalance(true);
        }
    }
    
    protected void addedToWorld(World world)
    {
        balanceIndicator = new BalanceIndicator(this);
        world.addObject(balanceIndicator, getX(), getY());
    }
}
