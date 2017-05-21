//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #6, 5/20/17
//	Object Class =>
//  Client Class =>
//  Dependency   =>
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn6/


/**
 * This...
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class PremiumCard extends Card
{
    // --QUESTION--

    // Unsure if its better to use parent class's power and toughness
    // along with its constructors or to redo all of that in this
    // child class so it can manage its own private fields or if it
    // is fine to use and inherit its parent private fields and just
    // use getters to access them. What is the better?
    
//=================================================================
//-------------------------CONSTRUCTORS----------------------------
//=================================================================

    /**
     * {@inheritDoc}
     */
    public PremiumCard() { super(); }
    
    /**
     * {@inheritDoc}
     */
    public PremiumCard(int num) { super(num); }
    
    /**
     * {@inheritDoc}
     */
    public PremiumCard(int power, int toughness) { super(power, toughness); }
    
//=================================================================
//---------------------------GETTERS-------------------------------
//=================================================================

    // INHERITED FROM CARD CLASS
    // getPower()
    // getToughness()
    // getCost()

//=================================================================
//-----------------------JAVA UTILITIES----------------------------
//=================================================================

    /**
     * Returns the card's power and toughness in string form
     *
     * @return                  String in form of "{{power/toughness}}"
     */
    @Override
    public String toString() { return "{{" + this.getPower() + "/" + this.getToughness() + "}}"; }
    
//=================================================================
//----------------------------GENERAL------------------------------
//=================================================================
    
    // INHERITED FROM CARD CLASS
    // weaken()
    // boost()
     
//=================================================================
//------------------------PRIVATE-HELPERS--------------------------
//=================================================================

    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


} // END CLASS