
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
    
    public CSVRecord coldest(CSVRecord currentRow, CSVRecord coldestSoFar){
        if(coldestSoFar == null){
            coldestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if(currentTemp < coldestTemp){
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }
    
    public CSVRecord humidity(CSVRecord currentRow, CSVRecord coldestSoFar){
        if(coldestSoFar == null){
            coldestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("Humidity"));
            if(currentTemp < coldestTemp){
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord record : parser){
            if(!record.get("TemperatureF").equals("-9999")){
                coldestSoFar = coldest(record, coldestSoFar);
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature(){
        File temp = null; 
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord cold = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null){
                coldestSoFar = cold;
                temp = f;
            }
            else{
                double t1 = Double.parseDouble(cold.get("TemperatureF"));
                double t2 = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(t1 < t2){
                    coldestSoFar = cold;
                    temp = f;
                } 
            }
            /*FileResource fr = new FileResource(f);
            CSVRecord cold = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = coldest(cold,coldestSoFar);
            if((cold.get("TemperatureF")).equals(coldestSoFar.get("TemperatureF"))){
                temp = f;
            }*/
        }
        //System.out.println("Hola");
        return temp.getName();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for(CSVRecord record : parser){
            if(!record.get("Humidity").equals("N/A")){
                lowestSoFar = humidity(record, lowestSoFar);
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord cold = lowestHumidityInFile(fr.getCSVParser());
            coldestSoFar = humidity(cold,coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double average = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            if(!record.get("TemperatureF").equals("-9999")){
                double temp = Double.parseDouble(record.get("TemperatureF"));
                average += temp;
                count++;
            }
        }
        return average/count;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double average = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            if(!record.get("Humidity").equals("N/A")){
                int hum = Integer.parseInt(record.get("Humidity"));
                if(hum >= value){
                    if(!record.get("TemperatureF").equals("-9999")){
                        double temp = Double.parseDouble(record.get("TemperatureF"));
                        average += temp;
                        count++;
                    }
                }
            }
            
        }
        return average/count;
    }
    
    public void testFileWithColdestTemperature(){
        String ans = fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+ans);
        int inicio = ans.indexOf("-");
        String anio = ans.substring(inicio+1,inicio+5);
        String path = "nc_weather/"+anio+"/"+ans;
        FileResource fr = new FileResource(path);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was "+coldest.get("TemperatureF"));
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest was "+coldest.get("TemperatureF")+" at "+
        coldest.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
        CSVRecord coldest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+coldest.get("Humidity")+" at "+
        coldest.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord coldest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+coldest.get("Humidity")+" at "+
        coldest.get("DateUTC"));
    } 
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is "+average);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        System.out.println("Average temperature in file is "+average);
    }

}
