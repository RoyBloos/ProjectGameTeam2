import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ConveyorBelt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConveyorBelt extends ActorsMinigame3
{
    String[] imageNames = { "ConveyorBelt1.png", "ConveyorBelt2.png", "ConveyorBelt3.png" };
    int imageNum;
    public void act() 
    {
        imageNum = (imageNum + 1) % imageNames.length;
        setImage(imageNames[imageNum]);
    }    
}
