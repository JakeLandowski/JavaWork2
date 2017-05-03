/**
 * Jacob Landowski
 * CS145 : Spring 2017 : Section 2723
 * Inclass Assignment WeEK #5 : 5/2/17
 *
 * This program searches a string recursively and tests each character
 * pair by pair to see if it is truly a palindrome. The method will take
 * a word, cut off and test the first and last characters, if they match
 * it will pass the remaining word to itself and continue, as soon as any
 * it finds any mismatch it kills the loop, if it reaches the end with no
 * mismatches it will return true up the chain.
 *
 */

public class RecursiveTestMain
{
    public static void main(String[] args)
    {
        System.out.println("madam : " + isPalindrome("madam"));
        System.out.println("racecar : " + isPalindrome("racecar"));
        System.out.println("step on no pets : " + isPalindrome("step on no pets"));
        System.out.println("able was I ere I saw elba : " + isPalindrome("able was I ere I saw elba"));
        System.out.println("Java : " + isPalindrome("Java"));
        System.out.println("rotater : " + isPalindrome("rotater"));
        System.out.println("byebye : " + isPalindrome("byebye"));
        System.out.println("notion : " + isPalindrome("notion"));
    }
    
    public static boolean isPalindrome(String word)
    {
            //  GRAB LENGTH
        int len = word.length();
        
        
        if(len < 2)
        {       //  IF REMAINING STRING IS 1 OR 0 CHARS ITS A PALINDROME
            return true;
        }
        else if(front.equals(back))
            //  GET FIRST CHAR
            String front = word.substring(0, 1);
                
                //  GET LAST CHAR
            String back  = word.substring(len - 1);
            
        {       //  RETURN WORD WITH FIRST AND LAST CHAR SLICED OFF
            return isPalindrome(word.substring(1, len - 1));
        }
        else
        {       //  AS SOON AS FIRST/LAST CHAR MISMATCH KILL RECURSION
            return false;
        }
    }
}