import java.util.Map;
import java.util.TreeMap;

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
    }
    
    public Set<String> getSymbols()
    {
        // return sorted set of strings of map keyset, the non-terminals
        //      available.
        // use TreeMap so keyset is sorted when we return it?
    }
    
    public String generate(String symbol)
    {
        // randomly and recursively generate lines based on grammar
        // if symbol given is terminal and not in key (which shouldnt happen because of contains)
        //      simply return it back
        // throw IllegalArgumentException if string is null or length < 0
        
    }
    
//=================================================================
//---------------------------HELPERS-------------------------------
//=================================================================

    private void populateMap(List<String> rules)
    {
        for(String line : rules)
        {
            String[] firstSplit = line.split("[ \t]+::=|::=[ \t]+|::=");
            String nonTerm = firstSplit[0];
            String terms   = firstSplit[1];
        }
    }

}