
/**
 * Write a description of class MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        System.out.println("Number of movies: "+sr.getMovieSize());
        System.out.println("Number of raters: "+sr.getRaterSize());
        ArrayList<Rating> rat = sr.getAverageRatings(3);
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = sr.getTitle(id);
            System.out.println("Average: "+average+" Movie: "+name);
        }
    }
    
    public void getAverageRatingOneMovie(){
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        String name = "Moneyball";
        String id = sr.getID(name);
        System.out.println("ID: "+id);
        ArrayList<Rating> rat = sr.getAverageRatings(1);
        for(Rating r : rat){
            String aux = r.getItem();
            if(aux.equals(id)){
                System.out.println(name+" Average: "+r.getValue());
                break;
            }
        }
    }
}
