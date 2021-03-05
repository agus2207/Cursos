
/**
 * Write a description of class WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {

    public boolean isVowel(char ch){
        boolean answer = false;
        if(ch == 65 || ch == 69 || ch == 73 || ch == 79 || ch == 85 || ch == 97
        || ch == 101 || ch == 105 || ch == 111 || ch == 117){
            answer = true;
        }
        return answer;
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder answer = new StringBuilder(phrase);
        for(int i = 0; i < answer.length(); i++){
            char currchar = answer.charAt(i);
            if(isVowel(currchar) == true){
                answer.setCharAt(i,ch);
            }
        }
        return answer.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder answer = new StringBuilder(phrase);
        for(int i = 0; i < answer.length(); i++){
            char currchar = answer.charAt(i);
            if(currchar == Character.toLowerCase(ch) || 
            currchar == Character.toUpperCase(ch)){
                if(i%2 == 0){
                    answer.setCharAt(i,'*');
                }
                else{
                    answer.setCharAt(i,'+');
                }
            }
        }
        return answer.toString();
    }
    
    public void testvowel(){
        //System.out.println(isVowel('F'));
        //System.out.println(isVowel('A'));
        //System.out.println(isVowel('a'));
        //String t1 = replaceVowels("HEllO WOrld", '*');
        //System.out.println(t1);
        String t2 = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(t2);
    }
}
