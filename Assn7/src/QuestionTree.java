//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #7, 5/28/17
//	Class   => QuestionTree
//  Used By => QuestionMain, VaderMain
//  Needs   => QuestionNode(included)
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn7/

import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class instantiates a Binary Tree of Questions and Answers, as well as contains
 * its helper class QuestionNode. The QuestionTree will maintain a Binary Tree of questions
 * either initialized with a default answer or loaded from a saved file, then populates the
 * tree during runtime as the user adds new questions and answers.
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
     * Initializes the question Binary Tree with a single default answer and stores the
     * given UserInterface.
     *
     * @param ui                The UserInterface the QuestionTree will use
     * @throws                  IllegalArgumentException if ui is null
     * @pre                     UserInterface must exist and not be null, must contain the
     *                          following methods: nextBoolean(), nextLine(), println(), print()
     * @post                    Populates a tree with a default answer and stores the given ui
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
     * Starts a round of 20 Questions, will continue guessing until the computer has
     * reached an answer, if that answer is wrong the computer will prompt the user for
     * a new answer and a corresponding question and handle the data changes internally.
     */
    public void play()
    {
        games++;
        root = findAnswer(root);
    }
    
    /**
     * Archives the questions and answers in the current tree to a file of specified name.
     *
     * @param output            The PrintStream pointing to the file
     * @throws                  IllegalArgumentException if the PrintStream is null
     * @pre                     PrintStream must contain a file and not be null
     * @post                    Saves the tree data to the text file
     */
    public void save(PrintStream output)
    {
        if(output == null) throw new IllegalArgumentException("QuestionTree needs a PrintStream");
        
        archiveNodeText(root, output);
    }
    
    /**
     * Takes a file of questions and answers and creates a Binary Tree out of it.
     *
     * @param input             The Scanner containing the file
     * @throws                  IllegalArgumentException if Scanner is null or if missing a line
     * @pre                     Scanner must contain a file and not be null or missing lines
     * @post                    Prepares a tree of the questions and answers
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
     * Returns a int representing the number of games played.
     *
     * @return                  The current number of games played
     */
    public int totalGames() { return games; }
    
    /**
     * Returns a int representing the number of computer wins.
     *
     * @return                  The current number of computer wins
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