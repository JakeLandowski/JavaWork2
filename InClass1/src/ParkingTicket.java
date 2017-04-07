public class ParkingTicket implements Ticket
{
    int fine;
    
    public ParkingTicket()
    {
        this(0);
    }
    
    public ParkingTicket(int fine)
    {
        this.fine = fine;
    }
    
    public void printMe()
    {
        System.out.println("This parking Ticket was $" + this.fine + ".");
    }
    
    public int getFine()
    {
        return this.fine;
    }
}