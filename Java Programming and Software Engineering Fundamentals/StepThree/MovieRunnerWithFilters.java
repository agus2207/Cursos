
/**
 * Write a description of class MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        ArrayList<Rating> rat = tr.getAverageRatings(35);
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
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(20, f);
        System.out.println("Found "+rat.size()+" movies");
        /*Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            System.out.println("Average: "+average+" | Movie: "+name);
        }*/
    }
    
    public void printAverageRatingsByGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new GenreFilter("Comedy");
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(20, f);
        System.out.println("Found "+rat.size()+" movies");
        /*Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String genres = MovieDatabase.getGenres(id);
            System.out.println("Average: "+average+" | Movie: "+name+
                " | Genres: "+genres);
        }*/
    }
    
    public void printAverageRatingsByMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new MinutesFilter(105, 135);
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(5, f);
        System.out.println("Found "+rat.size()+" movies");
        /*Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            int time = MovieDatabase.getMinutes(id);
            System.out.println("Average: "+average+" | Time: "+time+
                " | Movie: "+name);
        }*/
    }
    
    public void printAverageRatingsByDirectors(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(4, f);
        System.out.println("Found "+rat.size()+" movies");
        /*Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String director = MovieDatabase.getDirector(id);
            System.out.println("Average: "+average+" | Movie: "+name+
                " | Director: "+director);
        }*/
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(8, f);
        System.out.println("Found "+rat.size()+" movies");
        /*Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String genre = MovieDatabase.getGenres(id);
            System.out.println("Average: "+average+" | Movie: "+name+
                " | Genre: "+genre);
        }*/
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+tr.getRaterSize());
        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies: "+MovieDatabase.size());
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(90, 180));
        f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> rat = tr.getAverageRatingsByFilter(3, f);
        System.out.println("Found "+rat.size()+" movies");
        /*Collections.sort(rat);
        for(Rating r : rat){
            double average = r.getValue();
            String id = r.getItem();
            String name = MovieDatabase.getTitle(id);
            String director = MovieDatabase.getDirector(id);
            int time = MovieDatabase.getMinutes(id);
            System.out.println("Average: "+average+" | Time: "+time+
                " | Movie: "+name+" | Director: "+director);
        }*/
    }
}
