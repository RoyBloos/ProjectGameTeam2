import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ButtonSelectMinigame extends Actor
{
    private String gameName;
    private WorldMainMenu worldMainMenu;
    
    public ButtonSelectMinigame(WorldMainMenu worldMainMenu, String gameName, String imageName )
    {
        this.worldMainMenu = worldMainMenu;
        this.gameName = gameName;
        setImage(imageName);
    }    
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            worldMainMenu.StartNewGame(gameName);
        }
    }
}
