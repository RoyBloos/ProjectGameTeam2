import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HavenStoplicht here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HavenStoplicht extends Actor
{
    private Haven haven;
    private String kleur;
    public HavenStoplicht(Haven haven, String kleur)
    {
        this.haven = haven;
        this.kleur = kleur;
        ZetStoplicht(kleur);
    }
    /**
     * Act - do whatever the HavenStoplicht wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this) && kleur == "Oranje")
        {
           haven.LaatBootGaan();
           ZetStoplicht("Groen");
        }
    }    
    
    public void ZetStoplicht(String kleur)
    {
        this.kleur = kleur;
       setImage("StoplichtKlein" + kleur + ".png");
    }
}
