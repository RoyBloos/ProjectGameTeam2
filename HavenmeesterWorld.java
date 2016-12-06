import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

public class HavenmeesterWorld extends World
{

    public Loods SelectedLoods;
    public List<Boatlane> Boatlanes;
    public int Score = 0;
    public Text Scorebord;
    public World ParentWorld;
    private int counter = 1;
    private Random rand;
    private ArrayList<BoatSpawn> boatSpawns;
    private int nextLevelIndex;
    private final long createdMillis = System.currentTimeMillis();

    public HavenmeesterWorld(World parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        this.ParentWorld = parentWorld;
        rand = new Random();
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
        
        addObject(new LoodsGebouw(),100,340);
        addObject(new LoodsGebouw(),1180,340);
        
        Scorebord = new Text();
        addObject(Scorebord, 1200,50);
        
        CreateBoatSpawns();
    }
    
    private void CreateBoatSpawns()
    {
        boatSpawns = new ArrayList<BoatSpawn>();
        CreateBoatSpawn(boatSpawns, 5, 10 * 1000, 3000, 5000);
        CreateBoatSpawn(boatSpawns, 6, 30 * 1000, 5000, 10000);
        CreateBoatSpawn(boatSpawns, 3, 90 * 1000, 3000, 4000);
        CreateBoatSpawn(boatSpawns, 5, 120 * 1000, 3000, 4800);
    }
    
    private void CreateBoatSpawn(ArrayList<BoatSpawn> boatSpawns, int numberOfBoats, int spawntime, int spawnIntervalMin, int spawnIntervalMax)
    {
        int addedBoats = 0;
        while (addedBoats <= numberOfBoats)
        {
            boatSpawns.add(new BoatSpawn(new Boat(), spawntime + (randInt(spawnIntervalMin, spawnIntervalMax) * (addedBoats + 1))));
            addedBoats += 1;
        }
    }
    
    public void act()
    {
       long elapsedTime = System.currentTimeMillis() - createdMillis;
       if(boatSpawns.size() > 0 && boatSpawns.get(0).Time < elapsedTime)
       {
            addBoatToWorld(boatSpawns.get(0).Boat);
            boatSpawns.remove(0);
       }    
    }
    
    
    private void addBoatToWorld(Boat boat)
    {
       Boatlane boatlane = GetBoatlane();
       if(boatlane != null)
       {
           boat.AssignedBoatlane = boatlane;
           boat.OccupiedCounter = 1000;
           if(boatlane.Direction == 0)
           {
               addObject(boat, 0, boatlane.Yas);
           } else
           {
               addObject(boat,1280, boatlane.Yas);
           }
       }
    }
    
    public void AddPoints(int points)
    {
        Score += points;
        Scorebord.SetText(Integer.toString(Score) + " / 2000");
    }
    
    public void RemoveBoat(Boat boat)
    {
        removeObject(boat);
    }
    
    private Boatlane GetBoatlane()
    {
        for(Boatlane boatlane : getObjects(Boatlane.class)){
            if(!BoatlaneIsTaken(boatlane))
            {
                return boatlane;
            }
        }
        return null;
    }
    
    private boolean BoatlaneIsTaken(Boatlane boatlane)
    {
        for(Boat boat : getObjects(Boat.class)){
            if(boat.AssignedBoatlane == boatlane && boat.OccupiedCounter > 0)
            {
                return true;
            }
        }
        return false;
    }
    
    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}


