
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    
    private ArrayList<String> names;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            myFreqs.add(1);
        }
        else{
            int count = myFreqs.get(index);
            myFreqs.set(index, count+1);
        }
    }
    
    public void findAllCharacters(){
        names.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.lines()){
            int index = s.indexOf(".");
            if(index != -1){
                String person = s.substring(0,index);
                update(person);
            }
        } 
    }
    
    public void charactersWithNumParts(int num1, int num2){
        int number = 0;
        for(int i = 0; i < myFreqs.size(); i++){
            number = myFreqs.get(i);
            if(number >= num1 && number <= num2){
                System.out.println(names.get(i)+" -> "+myFreqs.get(i));
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
        findAllCharacters();
        /*for(int i = 0; i < names.size(); i++){
            System.out.println(names.get(i)+" -> "+myFreqs.get(i));
        }*/
        int max = findIndexOfMax();
        System.out.println("Name: "+names.get(max)+" Parts: "+myFreqs.get(max));
        charactersWithNumParts(10,15);
    }
}
