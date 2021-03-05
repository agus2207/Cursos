
/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max = 0;
        int index = 0;
        for(int i = 0; i < myFreqs.size(); i++){
            int aux = myFreqs.get(i);
            //System.out.println("Max: "+max+" Aux: "+aux);
            if(max == 0 || max < aux){
                max = aux;
                index = i;
            }
        }
        return index;
    }
    
    public void tester(){
        findUnique();
        System.out.println("Unique Words: "+myWords.size());
        /*for(int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i)+"\t"+myFreqs.get(i));
        }*/
        int max = findIndexOfMax();
        System.out.println("Word: "+myWords.get(max)+" Freq: "+myFreqs.get(max));
    }
}
