
/**
 * Write a description of class CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder answer = new StringBuilder(input);
        for(int i = 0; i < answer.length(); i++){
            char currchar = answer.charAt(i);
            int udx = alphabet.indexOf(currchar);
            int ldx = alphabet.toLowerCase().indexOf(currchar);
            if(udx != -1){
                if(i%2 == 0){
                    char newchar = shiftedAlphabet1.charAt(udx);
                    answer.setCharAt(i, newchar);
                }
                else{
                    char newchar = shiftedAlphabet2.charAt(udx);
                    answer.setCharAt(i, newchar);
                }
            }
            if(ldx != -1){
                if(i%2 == 0){
                    char newchar = shiftedAlphabet1.toLowerCase().charAt(ldx);
                    answer.setCharAt(i, newchar);
                }
                else{
                    char newchar = shiftedAlphabet2.toLowerCase().charAt(ldx);
                    answer.setCharAt(i, newchar);
                }
            }
        }
        return answer.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cct.encrypt(input);
    }
}
