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
 * This class manages the anagram data structure for a dictionary of words, as well as serving
 * requests for anagrams.
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
     * Constructor, takes an unmodifiable list of words and instantiates a Word object
     * for each, stored in an array. Initializes a map of canonical words to sets of
     * their matching anagrams. Initializes a random object.
     *
     * @param listOfWords       A List of strings passed to AnagramManager
     * @throws                  IllegalArgumentException if list is null or list length &lt; 1
     * @pre                     List must not be empty
     * @pre                     List must not be null
     * @post                    Instantiates an AnagramManager object with initialized Word array
     *                          and anagram map
     */
    public AnagramManager(List<String> listOfWords)
    {
        if(listOfWords == null || listOfWords.size() < 1) throw new IllegalArgumentException();
        
        rand = new Random();
        words = new Word[ listOfWords.size() ];
        anagramMap = new TreeMap<String,Set<String>>();
        
        populateWords(listOfWords);
        populateAnagrams();
    }
    
    /**
     * Sorts the internal array of Word objects by their original alphabetical ordering.
     */
    public void sortByWord()
    {
            //  SET COMPARE TO ORIGINAL WORD VALUE
        for(Word obj : words) { obj.compareOriginal(); }
        Arrays.sort(words);
    }
    
    /**
     * Sorts the internal array of Word objects by their canonical ordering.
     */
    public void sortByForm()
    {
            //  SET COMPARE TO CANONICAL WORD VALUE
        for(Word obj : words) { obj.compareCanonical(); }
        Arrays.sort(words);
    }

    /**
     * Picks a random word a canonical form equal to canonical form of the word given.<br>
     * Ex: [hurrdurr : hdrrrruu] == [durrhurr : hdrrrruu]
     *
     * @param word              The word used to find an anagram
     * @return                  Random word of matching canonical form, if no match returns
     *                          empty string
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
    
    /**
     * Finds and returns a set of all matching anagrams of the word given.
     *
     * @param word              The word used to find anagrams
     * @return                  A sorted set of all anagrams of the word given
     */
    public Set<String> getAnagrams(String word)
    {
        String canon = Word.canonicalize(word);
        boolean hasAnagram = anagramMap.containsKey(canon);
        
        Set<String> anagrams = (hasAnagram) ? anagramMap.get(canon) : new TreeSet<String>();
        
        return anagrams;
    }

    /**
     * Returns up to 5 of the first and last Words held in the internal array, displaying
     * their normal and canonical forms.<br>
     *
     * @return                  A string containing the first and last 5 internal Words
     */
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