
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String findSimpleGene(String a, String startCodon, String stopCodon){
        String res = "";
        int start = a.indexOf(startCodon);
        if(start == -1){
            return "";
        }
        int end = a.indexOf(stopCodon,start+3);
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
        String t1 = "AGCATGCCGTAACGA";
        System.out.println(findSimpleGene(t1,"ATG","TAA"));
        String t2 = "ATGTAA";
        System.out.println(findSimpleGene(t2,"ATG","TAA"));
        String t3 = "AGCGTACCGTAACGA";
        System.out.println(findSimpleGene(t3,"ATG","TAA"));
        String t4 = "AGCATGCCGTTTCGA";
        System.out.println(findSimpleGene(t4,"ATG","TAA"));
        String t5 = "AGCCAGCCGTCACGA";
        System.out.println(findSimpleGene(t5,"ATG","TAA"));
    }

}
