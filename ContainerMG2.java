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
    public TruckMg2 Truck;
    public KraanGrijper Grijper;
    private Random rand;
    public String Color;
    public int Layer;
    public int Size;
    public boolean IsPaused;
    public double BalanceWeight;
    private double weight;
    public ContainerMG2(int xOffset, int yOffset, BoatMg2 boat, int layer, int weight)
    {
        IsAddedToWorld = false;
        this.xOffset = boat.XMiddle + xOffset;
        this.yOffset = yOffset;
        Boat = boat;
        XPositie = Boat.getX() + xOffset;
        YPositie = Boat.getY() + yOffset;
        rand = new Random();
        int number = randInt(1,3);
        Layer = layer;
        Size = 1;
        this.weight = weight;

        if(number == 1)
        {
            Color = "Blauw";
        } else if (number == 2)
        {
            Color = "Groen";
        } else
        {
            Color = "Grijs";
        }
        setImage("MG2Container" + Color + ".png");
    }
    
    public void act() 
    {
        if(Boat != null)
        {
            BalanceWeight = (getX() - Boat.getX() - Boat.XMiddle) * weight;
        }
        SetLocation();
    }
    
    public void SetOffsets(Actor actor)
    {
        int actorX = actor.getX();
        int containerX = getX();
        xOffset = -(actorX - containerX);
        int actorY = actor.getY();
        int containerY = getY();
        yOffset = -(actorY - containerY);
    }
    
    public void SetLocation()
    {
        if(Boat != null)
        {
            XPositie = Boat.getX() + xOffset;
            YPositie = Boat.getY() + yOffset;
        }
        
        if(Truck != null)
        {
            XPositie = Truck.getX() + xOffset;
            YPositie = Truck.getY() + yOffset;
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
    
    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
