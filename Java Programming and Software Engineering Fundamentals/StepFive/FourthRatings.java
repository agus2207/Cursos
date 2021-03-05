
/**
 * Write a description of class FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters){
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : myMovies){
            double average = 0.0;
            for(Rater r : myRaters){
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for(String s : myMovies){
            double average = 0.0;
            for(Rater r : myRaters){
                average = getAverageByID(s, minimalRaters);
            }
            if(average != 0.0){
                Rating raux = new Rating(s, average);
                rat.add(raux);
            }
        }
        return rat;
    }
    
    private double dotProduct(Rater me, Rater r){
        HashSet<String> itemsRated = new HashSet<String>();
        itemsRated.addAll(me.getItemsRated());
        itemsRated.addAll(r.getItemsRated());
        double close = 0;
        double ratingme = 0;
        double ratingr = 0;
        for(String item : itemsRated){
            ratingme = me.getRating(item);
            ratingr = r.getRating(item);
            //if either of rater has not rated the movie
            if(ratingme == -1 || ratingr == -1){
                continue;
            }
            close += (ratingme-5)*(ratingr-5);
        }
        return close;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : raters){
            if(!r.getID().equals(id)){
               double aux = dotProduct(me, r);
               if(aux > 0.0){
                   Rating rat = new Rating(r.getID(), aux);
                   ans.add(rat);
               }
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<Rating> aux = getSimilarities(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String s : myMovies){
            double average = 0.0;
            int count = 0;
            for(int i = 0; i < numSimilarRaters; i++){
                double rate_close = aux.get(i).getValue();
                String rate_id = aux.get(i).getItem();
                double movie_r = RaterDatabase.getRater(rate_id).getRating(s);
                if(movie_r != -1){
                    count++;
                    average += rate_close*movie_r;
                }
            }
            if(count >= minimalRaters){
                Rating add = new Rating(s, (average/count)); 
                ans.add(add);
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f){
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<Rating> aux = getSimilarities(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(f);
        for(String s : myMovies){
            double average = 0.0;
            int count = 0;
            for(int i = 0; i < numSimilarRaters; i++){
                double rate_close = aux.get(i).getValue();
                String rate_id = aux.get(i).getItem();
                double movie_r = RaterDatabase.getRater(rate_id).getRating(s);
                if(movie_r != -1){
                    count++;
                    average += rate_close*movie_r;
                }
            }
            if(count >= minimalRaters){
                Rating add = new Rating(s, (average/count)); 
                ans.add(add);
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
}
