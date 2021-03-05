
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public boolean twoOccurrences(String a, String b){
        int first = b.indexOf(a);
        if(first != -1){
            int second = b.indexOf(a,first+1);
            if(second != -1){
                return true;
            }
        }
        return false;
    }
    
    public String lastPart(String a, String b){
        int first = b.indexOf(a);
        if(first == -1){
            return b;
        }
        return b.substring(first+a.length());
    }
    
    public void testing(){
        boolean first = twoOccurrences("a","banana");
        System.out.println(first);
        boolean second = twoOccurrences("by","A story by Abby Long");
        System.out.println(second);
        boolean third = twoOccurrences("atg","ctgtatgta");
        System.out.println(third);
        String t1 = lastPart("an","banana");
        System.out.println(t1);
        String t2 = lastPart("zoo","forest");
        System.out.println(t2);
    }
}
