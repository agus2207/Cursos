
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test.");
        ArrayList <String> test = mo.getFollows("t.");
        for(String s : test){
            System.out.println(s);
        }
        System.out.println("Tamaño: "+test.size());
    }
    
    public void testGetFollowsWithFile(){
        MarkovOne mo = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        mo.setTraining(st);
        ArrayList <String> test = mo.getFollows("th");
        System.out.println("Tamaño: "+test.size());
    }
}
