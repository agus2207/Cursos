
/**
 * Write a description of class MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel{
 
    private int number;
    
    public MarkovModel(int N) {
        myRandom = new Random();
        number = N;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-number);
        String key = myText.substring(index, index+number);
        sb.append(key);
        for(int k=0; k < numChars-number; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order "+number;
    }
}

