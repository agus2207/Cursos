
/**
 * Write a description of class MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;

public class MarkovOne {

    private String myText;
    private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public ArrayList<String> getFollows(String key){
	    ArrayList<String> words = new ArrayList<String>();
	    int pos = 0;
	    //System.out.println("Length: "+myText.length());
	    while(pos < myText.length()){
	        //System.out.println("Entre al while");
	        int index = myText.indexOf(key, pos);
	        //System.out.println("Index: "+index);
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
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-1);
		String key = myText.substring(index, index+1);
		sb.append(key);
		for(int k=0; k < numChars-1; k++){
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
}