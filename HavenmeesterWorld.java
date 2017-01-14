import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Color;

public class HavenmeesterWorld extends PausableWorld
{
    private Text scorebord;
    private Pilot selectedPilot;
    private int score = 0;
    private WorldMainMenu parentWorld;
    private ArrayList<BoatSpawn> boatSpawns;
    private ArrayList<Leven> levens;
    private long createdMillis = System.currentTimeMillis();
    private long pausedMillis;
    private boolean areActorsPaused;
    private int scoreToReach = 1000;
    GreenfootSound backgroundMusic = new GreenfootSound("HavenmeesterMusic.mp3");
    
    public HavenmeesterWorld(WorldMainMenu parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth); 
        this.parentWorld = parentWorld;
        Pilot loods1 = new Pilot();
        Pilot loods2 = new Pilot();
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
        addObject(new PilotOffice(loods1),100,340);
        addObject(new PilotOffice(loods2),1180,340);
        scorebord = new Text(Color.RED, 25);
        addObject(scorebord, 1200,50);
        addObject(new GameNavigationButton(this, "Pause"), 50,50);
        levens = new ArrayList<>();
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

        createBoatSpawns();
    }

    public int getScoreToReach()
    {
        return scoreToReach;
    }

    public void setSelectedPilot(Pilot pilot)
    {
        selectedPilot = pilot;
    }

    public Pilot getSelectedPilot()
    {
        return selectedPilot;
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
            if(button.getKnopType() != "Pause")
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
        setPauseOnAllActors();
        createdMillis += System.currentTimeMillis() - pausedMillis;
    }
    public void StartMusic()
    {
        backgroundMusic.playLoop();
    }
    
    public void RestartWorld()
    {
        parentWorld.StartNewGame("Havenmeester");
    }

    public void StopWorld()
    {
        Greenfoot.setWorld(parentWorld);
        backgroundMusic.stop();
    }

    public void act()
    {
        if(!getIsPaused())
        {
            if(areActorsPaused)
            {
                setPauseOnAllActors();
            }
            long elapsedTime = System.currentTimeMillis() - createdMillis;
            if(!boatSpawns.isEmpty() && boatSpawns.get(0).getTime() < elapsedTime)
            {
                addBoatToWorld(boatSpawns.get(0).getBoat());
                boatSpawns.remove(0);
            }
        } else
        {
            if(!areActorsPaused)
            {
                setPauseOnAllActors();
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
        parentWorld.setGameScore("Havenmeester", score);
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

    private void createBoatSpawns()
    {
        boatSpawns = new ArrayList<>();
        int levelDifficulty = 1;
        int totalBoatSpawns = 0;
        while (levelDifficulty <= 15)
        {
            int numberOfBoats = getRandomNumber(levelDifficulty, levelDifficulty + 3);
            int spawnTime = totalBoatSpawns * 5000;
            totalBoatSpawns += numberOfBoats;
            int spawnIntervalMin = getRandomNumber(3000, 5000);
            int spawnIntervanMax = getRandomNumber(5000, 7000);
            createBoatSpawn(boatSpawns, numberOfBoats, spawnTime, spawnIntervalMin, spawnIntervanMax);
            levelDifficulty += 1;
        }
    }

    private void createBoatSpawn(ArrayList<BoatSpawn> boatSpawns, int numberOfBoats, int spawntime, int spawnIntervalMin, int spawnIntervalMax)
    {
        int addedBoats = 0;
        while (addedBoats <= numberOfBoats)
        {
            boatSpawns.add(new BoatSpawn(new Boat(), spawntime + (getRandomNumber(spawnIntervalMin, spawnIntervalMax) * (addedBoats + 1))));
            addedBoats += 1;
        }
    }

    private void setPauseOnAllActors()
    {
        for(Boat boat : getObjects(Boat.class)){
            boat.setIsPaused(getIsPaused());
        }

        for(Harbor harbor : getObjects(Harbor.class)){
            harbor.setIsPaused(getIsPaused());
        }

        for(HarborStoplight stoplight : getObjects(HarborStoplight.class)){
            stoplight.setIsPaused(getIsPaused());
        }

        for(Pilot pilot : getObjects(Pilot.class)){
            pilot.setIsPaused(getIsPaused());
        }

        for(PilotOffice pilotOffice : getObjects(PilotOffice.class)){
            pilotOffice.setIsPaused(getIsPaused());
        }
    }

    private void addBoatToWorld(Boat boat)
    {
        Boatlane boatlane = getBoatlane();
        if(boatlane != null)
        {
            boat.setAssignedBoatlane(boatlane);
            boat.setOccupiedCountrer(1000);
            if(boatlane.getDirection() == 0)
            {
                addObject(boat, 0, boatlane.getyAs());
            } else
            {
                addObject(boat,1280, boatlane.getyAs());
            }
        }
    }

    private Boatlane getBoatlane()
    {
        for(Boatlane boatlane : getObjects(Boatlane.class)){
            if(!boatlaneIsTaken(boatlane))
            {
                return boatlane;
            }
        }
        return null;
    }

    private boolean boatlaneIsTaken(Boatlane boatlane)
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

