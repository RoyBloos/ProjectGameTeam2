import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BalanceIndicator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BalanceIndicator extends Actor
{
    private BalanceBar balanceBar;
    private int xOffset = 0;
    public BalanceIndicator(BalanceBar balanceBar)
    {
        this.balanceBar = balanceBar;
        SetIndicatorPosition();
    }

    public void act() 
    {
        SetIndicatorPosition();
    }    
    
    private void SetIndicatorPosition()
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
