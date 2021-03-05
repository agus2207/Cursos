
/**
 * Write a description of class LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        double max = 0;
        int index = 0;
        for(int i = 0; i < quakeData.size(); i++){
            double magnitude = quakeData.get(i).getMagnitude();
            if(max == 0 || magnitude > max){
                max = magnitude;
                index = i;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int i = 0; i < howMany; i++){
            //int currIndex = 0;
            if(i == quakeData.size()){
                break;
            }
            int max = indexOfLargest(copy);
            /*for(int j = 1; j < copy.size(); j++){
                QuakeEntry qe = copy.get(j);
                double magnitude = qe.getMagnitude(); 
                if(magnitude > copy.get(j).getMagnitude()){
                    currIndex = j;
                }
            }*/
            ret.add(copy.get(max));
            copy.remove(max);
        }
        return ret;
    }
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        //System.out.println("MAX INDEX: "+indexOfLargest(list));
        ArrayList<QuakeEntry> sort = getLargest(list, 50);
        for(QuakeEntry qe : sort){
            System.out.println(qe);
        }
    }
}   
