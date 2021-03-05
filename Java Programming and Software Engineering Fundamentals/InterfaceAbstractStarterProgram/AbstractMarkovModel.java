
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> words = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int index = myText.indexOf(key, pos);
            if(index != -1 && index+key.length() < myText.length()){
                words.add(""+myText.charAt(index+key.length()));
                pos = index+1;
            }
            else{
                break;
            }
        }
        return words;
    }
 
    abstract public String getRandomText(int numChars);

}
