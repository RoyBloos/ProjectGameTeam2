import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class WorldMinigame2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldMinigame2 extends World
{
    public Kraan KraanSpeler;
    public KraanGrijper KraanGrijperSpeler;
    public int ScoreToReach = 1;
    public BoatMg2 PlayerBoat = null;
    public BoatMg2 CPUBoat = null;
    public ArrayList<TruckMg2> PlayerTrucks;
    public int truckCounter;
    public boolean IsPaused;
    private boolean areActorsPaused;
    public WorldMainMenu ParentWorld;

    public WorldMinigame2(WorldMainMenu parentWorld, int gameHeight, int gameWidth)
    {    
        super(gameHeight, gameWidth, 1); 
        ParentWorld = parentWorld;
        PlayerTrucks = new ArrayList<TruckMg2>();
        spawnBoats();
        KraanSpeler = new Kraan();
        KraanGrijperSpeler = new KraanGrijper(KraanSpeler, PlayerBoat);
        KraanSpeler.Grijper = KraanGrijperSpeler;
        truckCounter = 0;
        addObject(KraanGrijperSpeler, 1100, 300);
        addObject(KraanSpeler,900,300);

        addObject(new GameNavigationButton(this, "Pause"), 50,50);

        setPaintOrder(GameNavigationButton.class, OpenLinkButton.class, PauseScreen.class, Kraan.class, KraanGrijper.class, ContainerMG2.class, BalanceIndicator.class, BalanceBar.class, BoatMg2.class);
    }

    public void act()
    {
        if(!IsPaused)
        {
            truckCounter += 1;
            if(PlayerTrucks.size() < 6 && truckCounter % 150 == 0)
            {
                TruckMg2 newTruck = new TruckMg2();
                addObject(newTruck, 967, 768);
                PlayerTrucks.add(newTruck);
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
        
        if(CPUBoat != null && CPUBoat.IsOutOfBalance)
        {
            // CPU lost
            if(!IsPaused)
            {
                PauseWorld(true);
            }
        }
        
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
            CPUBoat = new BoatMg2(this, true);
            addObject(CPUBoat, 560, getWidth());
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
        for(Kraan kraan : getObjects(Kraan.class)){
            kraan.IsPaused = IsPaused;
        }

        for(KraanGrijper kraanGrijper : getObjects(KraanGrijper.class)){
            kraanGrijper.IsPaused = IsPaused;
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
}
