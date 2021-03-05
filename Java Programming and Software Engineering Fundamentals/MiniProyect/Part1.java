/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("F")){
                totalGirls++;
            }
            else if(rec.get(1).equals("M")){
                totalBoys++;
            }
            totalNames++;
        }
        System.out.println("Total Boys Names: "+totalBoys);
        System.out.println("Total Girls Names: "+totalGirls);
        System.out.println("Total Names: "+totalNames);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        String filename = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
                if(rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        int count = 1;
        String filename = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec : fr.getCSVParser(false)){
            //count++;
            if(count == rank){
                //System.out.println("Entre y tengo un valor de "+rec.get(0));
                return rec.get(0);
                /*if(rec.get(1).equals(gender)){
                    System.out.println("Entre y tengo un valor de "+rec.get(0));
                    return rec.get(0);
                }*/
            }
            else{
                if(rec.get(1).equals(gender)){
                    //System.out.println("Soy mejor "+rec.get(0));
                    count++;
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int currRank = getRank(year, name, gender);
        String newname = getName(newYear, currRank, gender);
        if(gender.equals("F")){
            System.out.println(name+" born in "+year+" would be "+newname+
                                " if she was born in "+newYear);
        }
        else{
            System.out.println(name+" born in "+year+" would be "+newname+
                                " if he was born in "+newYear);
        }
    }
    
    public int yearOfHighestRank(String name, String gender){
        int highrank = 0;
        int finalyear = 0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //CSVRecord cold = coldestHourInFile(fr.getCSVParser(false));
            temp = f;
            int inicio = f.getName().indexOf("b");
            String filename = f.getName().substring(inicio+1, inicio+5);
            int year = Integer.parseInt(filename);
            int currRank = getRank(year,name,gender);
            //System.out.println("Año: "+year+" Rank:"+currRank);
            if(highrank == 0 || currRank < highrank){
                if(currRank != -1){
                    highrank = currRank;
                    finalyear = year;
                }
            }
        }
        if(highrank == -1){
            return -1;
        }
        return finalyear;
    }
    
    public double getAverageRank(String name, String gender){
        double average = 0.0;
        int count = 0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //CSVRecord cold = coldestHourInFile(fr.getCSVParser(false));
            temp = f;
            int inicio = f.getName().indexOf("b");
            String filename = f.getName().substring(inicio+1, inicio+5);
            int year = Integer.parseInt(filename);
            int currRank = getRank(year,name,gender);
            if(currRank != -1){
                average += currRank;
                count++;
            }
        }
        if(average == 0.0){
            return -1;
        }
        return average/count;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int currRank = getRank(year,name,gender);
        int total = 0;
        int count = 1;
        String filename = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec : fr.getCSVParser(false)){
            //count++;
            if(count == currRank){
                break;
            }
            else{
                if(rec.get(1).equals(gender)){
                    count++;
                    total += Integer.parseInt(rec.get(2));
                }
            }
        }
        return total;
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public void testing(){
        //System.out.println(getRank(1971, "Frank", "M"));
        //System.out.println(getName(1982, 450, "M"));
        //whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println(yearOfHighestRank("Mich", "M"));
        //System.out.println(getAverageRank("Robert", "M"));
        //System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
