
/**
 * Write a description of class MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        ArrayList<Rating> rat = tr.getAverageRatings(1);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            System.out.println("Average: "+average+" | Movie: "+name);
        }
    }
    
    public void printAverageRatingsByYear(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            System.out.println("Average: "+average+" | Movie: "+name);
        }
    }
    
    public void printAverageRatingsByGenre(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new GenreFilter("Crime");
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String genres = MovieDatabase.getGenres(id);
            System.out.println("Average: "+average+" | Movie: "+name+
                " | Genres: "+genres);
        }
    }
    
    public void printAverageRatingsByMinutes(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new MinutesFilter(110, 170);
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            int time = MovieDatabase.getMinutes(id);
            System.out.println("Average: "+average+" | Time: "+time+
                " | Movie: "+name);
        }
    }
    
    public void printAverageRatingsByDirectors(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String director = MovieDatabase.getDirector(id);
            System.out.println("Average: "+average+" | Movie: "+name+
                " | Director: "+director);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1980));
        f.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(1, f);
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
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(30, 170));
        f.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(1, f);
        System.out.println("Found "+rat.size()+" movies");
        Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String director = MovieDatabase.getDirector(id);
            int time = MovieDatabase.getMinutes(id);
            System.out.println("Average: "+average+" | Time: "+time+
                " | Movie: "+name+" | Director: "+director);
        }
    }
}
