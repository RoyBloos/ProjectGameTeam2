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

    private int highScoreHavenmeester = 0;
    private int highScoreMinigame2 = 0;
    private int highScoreMinigame3 = 0;
    private int highScoreMinigame4 = 0;

    private Text scorebordHavenmeester;
    private Text scorebordMinigame2;
    private Text scorebordMinigame3;
    private Text scorebordMinigame4;

    public WorldMainMenu()
    {    
        super(1280, 768, 1); 
        createMinigames();
        createMenuButtons();
    }

    public void setGameScore(String gameName, int score)
    {
        if(gameName == NAAMMINIGAME1)
        {
            setScoreAndColor(score, highScoreHavenmeester, havenmeesterWorld.getScoreToReach(), scorebordHavenmeester);
        } else if(gameName == NAAMMINIGAME2)
        {
            setScoreAndColor(score, highScoreMinigame2, worldMinigame2.getScoreToReach(), scorebordMinigame2);
        } else if(gameName == NAAMMINIGAME3)
        {
            Color textColor = Color.RED;
            if(score > highScoreMinigame3)
            {
                highScoreMinigame3 = score;
            }
            if (highScoreMinigame3 >= worldMinigame3.scoreToReach)
            {
                textColor = Color.GREEN;
            }
            scorebordMinigame3.SetText(Integer.toString(highScoreMinigame3) + " / " + Integer.toString(worldMinigame3.scoreToReach), textColor, null);             
        } else if(gameName == NAAMMINIGAME4)
        {
            Color textColor = Color.RED;
            if(score > highScoreMinigame4)
            {
                highScoreMinigame4 = score;
            }
            if (highScoreMinigame4 >= worldMinigame4.getScoreToReach())
            {
                textColor = Color.GREEN;
            }
            scorebordMinigame4.SetText(Integer.toString(highScoreMinigame4) + " / " + Integer.toString(worldMinigame4.getScoreToReach()), textColor, null);
        }
    }

    public void StartNewGame(String gameName)
    {
        if(gameName == NAAMMINIGAME1)
        {
            havenmeesterWorld = new HavenmeesterWorld(this, getWidth(), getHeight());
            Greenfoot.setWorld(havenmeesterWorld);
            havenmeesterWorld.StartMusic();
        } else if(gameName == NAAMMINIGAME2)
        {
            worldMinigame2 = new WorldMinigame2(this, getWidth(), getHeight());
            Greenfoot.setWorld(worldMinigame2);
            worldMinigame2.StartMusic();
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

    private void createMinigames()
    {
        havenmeesterWorld = new HavenmeesterWorld(this,getWidth(), getHeight());
        worldMinigame2 = new WorldMinigame2(this, getWidth(), getHeight());
        worldMinigame3 = new WorldMinigame3(this, getWidth(), getHeight());
        worldMinigame4 = new WorldMinigame4(this, getWidth(), getHeight());
    }

    private void createMenuButtons()
    {
        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME1, "MenuKnop1.png"), 344, 137);
        scorebordHavenmeester = new Text(Color.RED, 25);
        addObject(scorebordHavenmeester, 444,174);

        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME2, "MenuKnop2.png"), 132, 321);
        scorebordMinigame2 = new Text(Color.RED, 25);
        addObject(scorebordMinigame2, 232,358);

        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME3, "MenuKnop3.png"), 738, 258);
        scorebordMinigame3 = new Text(Color.RED, 25);
        addObject(scorebordMinigame3, 838,295);

        addObject(new ButtonSelectMinigame(this, NAAMMINIGAME4, "MenuKnop4.png"), 1024, 393);
        scorebordMinigame4 = new Text(Color.RED, 25);
        addObject(scorebordMinigame4, 1124,430);
    }

    private void setScoreAndColor(int score, int currentHighscore, int scoreToReach, Text scoreBoard)
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
}
