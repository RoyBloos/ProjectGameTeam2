import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class HavenmeesterWorld extends World
{

    public Loods SelectedLoods;
    public List<Boatlane> Boatlanes;
    public int Score = 0;
    public Text Scorebord;
    public World ParentWorld;
    /**
     * Constructor for objects of class HavenmeesterWorld.
     * 
     */
    public HavenmeesterWorld(World parentWorld, int gameHeight, int gameWidth)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(gameHeight, gameWidth, 1); 
        this.ParentWorld = parentWorld;
        
        addObject(new Boatspawner(), -1, -1);
        addObject(new Loods(), 100, 380);
        addObject(new Loods(), 1180, 380);
        addObject(new Haven(422), 446, 318);
        addObject(new Haven(582), 606, 318);
        addObject(new Haven(742), 766, 318);
        addObject(new Haven(890), 914, 318);
        
        addObject(new Boatlane(500, 0), -1,-1);
        addObject(new Boatlane(525, 180), -1,-1);
        addObject(new Boatlane(550, 0), -1,-1);
        addObject(new Boatlane(575, 180), -1,-1);
        addObject(new Boatlane(600, 0), -1,-1);
        
        Scorebord = new Text();
        addObject(Scorebord, 1200,50);
    }
    
    public void RemoveBoat(Boat boat)
    {
        Score += boat.StartAantalContainers - boat.AantalContainers;
        Scorebord.SetText(Integer.toString(Score));
        removeObject(boat);
    }
}


