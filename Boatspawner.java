import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.Math;
import java.util.Random;

/**
 * Write a description of class Boatspawner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boatspawner extends Actor
{
    private int counter = 1;
    private Random rand;
    
    public Boatspawner()
    {
        rand = new Random();
    }
    
    /**
     * Act - do whatever the Boatspawner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        addBoat();
        counter += 1;
    }   
    
    private void addBoat()
    {
        List<Boat> boats = getWorld().getObjects(Boat.class);
        if(boats.size() < (int)(counter / 200)){
           Boatlane boatlane = GetAvailableBoatlane();
           if(boatlane != null)
           {
               if(boatlane.Direction == 0)
               {
                   getWorld().addObject(new Boat(boatlane), 0, boatlane.Yas);
               } else if(boatlane.Direction == 180)
               {
                   getWorld().addObject(new Boat(boatlane), 1280, boatlane.Yas);
               }
           }
        }  
    }
    
    private Boatlane GetAvailableBoatlane()
    {
         for(Boatlane boatlane : getWorld().getObjects(Boatlane.class)){
            if(!boatlane.IsOccupied){
                return boatlane;
            }
        }
        return null;
    }
    
    public int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
