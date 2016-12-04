import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class WorldMainMenu extends World
{
    public WorldMainMenu()
    {    
        super(1280, 768, 1); 
        
        ButtonSelectMinigame buttonselectminigame1 = new ButtonSelectMinigame(new HavenmeesterWorld(this, 1280, 768), "MenuKnop1.png");
        addObject(buttonselectminigame1, 344, 137);
        
        ButtonSelectMinigame buttonselectminigame2 = new ButtonSelectMinigame(new WorldMinigame2(this, 1280, 768), "MenuKnop2.png");
        addObject(buttonselectminigame2, 132, 321);
        
        ButtonSelectMinigame buttonselectminigame3 = new ButtonSelectMinigame(new WorldMinigame3(this, 1280, 768), "MenuKnop3.png");
        addObject(buttonselectminigame3, 738, 258);
        
        ButtonSelectMinigame buttonselectminigame4 = new ButtonSelectMinigame(new WorldMinigame4(this, 1280, 768), "MenuKnop4.png");
        addObject(buttonselectminigame4, 1024, 393);
    }
}
