//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #2, 4/13/17
//  This class insantiates a Giant critter to be rendered on live on CritterFrame.

import java.awt.*;
import java.util.Random;


/**
 * This class instantiates a Gator critter that always INFECTS if it.......
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Gator extends Critter
{
    Random rand;
    int stalktime;
    
    /**
     * Only constructor for class Giant.
     */
    public Gator()
    {
        rand = new Random();
        stalktime = 1;
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
    {
        if     (info.getFront() == Neighbor.OTHER)  return Action.INFECT;
        else if(inDanger(info) ||
                info.getFront() == Neighbor.EMPTY)  return Action.HOP;
        else                                        return randAction();
    }
    
    private Action randAction()
    {
        int num = rand.nextInt(12);
        
             if(num == 11)     return Action.HOP;
        else if(num % 2 == 1 ) return Action.LEFT;
        else                   return Action.RIGHT;
    }
    
    private boolean inDanger(CritterInfo info)
    {
        switch(info.getDirection())
        {
            case NORTH:
                     if(info.getLeft() == Neighbor.OTHER &&
                        info.getLeftDirection()  == Direction.EAST)  return true;
                else if(info.getRight() == Neighbor.OTHER &&
                        info.getRightDirection() == Direction.WEST)  return true;
                else if(info.getBack() == Neighbor.OTHER &&
                        info.getBackDirection()  == Direction.NORTH) return true;
                break;
                
            case SOUTH:
                     if(info.getLeft() == Neighbor.OTHER &&
                        info.getLeftDirection()  == Direction.WEST)  return true;
                else if(info.getRight() == Neighbor.OTHER &&
                        info.getRightDirection() == Direction.EAST)  return true;
                else if(info.getBack() == Neighbor.OTHER &&
                        info.getBackDirection()  == Direction.SOUTH) return true;
                break;
                
            case EAST:
                     if(info.getLeft() == Neighbor.OTHER &&
                        info.getLeftDirection()  == Direction.SOUTH) return true;
                else if(info.getRight() == Neighbor.OTHER &&
                        info.getRightDirection() == Direction.NORTH) return true;
                else if(info.getBack() == Neighbor.OTHER &&
                        info.getBackDirection()  == Direction.EAST)  return true;
                break;
                
            case WEST:
                     if(info.getLeft() == Neighbor.OTHER &&
                        info.getLeftDirection()  == Direction.NORTH) return true;
                else if(info.getRight() == Neighbor.OTHER &&
                        info.getRightDirection() == Direction.SOUTH) return true;
                else if(info.getBack() == Neighbor.OTHER &&
                        info.getBackDirection()  == Direction.WEST)  return true;
                break;
        }
    
        return false;
    }

    /**
     * Returns the color of the giant.
     *
     * @return          wordColor, holding either Color.RED, Color.GREEN or Color.BLUE.
     */
    public Color getColor()
    {
        return new Color(0, 100, 0);
    }

    /**
     * Returns the string that is displayed as the label for the giant.
     *
     * @return          L
     */
    public String toString()
    {
        return "G";
    }
}
