
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    
    public String countryInfo(CSVParser parser, String country){
        String ans = "";
        for(CSVRecord record : parser){
            String con = record.get("Country");
            if(con.equals(country)){
                if(record.get("Exports").isEmpty()){
                    return "NOT FOUND";
                }
                else{
                    String exp = record.get("Exports");
                    String val = record.get("Value (dollars)");
                    ans = con+": "+exp+": "+val;
                }
            }
        }
        return ans;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exp = record.get("Exports");
            if(exp.contains(exportItem1) && exp.contains(exportItem2)){
                String con = record.get("Country");
                System.out.println(con);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exp = record.get("Exports");
            if(exp.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String exp = record.get("Value (dollars)");
            if(exp.length() > amount.length()){
                String con = record.get("Country");
                System.out.println(con+" "+exp);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String ans = countryInfo(parser, "Nauru");
        //System.out.println(ans);
        parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser, "cocoa"); 
        //System.out.println(count);
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999"); 
    }
}
