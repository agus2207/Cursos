import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder ans = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices){
            ans.append(message.charAt(i));
        }
        return ans.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String curr = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(curr);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String line : fr.lines()){
            if(!words.contains(line.toLowerCase())){
                words.add(line.toLowerCase());
            }
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count = 0;
        for(String s : words){
            if(dictionary.contains(s.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int max = 0;
        String dec = "";
        int size = 0;
        char mostCommon = mostCommonCharIn(dictionary);
        for(int i = 1; i <= 100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String curr = vc.decrypt(encrypted);
            int aux = countWords(curr, dictionary);
            /*if(i == 38){
                max = aux;
                dec = curr;
                size = keys.length;
                break;
            }*/
            if(max == 0 || aux > max){
                max = aux;
                dec = curr;
                size = keys.length;
            }
        }
        System.out.println("VALID WORDS: "+max);
        System.out.println("KEY LENGTH: "+size);
        return dec;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> mapping = new HashMap<Character, Integer>();
        char ans = ' ';
        int max = 0;
        for(String s : dictionary){
            for(int i = 0; i < s.length(); i++){
                if(!mapping.containsKey(s.charAt(i))){
                    mapping.put(s.charAt(i), 1);
                }
                else{
                    mapping.put(s.charAt(i), mapping.get(s.charAt(i))+1);
                }
            }
        }
        for(char s : mapping.keySet()){
            int aux = mapping.get(s);
            if(max == 0 || aux > max){
                max = aux;
                ans = s;
            }
        }
        return ans;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int max = 0;
        String lang = "";
        String finaldec = "";
        for(String s : languages.keySet()){
            HashSet<String> list = languages.get(s);
            System.out.println("LANGUAGE: "+s);
            String dec = breakForLanguage(encrypted, list);
            //System.out.println("DECRYPT:\n "+dec);
            int aux = countWords(dec, list);
            if(max == 0 || aux > max){
                max = aux;
                lang = s;
                finaldec = dec;
            }
        }
        System.out.println("LANGUAGE OF THE MESASAGE: "+lang);
        System.out.println("DECRYPT: \n"+finaldec);
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        HashMap<String, HashSet<String>> begin = new HashMap<String, HashSet<String>>();
        FileResource fr = new FileResource();
        String test = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr2 = new FileResource(f);
            HashSet<String> dict = readDictionary(fr2);
            String lang = f.getName();
            System.out.println("Charging: "+lang);
            begin.put(lang, dict);
        }
        breakForAllLangs(test, begin);
        //FileResource fr2 = new FileResource();
        //HashSet<String> dict = readDictionary(fr2);
        //String dec = breakForLanguage(test, dict);
        //System.out.println("DECRYPT:\n "+breakForLanguage(test,dict));
        //int[] keys = tryKeyLength(test, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        //String a = "Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm";
        //System.out.println("Decrypt:\n "+vc.decrypt(a));
    }
    
}
