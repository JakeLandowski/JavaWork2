//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #7, 5/20/17
//	Class   => QuestionTree
//  Used By => QuestionMain, VaderMain
//  Needs   => QuestionNode(included)
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn7/

import java.io.PrintStream;
import java.util.Scanner;

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
    private int games, wins;

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
        games++;
        root = findAnswer(root);
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
        
        archiveNodeText(root, output);
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
        
        root = createNode(input);
    }

//=================================================================
//---------------------------GETTERS-------------------------------
//=================================================================
    
    /**
     * Returns a random int between min and max inclusive.
     *
     * @return                  An integer
     */
    public int totalGames() { return games; }
    
    /**
     * Returns a random int between min and max inclusive.
     *
     * @return                  An integer
     */
    public int gamesWon() { return wins; }


//=================================================================
//------------------------PRIVATE-HELPERS--------------------------
//=================================================================

    private QuestionNode findAnswer(QuestionNode node)
    {
            //  IS ANSWER
        if(node.left == null || node.right == null) node = checkAnswer(node);
        else
        {       //  IS QUESTION SO ASK
            ui.print(node.text);
            
            if ( ui.nextBoolean() )  node.left = findAnswer(node.left);
            else                    node.right = findAnswer(node.right);
        }
        
        return node;
    }
    
    private QuestionNode checkAnswer(QuestionNode answer)
    {
        ui.print("\nWould your object happen to be " + answer.text + "?");
            
        if( ui.nextBoolean() )
        {
            wins++;
            ui.println("I win!");
            return answer;
        }
        
        ui.print("\nI lose. What is your object?");
        QuestionNode newAnswer = new QuestionNode( ui.nextLine() );
        
        ui.print("\nType a yes/no question to distinguish your item from " + answer.text);
        QuestionNode question = new QuestionNode( ui.nextLine() );
        
        ui.print("\nAnd what is the answer for your object?");
        boolean yes = ui.nextBoolean();
        
        question.left  = (yes) ? newAnswer : answer;
        question.right = (yes) ? answer    : newAnswer;
        
        return question;
    }

        // this is my favorite one, i love recursion
    private QuestionNode createNode(Scanner lines)
    {
        if( !lines.hasNextLine() ) throw new IllegalArgumentException("Missing line in Scanner");
        
        String text = lines.nextLine();
        QuestionNode node = new QuestionNode(text.substring(2));
        
        if( text.startsWith("Q:") )
        {
            node.left  = createNode(lines);
            node.right = createNode(lines);
        }
        
        return node;
    }
    
    private void archiveNodeText(QuestionNode node, PrintStream file)
    {
        boolean isQuestion = (node.left != null && node.right != null);
        
        file.println( (isQuestion ? "Q:" : "A:") + node.text);
        
        if(isQuestion)
        {
            archiveNodeText(node.left, file);
            archiveNodeText(node.right, file);
        }
    }

//=================================================================
//-----------------------PRIVATE-CLASSES---------------------------
//=================================================================

    private class QuestionNode
    {
        String text;
        QuestionNode left, right;
        
        QuestionNode() { throw new IllegalStateException("Node must be given a Question or Answer"); }
        
        QuestionNode(String text) { this.text = text; }
        
    } // END CLASS

//=================================================================
//-----------------------JAVA-UTILITIES----------------------------
//=================================================================
   
//=================================================================
//-------------------------STATIC-HELPERS--------------------------
//=================================================================

//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================


} // END CLASS