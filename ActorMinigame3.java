import greenfoot.*;

public abstract class ActorMinigame3 extends Actor
{
    public ActorMinigame3()
    {
        String className = this.getClass().getName();
        GreenfootImage defaultImage = new GreenfootImage("images/Minigame3/" + className + "_default.png");
        setImage(defaultImage);
    }
}


