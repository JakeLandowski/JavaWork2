//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #5, 5/12/17
//	Object Class => Word
//  Client Class => AnagramManager
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn5/


import java.util.Arrays;

/**
 * This class manages both the original and canonical forms of a single word,
 * as well as managing its comparable state for sorting. It also provides a static
 * method for sorting letters in a word, used by its client object AnagramManager.
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Word implements Comparable<Word>
{
    String original;
    String canonical;
    String compareForm;
    
    /**
     * Constructor, takes a String and initializes internal strings representing
     * both the original form of the string and the canonical version.
     *
     * @param word              A string representing the original form of the Word
     */
    public Word(String word)
    {
        String lower = word.toLowerCase();
        original = lower;
        canonical = canonicalize(original);
    }
    
    /**
     * Returns the original form of the word.
     *
     * @return                  The original form of the word
     */
    public String getWord() { return original; }
    
    /**
     * Returns the canonical version of the word.
     *
     * @return                  The canonical form of the word
     */
    public String getForm() { return canonical; }
    
    /**
     * Returns the original and canonical word in this form : [blah=abhl]
     *
     * @return                  A string containing the original and canonical
     *                          form of the word
     */
    public String toString() { return "[" + original + "=" + canonical + "]"; }
    
    /**
     * Compares another Word to this Word based on whichever form is currently set to
     * be compared to.
     *
     * @param other             Another Word object to compare against
     * @return                  A negative or positive number or zero determining equality
     * @pre                     Must be another Word object
     */
    public int compareTo(Word other) { return compareForm.compareTo(other.getCompareForm()); }
    
    /**
     * Sets the Word to be compared based on its original form.
     */
    public void compareOriginal() { compareForm = original; }

    /**
     * Sets the Word to be compared based on its canonical form.
     */
    public void compareCanonical() { compareForm = canonical; }
    
    /**
     * Returns the current form of the word being used for comparison.
     *
     * @return                  Either original form or canonical form based on which
     *                          is currently set
     */
    public String getCompareForm() { return compareForm; }
    
    /**
     * Takes a word and returns a letter-sorted (canonical) version of that word.
     *
     * @param word              The word that will be letter-sorted
     * @return                  The canonical form of the word
     * @throws                  IllegalArgumentException if string is null or empty
     * @pre                     String must not be null
     * @pre                     String must not be empty
     * @post                    Will return the canonical form of the words
     */
    public static String canonicalize(String word)
    {
        if(word == null || word.length() < 1) throw new IllegalArgumentException();
        
        char[] ch = word.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }
    
//=================================================================
//---------------------------HELPERS-------------------------------
//=================================================================

    
    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


}