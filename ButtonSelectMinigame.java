import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ButtonSelectMinigame extends Actor
{
    public int minigameNumber;
    public int imageNumber;
    public ButtonSelectMinigame(int minigameSelection)
    {
        minigameNumber = minigameSelection;
    }    
    public void act() 
    {
       openMinigameWorld();
    }
    public void openMinigameWorld()
    {
        if (Greenfoot.mouseClicked(this))
        {
            if (this.minigameNumber == 1)
            {
                Greenfoot.setWorld(new HavenmeesterWorld());
            }
            else if (this.minigameNumber == 2)
            {
                Greenfoot.setWorld(new WorldMinigame2());
            }
            else if (this.minigameNumber == 3)
            {
                Greenfoot.setWorld(new WorldMinigame3());
            }
            else if (this.minigameNumber == 4)
            {
                Greenfoot.setWorld(new WorldMinigame4());
            }
        }
    }
}
