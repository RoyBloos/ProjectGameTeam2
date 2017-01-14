import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Text extends Actor
{
    public Text(Color textColor, int fontSize)
    {
        SetText("0", textColor, fontSize);
    }
    
    public void SetText(String text, Color textColor, Integer fontSize)
    {
        GreenfootImage txtImg = new GreenfootImage(text, fontSize, textColor, null);
        GreenfootImage image = new GreenfootImage(200, txtImg.getHeight());
        image.drawImage(txtImg, 0, 0);
        setImage(image);
    }
}
