
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            if(word.length() < 30){
                //System.out.println(word);
                int count = word.length();
                if(! Character.isLetter(word.charAt(0))){
                    count -= 1;
                }
                if(! Character.isLetter(word.charAt(word.length()-1))){
                    count -= 1;
                }
                if(count > 0){
                    counts[count] += 1;
                }
            }
            else{
                counts[30] += 1;
            }
        }
    }
    
    public int indexOfMax(int[] values){
        int answer = 0;
        for(int i = 0; i < values.length; i++){
            if(answer == 0 || answer < values[i]){
                answer = values[i];
            }
        }
        return answer;
    }
    
    public void testCount(){
        FileResource fr = new FileResource("manywords.txt.");
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for(int i = 1; i < 31; i++){
            System.out.println("Posicion: "+i+" tiene : "+counts[i]);
        }
        System.out.println("Largest element: "+indexOfMax(counts));
    }

}
