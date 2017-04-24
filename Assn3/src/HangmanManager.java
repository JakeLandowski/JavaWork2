/**
 * NEED TO FINISH JAVADOCS
 */

import java.util.*;

public class HangmanManager
{
    private Set<String> curSet;
    private SortedSet<Character> guessed;
    private String curPattern;
    private int guessesRemaining;
    
    public HangmanManager(List<String> dictionary, int length, int max)
    {
        if(length < 1 || max < 0) throw new IllegalArgumentException();
        
        guessed = new TreeSet<Character>();
        guessesRemaining = max;
        initializePattern(length);
        initializeSet(dictionary, length);
    }
    
    public Set<String> words()
    {
        return curSet;
    }
    
    public int guessesLeft()
    {
        return guessesRemaining;
    }
    
    public SortedSet<Character> guesses()
    {
        return guessed;
    }
    
    public String pattern()
    {
        errIfEmpty();
        return expandedPattern();
    }
    
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
    
    
        //  CREATE MAP WITH ALL PATTERNS AND THEIR NUMBER OF MATCHED WORDS
        //  FIND MOST POPULAR PATTERN
        //  UPDATE CURRENT DISPLAY PATTERN
        //  UPDATE CURRENT SET WITH WORDS MATCHING NEW PATTERN
        //  RETURN NUMBER OF OCCURENCES OF NEW PATTERN
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