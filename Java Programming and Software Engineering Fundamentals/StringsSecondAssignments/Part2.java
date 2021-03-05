
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String a, String b){
        int currIndex = 0;
        int count = 0;
        while(true){
            int startIndex = b.indexOf(a, currIndex);
            if(startIndex == -1){
                break;
            }
            currIndex = startIndex+a.length();
            count++;
        }
        return count;
    }
    
    public void testHowMany(){
        int test1 = howMany("GAA", "ATGAACGAATTGAATC");
        int test2 = howMany("AA", "ATAAAA");
        int test3 = howMany("ABC", "DEFGHIJKML");
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
    }
}
