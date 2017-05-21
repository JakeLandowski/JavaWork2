//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #6, 5/20/17
//	Object Class =>
//  Client Class =>
//  Dependency   =>
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn6/


import java.util.Random;

/**
 * This...
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
        this.power     = rand.nextInt(MAX_POWER - (MIN_POWER - 1)) + MIN_POWER;
        this.toughness = rand.nextInt(MAX_TOUGHNESS - (MIN_TOUGHNESS - 1)) + MIN_TOUGHNESS;
        System.out.println(this + " constructor called, " + this.power + " " + this.toughness);
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
    public int getPower() { return this.power; }
    
    /**
     * Getter, returns the card's toughness level
     *
     * @return                  The card's toughness level
     */
    public int getToughness() { return this.toughness; }
    
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
                            this.power * 1.5 + this.toughness * 0.9
                        )
                    );
    }

//=================================================================
//-----------------------JAVA UTILITIES----------------------------
//=================================================================

    /**
     * Returns the card's power and toughness in string form
     *
     * @return                  String in form of "[power/toughness]"
     */
    public String toString() { return "[" + this.power + "/" + this.toughness + "]"; }
    
    /**
     * Compares by cost first, then power, then toughness
     *
     * @return                  String in form of "[power/toughness]"
     */
    public int compareTo(Card other)
    {
        int costDiff = this.getCost() - other.getCost();
        
        if(costDiff == 0)
        {
            int powerDiff = this.getPower() - other.getPower();
            
            if(powerDiff == 0) return this.getToughness() - other.getToughness();
            
            return powerDiff;
        }
        
        return costDiff;
    }
    
//=================================================================
//----------------------------GENERAL------------------------------
//=================================================================
    
    /**
     * Permanently lowers this card's current power and toughness by 10%, rounded up.
     */
    public void weaken()
    {
        this.power     = (int) Math.ceil(this.power * 0.9);
        this.toughness *= 0.9;
    }
    
    /**
     * Permanently increases this card's current power and toughness by 10%, rounded up.
     */
    public void boost()
    {
        this.power     *= 1.1;
        this.toughness *= 1.1;
    }
     
//=================================================================
//------------------------PRIVATE-HELPERS--------------------------
//=================================================================

    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


} // END CLASS