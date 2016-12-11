import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class ContainerMG2 extends Actor
{
    public int XPositie;
    public int YPositie;
    private int xOffset;
    private int yOffset;
    public Boolean IsAddedToWorld;
    public BoatMg2 Boat;
    private Random rand;
    private String color;
    
    public ContainerMG2(int xOffset, int yOffset, BoatMg2 boat)
    {
        IsAddedToWorld = false;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        Boat = boat;
        XPositie = Boat.getX() + xOffset;
        YPositie = Boat.getY() + yOffset;
        rand = new Random();
        int number = randInt(1,3);
        if(number == 1)
        {
            color = "Blauw";
        } else if (number == 2)
        {
            color = "Groen";
        } else
        {
            color = "Grijs";
        }
        setImage("MG2Container" + color + ".png");
    }
    
    public void SetLocation()
    {
        XPositie = Boat.getX() + xOffset;
        YPositie = Boat.getY() + yOffset;
        
        if (!IsAddedToWorld && YPositie <= 768)
        {
            Boat.ParentWorld.addObject(this, XPositie, YPositie);
            IsAddedToWorld = true;
        }
    }
    
    public void act() 
    {
        setLocation(XPositie,YPositie);
    }
    
    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
