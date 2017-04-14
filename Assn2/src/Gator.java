//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #2, 4/13/17
//  This class insantiates a Giant critter to be rendered on live on CritterFrame.
//  Failed code is at the bottom.
//
//  I initially tried to make gator with out making it
//  copy the super overpowered Bear and Giant critters by following Flytrap's strategy
//  of gathering together and just spinning while sometimes moving, this failed miserably.
//
//  I then tried implementing an IN DANGER method that would detect if an enemy was near and
//  force a HOP, I initially combined this with the turtling stategy before, also useless.
//
//  I then implemented a stalking that the gator would start when hitting a wall. An array
//  held a chain of movements to fire in sequence when hitting a wall, causing it to turn
//  around, move back one, and turn to face the wall again, then wait, attemping to infect
//  anything that would run by. I tried this because the bear/giants tend to circle the map,
//  usually on the boundary, I figured, despite the accuracy penalty to INFECT for not moving,
//  that this would still be beneficial as a gator could potentially snag 1-2 enemies for free,
//  that move by, this failed as well.
//
//  Based on the rules and experiments I found that moving a lot is just too OP, so anything
//  that involved halting movement ended up being extremely detrimental. So my final strategy
//  that ended up working (mostly) was combining both moving as much as possible, with the first
//  strategy, which was grouping together. So now, the gators when running into each other, will
//  mimic the direction of the gator they ran into, thus following them side by side, but turning
//  randomly if 2 gators hit each other so they don't get stuck. Otherwise, they just keep moving
//  and turn randomly when hitting a wall. Turning left everytime copies bears and lets bears win,
//  turning right everytime copies giants and lets giants win, so instead gators just random this
//  turn when hitting walls, otherwise they tend to gang up and win.
//
//  It's not perfect as variance still lets gators win about 43% of the time, bears and fly traps
//  tend to tie, giants won half as much, and lions win never. Lions suck. Even when bears are
//  dominating, the grouping behavior of gators lets them SOMETIMES make a comeback, or at the
//  very least, put up a good fight and last quite a while.
//
//  Tries: 51
//------------
//  gator: 22
//  trap : 11
//  bear : 12
//  giant: 5
//  stalemate[flytrap vs giant] : 1

import java.awt.*;
import java.util.Random;

/**
 * This class instantiates a Gator critter that always INFECTS if it can,
 * otherwise if it runs into a fellow Gator, it turns to face the same Direction
 * as that Gator, otherwise tries to HOP forward, otherwise turns LEFT or RIGHT.
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Gator extends Critter
{
    private Random rand;
    private String body;
    private boolean repeat;
    private Action echoAction; // NEEDED TO REPEAT A SPECIFIED ACTION
    
    /**
     * Only constructor for class Gator.
     */
    public Gator()
    {
        rand = new Random();
        repeat = false;
        echoAction = Action.HOP;
        body = "g";
    }
    
    /**
     * Accepts info about current gator status.
     *
     * Returns an action based on conditions :
     * <ul>
     * <li>If enemy in front : <b>INFECT ENEMY</b></li>
     * <li>If friend in front : <b>TURN LEFT/RIGHT</b> to copy their direction</li>
     * <li>Otherwise if able : <b>HOP FORWARD</b></li>
     * <li>Otherwise : <b>TURN LEFT/RIGHT</b> randomly</li>
     * </ul>
     *
     * @param info      object containing information about critter's current status
     * @return          Action.INFECT if enemy in front, else Action.LEFT/Action.RIGHT if friend in front,
     *                  else Action.HOP if front empty, else Action.LEFT/Action.RIGHT randomly.
     */
    public Action getMove(CritterInfo info)
    {
        if(info.getFront() == Neighbor.OTHER)
        {
            body = "G";
            return Action.INFECT;
        }
        else if(repeat) return echoAction;
        else if(foundFriend(info))
        {
            if(!friendFacingMe(info))
            {
                switch(info.getFrontDirection())
                {
                    case NORTH: return turnNorth(info);
                        
                    case SOUTH: return turnSouth(info);
                        
                    case EAST:  return turnEast(info);
                        
                    case WEST:  return turnWest(info);
                }
            }
        }
        else if(info.getFront() == Neighbor.EMPTY)
        {
            body = "g";
            return Action.HOP;
        }
        return randTurn();
    }
    
    /**
     * Returns the color of the giant.
     *
     * @return          Color rgb of [50, 200, 50], darker green.
     */
    public Color getColor()
    {
        return new Color(50, 200, 50);
    }

    /**
     * Returns the string that is displayed as the label for the gator.
     *
     * @return          g or G if gator is infecting
     */
    public String toString()
    {
        return body;
    }
    
