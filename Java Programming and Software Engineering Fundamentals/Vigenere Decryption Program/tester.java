
/**
 * Write a description of class tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class tester {

    public void test(){
        FileResource fr = new FileResource();
        String test = fr.asString();
        CaesarCipher cc = new CaesarCipher(4);
        String enc = cc.encrypt(test);
        System.out.println("Encripted: "+enc);
        String dec = cc.decrypt(enc);
        System.out.println("Decrypted: "+dec);
    }
    
    public void VBtest(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String test = fr.asString();
        //String test = "abcdefghijklm";
        //System.out.println(vb.sliceString(test, 1, 5));
        int[] res = vb.tryKeyLength(test, 4, 'e');
        for(int i : res){
            System.out.println(i);
        }
    }
}
