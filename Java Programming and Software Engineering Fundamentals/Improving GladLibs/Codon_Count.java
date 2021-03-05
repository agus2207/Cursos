
/**
 * Write a description of class Codon_Count here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Codon_Count {
    
    private HashMap<String, Integer> codons; 
    
    public Codon_Count(){
        codons = new HashMap<String, Integer>();   
    }
    
    public void buildCodonMap(int start, String dna){
        codons.clear();
        int len = dna.length();
        String res = "";
        for(int i = start; i < len; i++){
            if(!Character.isLetter(dna.charAt(i))){
                res = "";
                continue;
            }
            res += dna.charAt(i);
            //System.out.println("current: "+res);
            if(res.length()%3 == 0){
                if(codons.keySet().contains(res)){
                    codons.put(res, codons.get(res)+1);
                }
                else{
                    codons.put(res,1);
                }
                //System.out.println("Codon found: "+res);
                res = "";
            }
        }
    }
    
    public String getMostCommonCodon(){
        String ans = "";
        int aux = 0;
        for(String s : codons.keySet()){
            int curr = codons.get(s);
            //System.out.println("Aux: "+aux+" curr: "+curr);
            if(aux == 0 || curr > aux){
                aux = curr;
                ans = s;
                //System.out.println("Cumpli y ahora s vale: " +ans);
            }
        }
        return ans;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : codons.keySet()){
            int val = codons.get(s);
            if(val >= start && val <= end){
                System.out.println("Codon: "+s+" Value: "+codons.get(s));
            }
        }
    }
    
    public void tester(){
        //FileResource fr = new FileResource("dnaMystery1");
        //for(String s : fr.words()){}
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        buildCodonMap(0, dna);
        String max = getMostCommonCodon();
        System.out.println("Maximum codon: "+max+" with: "+codons.get(max));
        printCodonCounts(5,7);
        System.out.println("UNIQUE CODONS: "+codons.size());
    }

}
