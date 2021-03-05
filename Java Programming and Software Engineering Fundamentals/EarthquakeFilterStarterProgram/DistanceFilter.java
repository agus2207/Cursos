
/**
 * Write a description of class DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    
    private Location loc;
    private double maxDistance;
    private String name;
    
    public DistanceFilter(Location from, double max, String fname){
        loc = from;
        maxDistance = max;
        name = fname;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getLocation().distanceTo(loc))/1000 < maxDistance;
    }
    
    public String getName(){
        return name;
    }
}
