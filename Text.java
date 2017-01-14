import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Text extends Actor
{
    private Color textColor;
    private int fontSize;
    public Text(Color textColor, int fontSize)
    {
        this.textColor = textColor;
        this.fontSize = fontSize;
        SetText("0", textColor, fontSize);
    }
    
    public void SetText(String text, Color tc, Integer fs)
    {
        Color overideTextColor = textColor;
        if(tc != null)
        {
            overideTextColor = tc;
        }
        
        int overrideFontSize = fontSize;
        if (fs != null)
        {
            overrideFontSize = fs;
        }
        GreenfootImage txtImg = new GreenfootImage(text, overrideFontSize, overideTextColor, null);
        GreenfootImage image = new GreenfootImage(200, txtImg.getHeight());
        image.drawImage(txtImg, 0, 0);
        setImage(image);
    }
}
