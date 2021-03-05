
/**
 * Write a description of class FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie m = new Movie(id, title, year, genres, director, country, poster, minutes);
            movies.add(m);
        }
        return movies;
    }
    
    public int indexOf(String id, ArrayList<EfficientRater> rates){
        for(int i = 0; i < rates.size(); i++){
            if(rates.get(i).getID().equals(id)){
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename){
        ArrayList<EfficientRater> rates = new ArrayList<EfficientRater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String id = record.get("rater_id");
            double rating = Double.parseDouble(record.get("rating"));
            String movie = record.get("movie_id");
            int index = indexOf(id,rates);
            if(index == -1){
                EfficientRater r = new EfficientRater(id);
                r.addRating(movie, rating);
                rates.add(r);
            }
            else{
                EfficientRater r = rates.get(index);
                r.addRating(movie, rating);
            }
        }
        return rates;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Total movies: "+movies.size());
        HashMap<String, Integer> dir = new HashMap<String, Integer>();
        int countg = 0;
        int min = 0;
        int director = 0;
        for(Movie m : movies){
            //System.out.println(m.getGenres());
            if(m.getGenres().contains("Comedy")){
                countg++;
            }
            if(m.getMinutes() > 150){
                min++;
            }
            String direc = m.getDirector();
            String[] directors = direc.split(",");
            int aux = directors.length;
            if(aux == 1){
                if(dir.containsKey(direc)){
                    dir.put(direc, dir.get(direc)+1);
                }
                else{
                    dir.put(direc, 1);
                }
            }
        }
        System.out.println("Number of Comedy: "+countg);//Numbers of movies with certain genre
        System.out.println("Movies greater than 150: "+min);//Movies with certain duration
        int max = 0;
        String name = "";
        for(String s : dir.keySet()){
            int aux = dir.get(s);
            if(max == 0 || max < aux){
                max = aux;
                name = s;
            }
        }
        //name of director with the max directed movies
        System.out.println("Director name: "+name+" Directed: "+max);
    }
    
    public void testLoadRaters(){
        ArrayList<EfficientRater> rates = loadRaters("data/ratings.csv");
        System.out.println("Total rates: "+rates.size());
        int num_rat = 0;
        int max = 0;
        int movie_r = 0;
        for(EfficientRater r : rates){
            if(r.getID().equals("193")){
                num_rat = r.numRatings();
            }
            int aux = r.numRatings();
            if(max == 0 || max < aux){
                max = aux;
            }
            if(r.hasRating("1798709")){
                movie_r++;
            }
            //System.out.println("Rater: "+r.getID());
            //ArrayList<String> rating = r.getItemsRated();
            //for(String s : rating){
                //System.out.println("Rating: "+s);
            //}
        }
        System.out.println("Number of ratings: "+num_rat); //rating by a user
        System.out.println("Movie was rating by: "+movie_r); //number of raters for a movie
        int count = 0;
        for(EfficientRater r : rates){
            if(r.numRatings() == max){
                System.out.println("Identifier: "+r.getID());//id of user
                count++;
            }
        }
        //record of max number of ratings and the total user with that record
        System.out.println("Max number: "+max+" Number of Users: "+count);
        ArrayList<String> actualm = new ArrayList<String>();
        for(EfficientRater r : rates){
            ArrayList<String> aux = r.getItemsRated();
            for(String s : aux){
                if(!actualm.contains(s)){
                    actualm.add(s);
                }
            }
        }
        //Unique movies
        System.out.println("Actual movies rated: "+actualm.size());
    }
}
