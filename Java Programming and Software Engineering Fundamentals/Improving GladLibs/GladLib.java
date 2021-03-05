import edu.duke.*;
import java.util.*;

public class GladLib {
    
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> wordList;
    private ArrayList<String> tracking;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        myMap = new HashMap<String, ArrayList<String>>();
        wordList = new ArrayList<String>();
        tracking = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLib(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        wordList = new ArrayList<String>();
        tracking = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap.clear();
        String[] labels = {"country", "noun", "animal", "adjective",
                            "name", "color", "timeframe", "verb", "fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        //System.out.println("PALABRA GENERADA: "+sub);
        if(!wordList.contains(sub)){
            //System.out.println("NUEVA PALABRA: "+sub);
            wordList.add(sub);
        }
        else{
            //System.out.println("PALABRA REPETIDA: "+sub);
            while(wordList.contains(sub)){
                sub = getSubstitute(w.substring(first+1,last));
                //System.out.println("NUEVA GENERADA: "+sub);
            }
            wordList.add(sub);
        }
        if(!tracking.contains(w.substring(first+1,last))){
            tracking.add(w.substring(first+1,last));
        }
        //System.out.println("RESULTADO: "+prefix+sub+suffix);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int total = 0;
        for(String s : myMap.keySet()){
            ArrayList<String> words = myMap.get(s);
            total += words.size();
        }
        return total;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
        for(int i = 0; i < tracking.size(); i++){
            String aux = tracking.get(i);
            if(myMap.keySet().contains(aux)){
                ArrayList<String> words = myMap.get(aux);
                total += words.size();
            }
        }
        return total;
    }
    
    public void makeStory(){
        wordList.clear();
        tracking.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nWORDS REPLACED: "+wordList.size());
        /*for(int i = 0; i < wordList.size(); i++){
            System.out.println("Contenido: "+wordList.get(i));
        }*/
        System.out.println("TOTAL WORDS TO PICK: "+totalWordsInMap());
        System.out.println("TOTAL WORDS CONSIDERED: "+totalWordsConsidered());
    }
}
