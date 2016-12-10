import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class ButtonSelectMinigame extends Actor
{
    public String GameName;
    private WorldMainMenu worldMainMenu;
    public ButtonSelectMinigame(WorldMainMenu worldMainMenu, String gameName, String imageName )
    {
        this.worldMainMenu = worldMainMenu;
        GameName = gameName;
        setImage(imageName);
    }    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this))
        {
            worldMainMenu.StartNewGame(GameName);
        }
    }
}
