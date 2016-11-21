import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HavenmeesterButton extends Actor
{
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Button moet een nieuw havenmeester spel starten
        // Maak havenmeesterwereld aan en laat deze zien
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new HavenmeesterWorld());
        }   
    }    
}
