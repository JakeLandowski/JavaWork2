public interface Ticket
{
  public int getFine();
  
  default public void printMe()
  {
    System.out.println("This is a unknown type of ticket");
  }
}