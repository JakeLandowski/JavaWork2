// CS145 Jacob Landowski
// In Class program #2
// Array List Practice
// Created method to swap each pair of values in the array list,
// excluding the last if it is alone.

import java.util.*;

public class CS145InClassArrayLists
{
  public static void main(String[] args)
  {
    ArrayList<String> myList = new ArrayList<String>();
    
    myList.add("Four");
    myList.add("score");
    myList.add("and");
    myList.add("seven");
    myList.add("years");
    myList.add("ago.");

    System.out.print("Before: ");
    System.out.println(myList);
    

    swapPairs(myList);
    
    System.out.print("After:  ");
    System.out.println(myList);

    // Round 2 - Odd
    System.out.println();
    System.out.println("***********");
    System.out.println();
    
    myList.clear();
    
    myList.add("1");
    myList.add("2");
    myList.add("3");
    myList.add("4");
    myList.add("5");
    myList.add("6");
    myList.add("7");
    myList.add("8");
    myList.add("9");

    System.out.print("Before: ");
    System.out.println(myList);
    

    swapPairs(myList);
    
    System.out.print("After:  ");
    System.out.println(myList);

  }
  
  public static void swapPairs(ArrayList<String> list)
  {
      for(int i = 0; i < list.size() - 1; i+=2) // DONT CHECK LAST SPOT UNNECESSARY
      {                                         // COUNT BY 2
          String temp = list.get(i);    // STORE LEFT STRING
          list.set(i, list.get(i + 1)); // SET LEFT TO RIGHT
          list.set(i + 1, temp);        // SET RIGHT TO STORED LEFT
      }
  }
  
}
    
    
    
    