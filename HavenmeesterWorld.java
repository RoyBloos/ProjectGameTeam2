import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Color;

public class HavenmeesterWorld extends PausableWorld
{
    private Text scorebord;
    private Loods selectedLoods;
    private int score = 0;
    private WorldMainMenu parentWorld;
    private int counter = 1;
    private ArrayList<BoatSpawn> boatSpawns;
    private ArrayList<Leven> levens;
    private int nextLevelIndex;
    private long createdMillis = System.currentTimeMillis();
    private long pausedMillis;
    private boolean areActorsPaused;
    private int scoreToReach = 1000;

    public int getScoreToReach()
    {
        return scoreToReach;
    }

    public void setSelectedLoods(Loods loods)
    {
        selectedLoods = loods;
    }

    public Loods getSelectedLoods()
    {
        return selectedLoods;
    }

    public HavenmeesterWorld(WorldMainMenu parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth); 
        this.parentWorld = parentWorld;
        Loods loods1 = new Loods();
        Loods loods2 = new Loods();
        addObject(loods1, 100, 380);
        addObject(loods2, 1180, 380);
        addObject(new Harbor(422), 446, 318);
        addObject(new Harbor(582), 606, 318);
        addObject(new Harbor(742), 766, 318);
        addObject(new Harbor(890), 914, 318);
        addObject(new Boatlane(500, 0), -1,-1);
        addObject(new Boatlane(525, 180), -1,-1);
        addObject(new Boatlane(550, 0), -1,-1);
        addObject(new Boatlane(575, 180), -1,-1);
        addObject(new Boatlane(600, 0), -1,-1);
        addObject(new LoodsGebouw(loods1),100,340);
        addObject(new LoodsGebouw(loods2),1180,340);
        scorebord = new Text(Color.RED, 25);
        addObject(scorebord, 1200,50);
        addObject(new GameNavigationButton(this, "Pause"), 50,50);
        levens = new ArrayList<Leven>();
        levens.add(new Leven());
        levens.add(new Leven());
        levens.add(new Leven());
        int xPositieLeven = 1247;
        for(Leven l : levens)
        {
            addObject(l, xPositieLeven, 80);
            l.setRotation(345);
            xPositieLeven -= 60;
        }

        CreateBoatSpawns();
    }

    public void PauseWorld(boolean isGameOver)
    {
        pausedMillis =  System.currentTimeMillis();
        setIsPaused(true);
        addObject(new PauseScreen("PauseScreenHavenmeester.png"), 640, 384);
        if(!isGameOver)
        {
            addObject(new GameNavigationButton(this, "Resume"), 500, 560);
        }
        addObject(new GameNavigationButton(this, "Restart"), 600, 560);
        addObject(new GameNavigationButton(this, "Stop"), 700, 560);
        addObject(new OpenLinkButton("https://www.youtube.com/watch?v=PBQSC-e9tWY&feature=youtu.be", "PlayLoodsIntroductie.png"), 850, 560);
    }

    public void ResumeWorld()
    {

        for(GameNavigationButton button : getObjects(GameNavigationButton.class)){
            if(button.knopType != "Pause")
            {
                removeObject(button);
            }
        }
        for(OpenLinkButton button : getObjects(OpenLinkButton.class)){
            removeObject(button);
        }

        for(PauseScreen pauseScreen : getObjects(PauseScreen.class)){
            removeObject(pauseScreen);
        }
        setIsPaused(false);
        SetPauseOnAllActors();
        createdMillis += System.currentTimeMillis() - pausedMillis;
    }

    public void RestartWorld()
    {
        parentWorld.StartNewGame("Havenmeester");
    }

    public void StopWorld()
    {
        Greenfoot.setWorld(parentWorld);
    }

    public void act()
    {
        if(!getIsPaused())
        {
            if(areActorsPaused)
            {
                SetPauseOnAllActors();
            }
            long elapsedTime = System.currentTimeMillis() - createdMillis;
            if(!boatSpawns.isEmpty() && boatSpawns.get(0).Time < elapsedTime)
            {
                addBoatToWorld(boatSpawns.get(0).Boat);
                boatSpawns.remove(0);
            }
        } else
        {
            if(!areActorsPaused)
            {
                SetPauseOnAllActors();
            }
        }
    }

    public void AddPoints(int points)
    {
        score += points;
        Color textColor = Color.RED;
        if(score >= scoreToReach)
        {
            textColor = Color.GREEN;
        }
        scorebord.SetText(Integer.toString(score) + " / " + Integer.toString(scoreToReach), textColor, null);
        parentWorld.SetScore("Havenmeester", score);
    }

    public void RemoveBoat(Boat boat)
    {
        removeObject(boat);
        if(boat.getNumberOfContainers() > 0)
        {
            removeObject(levens.remove(0));

            if(levens.isEmpty())
            {
                PauseWorld(true);
            }
        }
    }

    private void CreateBoatSpawns()
    {
        boatSpawns = new ArrayList<BoatSpawn>();
        int levelDifficulty = 1;
        int totalBoatSpawns = 0;
        while (levelDifficulty <= 15)
        {
            int numberOfBoats = getRandomNumber(levelDifficulty, (levelDifficulty + 3));
            int spawnTime = totalBoatSpawns * 5000;
            totalBoatSpawns += numberOfBoats;
            int spawnIntervalMin = getRandomNumber(3000, 5000);
            int spawnIntervanMax = getRandomNumber(5000, 7000);
            CreateBoatSpawn(boatSpawns, numberOfBoats, spawnTime, spawnIntervalMin, spawnIntervanMax);
            levelDifficulty += 1;
        }
    }

    private void CreateBoatSpawn(ArrayList<BoatSpawn> boatSpawns, int numberOfBoats, int spawntime, int spawnIntervalMin, int spawnIntervalMax)
    {
        int addedBoats = 0;
        while (addedBoats <= numberOfBoats)
        {
            boatSpawns.add(new BoatSpawn(new Boat(), spawntime + (getRandomNumber(spawnIntervalMin, spawnIntervalMax) * (addedBoats + 1))));
            addedBoats += 1;
        }
    }

    private void SetPauseOnAllActors()
    {
        for(Boat boat : getObjects(Boat.class)){
            boat.setIsPaused(getIsPaused());
        }

        for(Harbor harbor : getObjects(Harbor.class)){
            harbor.setIsPaused(getIsPaused());
        }

        for(HavenStoplicht havenStoplicht : getObjects(HavenStoplicht.class)){
            havenStoplicht.setIsPaused(getIsPaused());
        }

        for(Loods loods : getObjects(Loods.class)){
            loods.setIsPaused(getIsPaused());
        }

        for(LoodsGebouw loodsGebouw : getObjects(LoodsGebouw.class)){
            loodsGebouw.setIsPaused(getIsPaused());
        }
    }

    private void addBoatToWorld(Boat boat)
    {
        Boatlane boatlane = GetBoatlane();
        if(boatlane != null)
        {
            boat.setAssignedBoatlane(boatlane);
            boat.setOccupiedCountrer(1000);
            if(boatlane.Direction == 0)
            {
                addObject(boat, 0, boatlane.Yas);
            } else
            {
                addObject(boat,1280, boatlane.Yas);
            }
        }
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
            if(boat.getAssignedBoatlane() == boatlane && boat.getOccupiedCountrer() > 0)
            {
                return true;
            }
        }
        return false;
    }

    private int getRandomNumber(int start, int end)
    {
        int normal = Greenfoot.getRandomNumber(end - start + 1);
        return normal + start;
    }
}

