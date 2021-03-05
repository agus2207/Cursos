
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("UNIQUE IPs: "+la.countUniqueIPs());
        la.printAllHigherThanNum(400);
        ArrayList<String> Uniqueips = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("TOTAL UNIQUE IP IN A DATE: "+Uniqueips.size());
        System.out.println("TOTAL RANGE: "+la.countUniqueIPsInRange(200, 299));
    }
    
    public void testCountIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> ipcount = la.countVisitsPerIP();
        /*for(String s : ipcount.keySet()){
            System.out.println("Key: "+s+" Value: "+ipcount.get(s));
        }*/
        int max = la.mostNumberVisitsByIP(ipcount);
        System.out.println("MAX NUMBER OF VISITS: "+max);
        ArrayList<String> ips = la.iPsMostVisits(ipcount);
        for(String s : ips){
            System.out.println("IP FOUND: "+s);
        }
        HashMap<String, ArrayList<String>> days = la.iPsForDays();
        /*for(String s : days.keySet()){
            System.out.println("Key: "+s+" Value: "+days.get(s).size());
        }*/
        System.out.println("DATE: "+la.dayWithMostIPVisits(days));
        ArrayList<String> dateip = la.iPsWithMostVisitsOnDay(days, "Sep 29");
        for(String a : dateip){
            System.out.println("IP WITH DATE: "+a);
        }
    }
}
