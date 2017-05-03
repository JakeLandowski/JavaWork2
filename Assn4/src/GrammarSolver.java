import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        populateMap(rules);
    
        // rules is a list of each line in the file
        // create a map of the rules, non-terminals as keys
        // value will be a, array of each non-terminal or terminal
        //      associated with the initial non-terminal
        
        // throw IllegalArgumentException if list null or size 0
        // throw IllegalArgumentException if grammar contains more
        //      than 1 line for same non-terminal, basically same key
    }
    
    public boolean contains(String symbol)
    {
        // return true if symbol asked for exists as key in map
        // throw IllegalArgumentException if string null or length < 0
        return true;
    }
    
    public Set<String> getSymbols()
    {
        // return sorted set of strings of map keyset, the non-terminals
        //      available.
        // use TreeMap so keyset is sorted when we return it?
        return new TreeSet<String>();
    }
    
    public String generate(String symbol)
    {
        // randomly and recursively generate lines based on grammar
        // if symbol given is terminal and not in key (which shouldnt happen because of contains)
        //      simply return it back
        // throw IllegalArgumentException if string is null or length < 0
        return "";
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

}