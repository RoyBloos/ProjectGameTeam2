public class BoatSpawn  
{
    private Boat boat;
    private int time;
    
    public BoatSpawn(Boat boat, int time)
    {
       this.boat = boat;
       this.time = time;
    }
    
    public Boat getBoat()
    {
        return boat;
    }
    
    public int getTime()
    {
        return time;
    }
}
