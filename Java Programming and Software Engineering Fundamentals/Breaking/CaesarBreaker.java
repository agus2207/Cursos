
/**
 * Write a description of class CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarBreaker {
    
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
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        for(int i = 0; i < 26; i++){
            System.out.println("["+i+"] = "+freqs[i]);
        }
        int maxDex = MaxIndex(freqs);
        int dkey = maxDex - 4;
        System.out.println("dkey: "+dkey+" MaxDex: "+maxDex);
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        System.out.println("dkey: "+dkey);
        System.out.println("Key: "+(26-dkey));
        return cc.encrypt(encrypted, 26-dkey);
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
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = MaxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc);
        int key1 = getKey(halfOfString(encrypted,0));
        int key2 = getKey(halfOfString(encrypted,1));
        System.out.println("Key 1: "+key1+" Key 2: "+key2);
        String des = cc.encryptTwoKeys(encrypted,26-key1,26-key2);
        return des;
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        String message = fr.asString();
        //System.out.println(message);
        //String answer = decrypt(message);
        //System.out.println(answer);
        //String p1 = halfOfString("Qbkm Zgis", 1);
        //System.out.println(p1);
        //int key = getKey(message);
        //System.out.println(key);
        String des = decryptTwoKeys(message);
        System.out.println(des);
    }
}
