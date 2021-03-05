
/**
 * Write a description of class MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{

    private int min;
    private int max;
    
    public MinutesFilter(int mintime, int maxtime){
        min = mintime;
        max = maxtime;
    }
    
    public boolean satisfies(String id){
        int moviet = MovieDatabase.getMinutes(id);
        if(moviet >= min && moviet <= max){
            return true;
        }
        return false;
    }
}
