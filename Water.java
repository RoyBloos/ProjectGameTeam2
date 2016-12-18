import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Water here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Water extends Actor
{
  
    public Water()
    {
        GreenfootImage image = getImage();
        image.scale(image.getWidth() - 1, image.getHeight() - 10);
        setImage(image);
    }
   public void act() 
    {
        burn();
    }    
    
    public void burn()
    {
        Actor removePresent = getOneIntersectingObject(Present.class);
                
        if(removePresent != null)
        {
            getWorld().removeObject(removePresent);
        }
    }
  
}
