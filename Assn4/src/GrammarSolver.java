import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Random;

import java.util.Arrays;
/* DEBUGGING PRINT MAP WITH ARRAYS IN IT
import java.util.Arrays;


for(String key : ruleMap.keySet())
        {
            System.out.print(key + " : ");
            
            String[][] arr = ruleMap.get(key);
            
            
            
            for(int i = 0; i < arr.length; i++)
            {
                System.out.print(Arrays.toString(arr[i]));
            }
            System.out.println();
            
        }
*/




/**
 * This object is utilized by GrammarMain.java and accepts an immutable
 * list of grammar rules, seperated by "::=" and "|" then parses it and
 * returns randomly generated sentences from it when calling generate().
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 *
 *
 *
 * Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn3/
 */
 
public class GrammarSolver
{
    Map<String, String[][]> ruleMap = new TreeMap<String, String[][]>();
    Random rand; // GLOBAL RAND TO AVOID RE-SEEDS
    
    /**
     * Default constructor
     *
     * @param dictionary        the list of words to create the initial set of words
     * @param length            the length of the words to intially pick from
     * @param max               the remaining amount of guesses to start with
     * @throws                  IllegalArgumentException if length &lt; 1 or max &lt; 0
     * @pre                     List must not be empty.
     * @pre                     length &gt; 0
     * @pre                     max &gt;= 0
     */
    public GrammarSolver(List<String> rules)
    {
        rand = new Random();
        populateMap(rules); // throws IllegalArgumentException if duplicate NonTerminal
        
        if(rules == null || rules.size() < 1) throw new IllegalArgumentException();
    }
    
    public boolean contains(String symbol)
    {
        // return true if symbol asked for exists as key in map
        // throw IllegalArgumentException if string null or length < 0
        if(symbol == null || symbol.length() < 1) throw new IllegalArgumentException();
        return ruleMap.containsKey(symbol);
    }
    
    public Set<String> getSymbols()
    {
        return ruleMap.keySet();
    }
    
    public String generate(String symbol)
    {
            //  IF SYMBOL IS NOT KEY RETURN IT, SHOULDNT HAPPEN THOUGH B/C OF CONTAINS()
        if(symbol == null || symbol.length() < 1) throw new IllegalArgumentException();
        else if(!ruleMap.containsKey(symbol)) return symbol;
        
        return pickRandomWord(ruleMap.get(symbol));
    }
    
//=================================================================
//---------------------------HELPERS-------------------------------
//=================================================================

    private void populateMap(List<String> rules)
    {
        for(String line : rules)
        {
                //  SPLIT ON ::= SURROUNDED BY ANY SPACE
                //  EX: "<derp> ::= durr | hurr durr | <blah> <dah>"
                //  EX: ["<derp>", "durr | hurr durr | <blah> <dah>"]
            String[] firstSplit = line.split("[ \t]*::=[ \t]*");
            
                //  EX: "<derp>"
            String nonTerm = firstSplit[0].trim();
            if(ruleMap.containsKey(nonTerm)) throw new IllegalArgumentException();
            
                //  EX: "durr | hurr durr | <blah> <dah>"
            String terms   = firstSplit[1];
                
                //  SPLIT ON PIPE "|" WITH ANY SPACE
                //  EX: ["durr", "hurr durr", "<blah> <dah>"]
            String[] firstTermSplit = terms.split("[ \t]*[|][ \t]*");
            
                //  CREATE FULL 2D STRING SIZE OF TOTAL TERMS
                //  ["durr", "hurr durr", "<blah> <dah>"] == 3 TERMS
            String[][] fullTerminals = new String[firstTermSplit.length][];
            
            
                //  BREAK UP EACH TERM INTO ANOTHER ARRAY
            for(int i = 0; i < firstTermSplit.length; i++)
            {
                String term = firstTermSplit[i];
                
                    //  SPLIT SEPERATED WORDS/TOKENS
                    //  EX: "<blah> <dah>" => ["<blah>", "<dah>"]
                fullTerminals[i] = term.trim().split("[ \t]+");
            }
            
                //  PLACE PROCESSED LINE IN MAP
            ruleMap.put(nonTerm, fullTerminals);
        }
    }
    
    private String pickRandomWord(String[][] terminals)
    {
        String phrase = "";
        int num = rand.nextInt(terminals.length);
        
        String[] term = terminals[num];
        
        for(int i = 0; i < term.length; i++)
        {
            if(ruleMap.containsKey(term[i]))
            {
                phrase += pickRandomWord(ruleMap.get(term[i]));
            }
            else
            {
                phrase += term[i] + " ";
            }
        }
        
        return phrase;
    }

}