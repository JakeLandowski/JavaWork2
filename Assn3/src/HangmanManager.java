/**
 * This class instantiates a basic Animal object with a name, x/y coordinate values,
 * and a max speed. It is used in the AnimalMain class
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 *
 *
 *
 * javadoc -d doc -tag pre:cm:"Precondition: " -tag post:cm:"Postcondition: " *.java
 */

import java.util.*;

public class HangmanManager
{
    private Set<String> curSet;
    private SortedSet<Character> guessed;
    private String curPattern;
    private int guessesRemaining;
    
    /**
     * Default constructor that takes a List of words to use for hangman,
     * an int length to decide which length words to pick from, and int max
     * to track how many guesses are remaining.
     *
     * @param dictionary        the list of words to create the initial set of words
     * @param length            the length of the words to intially pick from
     * @param max               the remaining amount of guesses to start with
     * @throws                  IllegalArgumentException if length &lt; 1 or max &lt; 0
     * @pre                     List must not be empty.
     * @pre                     length &gt; 0
     * @pre                     max &gt;= 0
     */
    public HangmanManager(List<String> dictionary, int length, int max)
    {
        if(length < 1 || max < 0) throw new IllegalArgumentException();
        
        guessed = new TreeSet<Character>();
        guessesRemaining = max;
        initializePattern(length);
        initializeSet(dictionary, length);
    }
    
    /**
     * Returns the current set of words being used for guessing.
     *
     * @return                  curSet
     * @post                    list.size() &gt; 0
     */
    public Set<String> words()
    {
        return curSet;
    }
    
    
    /**
     * Returns the current guesses remaining for the player.
     *
     * @return                  guessesRemaining
     * @post                    guessesRemaining &gt;= 0
     */
    public int guessesLeft()
    {
        return guessesRemaining;
    }
    
    /**
     * Returns a sorted set of characters already guessed by the player.
     *
     * @return                  guessed
     */
    public SortedSet<Character> guesses()
    {
        return guessed;
    }
    
    /**
     * Returns the pattern string with white space seperating each character ex: "- e - -" instead of "-e--".
     *
     * @throws                  IllegalStateException if current set of words is empty (size &lt; 0)
     * @return                  current pattern string with characters seperated by whitespace
     * @post                    String characters are space seperated with no beginning or trailing space
     */
    public String pattern()
    {
        errIfEmpty();
        return expandedPattern();
    }
    
    /**
     * Takes a guessed character, archives it in the guessed sorted set, checks if already guessed,
     * then chooses the new set of words to use and returns the number of occurences of the guessed
     * character, either 0 or more.
     *
     * @param guess             the character guessed by the player
     * @throws                  IllegalArgumentException if char guessed is was already guessed
     * @return                  the number of occurences of the guessed character (int 0 or more)
     * @pre                     Character must be lower-case
     * @post                    returned occurences &gt;= 0
     */
    public int record(char guess)
    {
        errIfSameGuess(guess);
        archiveGuess(guess);
        
        int occurences = chooseNextSet(guess);
        
        if(occurences < 1) guessesRemaining--;
        
        return occurences;
    }
    
//=================================================================
//-----------------------PRIVATE HELPERS---------------------------
//=================================================================
    
    
        //  1 : CREATE MAP WITH ALL PATTERNS AND THEIR NUMBER OF MATCHED WORDS
        //  2 : FIND MOST POPULAR PATTERN
        //  3 : UPDATE CURRENT DISPLAY PATTERN
        //  4 : UPDATE CURRENT SET WITH WORDS MATCHING NEW PATTERN
        //  5 : RETURN NUMBER OF OCCURENCES OF NEW PATTERN
    private int chooseNextSet(char guess)
    {
        Map<String, Integer> patterns = new TreeMap<String, Integer>();
        
        populatePatternsMap(patterns, guess);
        String newPattern = findLargestPattern(patterns);
        mergePatterns(newPattern);
        updateCurrentSet(newPattern, guess);
        
        return numOccurences(newPattern, guess);
    }
    
