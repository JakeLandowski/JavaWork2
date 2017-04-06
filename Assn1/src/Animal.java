import java.util.Random;

/**
 * This class instantiates a basic Animal object with a name, x/y coordinate values,
 * and a max speed. It is used in the AnimalMain class.
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class Animal
{
    private final Random rand = new Random();
    private final int[] DOMAIN = { -20, 20 };     //  SET TO [-2 <=> 2] FOR EASIER TESTING
    private final int[] RANGE = { -20, 20 };      //  FIGHTS ARE RARE AT [-20 <=> 20] RANGE
    private final int[] MAX_SPD_RANGE = { 2, 5 };
    private String name;
    private int x, y;       //  -20 <=> 20
    private int maxspd; //   2 <=> 5
    private boolean isfighting;
    
    /**
     * Default constructor which calls its parameterized constructor
     * Passes String "unknown", int 0, int 0, int 4.
     *
     */
    public Animal()
    {
        this("unknown", 0, 0, 4);
    }
    
    /**
     * Parameterized constructor.
     *
     * @param initName      the name of the animal.
     * @param initX         the x coordinate, will be capped at DOMAIN values.
     * @param initY         the y coordinate, will be capped at RANGE values
     * @param initMaxSpd    the max movement value used when determining how much to randomly move,
     *                      starting from 1 to this number, will be capped at
     *                      <code>MAX_SPD_RANGE</code> values.
     * @see                 #DOMAIN
     * @see                 #RANGE
     * @see                 #MAX_SPD_RANGE
     * @see                 #setX(int)
     * @see                 #setY(int)
     * @see                 #setMaxSpd(int)
     */
    public Animal(String initName, int initX, int initY, int initMaxSpd)
    {
        name = initName;
        setX(initX);
        setY(initY);
        setMaxSpd(initMaxSpd);
        isfighting = false;
    }
    
    /**
     * Moves this animal in a random direction a distance calculated from
     * random 1 to maxspd.
     *
     * @see                 #maxspd
     */
    public void move()
    {
        int dir = rand.nextInt(4) + 1;
        int dist = rand.nextInt(maxspd) + 1;
        
        switch(dir) //  MOVE EACH DIRECTION
        {
            case 1:
                setX(x - dist);
                break;
            case 2:
                setX(x + dist);
                break;
            case 3:
                setY(y - dist);
                break;
            case 4:
                setY(y + dist);
                break;
        }
    }
    
    /**
     * Tests to see if this Animal and the passed Animal objects have
     * x and y that are both equal to each other, returns true/false.
     *
     * @param other         The seperate Animal object instance to test against.
     * @return              <code>True</code> if this.x == other.x and this.y == other.y;
     *                      <code>false</code> otherwise.
     */
    public boolean touching(Animal other)
    {
        if(x == other.getX() && y == other.getY()) return true;
        else return false;
    }
    
    /**
     * Set this Animal's x. Caps out at DOMAIN values.
     *
     * @param num           The number to set x to.
     * @see                 #DOMAIN
     */
    private void setX(int num)
    {
        if(num >= DOMAIN[0] && num <= DOMAIN[1]) x = num;
        else if(num < DOMAIN[0])                 x = DOMAIN[0];
        else if(num > DOMAIN[1])                 x = DOMAIN[1];
    }
    
    /**
     * Set this Animal's y. Caps out at RANGE values.
     *
     * @param num           The number to set y to.
     * @see                 #RANGE
     */
    private void setY(int num)
    {
        if(num >= RANGE[0] && num <= RANGE[1]) y = num;
        else if(num < RANGE[0])                y = RANGE[0];
        else if(num > RANGE[1])                y = RANGE[1];
    }
    
    /**
     * Set this Animal's maxspd. Caps out at MAX_SPD_RANGE values.
     *
     * @param num           The number to set maxspd to.
     * @see                 #MAX_SPD_RANGE
     */
    private void setMaxSpd(int num)
    {
        if(num >= MAX_SPD_RANGE[0] &&
           num <= MAX_SPD_RANGE[1])     maxspd = num;
        else if(num < MAX_SPD_RANGE[0]) maxspd = MAX_SPD_RANGE[0];
        else if(num > MAX_SPD_RANGE[1]) maxspd = MAX_SPD_RANGE[1];
    }
    
    /**
     * Set this animal's fighting status : true or false.
     *
     * @param status        The new fighting status passed <code>true</code>
     *                      or <code>false</code>.
     * @see                 #isfighting
     */
    public void setFighting(boolean status) { isfighting = status; }
    
    /**
     * Returns Animal's name and [x, y] coordinates as a string.
     *
     * @return              name [x, y]
     */
    public String toString()    { return name + " [" + x + ", " + y + "]"; }
    /**
     * @return              name
     */
    public String getName()     { return name; }
    /**
     * @return              x
     */
    public int getX()           { return x; }
    /**
     * @return              y
     */
    public int getY()           { return y; }
    /**
     * @return              isfighting
     */
    public boolean isFighting() { return isfighting; }
}