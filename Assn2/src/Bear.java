//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #2, 4/13/17


import java.awt.*;


/**
 * This class instantiates a Bear critter that always INFECTS if it can,
 * otherwise HOPS forward if it can, or turns LEFT if it can't do anything else.
 * Is displayed either black or white, and displays as a forward or back slash,
 * alternating between the 2 when moving.
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Bear extends Critter
{
    private Color bearColor;
    private boolean frontSlash;
    
    /**
     * Only constructor for class Bear, accepts boolean
     * that determines if white or black.
     *
     * @param polar         color bear will be displayed as,
     *                      true = white / false = black.
     */
    public Bear(boolean polar)
    {
        if(polar) bearColor = Color.WHITE;
        else      bearColor = Color.BLACK;
    }
    
    
    /**
     * Accepts info about current bear status, flips between front slash and back slash.
     * Returns an action based on conditions :
     * <ul>
     * <li>If enemy in front : <b>INFECT ENEMY</b></li>
     * <li>Otherwise if front is empty : <b>HOP FORWARD</b></li>
     * <li>Otherwise : <b>TURN LEFT</b></li>
     * </ul>
     *
     * @param info      object containing information about critter's current status
     * @return          Action.INFECT if enemy in front, else Action.HOP if front empty,
     *                  else Action.LEFT if nothing else valid.
     */
    public Action getMove(CritterInfo info)
    {
        frontSlash = !frontSlash;
        
        if(info.getFront() == Neighbor.OTHER)       return Action.INFECT;
        else if(info.getFront() == Neighbor.EMPTY)  return Action.HOP;
        else                                        return Action.LEFT;
    }

    /**
     * Returns the color of the bear.
     *
     * @return          bearColor, holding either Color.WHITE or Color.BLACK.
     */
    public Color getColor()
    {
        return bearColor;
    }

    /**
     * Returns the string that is displayed as the label for the bear.
     *
     * @return          / or \ alternating each time the bear performs an action.
     */
    public String toString()
    {
        if(frontSlash) return "/";
        else           return "\\";
    }
}
