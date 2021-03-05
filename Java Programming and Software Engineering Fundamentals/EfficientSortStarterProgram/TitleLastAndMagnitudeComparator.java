
/**
 * Write a description of class TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry q1, QuakeEntry q2){
        String[] word1 = q1.getInfo().split(" ");
        String[] word2 = q2.getInfo().split(" ");
        int res = word1[word1.length-1].compareTo(word2[word2.length-1]);
        if(res == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return res;
    }
}
