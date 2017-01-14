import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HavenStoplicht extends PausableActor
{
    private Harbor harbor;
    private String kleur;

    public HavenStoplicht(Harbor harbor, String kleur)
    {
        this.harbor = harbor;
        this.kleur = kleur;
        ZetStoplicht(kleur);
    }

    public void act() 
    {
        if(!getIsPaused())
        {
            if(Greenfoot.mouseClicked(this) && kleur == "Oranje")
            {
               harbor.ReleaseBoat();
               ZetStoplicht("Groen");
            }
        }
    }    
    
    public void ZetStoplicht(String kleur)
    {
        this.kleur = kleur;
       setImage("StoplichtKlein" + kleur + ".png");
    }
}
