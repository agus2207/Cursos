
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String a){
        String res = "";
        int start = a.indexOf("ATG");
        if(start == -1){
            return "";
        }
        int end = a.indexOf("TAA",start+3);
        if(end == -1){
            return "";
        }
        res = a.substring(start,end+3);
        if(res.length()%3 != 0){
            return "";
        }
        return res;
    }
    
    public void testSimpleGene(){
        String t1 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println(findSimpleGene(t1));
        String t2 = "ATGTAA";
        System.out.println(findSimpleGene(t2));
        String t3 = "AGCGTACCGTAACGA";
        System.out.println(findSimpleGene(t3));
        String t4 = "AGCATGCCGTTTCGA";
        System.out.println(findSimpleGene(t4));
        String t5 = "AGCCAGCCGTCACGA";
        System.out.println(findSimpleGene(t5));
    }

}
