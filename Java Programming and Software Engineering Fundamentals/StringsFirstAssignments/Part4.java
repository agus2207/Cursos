import edu.duke.*;
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 extends java.lang.Object{
    
    public void read_url(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.lines()) {
            String a = s.toLowerCase();
            int inicio = a.indexOf("youtube");
            if(inicio != -1){
                int aux = s.lastIndexOf("\"",inicio);
                int fin = s.indexOf("\"",aux+1);
                //System.out.println(s);
                System.out.println(s.substring(aux+1,fin));
            }
        }      
    }

}
