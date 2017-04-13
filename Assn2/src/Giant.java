//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #2, 4/13/17
//  This class insantiates a Giant critter to be rendered on live on CritterFrame.

import java.awt.*;
import java.util.Random;


/**
 * This class instantiates a Giant critter that always INFECTS if it can,
 * otherwise HOP forward if nothing is in front, otherwise turns RIGHT.
 * Giant is displayed as fee, fie, foe, fum, and changes every 6 steps.
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Giant extends Critter
{
    private final String[] WORDS = { "fee", "fie", "foe", "fum" };
    private final int ITERATIONS = 6;
    private Color giantColor;
    private int wordLoop;
    private String body;
    
    /**
     * Only constructor for class Giant.
     */
    public Giant()
    {
        body = WORDS[0];
        wordLoop = 1;
    }
    
    /**
     * Accepts info about current giant status, increments FeeFieFoeFum
     * loop every 6 calls.
     *
     * Returns an action based on conditions :
     * <ul>
     * <li>If enemy in front : <b>INFECT ENEMY</b></li>
     * <li>Otherwise if able : <b>HOP FORWARD</b></li>
     * <li>Otherwise : <b>TURN RIGHT</b></li>
     * </ul>
     *
     * @param info      object containing information about critter's current status
     * @return          Action.INFECT if enemy in front, else Action.HOP if front empty,
     *                  Action.RIGHT if nothing else valid.
     */
    public Action getMove(CritterInfo info)
    {       //  IF BEYOND WORD LIST RESET
        if(wordLoop/ITERATIONS == 4) wordLoop = 1;
        
                //  SET TO WORD IN LIST BASED ON ITERATION
        body = WORDS[wordLoop/ITERATIONS];
        wordLoop++;
        
        if     (info.getFront() == Neighbor.OTHER)  return Action.INFECT;
        else if(info.getFront() == Neighbor.EMPTY)  return Action.HOP;
        else                                        return Action.RIGHT;
    }

    /**
     * Returns the color of the giant.
     *
     * @return          wordColor, holding either Color.RED, Color.GREEN or Color.BLUE.
     */
    public Color getColor()
    {
        return Color.GRAY;
    }

    /**
     * Returns the string that is displayed as the label for the giant.
     *
     * @return          L
     */
    public String toString()
    {
        return body;
    }
}
