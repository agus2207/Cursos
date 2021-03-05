
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         records.clear();
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddress = le.getIpAddress();
             if(!unique.contains(ipAddress)){
                 unique.add(ipAddress);
             }
         }
         return unique.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             if(date.contains(someday)){
                 if(!unique.contains(le.getIpAddress())){
                     unique.add(le.getIpAddress());
                 }
             }
         }
         return unique;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status <= high && status >= low){
                 if(!unique.contains(le.getIpAddress())){
                     unique.add(le.getIpAddress());
                 }
             }
         }
         return unique.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> unique = new HashMap<String, Integer>();
         for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!unique.containsKey(ip)){
                unique.put(ip, 1);
            }
            else{
                unique.put(ip, unique.get(ip)+1);
            }
         }
         return unique;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> unique){
         int max = 0;
         for(String ip : unique.keySet()){
             int aux = unique.get(ip);
             if(max == 0 || aux > max){
                 max = aux;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> unique){
         ArrayList<String> ips = new ArrayList<String>();
         int max = mostNumberVisitsByIP(unique);
         for(String ip : unique.keySet()){
             int aux = unique.get(ip);
             if(aux == max){
                 ips.add(ip);
             }
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> dates = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){
            String date = le.getAccessTime().toString();
            String[] d = date.split(" ");
            String newdate = d[1]+" "+d[2];
            if(!dates.containsKey(newdate)){
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(le.getIpAddress());
                dates.put(newdate, ips);
            }
            else{
                dates.get(newdate).add(le.getIpAddress());
            }
        }
        return dates;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ips){
         String ans = "";
         int max = 0;
         for(String date : ips.keySet()){
             int aux = ips.get(date).size();
             if(max == 0 || aux > max){
                 max = aux;
                 ans = date;
             }
         }
         return ans;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ips, String date){
         ArrayList<String> res = new ArrayList<String>();
         ArrayList<String> aux = ips.get(date);
         HashMap<String, Integer> ipmax = new HashMap<String, Integer>();
         for(String s : aux){
            if(!ipmax.containsKey(s)){
                ipmax.put(s,1);
            }
            else{
                ipmax.put(s, ipmax.get(s)+1);
            }
         }
         //System.out.println(ipmax.size());
         res = iPsMostVisits(ipmax);
         return res;
     }
}
