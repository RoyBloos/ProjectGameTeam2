import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class WorldMinigame2 extends PausableWorld
{

    private int scoreToReach = 24;
    private int truckCounter;
    private WorldMainMenu parentWorld;
    private boolean areActorsPaused;
    private Random rand;
    private ArrayList<TruckMg2> playerTrucks;
    private ArrayList<TruckMg2> teSpawnenPlayerTrucks;
    private ArrayList<TruckMg2> cpuTrucks;
    private ArrayList<TruckMg2> teSpawnenCpuTrucks;
    private BoatMg2 playerBoat = null;
    private Text playerScorebord;
    private int playerScore = 0;
    private Crane playerCrane;
    private CraneReacher playerCraneReacher;
    private BoatMg2 cpuBoat = null;
    private Text cpuScorebord;
    private int cpuScore = 0;
    private Crane cpuCrane;
    private CraneReacher cpuCraneReacher;
    GreenfootSound backgroundMusic = new GreenfootSound("Minigame2Music.mp3");

    public WorldMinigame2(WorldMainMenu parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth); 
        rand = new Random();
        this.parentWorld = parentWorld;
        truckCounter = 0;
        spawnBoats();
        addObject(new GameNavigationButton(this, "Pause"), 50,50);
        setPaintOrder(GameNavigationButton.class, OpenLinkButton.class, PauseScreen.class, Crane.class, CraneReacher.class, ContainerMG2.class, BalanceIndicator.class, BalanceBar.class, BoatMg2.class);

        playerTrucks = new ArrayList<>();
        teSpawnenPlayerTrucks = createTrucks(playerBoat);
        playerCrane = new Crane();
        playerCraneReacher = new CraneReacher(playerCrane, playerBoat, true);
        playerCrane.setCraneReacher(playerCraneReacher);
        playerScorebord = new Text(Color.GREEN, 25);
        addObject(playerCraneReacher, 1100, 300);
        addObject(playerCrane,900,300);
        addObject(playerScorebord, 1200,50);

        cpuTrucks = new ArrayList<>();
        teSpawnenCpuTrucks = createTrucks(cpuBoat);
        cpuCrane = new Crane();
        cpuCraneReacher = new CraneReacher(cpuCrane, cpuBoat, false);
        cpuCrane.setCraneReacher(cpuCraneReacher);
        cpuScorebord = new Text(Color.GREEN, 25);

        addObject(cpuCraneReacher, 180, 300);
        addObject(cpuCrane,380,300);
        addObject(cpuScorebord, 200,50);

    }

    public void StartMusic()
    {
        backgroundMusic.playLoop();
    }

    public int getScoreToReach()
    {
        return scoreToReach;
    }

    public List<TruckMg2> getCpuTrucks()
    {
        return cpuTrucks;
    }

    public BoatMg2 getCpuBoat()
    {
        return cpuBoat;
    }

    public void act()
    {
        if(!getIsPaused())
        {
            spawnTruck();
            checkEndOfGamePlayer();
            checkEndOfGameCpu();
        } else
        {
            if(!areActorsPaused)
            {
                setPauseOnAllActors();
            }
        }
    }

    private void spawnTruck()
    {
        truckCounter += 1;
        if(truckCounter >= 150)
        {
            if(playerTrucks != null && playerTrucks.size() < 6)
            {
                AddTruck(true);
            }

            if(cpuTrucks != null && cpuTrucks.size() < 6)
            {
                AddTruck(false);
            }

            truckCounter = 0;
        }
    }

    private void checkEndOfGameCpu()
    {
        boolean cpuBoatIsOutOfBalance = cpuBoat != null && cpuBoat.getIsOutOfBalance();
        boolean allCpuTrucksLeftGame = cpuTrucks != null && cpuTrucks.isEmpty() && teSpawnenCpuTrucks != null && teSpawnenCpuTrucks.isEmpty();
        if(cpuBoatIsOutOfBalance || allCpuTrucksLeftGame)
        {
            PauseWorld(true);
        }
    }

    public void checkEndOfGamePlayer()
    {
        boolean playerBoatIsOutOfBalance = playerBoat != null && playerBoat.getIsOutOfBalance();        
        boolean allPlayerTrucksLeftGame = playerTrucks != null && playerTrucks.isEmpty() && teSpawnenPlayerTrucks != null && teSpawnenPlayerTrucks.isEmpty();
        if(playerBoatIsOutOfBalance || allPlayerTrucksLeftGame)
        {
            PauseWorld(true);
        }
    }

    public void AddTruck(boolean isPlayerTruck)
    {
        if(isPlayerTruck)
        {
            if(!teSpawnenPlayerTrucks.isEmpty())
            {
                TruckMg2 newTruck = teSpawnenPlayerTrucks.get(randInt(0, teSpawnenPlayerTrucks.size() - 1));
                teSpawnenPlayerTrucks.remove(newTruck);
                addObject(newTruck, 967, 768);
                playerTrucks.add(newTruck);
            }
        } 
        else
        {

            if(!teSpawnenCpuTrucks.isEmpty())
            {
                TruckMg2 newTruck = teSpawnenCpuTrucks.get(randInt(0, teSpawnenCpuTrucks.size() - 1));
                teSpawnenCpuTrucks.remove(newTruck);
                addObject(newTruck, 312, 768);
                cpuTrucks.add(newTruck);
            }
        }
    }

    public void RemoveTruck(TruckMg2 truck)
    {
        if(truck.getIsPlayerTruck())
        {
            playerTrucks.remove(truck);
            addPoints(2, true);
        }
        else
        {
            cpuTrucks.remove(truck);
            addPoints(2, false);
        }
        removeObject(truck);
    }

    public TruckMg2 GetTruck(String color)
    {
        for(TruckMg2 truck : cpuTrucks)
        {
            if(!truck.getIsLoaded() && truck.getColor() == color)
            {
                return truck;
            }
        }
        return null;
    }

    public void PauseWorld(boolean isGameOver)
    {
        this.setIsPaused(true);
        addObject(new PauseScreen("PauseScreenMinigame2.png"), 640, 384);
        if(!isGameOver)
        {
            addObject(new GameNavigationButton(this, "Resume"), 500, 560);
        }
        addObject(new GameNavigationButton(this, "Restart"), 600, 560);
        addObject(new GameNavigationButton(this, "Stop"), 700, 560);
        addObject(new OpenLinkButton("https://www.youtube.com/watch?v=xnQdipnFEmw", "PlayContainerIntroductie.png"), 850, 560);
    }

    public void StopWorld()
    {
        Greenfoot.setWorld(parentWorld);
        backgroundMusic.stop();
    }

    public void RestartWorld()
    {
        parentWorld.StartNewGame("minigame2");
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
        this.setIsPaused(false);
        setPauseOnAllActors();
    }

    private void addPoints(int points, boolean isPlayerScore)
    {
        if(isPlayerScore)
        {
            playerScore += points;
            playerScorebord.SetText(Integer.toString(playerScore), Color.GREEN, null);
            parentWorld.setGameScore("minigame2", playerScore);
        } 
        else
        {
            cpuScore += points;
            cpuScorebord.SetText(Integer.toString(cpuScore), Color.GREEN, null);
        }

    }

    private ArrayList<TruckMg2> createTrucks(BoatMg2 boat)
    {
        ArrayList<TruckMg2> truckArray = new ArrayList<>();

        for(int i = 1; i <= boat.getRows() / 2; i++)
        {
            truckArray.add(new TruckMg2(!boat.getIsCpuBoat(), "Blauw"));
            truckArray.add(new TruckMg2(!boat.getIsCpuBoat(), "Groen"));
            truckArray.add(new TruckMg2(!boat.getIsCpuBoat(), "Grijs"));
        }
        return truckArray;
    }

    private void spawnBoats()
    {
        boolean hasCpuBoat = false;
        boolean hasPlayerBoat = false;
        for(BoatMg2 boat : getObjects(BoatMg2.class))
        {
            if(boat.getIsCpuBoat())
            {
                hasCpuBoat = true;
            } else
            {
                hasPlayerBoat = true;
            }
        }

        if (!hasCpuBoat)
        {
            cpuBoat = new BoatMg2(this, true);
            addObject(cpuBoat, 560, getWidth());
        }

        if (!hasPlayerBoat)
        {
            playerBoat = new BoatMg2(this, false);
            addObject(playerBoat, 760, getWidth());
        }
    }

    private void setPauseOnAllActors()
    {
        for(Crane crane : getObjects(Crane.class)){
            crane.setIsPaused(getIsPaused());
        }

        for(CraneReacher craneReacher : getObjects(CraneReacher.class)){
            craneReacher.setIsPaused(getIsPaused());
        }

        for(BoatMg2 boatMg2 : getObjects(BoatMg2.class)){
            boatMg2.setIsPaused(getIsPaused());
        }

        for(TruckMg2 truckMg2 : getObjects(TruckMg2.class)){
            truckMg2.setIsPaused(getIsPaused());
        }

        for(ContainerMG2 containerMG2 : getObjects(ContainerMG2.class)){
            containerMG2.setIsPaused(getIsPaused());
        }
    }

    private int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
