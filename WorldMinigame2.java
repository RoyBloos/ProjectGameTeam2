import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class WorldMinigame2 extends World
{

    public int ScoreToReach = 1;
    public int truckCounter;
    public boolean IsPaused;
    public WorldMainMenu ParentWorld;
    private boolean areActorsPaused;
    private Random rand;
    
    public ArrayList<TruckMg2> PlayerTrucks;
    private ArrayList<TruckMg2> teSpawnenPlayerTrucks;
    public ArrayList<TruckMg2> CpuTrucks;
    private ArrayList<TruckMg2> teSpawnenCpuTrucks;

    public BoatMg2 PlayerBoat = null;
    public Text PlayerScorebord;
    public int PlayerScore = 0;
    public Crane PlayerCrane;
    public CraneReacher PlayerCraneReacher;
    
    public BoatMg2 CpuBoat = null;
    public Text CpuScorebord;
    public int CpuScore = 0;
    public Crane CpuCrane;
    public CraneReacher CpuCraneReacher;

    public WorldMinigame2(WorldMainMenu parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        rand = new Random();
        ParentWorld = parentWorld;
        truckCounter = 0;
        spawnBoats();
        addObject(new GameNavigationButton(this, "Pause"), 50,50);
        setPaintOrder(GameNavigationButton.class, OpenLinkButton.class, PauseScreen.class, Crane.class, CraneReacher.class, ContainerMG2.class, BalanceIndicator.class, BalanceBar.class, BoatMg2.class);
        
        PlayerTrucks = new ArrayList<TruckMg2>();
        teSpawnenPlayerTrucks = createTrucks(PlayerBoat);
        PlayerCrane = new Crane();
        PlayerCraneReacher = new CraneReacher(PlayerCrane, PlayerBoat, true);
        PlayerCrane.CraneReacher = PlayerCraneReacher;

        addObject(PlayerCraneReacher, 1100, 300);
        addObject(PlayerCrane,900,300);
        
        CpuTrucks = new ArrayList<TruckMg2>();
        teSpawnenCpuTrucks = createTrucks(CpuBoat);
        CpuCrane = new Crane();
        CpuCraneReacher = new CraneReacher(CpuCrane, CpuBoat, false);
        CpuCrane.CraneReacher = CpuCraneReacher;
        
        addObject(CpuCraneReacher, 180, 300);
        addObject(CpuCrane,380,300);

    }

    public void act()
    {
        if(!IsPaused)
        {
            truckCounter += 1;
            if(truckCounter >= 150)
            {
                if(PlayerTrucks != null && PlayerTrucks.size() < 6)
                {
                    AddTruck(true);
                }
                
                if(CpuTrucks != null && CpuTrucks.size() < 6)
                {
                    AddTruck(false);
                }
                
                truckCounter = 0;
            }
        } else
        {
            if(!areActorsPaused)
            {
                SetPauseOnAllActors();
            }
        }
        
        if(PlayerBoat != null && PlayerBoat.IsOutOfBalance)
        {
            // Player lost
            if(!IsPaused)
            {
                PauseWorld(true);
            }
        }
        
        if(CpuBoat != null && CpuBoat.IsOutOfBalance)
        {
            // CPU lost
            if(!IsPaused)
            {
                PauseWorld(true);
            }
        }
        
    }

    public void AddTruck(boolean isPlayerTruck)
    {
        if(isPlayerTruck)
        {
            TruckMg2 newTruck = teSpawnenPlayerTrucks.get(randInt(0, teSpawnenPlayerTrucks.size() - 1));
            teSpawnenPlayerTrucks.remove(newTruck);
            addObject(newTruck, 967, 768);
            PlayerTrucks.add(newTruck);
        } 
        else
        {
            TruckMg2 newTruck = teSpawnenCpuTrucks.get(randInt(0, teSpawnenCpuTrucks.size() - 1));
            teSpawnenCpuTrucks.remove(newTruck);
            addObject(newTruck, 312, 768);
            CpuTrucks.add(newTruck);
        }
    }
    
    public void RemoveTruck(TruckMg2 truck)
    {
        if(truck.IsPlayerTruck)
        {
            PlayerTrucks.remove(truck);
        }
        else
        {
            CpuTrucks.remove(truck);
        }
        removeObject(truck);
    }
    
    public TruckMg2 GetTruck(String color)
    {
        for(TruckMg2 truck : CpuTrucks)
        {
            if(!truck.IsLoaded && truck.Color == color)
            {
                return truck;
            }
        }
        return null;
    }
    
    private ArrayList<TruckMg2> createTrucks(BoatMg2 boat)
    {
        ArrayList<TruckMg2> truckArray = new ArrayList<TruckMg2>();
        
        for(int i = 1; i <= boat.Rows; i++)
        {
            truckArray.add(new TruckMg2(!boat.IsCpuBoat, "Blauw"));
            truckArray.add(new TruckMg2(!boat.IsCpuBoat, "Groen"));
            truckArray.add(new TruckMg2(!boat.IsCpuBoat, "Grijs"));
        }
        
        return truckArray;
    }
    
    private void spawnBoats()
    {
        boolean hasCpuBoat = false;
        boolean hasPlayerBoat = false;
        for(BoatMg2 boat : getObjects(BoatMg2.class))
        {
            if(boat.IsCpuBoat)
            {
                hasCpuBoat = true;
            } else
            {
                hasPlayerBoat = true;
            }
        }

        if (!hasCpuBoat)
        {
            CpuBoat = new BoatMg2(this, true);
            addObject(CpuBoat, 560, getWidth());
        }

        if (!hasPlayerBoat)
        {
            PlayerBoat = new BoatMg2(this, false);
            addObject(PlayerBoat, 760, getWidth());
        }
    }

    public void PauseWorld(boolean isGameOver)
    {
        this.IsPaused = true;
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
        Greenfoot.setWorld(ParentWorld);
    }

    public void RestartWorld()
    {
        ParentWorld.StartNewGame("minigame2");
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
        this.IsPaused = false;
        SetPauseOnAllActors();
    }

    private void SetPauseOnAllActors()
    {
        for(Crane crane : getObjects(Crane.class)){
            crane.IsPaused = IsPaused;
        }

        for(CraneReacher craneReacher : getObjects(CraneReacher.class)){
            craneReacher.IsPaused = IsPaused;
        }

        for(BoatMg2 boatMg2 : getObjects(BoatMg2.class)){
            boatMg2.IsPaused = IsPaused;
        }

        for(TruckMg2 truckMg2 : getObjects(TruckMg2.class)){
            truckMg2.IsPaused = IsPaused;
        }

        for(ContainerMG2 containerMG2 : getObjects(ContainerMG2.class)){
            containerMG2.IsPaused = IsPaused;
        }
    }
    
    private int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
