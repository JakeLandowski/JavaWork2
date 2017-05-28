//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #7, 5/20/17
//	Class   => QuestionTree
//  Used By => QuestionMain, VaderMain
//  Needs   => QuestionNode(included)
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn7/



/**
 * This class instantiates a Binary Tree of Questions and Answers...
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class QuestionTree
{

    private UserInterface ui;
    private QuestionNode root;

//=================================================================
//-------------------------CONSTRUCTORS----------------------------
//=================================================================
    
    /**
     * Initializes ahe Question
     *
     * @param min               Minimum int value
     * @param max               Maximum int value
     * @throws                  IllegalArgumentException if min &gt; max
     * @return                  An integer between min and max inclusive
     * @pre                     Min must be less or equal to Max
     * @post                    Returns an int between min and max inclusive
     */
    public QuestionTree (UserInterface ui)
    {
        if(ui == null) throw new IllegalArgumentException("QuestionTree needs a UserInterface.");
        this.ui = ui;
        this.root = new QuestionNode("Computer");
    }

//=================================================================
//----------------------------GENERAL------------------------------
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
    public void play()
    {
        //  LOOP TREE ASKING QUESTIONS UNTIL REACH ANSWER LEAF NODE
        //  IF COMPUTER WINS PRINT WIN MESSAGE
        //  IF COMPUTER LOSES PRINT LOSE MESSAGE
        //  IF COMPUTER LOSES ASK FOR CORRECT OBJECT
        //  ASK FOR QUESTIONS TO DISTINGUISH CORRECT OBJECT
        //  ASK IF CORRECT OBJECT IS YES OR NOR FOR NEW QUESTION
    }
    
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
    public void save(PrintStream output)
    {
        if(output == null) throw new IllegalArgumentException("QuestionTree needs a PrintStream");
        
        //  STORE CURRENT TREE STATE TO OUTPUT FILE
        //  DONT SAVE NUMBER OF GAMES PLAYED/WON
        //  EACH LINE = NODE
        //  PREFIX LINE WITH Q: OR A: TO DIFFERENTIATE
        //  LIST NODES IN PREFIX ORDER
    }
    
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
    public void load(Scanner input)
    {
        if(input == null) throw new IllegalArgumentException("QuestionTree needs a Scanner.");
        
        //  REPLACE CURRENT TREE WITH NEW TREE FROM PARSING SAVED FILE
        //  PASSED SCANNER WILL HOLD FILE
        //  CALL NEXTLINE
    }

//=================================================================
//---------------------------GETTERS-------------------------------
//=================================================================
    
    /**
     * Returns a random int between min and max inclusive.
     *
     * @return                  An integer
     */
    public int totalGames()
    {
        return 0;
    }
    
    /**
     * Returns a random int between min and max inclusive.
     *
     * @return                  An integer
     */
    public int gamesWon()
    {
        return 0;
    }


//=================================================================
//-----------------------PRIVATE-CLASSES---------------------------
//=================================================================

    private class QuestionNode
    {
        String text;
        QuestionNode left;
        QuestionNode right;
        
        QuestionNode() { throw new IllegalStateException("Node must be given a Question or Answer"); }
        
        QuestionNode(String text) { this.text = text; }
        
    } // END CLASS


//=================================================================
//-----------------------JAVA-UTILITIES----------------------------
//=================================================================
   
//=================================================================
//------------------------PRIVATE-HELPERS--------------------------
//=================================================================

//=================================================================
//-------------------------STATIC-HELPERS--------------------------
//=================================================================

//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


} // END CLASS