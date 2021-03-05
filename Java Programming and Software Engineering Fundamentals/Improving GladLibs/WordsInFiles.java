
/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    
    private HashMap<String, ArrayList<String>> ocurrences; 
    
    public WordsInFiles(){  
        ocurrences = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            String aux = word.toLowerCase();
            if(ocurrences.keySet().contains(word)){
                ArrayList<String> files = new ArrayList<String>();
                files = ocurrences.get(word);
                if(!files.contains(f.getName())){
                    files.add(f.getName());
                }
                ocurrences.put(word, files);
            }
            else{
                ArrayList<String> files = new ArrayList<String>();
                files.add(f.getName());
                ocurrences.put(word, files);
            }
        }
    }
    
    public void buildWordFileMap(){
        ocurrences.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max = 0;
        for(String s : ocurrences.keySet()){
            ArrayList<String> files = new ArrayList<String>();
            files = ocurrences.get(s);
            int curr = files.size();
            if(max == 0 || curr > max){
                max = curr;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String s : ocurrences.keySet()){
            ArrayList<String> files = new ArrayList<String>();
            files = ocurrences.get(s);
            int curr = files.size();
            if(curr == number){
                words.add(s);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> files = new ArrayList<String>();
        if(ocurrences.keySet().contains(word)){
            files = ocurrences.get(word);
            for(int i = 0; i < files.size(); i++){
                System.out.println(files.get(i));
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int max =  maxNumber();
        System.out.println("Max files: "+max);
        ArrayList<String> words = new ArrayList<String>();
        words = wordsInNumFiles(4);
        System.out.println("WORDS: "+words.size());
        /*for(int i = 0; i < words.size(); i++){
            System.out.println(words.get(i));
        }*/
        printFilesIn("tree");
    }
}
