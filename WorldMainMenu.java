import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class WorldMainMenu extends World
{
    public WorldMainMenu()
    {    
        super(1280, 768, 1); 
        ButtonSelectMinigame buttonselectminigame1 = new ButtonSelectMinigame(1);
        addObject(buttonselectminigame1, 344, 137);
        ButtonSelectMinigame buttonselectminigame2 = new ButtonSelectMinigame(2);
        addObject(buttonselectminigame2, 132, 321);
        ButtonSelectMinigame buttonselectminigame3 = new ButtonSelectMinigame(3);
        addObject(buttonselectminigame3, 738, 258);
        ButtonSelectMinigame buttonselectminigame4 = new ButtonSelectMinigame(4);
        addObject(buttonselectminigame4, 1024, 393);
    }
}
