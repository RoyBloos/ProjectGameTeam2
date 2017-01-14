import greenfoot.*;
import java.awt.Color;

public class WorldMainMenu extends World
{
    private HavenmeesterWorld havenmeesterWorld;
    private WorldMinigame2 worldMinigame2;
    private WorldMinigame3 worldMinigame3;
    private WorldMinigame4 worldMinigame4;

    private static final String NAAMMINIGAME1 = "Havenmeester";
    private static final String NAAMMINIGAME2 = "minigame2";
    private static final String NAAMMINIGAME3 = "minigame3";
    private static final String NAAMMINIGAME4 = "minigame4";

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

    private void setScore(int score, int currentHighscore, int scoreToReach, Text scoreBoard)
    {
        Color textColor = Color.RED;
        int scoreToSet = currentHighscore;
        if(score > currentHighscore)
        {
            scoreToSet = score;
        }
        if (scoreToSet >= scoreToReach)
        {
            textColor = Color.GREEN;
        }
        scoreBoard.SetText(Integer.toString(scoreToSet) + " / " + Integer.toString(scoreToReach), textColor, null);
    }

    public void setGameScore(String gameName, int score)
    {

        if(gameName == NAAMMINIGAME1)
        {
            setScore(score, HighScoreHavenmeester, havenmeesterWorld.getScoreToReach(), ScorebordHavenmeester);
        } else if(gameName == NAAMMINIGAME2)
        {
            setScore(score, HighScoreMinigame2, worldMinigame2.ScoreToReach, ScorebordMinigame2);
        } else if(gameName == NAAMMINIGAME3)
        {
            if(score > HighScoreMinigame3)
            {
                HighScoreMinigame3 = score;
            }
        } else if(gameName == NAAMMINIGAME4)
        {
            Color textColor = Color.RED;
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
        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME1, "MenuKnop1.png"), 344, 137);
        ScorebordHavenmeester = new Text(Color.RED, 25);
        addObject(ScorebordHavenmeester, 444,174);

        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME2, "MenuKnop2.png"), 132, 321);
        ScorebordMinigame2 = new Text(Color.RED, 25);
        addObject(ScorebordMinigame2, 232,358);

        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME3, "MenuKnop3.png"), 738, 258);
        ScorebordMinigame3 = new Text(Color.RED, 25);
        addObject(ScorebordMinigame3, 838,295);

        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME4, "MenuKnop4.png"), 1024, 393);
        ScorebordMinigame4 = new Text(Color.RED, 25);
        addObject(ScorebordMinigame4, 1124,430);
    }

    public void StartNewGame(String gameName)
    {
        if(gameName == NAAMMINIGAME1)
        {
            havenmeesterWorld = new HavenmeesterWorld(this, getWidth(), getHeight());
            Greenfoot.setWorld(havenmeesterWorld);
        } else if(gameName == NAAMMINIGAME2)
        {
            worldMinigame2 = new WorldMinigame2(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame2);
        } else if(gameName == NAAMMINIGAME3)
        {
            worldMinigame3 = new WorldMinigame3(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame3);
        } else if(gameName == NAAMMINIGAME4)
        {
            worldMinigame4 = new WorldMinigame4(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame4);
        }
    }
}
