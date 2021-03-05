
/**
 * Write a description of class MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "ratings_short.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        ArrayList<Rating> rat = fr.getAverageRatings(1);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            System.out.println("Average: "+average+" | Movie: "+name);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "ratings_short.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1980));
        f.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> rat = fr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String genre = MovieDatabase.getGenres(id);
            System.out.println("Average: "+average+" | Movie: "+name+
                " | Genre: "+genre);
        }
    }
    
    public void printSimilarRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        ArrayList<Rating> ans = fr.getSimilarRatings("71", 20, 5);
        //System.out.println("Ans size: "+ans.size());
        //System.out.println("Movie: "+ans.get(0).getItem());
        System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
    }
    
    public void printSimilarRatingsByGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> ans = fr.getSimilarRatingsByFilter("964",20,5,f);
        //System.out.println("Movie: "+ans.get(0).getItem());
        System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
    }
    
    public void printSimilarRatingsByDirector(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ans = fr.getSimilarRatingsByFilter("120",10,2,f);
        //System.out.println("Movie: "+ans.get(0).getItem());
        System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(80, 160));
        f.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ans = fr.getSimilarRatingsByFilter("168",10,3,f);
        //System.out.println("Movie: "+ans.get(0).getItem());
        System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(70, 200));
        f.addFilter(new YearAfterFilter(1975));
        ArrayList<Rating> ans = fr.getSimilarRatingsByFilter("314",10,5,f);
        //System.out.println("Movie: "+ans.get(0).getItem());
        System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
    }
}
