
import java.security.InvalidParameterException;

public class WordGram {
    
    private String[] myWords;
    private int myHash;
    
    private void require (boolean condition, String message) {
        if (! condition)
            throw new InvalidParameterException(message);
    }

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    public int hashCode() { 
        return myHash; 
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String word : myWords){
            sb.append(word).append(" ");
        }
        // remember to remove the trailing space.
        return sb.toString().trim();
    }

    public boolean equals(Object o) {
        require(o != null, "equals() received a null object");
        WordGram other = (WordGram) o;
        if (myWords.length != other.length()) return false;
        // same size, so compare contents.
        for (int k = 0; k < myWords.length; k++){
            if (false == myWords[k].equals(other.myWords[k])){
                return false;
            }
        }
        // all words match if we get here, so they are equal
        return true;

    }

    public WordGram shiftAdd(String word) {	
        // myWords.length is guaranteed to be > 0 by the constructor.
        WordGram out = new WordGram(myWords, 0, myWords.length);

        // decided to allow a word with no content to slide in, as an
        // empty string.  No NULLS!  Don't have to test here for empty then.
        if (word == null){
            word = "";
        }

        // shift all words one spot towards 0 and add word at the end.
        // you lose the first word
        System.arraycopy(out.myWords, 1, out.myWords, 0, out.myWords.length-1);
        out.myWords[out.myWords.length-1] = word;

        // changed `myWords`, so must recompute the hash code!  Otherwise
        // hashmap lookup will fail.
        out.myHash = out.toString().hashCode();

        return out;
    }

}