    private int numOccurences(String newPattern, char guess)
    {
        int count = 0;
        
        for(int i = 0; i < newPattern.length(); i++)
        {
            if(newPattern.charAt(i) == guess) count++;
        }
        
        return count;
    }
    
        //  IF WORDS IN SET MATCH NEW PATTERN ADD TO NEW SET
    private void updateCurrentSet(String newPattern, char guess)
    {
        Set<String> newSet = new TreeSet<String>();
        
        for(String word : curSet)
        {
            if(strip(word, guess).equals(newPattern)) newSet.add(word);
        }
        
        curSet = newSet;
    }
    
        //  UPDATE CLASS PATTERN STRING WITH OLD + NEW MERGED
    private void mergePatterns(String newPattern)
    {
        String temp = "";
        
        for(int i = 0; i < curPattern.length(); i++)
        {
            char current = curPattern.charAt(i);
            char other   = newPattern.charAt(i);
            
            boolean bothDash = (current == '-' && other == '-');
            boolean onlyCur  = (current != '-' && other == '-');
            
                 if(bothDash) temp += '-';
            else if(onlyCur)  temp += current;
            else              temp += other;
        }
        
            //  SET CURRENT CLASS STRING PATTERN TO NEW ONE
        curPattern = temp;
    }
    
        //  RETURN PATTERN WITH LARGEST COUNT VALUE I.E. MOST WORDS
    private String findLargestPattern(Map<String, Integer> patterns)
    {
        int largest = 0;
        String biggestPattern = "";
        
        for(String pat : patterns.keySet())
        {
            int value = patterns.get(pat);
            
            if(value > largest)
            {
                largest = value;
                biggestPattern = pat;
            }
        }
        
        return biggestPattern;
    }
    
        //  STRIPS WORDS TO MAKE PATTERNS AND ADDS TO MAP
        //  IF ALREADY THERE INCREMENT ITS VALUE
    private void populatePatternsMap(Map<String, Integer> patterns, char guess)
    {
        for(String word : curSet)
        {
            String pat = strip(word, guess);
            if(patterns.containsKey(pat))   patterns.put(pat, patterns.get(pat) + 1);
            else                            patterns.put(pat, 1);
        }
    }
    
    
        //  STRIPS EVERYTHING BUT THE GUESSED CHAR AND RETURNS IT
        //  PLACES DASHES WHERE EMPTY
    private String strip(String word, char guess)
    {
        String temp = "";
        
        for(int i = 0; i < word.length(); i++)
        {
            if(word.charAt(i) == guess) temp += guess;
            else                        temp += '-';
        }
        
        return temp;
    }
    
    private void initializeSet(List<String> list, int length)
    {
        curSet = new TreeSet<String>();
        
        for(int i = 0; i < list.size(); i++)
        {       //  ADD WORDS TO SET OF SPECIFIED LENGTH
            String word = list.get(i);
            if(word.length() == length) curSet.add(word);
        }
    }
    
        //  SET INITIAL PATTERN STRING TO ALL DASHES
    private void initializePattern(int length)
    {
        curPattern = "";
        for(int i = 0; i < length; i++)
        {
            curPattern += '-';
        }
    }
    
        //  RETURN STRING WITH WHITESPACE BETWEEN CHARS
    private String expandedPattern()
    {
        int len = curPattern.length();
        String temp = "";
        
            // APPEND CHARS ADD SPACE EXCEPT LAST
        for(int i = 0; i < len-1; i++)
        {
            temp += curPattern.charAt(i);
            temp += ' ';
        }
            // APPEND LAST CHAR
        temp += curPattern.charAt(len-1);
        
        return temp;
    }
    
    private void archiveGuess(char guess)
    {
        guessed.add(guess);
    }
    
    private void errIfSameGuess(char guess)
    {
        if(guessed.contains(guess)) throw new IllegalArgumentException();
    }
    
    private void errIfEmpty()
    {
        if(curSet.size() < 1) throw new IllegalStateException();
    }
}