import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.net.*;
import java.awt.*;
import java.io.IOException;

/**
 * Write a description of class OpenLinkButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OpenLinkButton extends Actor
{
    private String test;
    public OpenLinkButton(String url, String imageName)
    {
        test = url;
        setImage(imageName);
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            try{
                if (Desktop.isDesktopSupported()) 
                {
                    Desktop desk = Desktop.getDesktop();
                    desk.browse(URI.create(test));
                }
            } catch(IOException e)
            {
            }
        }
    }    
}
