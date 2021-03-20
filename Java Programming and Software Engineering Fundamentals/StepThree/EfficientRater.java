
/**
 * Write a description of class EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater{
    
    private String myID;
    //private ArrayList<Rating> myRatings;
    private HashMap<String, Rating> myRatings; //movie_id is the key

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        Rating rat = new Rating(item,rating);
        myRatings.put(item, rat);
        //myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(String s : myRatings.keySet()){
            if(s.equals(item)){
                return myRatings.get(s).getValue();
            }
        }
        /*for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }*/
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(s);
        }
        return list;
    }

}