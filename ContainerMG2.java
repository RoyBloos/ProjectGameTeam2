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
    public KraanGrijper Grijper;
    private Random rand;
    private String color;
    public int Laag;
    
    public ContainerMG2(int xOffset, int yOffset, BoatMg2 boat, int laag)
    {
        IsAddedToWorld = false;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        Boat = boat;
        XPositie = Boat.getX() + xOffset;
        YPositie = Boat.getY() + yOffset;
        rand = new Random();
        int number = randInt(1,3);
        Laag = laag;
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
    
    public void SetOffsets(BoatMg2 boat)
    {
        int boatX = boat.getX();
        int containerX = getX();
        xOffset = -(boatX - containerX);
        int boatY = boat.getY();
        int containerY = getY();
        yOffset = -(boatY - containerY);
    }
    
    public void SetLocation()
    {
        if(Boat != null)
        {
            XPositie = Boat.getX() + xOffset;
            YPositie = Boat.getY() + yOffset;
        }
        
        if(Grijper != null)
        {
            XPositie = Grijper.getX();
            YPositie = Grijper.getY();
        }
        setLocation(XPositie,YPositie);
        if (!IsAddedToWorld && YPositie <= 768)
        {
            Boat.ParentWorld.addObject(this, XPositie, YPositie);
            IsAddedToWorld = true;
        }
    }
    
    public void SetScale(int pixels)
    {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() + pixels, image.getHeight() + pixels);
    }
    
    public void act() 
    {
        SetLocation();
    }
    
    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
