
/**
 * Write a description of class ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    
    //private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        //myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double average = 0.0;
        int count = 0;
        for(EfficientRater r : myRaters){
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
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : myMovies){
            double average = 0.0;
            for(EfficientRater r : myRaters){
                average = getAverageByID(m, minimalRaters);
            }
            if(average != 0.0){
                Rating raux = new Rating(m, average);
                rat.add(raux);
            }
        }
        return rat;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> rat = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for(String s : myMovies){
            double average = 0.0;
            for(EfficientRater r : myRaters){
                average = getAverageByID(s, minimalRaters);
            }
            if(average != 0.0){
                Rating raux = new Rating(s, average);
                rat.add(raux);
            }
        }
        return rat;
    }
}
