//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #4, 5/4/17
//	Object Class - GrammarSolver
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn4/

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;

import java.util.Arrays; // DEBUGGING

/**
 * This object is utilized by GrammarMain.java and accepts an immutable
 * list of grammar rules, seperated by "::=" and "|" then parses it and
 * returns randomly generated sentences from it when calling generate().
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class GrammarSolver
{
    Map<String, String[][]> ruleMap = new TreeMap<String, String[][]>();
    Random rand; // GLOBAL RAND TO AVOID RE-SEEDS
    
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
    public GrammarSolver(List<String> rules)
    {
        rand = new Random();
        populateMap(rules); // throws IllegalArgumentException if duplicate NonTerminal
        
        if(rules == null || rules.size() < 1) throw new IllegalArgumentException();
        
        //  DEBUG
        //  printMapDEBUG();
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
    public boolean contains(String symbol)
    {
        if(symbol == null || symbol.length() < 1) throw new IllegalArgumentException();
        return ruleMap.containsKey(symbol);
    }
    
    /**
     * Returns a sorted set of Non-Terminals available in the rule map.
     *
     * @return                  A set of non-terminal keys to generate phrases from
     */
    public Set<String> getSymbols()
    {
        return ruleMap.keySet(); // TreeMap sorts it. So.... yay?
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
            
                //  CREATE EMPTY 2D STRING SIZE OF TOTAL TERMS
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
        
        String[] term = terminals[num];         //  CHOOSE RANDOM TERMINAL SECTION
        
        for(int i = 0; i < term.length; i++)    //  LOOP THROUGH WORDS IF MORE THAN ONE
        {
            if(ruleMap.containsKey(term[i]))    //  IF KEY IN MAP, RECURSIVE
            {
                phrase += pickRandomWord(ruleMap.get(term[i]));
            }
            else
            {
                phrase += term[i] + " ";        //  IF NOT KEY JUST APPEND
            }
        }
        
        return phrase;
    }
    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================
    
    private void printMapDEBUG()
    {
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
    }
}