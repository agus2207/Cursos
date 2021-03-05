
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder answer = new StringBuilder(input);
        for(int i = 0; i < answer.length(); i++){
            char currchar = answer.charAt(i);
            int udx = alphabet.indexOf(currchar);
            int ldx = alphabet.toLowerCase().indexOf(currchar);
            if(udx != -1){
                char newchar = shiftedAlphabet.charAt(udx);
                answer.setCharAt(i, newchar);
            }
            if(ldx != -1){
                char newchar = shiftedAlphabet.toLowerCase().charAt(ldx);
                answer.setCharAt(i, newchar);
            }
        }
        return answer.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
