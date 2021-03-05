
/**
 * Write a description of class PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    
    private String index;
    private String phrase;
    private String name;
    
    public PhraseFilter(String begin, String frase, String fname){
        index = begin;
        phrase = frase;
        name = fname;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if(index.equals("start")){
            return qe.getInfo().startsWith(phrase);
        }
        else if(index.equals("end")){
            return qe.getInfo().endsWith(phrase);
        }
        else{
            return qe.getInfo().contains(phrase);
        }
    }
    
    public String getName(){
        return name;
    }

}
