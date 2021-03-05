
/**
 * Write a description of class TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < message.length(); i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int MaxIndex(int[] vals){
        int maxDex = 0;
        for(int i = 0; i < vals.length; i++){
            if(vals[i] > vals[maxDex]){
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = MaxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public String halfOfString(String message, int start){
        StringBuilder str = new StringBuilder(); 
        for(int i = 0; i < message.length(); i++){
            if(i%2 == start){
                str.append(message.charAt(i));
            }
        } 
        return str.toString();
    }
    
    public String breakCaesarCipher(String input){
        String part1 = halfOfString(input,0);
        String part2 = halfOfString(input,1);
        int key1 = getKey(part1);
        int key2 = getKey(part2);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1,key2);
        return cct.decrypt(input);     
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource("encrypted.txt");
        String message = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        String enc = cct.encrypt(message);
        System.out.println("Encrypted: "+enc);
        String dec = cct.decrypt(enc);
        System.out.println("Decrypted: "+dec);
        System.out.println("Breaker: "+breakCaesarCipher(enc));
    }

}
