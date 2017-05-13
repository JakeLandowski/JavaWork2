//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #5, 5/12/17
//	Object Class => Word
//  Client Class => AnagramManager
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn5/


import java.util.Arrays;

/**
 * This class...
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
     * Constructor, takes an unmodifiable list of grammar lines and creates a rule map
     * out of it, initializes GrammarSolver's random object.
     *
     * @param rules             The list of grammar lines used to populate the ruleMap
     * @throws                  IllegalArgumentException if list is null or list length &lt; 1
     * @pre                     List elements must be seperate lines of grammar rules, starting
     *                          with the non-terminal follow by ::= and its terminals, each AND
     *                          terminal must be seperated by whitespace and each OR terminal
     *                          seperated by pipes (|). If non-terminal references itself as a
     *                          terminal, must include a way to end the loop eventually, such as
     *                          giving another terminal option with a pipe (|).
     * @pre                     List must not be empty
     * @pre                     List must not be null
     * @post                    Instantiates a GrammarSolver object with initialized rule map
     */
    public Word(String word)
    {
        String lower = word.toLowerCase();
        original = lower;
        canonical = canonicalize(original);
    }
    
    /**
     * Checks to see if the entered symbol exists in the rule map, returns true if so.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  True if symbol is a Non-Terminal, false otherwise
     * @pre                     String must not be empty
     * @pre                     String must not be null
     */
    public String getWord() { return original; }
    
    /**
     * Returns a sorted set of Non-Terminals available in the rule map.
     *
     * @return                  A set of non-terminal keys to generate phrases from
     */
    public String getForm() { return canonical; }
    
    /**
     * Generates a phrase of words from the given symbol using the rule map.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  The symbol given if it is not a non-terminal, otherwise a string of words
     *                          randomly picked from the terminals of the symbol given.
     * @pre                     Symbol given must exist as a non-terminal.
     * @pre                     String must not be empty.
     * @pre                     String must not be null.
     * @post                    Will return a string of random terminals from the given
     */
    public String toString() { return "[" + original + "=" + canonical + "]"; }
    
    /**
     * Generates a phrase of words from the given symbol using the rule map.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  The symbol given if it is not a non-terminal, otherwise a string of words
     *                          randomly picked from the terminals of the symbol given.
     * @pre                     Symbol given must exist as a non-terminal.
     * @pre                     String must not be empty.
     * @pre                     String must not be null.
     * @post                    Will return a string of random terminals from the given
     */
    public int compareTo(Word other) { return compareForm.compareTo(other.getCompareForm()); }
    
    /**
     * Generates a phrase of words from the given symbol using the rule map.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  The symbol given if it is not a non-terminal, otherwise a string of words
     *                          randomly picked from the terminals of the symbol given.
     * @pre                     Symbol given must exist as a non-terminal.
     * @pre                     String must not be empty.
     * @pre                     String must not be null.
     * @post                    Will return a string of random terminals from the given
     */
    public void compareOriginal() { compareForm = original; }

    /**
     * Generates a phrase of words from the given symbol using the rule map.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  The symbol given if it is not a non-terminal, otherwise a string of words
     *                          randomly picked from the terminals of the symbol given.
     * @pre                     Symbol given must exist as a non-terminal.
     * @pre                     String must not be empty.
     * @pre                     String must not be null.
     * @post                    Will return a string of random terminals from the given
     */
    public void compareCanonical() { compareForm = canonical; }
    
    /**
     * Generates a phrase of words from the given symbol using the rule map.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  The symbol given if it is not a non-terminal, otherwise a string of words
     *                          randomly picked from the terminals of the symbol given.
     * @pre                     Symbol given must exist as a non-terminal.
     * @pre                     String must not be empty.
     * @pre                     String must not be null.
     * @post                    Will return a string of random terminals from the given
     */
    public String getCompareForm() { return compareForm; }
    
    /**
     * Generates a phrase of words from the given symbol using the rule map.
     *
     * @param symbol            The non-terminal string entered by the user
     * @throws                  IllegalArgumentException if symbol is null or list length &lt; 1
     * @return                  The symbol given if it is not a non-terminal, otherwise a string of words
     *                          randomly picked from the terminals of the symbol given.
     * @pre                     Symbol given must exist as a non-terminal.
     * @pre                     String must not be empty.
     * @pre                     String must not be null.
     * @post                    Will return a string of random terminals from the given
     */
    public static String canonicalize(String word)
    {
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