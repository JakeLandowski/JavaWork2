public class InClassAssignment1
{
 public static void main(String[] args)
 {
    Ticket[] docket = new Ticket[10];
    docket[0] = new ParkingTicket(100);
    docket[1] = new ParkingTicket(100);
    docket[2] = new SpeedingTicket(75, 55);
    docket[3] = new SpeedingTicket(45, 40);
    docket[4] = new ParkingTicket(80);
    docket[5] = new SpeedingTicket(35, 25);
    docket[6] = new SpeedingTicket(75, 44);
    docket[7] = new ParkingTicket(90);
    docket[8] = new ParkingTicket(75);
    docket[9] = new SpeedingTicket(25, 26);
    
    for (int i = 0; i < docket.length; i++)
    {
      docket[i].printMe();
    }
    
    int total = 0;
    for (int i = 0; i < docket.length; i++)
    {
      total += docket[i].getFine();
    }
    System.out.println("*********************");
    System.out.printf("The total of the fines is $%d.", total);
    
 }
}