//=======================================================================================
//------------------------------------PRIVATE HELPERS------------------------------------
//=======================================================================================
    
    private Action randTurn()
    {
        if( rand.nextBoolean() ) return Action.LEFT;
        else                     return Action.RIGHT;
    }
    
    private boolean friendFacingMe(CritterInfo info)
    {
        Direction meFacing   = info.getDirection();
        Direction themFacing = info.getFrontDirection();
        
        switch(meFacing)
        {
            case NORTH: return themFacing == Direction.SOUTH;
            
            case SOUTH: return themFacing == Direction.NORTH;
            
            case EAST:  return themFacing == Direction.WEST;
            
            case WEST:  return themFacing == Direction.EAST;
            
            default:    return false;
        }
    }
    
    private boolean foundFriend(CritterInfo info)
    {
        return info.getFront() == Neighbor.SAME;
    }
    
    
    private Action turnWest(CritterInfo info)
    {
             if(info.getDirection() == Direction.NORTH) return Action.LEFT;
        else if(info.getDirection() == Direction.SOUTH) return Action.RIGHT;
        else if(info.getDirection() == Direction.EAST)
        {
            Action act = randTurn();
            repeat = true;
            echoAction = act;
            return act;
        }
        else return Action.HOP;
    }
    
    private Action turnNorth(CritterInfo info)
    {
             if(info.getDirection() == Direction.EAST)  return Action.LEFT;
        else if(info.getDirection() == Direction.WEST)  return Action.RIGHT;
        else if(info.getDirection() == Direction.SOUTH)
        {
            Action act = randTurn();
            repeat = true;
            echoAction = act;
            return act;
        }
        else return Action.HOP;
    }
    
    private Action turnEast(CritterInfo info)
    {
             if(info.getDirection() == Direction.NORTH)  return Action.RIGHT;
        else if(info.getDirection() == Direction.SOUTH)  return Action.LEFT;
        else if(info.getDirection() == Direction.WEST)
        {
            Action act = randTurn();
            repeat = true;
            echoAction = act;
            return act;
        }
        else return Action.HOP;
    }
    
    private Action turnSouth(CritterInfo info)
    {
             if(info.getDirection() == Direction.EAST)  return Action.RIGHT;
        else if(info.getDirection() == Direction.WEST)  return Action.LEFT;
        else if(info.getDirection() == Direction.NORTH)
        {
            Action act = randTurn();
            repeat = true;
            echoAction = act;
            return act;
        }
        else return Action.HOP;
    }

}
//=======================================================================================
//---------------------------------FAILED EXPERIMENTS------------------------------------
//=======================================================================================
    
    //    stalking = false;
    
    //  stalktime = stalkStage = 0;
    /*private final Action[] stalkActions = { Action.LEFT, Action.LEFT, Action.HOP,
                                            Action.RIGHT, Action.RIGHT, Action.INFECT,
                                            Action.INFECT, Action.INFECT }; */
    
    //private int stalktime, stalkStage;
    //private boolean stalking;
    //private final Action[] stalkActions = { Action.LEFT, Action.LEFT };
    
    
    
    /*private Action stalk()
    {
        Action act = stalkActions[stalkStage];
        
        stalkStage++;
        if(stalkStage > stalkActions.length - 1)
        {
            stalkStage = 0;
            stalking = false;
        }
        
        return act;
    }*/
    
    /*public Action getMove(CritterInfo info)
    {
        if(info.getFront() == Neighbor.WALL) stalking = true;
        
        if(info.getFront() == Neighbor.OTHER)
        {
            body = "G";
            return Action.INFECT;
        }
        else if(stalking)
        {
            if( isMoving(info) ) return Action.INFECT;
            else                 return stalk();
        }
        else if(info.getFront() != Neighbor.EMPTY)
        {
            body = "g";
            return randTurn();
        }
        else return Action.HOP;
    }
    
    private boolean isMoving(CritterInfo info)
    {
        return stalkActions[stalkStage] == Action.HOP &&
                        info.getFront() != Neighbor.EMPTY;
    }*/
    
    
    /*private boolean huggingFriend(CritterInfo info)
    {
        Neighbor friend = Neighbor.SAME;
        
        if(info.getLeft()  == friend ||
           info.getRight() == friend ||
           info.getFront() == friend ||
           info.getBack()  == friend)
         {
            return true;
         }
        else return false;
    }*/
    
    
    /*private boolean inDanger(CritterInfo info)
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
    }*/
