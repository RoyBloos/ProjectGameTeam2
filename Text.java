import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    public Text()
    {
        setImage(new GreenfootImage("0", 25, Color.BLACK, Color.WHITE));
    }
    
    public void SetText(String text)
    {
        setImage(new GreenfootImage(text, 25, Color.BLACK, Color.WHITE));
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
