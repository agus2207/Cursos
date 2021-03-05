
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double average = 0.0;
        int count = 0;
        for(Rater r : myRaters){
            if(r.hasRating(id)){
                average += r.getRating(id);
                count++;
            }
        }
        if(count < minimalRaters){
            return 0.0;
        }
        return average/count;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> rat = new ArrayList<Rating>();
        for(Movie m : myMovies){
            String name = m.getID();
            double average = 0.0;
            for(Rater r : myRaters){
                average = getAverageByID(name, minimalRaters);
            }
            if(average != 0.0){
                Rating raux = new Rating(name, average);
                rat.add(raux);
            }
        }
        return rat;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "MOVIE NOT FOUND";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
