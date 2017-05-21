//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #6, 5/20/17
//	Class   => Card
//  Used By => CardArrayMaster, CardArrayList
//  Needs   =>
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn6/


import java.util.Random;

/**
 * ..
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Card implements Comparable<Card>
{
    private static final int MIN_POWER     = 1;     // STATIC FOR SHARED EFFICIENCY
    private static final int MAX_POWER     = 1000;
    private static final int MIN_TOUGHNESS = 1;
    private static final int MAX_TOUGHNESS = 1000;
    private static Random rand = new Random();        // STATIC FOR TRUE RANDOM

    private int power;
    private int toughness;
    
//=================================================================
//-------------------------CONSTRUCTORS----------------------------
//=================================================================

    /**
     * Default Constructor, sets card power and toughness to random values between MIN and MAX
     * contraints. (Default is 1 - 1000 inclusive for both).
     */
    public Card()
    {
            //  GET RANDOM FROM MIN TO MAX INCLUSIVE
        power     = randomInt(MIN_POWER,     MAX_POWER);
        toughness = randomInt(MIN_TOUGHNESS, MAX_TOUGHNESS);
    }

    /**
     * Constructor, takes an int argument that sets both power and toughness to that argument.
     *
     * @param num               The value for both card power and toughness
     * @throws                  IllegalArgumentException if num &lt; MIN or num &gt; MAX
     * @pre                     Num needs to be within MIN-MAX constraints for both power and toughness
     * @post                    Sets internal power and toughness values to num value
     */
    public Card(int num) { this(num, num); }
    
    /**
     * Constructor, takes 2 int arguments to set both power and toughness values seperately.
     *
     * @param power             The value for card power
     * @param toughness         The value for card toughness
     * @throws                  IllegalArgumentException if power &lt; MIN or power &gt; MAX
     * @throws                  IllegalArgumentException if toughness &lt; MIN or toughness &gt; MAX
     * @pre                     Power needs to be within MIN-MAX constraints for power
     * @pre                     Toughness needs to be within MIN-MAX constraints for toughness
     * @post                    Sets internal power and toughness values
     */
    public Card(int power, int toughness)
    {
        if(power < MIN_POWER || power > MAX_POWER)
        {
           throw new IllegalArgumentException("Input power is out of bounds for constructor");
        }
        else if(toughness < MIN_TOUGHNESS || toughness > MAX_TOUGHNESS)
        {
            throw new IllegalArgumentException("Input toughness is out of bounds for constructor");
        }
        
        this.power     = power;
        this.toughness = toughness;
    }
    
//=================================================================
//---------------------------GETTERS-------------------------------
//=================================================================

    /**
     * Getter, returns the card's power level
     *
     * @return                  The card's power level
     */
    public int getPower() { return power; }
    
    /**
     * Getter, returns the card's toughness level
     *
     * @return                  The card's toughness level
     */
    public int getToughness() { return toughness; }
    
    /**
     * Getter, returns the card's cost based on its power and toughness
     *
     * @return                  ceiling( square root( power * 1.5 + toughness * 0.9 ) )
     */
    public int getCost()
    {
        return (int)Math.ceil
                    (
                        Math.sqrt
                        (
                            power * 1.5 + toughness * 0.9
                        )
                    );
    }

//=================================================================
//-----------------------JAVA-UTILITIES----------------------------
//=================================================================

    /**
     * Returns the card's power and toughness in string form
     *
     * @return                  String in form of "[power/toughness]"
     */
    public String toString() { return "[" + power + "/" + toughness + "]"; }
    
    /**
     * Compares by cost first, then power, then toughness
     *
     * @return                  String in form of "[power/toughness]"
     */
    public int compareTo(Card other)
    {
        int costDiff = getCost() - other.getCost();
        
        if(costDiff == 0)
        {
            int powerDiff = getPower() - other.getPower();
            
            if(powerDiff == 0) return getToughness() - other.getToughness();
            
            return powerDiff;
        }
        
        return costDiff;
    }
    
//=================================================================
//----------------------------GENERAL------------------------------
//=================================================================
    
    /**
     * Permanently lowers this card's current power and toughness by 10%, rounded.
     */
    public void weaken() // Documentation doesn't specify if should be rounded up or down.
    {
        power     = (int) Math.round(power     * 0.9);
        toughness = (int) Math.round(toughness * 0.9);
    }
    
    /**
     * Permanently increases this card's current power and toughness by 10%, rounded.
     */
    public void boost()
    {
        power     = (int) Math.round(power     * 1.1);
        toughness = (int) Math.round(toughness * 1.1);
    }
     
//=================================================================
//------------------------PRIVATE-HELPERS--------------------------
//=================================================================

//=================================================================
//-------------------------STATIC-HELPERS--------------------------
//=================================================================
    
    /**
     * Returns a random int between min and max inclusive.
     *
     * @param min               Minimum int value
     * @param max               Maximum int value
     * @throws                  IllegalArgumentException if min &gt; max
     * @return                  An integer between min and max inclusive
     * @pre                     Min must be less or equal to Max
     * @post                    Returns an int between min and max inclusive
     */
    public static int randomInt(int min, int max)
    {
        if(min > max) throw new IllegalArgumentException("Min cannot be greater than max");
        
        return rand.nextInt(max - (min - 1)) + min;
    }
    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


} // END CLASS