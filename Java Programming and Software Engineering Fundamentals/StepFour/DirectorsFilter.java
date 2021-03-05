
/**
 * Write a description of class DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    
    private String myDirector;
    
    public DirectorsFilter(String director){
        myDirector = director;
    }
    
    public boolean satisfies(String id){
        String [] directors = myDirector.split(",");
        for(String s : directors){
            String aux = MovieDatabase.getDirector(id);
            if(aux.contains(s)){
                return true;
            }
        }
        return false;
    }

}
