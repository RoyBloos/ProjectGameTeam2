import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BalanceIndicator extends Actor
{
    private BalanceBar balanceBar;
    private int xOffset = 0;
    public BalanceIndicator(BalanceBar balanceBar)
    {
        this.balanceBar = balanceBar;
        setIndicatorPosition();
    }

    public void act() 
    {
        setIndicatorPosition();
    }    
    
    private void setIndicatorPosition()
    {
        if(balanceBar != null)
        {
            setLocation(balanceBar.getX() + xOffset, balanceBar.getY());
        }
    }
    
    public void SetXOffset(int xOffset)
    {
        this.xOffset = xOffset;
    }
}
