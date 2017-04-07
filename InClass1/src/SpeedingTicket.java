public class SpeedingTicket implements Ticket
{
    int carspd;
    int spdlimit;
    
    public SpeedingTicket()
    {
        this(0, 0);
    }
    
    public SpeedingTicket(int carspd, int spdlimit)
    {
        this.carspd = carspd;
        this.spdlimit = spdlimit;
    }
    
    public void printMe()
    {
        System.out.println("This speeding Ticket was $" + getFine() + ".");
    }
    
    public int getFine()
    {
        int excess = this.carspd - this.spdlimit;
        
        if(excess > 0) return excess * excess;
        else return 0;
    }
}