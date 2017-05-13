//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #5, 5/12/17
//	Object Class => AnagramManager
//  Client Class => AnagramMain
//  Dependency   => Word
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn5/

import java.util.Arrays;
import java.util.Random;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;


/**
 * This class...
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class AnagramManager
{
    Word[] words;
    Map<String,Set<String>> anagramMap;
    Random rand;
    
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
    public AnagramManager(List<String> listOfWords)
    {
        if(listOfWords == null || listOfWords.size() < 0) throw new IllegalArgumentException();
        
        rand = new Random();
        words = new Word[ listOfWords.size() ];
        anagramMap = new TreeMap<String,Set<String>>();
        
        populateWords(listOfWords);
        populateAnagrams();
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
    public void sortByWord()
    {
            //  SET COMPARE TO ORIGINAL WORD VALUE
        for(Word obj : words) { obj.compareOriginal(); }
        Arrays.sort(words);
    }
    
    /**
     * Returns a sorted set of Non-Terminals available in the rule map.
     *
     * @return                  A set of non-terminal keys to generate phrases from
     */
    public void sortByForm()
    {
            //  SET COMPARE TO CANONICAL WORD VALUE
        for(Word obj : words) { obj.compareCanonical(); }
        Arrays.sort(words);
    }

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
     *                          non-terminal, assuming the symbol exists as a non-terminal,
     *                          it contains terminal values to pick from, the syntax rules
     *                          were followed, and a non-terminal referencing itself does
     *                          not create an infinite loop.
     */
    public String getAnagram(String word)
    {
        String canon = Word.canonicalize(word);
        if(anagramMap.containsKey(canon))
        {
            Set<String> set = anagramMap.get(canon);
            String[] matches = set.toArray(new String[ set.size() ]);
            return matches[ rand.nextInt(matches.length) ];
        }
        else return "";
    }
    
    public Set<String> getAnagrams(String word)
    {
        String canon = Word.canonicalize(word);
        boolean hasAnagram = anagramMap.containsKey(canon);
        
        Set<String> anagrams = (hasAnagram) ? anagramMap.get(canon) : new TreeSet<String>();
        
        return anagrams;
    }

    public String toString()
    {
        String printedWords = "";
        int len = words.length;
        
        if(len < 1) printedWords += "[]";
        else
        {
            int cap = (len < 5) ? len : 5;
    
            for(int i = 0; i < cap; i++) printedWords += words[i];
            
            printedWords += "[...]";
            
            for(int i = len-cap; i < len; i++) printedWords += words[i];
        }
        
        return printedWords;
    }
    
//=================================================================
//---------------------------HELPERS-------------------------------
//=================================================================

    private void populateWords(List<String> list)
    {
        int index = 0;
        
        for(String originalWord : list)
        {
            words[index] = new Word(originalWord);
            index++;
        }
    }
    
    private void populateAnagrams()
    {
        for(Word obj : words)
        {
            String canon = obj.getForm();
            String orig  = obj.getWord();
            
            if( !anagramMap.containsKey(canon) )
            {       //  ADD KEY MAPPED TO NEW SET
                Set<String> set = new TreeSet<String>();
                set.add(orig);
                anagramMap.put(canon, set);
            }
            else
            {       //  GRAB SET AND ADD NORMAL WORD TO IT
                anagramMap.get(canon).add(orig);
            }
        }
    }
    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


}