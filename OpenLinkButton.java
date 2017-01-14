import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.net.*;
import java.awt.*;
import java.io.IOException;

public class OpenLinkButton extends Actor
{
    private String url;
    
    public OpenLinkButton(String url, String imageName)
    {
        this.url = url;
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
                    desk.browse(URI.create(url));
                }
            } catch(IOException e)
            {
                throw new OpenLinkRuntimeException(e);
            }
        }
    }    
}
