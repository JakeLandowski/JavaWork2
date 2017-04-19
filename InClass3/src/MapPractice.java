/**
 * Jacob Landowski
 * CS145 : Spring 2017 : Section 2723
 * Inclass Assignment #3 : 4/19/17
 *
 * This program opens a text file of words in a Scanner, then loops through
 * each word, adding to a TreeMap and either adds to the current number mapped
 * to that word, or adds the word with a 1 if it is fresh. Then the map is looped
 * through and each word along with its associated number is printed off and formatted.
 *
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class MapPractice
{
    public static void main(String args[]) throws FileNotFoundException
    {
        Map<String, Integer> wordCount = new TreeMap<String, Integer>();
        Scanner file = openFile("DataFile1.txt");
        
        populateMap(wordCount, file); // Loops through Scanner file and appends words to map
        displayCounts(wordCount);     // Loops through map and displays each key/pair
    }
    
    private static Scanner openFile(String name) throws FileNotFoundException
    {
        return new Scanner(new File(name)); // Opens file name and returns encased in Scanner
    }
    
    private static void populateMap(Map<String, Integer> map, Scanner file)
    {
        while(file.hasNext())
        {
            String word = file.next();
                                            //  If word in map, increment
            if(map.containsKey(word))   map.put(word, map.get(word) + 1);
                                            //  Else, add to map at 1
            else                        map.put(word, 1);
        }
    }
    
    private static void displayCounts(Map<String, Integer> map)
    {
        for(String word : map.keySet())
        {                               //  Print left-aligned with 14 space minimum
            System.out.printf("%-14s : found " + map.get(word) + " times.%n", word);
        }
    }
}
