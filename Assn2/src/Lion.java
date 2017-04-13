//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #2, 4/13/17
//  This class insantiates a Lion critter to be rendered on live on CritterFrame.

import java.awt.*;
import java.util.Random;


/**
 * This class instantiates a Lion critter that always INFECTS if it can,
 * otherwise turns LEFT if a wall is to the front or right, otherwise
 * turns RIGHT if a fellow lion is in front, otherwise just HOPs forward.
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Lion extends Critter
{
    private Color lionColor;
    private Random rand;
    private int colorLoop, lastColor;
    
    /**
     * Only constructor for class Lion.
     */
    public Lion()
    {
        rand = new Random();
        lastColor = -1;
        lastColor = randRGB();
        colorLoop = 0;
    }
    
    private int randRGB()
    {
        int rgb = -1;
        
        while(rgb == lastColor) // IF OLD COLOR PICK DIFFERENT
        {
            rgb = rand.nextInt(2);
        }
        
        if     (rgb == 0)   lionColor = Color.RED;
        else if(rgb == 1)   lionColor = Color.GREEN;
        else                lionColor = Color.BLUE;
        
        return rgb;
    }
    
    /**
     * Accepts info about current lion status, and will change color every
     * 3 times this method is called.
     *
     * Returns an action based on conditions :
     * <ul>
     * <li>If enemy in front : <b>INFECT ENEMY</b></li>
     * <li>Otherwise if front/right is a wall : <b>TURN LEFT</b></li>
     * <li>Otherwise if front is fellow Lion : <b>TURN RIGHT</b></li>
     * <li>Otherwise : <b>HOP FORWARD</b></li>
     * </ul>
     *
     * @param info      object containing information about critter's current status
     * @return          Action.INFECT if enemy in front, else Action.LEFT if front/right
     *                  is a wall, else Action.RIGHT if front is fellow lion, Action.HOP
     *                  if nothing else valid.
     */
    public Action getMove(CritterInfo info)
    {
        if(colorLoop > 2)
        {
            colorLoop = 0;
            lastColor = randRGB();
        }
        colorLoop++;
        
        if     (info.getFront() == Neighbor.OTHER)  return Action.INFECT;
        else if(info.getFront() == Neighbor.WALL ||
                info.getRight() == Neighbor.WALL)   return Action.LEFT;
        else if(info.getFront() == Neighbor.SAME)   return Action.RIGHT;
        else                                        return Action.HOP;
    }

    /**
     * Returns the color of the lion.
     *
     * @return          lionColor, holding either Color.RED, Color.GREEN or Color.BLUE.
     */
    public Color getColor()
    {
        return lionColor;
    }

    /**
     * Returns the string that is displayed as the label for the lion.
     *
     * @return          L
     */
    public String toString()
    {
        return "L";
    }
}
