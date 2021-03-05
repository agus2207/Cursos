
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while(currIndex != -1){
            if((currIndex-startIndex)%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+3);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int min = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
        if (min != dna.length()){
            return dna.substring(startIndex,min+3);
        }
        return "";
    }
    
    public void printAllGenes(){
        String dna = "ATGTAAGTAATGTGGTAGGGGTTAATGTGA";
        int currIndex = 0;
        while(true){
            int startIndex = dna.indexOf("ATG",currIndex);
            if(startIndex == -1){
                break;
            }
             int taaIndex = findStopCodon(dna,startIndex,"TAA");
             int tagIndex = findStopCodon(dna,startIndex,"TAG");
             int tgaIndex = findStopCodon(dna,startIndex,"TGA");
             int min = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
             if (min != dna.length()){
                 System.out.println(dna.substring(startIndex,min+3));
                 currIndex = min+3;
             }
             else{
                 break;
             }
        }
    }
    
    public void testFindStopCodon(){
        String test1 = "AGTAAATTTTATTATATATAATTATAA";
        int index = findStopCodon(test1, 0, "TAA");
        System.out.println(index);
        String test2 = "TAGGATAATAAGGGACTA";
        index = findStopCodon(test2,0,"TAA");
        System.out.println(index);
        String test3 = "TAGGATAATAA";
        index = findStopCodon(test3,2,"TAA");
        System.out.println(index);
    }
    
    public void testFindGene(){
        String test1 = "GGAATGTAATAGTGA";
        String test2 = "GGAGGAATGGAATGAAAAATT";
        String test3 = "ATGTGA";
        String test4 = "TAATAAGATTAGGATTTA";
        System.out.println(findGene(test1));
        System.out.println(findGene(test2));
        System.out.println(findGene(test3));
        System.out.println(findGene(test4));
    }

}
