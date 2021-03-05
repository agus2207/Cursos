
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key){
        StringBuilder answer = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key)+alphabet.substring(0,key);
        for(int i = 0; i < answer.length(); i++){
            char currchar = answer.charAt(i);
            int udx = alphabet.indexOf(currchar);
            int ldx = alphabet.toLowerCase().indexOf(currchar);
            if(udx != -1){
                char newchar = shifted.charAt(udx);
                answer.setCharAt(i, newchar);
            }
            if(ldx != -1){
                char newchar = shifted.toLowerCase().charAt(ldx);
                answer.setCharAt(i, newchar);
            }
        }
        return answer.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder answer = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shifted2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        for(int i = 0; i < answer.length(); i++){
            char currchar = answer.charAt(i);
            int udx = alphabet.indexOf(currchar);
            int ldx = alphabet.toLowerCase().indexOf(currchar);
            if(udx != -1){
                if(i%2 == 0){
                    char newchar = shifted1.charAt(udx);
                    answer.setCharAt(i, newchar);
                }
                else{
                    char newchar = shifted2.charAt(udx);
                    answer.setCharAt(i, newchar);
                }
            }
            if(ldx != -1){
                if(i%2 == 0){
                    char newchar = shifted1.toLowerCase().charAt(ldx);
                    answer.setCharAt(i, newchar);
                }
                else{
                    char newchar = shifted2.toLowerCase().charAt(ldx);
                    answer.setCharAt(i, newchar);
                }
            }
        }
        return answer.toString();
    }
    
    public void testCaesar(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        int key = 15;
        int key1 = 14;
        int key2 = 24;
        //String encrypted = encrypt(message, key);
        //System.out.println("key is " + key + "\n" + encrypted);
        String encrypted = encryptTwoKeys(message,26-key1,26-key2);
        System.out.println("key1 is " + key1 + "\n" + encrypted);
        
    }
}
