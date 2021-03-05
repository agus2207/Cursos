
/**
 * Write a description of class TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {
    
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
    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = MaxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);     
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource("encrypted.txt");
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String enc = cc.encrypt(message);
        System.out.println("Encrypted: "+enc);
        String dec = cc.decrypt(enc);
        System.out.println("Decrypted: "+dec);
        System.out.println("Breaker: "+breakCaesarCipher(enc));
    }

}
