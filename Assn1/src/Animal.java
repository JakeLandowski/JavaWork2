import java.util.Random

public class Animal
{
    private final Random rand = new Random();
    private final int[] DOMAIN = { -20, 20 };
    private final int[] RANGE = { -20, 20 };
    private final int[] MAX_SPD_RANGE = { 2, 5 };
    private String name;
    private int x, y;       //  -20 <--> 20
    private double maxspd; //   2 <--> 5
    
    public Animal()
    {
        name = "unknown";
        x = y = 0;
        maxspd = 4;
    }
    
    public Animal(int initX, int initY, String initName, int initMaxSpd)
    {
        name = initName;
        setX(initX);
        setY(initY);
        maxspd = setMaxSpd(initMaxSpd);
    }
    
    public void move()
    {
        int dir = rand.nextInt(1, 5);
        int dist = rand.nextInt(1, maxspd+1);
        
        switch(dir)
        {
            case 1:
                setX(-dist);
                break;
            case 2:
                setX(dist);
                break;
            case 3:
                setY(-dist);
                break;
            case 4:
                setY(dist);
                break;
        }
    }
    
    public boolean touching(Animal other)
    {
        if(x == other.getX() && y == other.getY()) return true;
        else return false;
    }
    
    private void setX(int num)
    {
        if(num >= DOMAIN[0] && num <= DOMAIN[1]) x = num;
        else if(num < DOMAIN[0])                 x = DOMAIN[0];
        else if(num > DOMAIN[1])                 x = DOMAIN[1];
    }
    
    private void setY(int num)
    {
        if(num >= RANGE[0] && num <= RANGE[1]) y = num;
        else if(num < RANGE[0])                y = RANGE[0];
        else if(num > RANGE[1])                y = RANGE[1];
    }
    
    private void setMaxSpd(int num)
    {
        if(num >= MAX_SPD_RANGE[0] &&
           num <= MAX_SPD_RANGE[1])     maxspd = num;
        else if(num < MAX_SPD_RANGE[0]) maxspd = MAX_SPD_RANGE[0];
        else if(num > MAX_SPD_RANGE[1]) maxpsd = MAX_SPD_RANGE[1];
    }
    
    public String toString() { return name + " [" + x + ", " + y + "]" ; }
    public int getX()        { return x; }
    public int getY()        { return y; }
}