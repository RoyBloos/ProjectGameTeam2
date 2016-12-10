import greenfoot.*;
import java.awt.Color;

public class WorldMainMenu extends World
{
    public HavenmeesterWorld havenmeesterWorld;
    public WorldMinigame2 worldMinigame2;
    public WorldMinigame3 worldMinigame3;
    public WorldMinigame4 worldMinigame4;

    public int HighScoreHavenmeester = 0;
    public int HighScoreMinigame2 = 0;
    public int HighScoreMinigame3 = 0;
    public int HighScoreMinigame4 = 0;
    
    public Text ScorebordHavenmeester;
    public Text ScorebordMinigame2;
    public Text ScorebordMinigame3;
    public Text ScorebordMinigame4;
    
    public WorldMainMenu()
    {    
        super(1280, 768, 1); 
        CreateMinigames();
        CreateMenuButtons();
    }
    
   public void SetScore(String gameName, int score)
   {
        Color textColor = Color.RED;
        if(gameName == "Havenmeester")
        {
            if(score > HighScoreHavenmeester)
            {
                HighScoreHavenmeester = score;
            }
            if (HighScoreHavenmeester >= havenmeesterWorld.ScoreToReach)
            {
                textColor = Color.GREEN;
            }
            ScorebordHavenmeester.SetText(Integer.toString(HighScoreHavenmeester) + " / " + Integer.toString(havenmeesterWorld.ScoreToReach), textColor, null);
        } else if(gameName == "minigame2")
        {
            if(score > HighScoreMinigame2)
            {
                HighScoreMinigame2 = score;
            }
            if (HighScoreMinigame2 >= worldMinigame2.ScoreToReach)
            {
                textColor = Color.GREEN;
            }
            ScorebordMinigame2.SetText(Integer.toString(HighScoreMinigame2) + " / " + Integer.toString(worldMinigame2.ScoreToReach), textColor, null);
        } else if(gameName == "minigame3")
        {
            if(score > HighScoreMinigame3)
            {
                HighScoreMinigame3 = score;
            }
            if (HighScoreMinigame3 >= worldMinigame3.ScoreToReach)
            {
                textColor = Color.GREEN;
            }
            ScorebordMinigame3.SetText(Integer.toString(HighScoreMinigame3) + " / " + Integer.toString(worldMinigame3.ScoreToReach), textColor, null);
        } else if(gameName == "minigame4")
        {
           if(score > HighScoreMinigame4)
            {
                HighScoreMinigame4 = score;
            }
            if (HighScoreMinigame4 >= worldMinigame4.ScoreToReach)
            {
                textColor = Color.GREEN;
            }
            ScorebordMinigame4.SetText(Integer.toString(HighScoreMinigame4) + " / " + Integer.toString(worldMinigame4.ScoreToReach), textColor, null);
        }
    }
    
    private void CreateMinigames()
    {
        havenmeesterWorld = new HavenmeesterWorld(this,getWidth(), getHeight());
        worldMinigame2 = new WorldMinigame2(this, getWidth(), getHeight());
        worldMinigame3 = new WorldMinigame3(this, getWidth(), getHeight());
        worldMinigame4 = new WorldMinigame4(this, getWidth(), getHeight());
    }
    
    private void CreateMenuButtons()
    {
        addObject(new ButtonSelectMinigame(this, "Havenmeester", "MenuKnop1.png"), 344, 137);
        ScorebordHavenmeester = new Text(Color.RED, 25);
        addObject(ScorebordHavenmeester, 444,174);
        
        addObject(new ButtonSelectMinigame(this, "minigame2", "MenuKnop2.png"), 132, 321);
        ScorebordMinigame2 = new Text(Color.RED, 25);
        addObject(ScorebordMinigame2, 232,358);
        
        addObject(new ButtonSelectMinigame(this, "minigame3", "MenuKnop3.png"), 738, 258);
        ScorebordMinigame3 = new Text(Color.RED, 25);
        addObject(ScorebordMinigame3, 838,295);
        
        addObject(new ButtonSelectMinigame(this, "minigame4", "MenuKnop4.png"), 1024, 393);
        ScorebordMinigame4 = new Text(Color.RED, 25);
        addObject(ScorebordMinigame4, 1124,430);
    }
    
    public void StartNewGame(String gameName)
    {
        if(gameName == "Havenmeester")
        {
            havenmeesterWorld = new HavenmeesterWorld(this, getWidth(), getHeight());
            Greenfoot.setWorld(havenmeesterWorld);
        } else if(gameName == "minigame2")
        {
            worldMinigame2 = new WorldMinigame2(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame2);
        } else if(gameName == "minigame3")
        {
            worldMinigame3 = new WorldMinigame3(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame3);
        } else if(gameName == "minigame4")
        {
            worldMinigame4 = new WorldMinigame4(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame4);
        }
    }
}
