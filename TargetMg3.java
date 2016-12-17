import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TargetMg3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TargetMg3 extends Actor
{
    /**
     * Act - do whatever the TargetMg3 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TargetMg3()
    {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() + 1, image.getHeight() + 10);
        setImage(image);
    }   
}
