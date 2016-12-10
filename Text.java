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
    public Color TextColor;
    public Integer FontSize;
    public Text(Color textColor, int fontSize)
    {
        this.TextColor = textColor;
        this.FontSize = fontSize;
        SetText("0", TextColor, FontSize);
    }
    
    public void SetText(String text, Color textColor, Integer fontSize)
    {
        if (textColor != null)
        {
            TextColor = textColor;
        }
        
        if(fontSize != null)
        {
            FontSize = fontSize;
        }
        
        GreenfootImage txtImg = new GreenfootImage(text, FontSize, TextColor, null);
        GreenfootImage image = new GreenfootImage(200, txtImg.getHeight());
        image.drawImage(txtImg, 0, 0);
        setImage(image);
    }
}
