import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HarborStoplight extends PausableActor
{
    private Harbor harbor;
    private String kleur;

    public HarborStoplight(Harbor harbor, String kleur)
    {
        this.harbor = harbor;
        this.kleur = kleur;
        ZetStoplicht(kleur);
    }

    public void act() 
    {
        if(!getIsPaused() && Greenfoot.mouseClicked(this) && kleur == "Oranje")
        {
            harbor.ReleaseBoat();
            ZetStoplicht("Groen");
        }
    }    

    public void ZetStoplicht(String kleur)
    {
        this.kleur = kleur;
        setImage("StoplichtKlein" + kleur + ".png");
    }
}
