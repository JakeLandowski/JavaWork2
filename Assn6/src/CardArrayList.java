//	Jacob Landowski, CS 145, Spring 2017, Section A, #2723
//	Programming Assignment #6, 5/20/17
//	Class   => CardArrayList
//  Used By => CardArrayMaster
//  Needs   => Card
//
//  Hosted Javadocs => http://jlandowski.greenrivertech.net/Javadocs/IT145/assn6/


// Is it bad to create a private helper method that checks my bounds and throws
// if I am checking bounds multiple times? Or is that bad practice for exceptions?

import java.util.Arrays;

/**
 * ..
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                    1.0
 */
public class CardArrayList
{
    private Card[] internalArray;
    private int size;

//=================================================================
//-------------------------CONSTRUCTORS----------------------------
//=================================================================

    /**
     * Default Constructor, sets initial capacity of CardArrayList to 10 and size to 0.
     */
    public CardArrayList()
    {
        internalArray = new Card[10];
        size = 0;
    }
    
    /**
     * Constructor, sets initial capacity of CardArrayList to argument passed and size to 0.
     *
     * @param capacity          The intial size of the CardArrayList
     * @throws                  IllegalArgumentException if capacity &lt; 1
     * @pre                     Capacity specified must be &gt; 0
     * @post                    Creates a CardArrayList with an initial capacity specified
     */
    public CardArrayList(int capacity)
    {
        if(capacity < 1) throw new IllegalArgumentException();
        
        internalArray = new Card[capacity];
        size = 0;
    }
    
//=================================================================
//---------------------------GETTERS-------------------------------
//=================================================================
    
    /**
     * Returns the current number of CardArrayList elements.
     *
     * @return                  size
     */
    public int size() { return size; }

//=================================================================
//-----------------------JAVA-UTILITIES----------------------------
//=================================================================
    
    /**
     * Returns a String representation of the CardArrayList prefixed with 0 and postfixed with
     * current size. Ex: [0: [2/3], [4/10], {{10/10}}, :3]
     *
     * @return                  String representing the CardArrayList of Cards
     */
    public String toString()
    {                           //  ROUGH OVER-ESTIMATATION OF CHARS NEEDED
        StringBuilder str = new StringBuilder(size * 13 + 8);
        String end = (size > 0) ? internalArray[size - 1] + " :" : " :";
        
        str.append("[0: ");
        
        for(int i = 0; i < size - 1; i++)
            str.append(internalArray[i] + ", ");
        
        str.append(end + size + "]");
        
        return str.toString();
    }

//=================================================================
//----------------------------GENERAL------------------------------
//=================================================================
    
    /**
     * Adds a Card object to the end of the CardArrayList
     *
     * @param card              The Card object to add
     */
    public void add(Card card)
    {
        if( isRoom() )
        {
            internalArray[size] = card;
            size++;
        }
        else
        {
            expand();
            add(card);
        }
    }
    
    /**
     * Removes and returns the last Card object in the CardArrayList
     *
     * @return                  Card object
     * @throws                  IllegalStateException if size &lt; 1
     * @pre                     CardArrayList must have atleast one element to remove
     * @post                    Removes and returns the last Card object
     */
    public Card remove()
    {
        if(size < 1) throw new IllegalStateException("CardArrayList has nothing to remove");
        
        Card last = internalArray[size - 1];
        size--;
        
        return last;
    }
    
    /**
     * Returns the Card object found at given index
     *
     * @param index             The index from which to return the Card Object
     * @return                  Card object
     * @throws                  IndexOutOfBoundsException if index &lt; 0 or index &gt;= size
     * @pre                     Index given must be within current CardArrayList bounds
     * @post                    Returns the Card object found at index
     */
    public Card get(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        
        return internalArray[index];
    }
    
    /**
     * Returns the index of the first Card object equal to the Card given
     *
     * @param card              The Card object to search for
     * @return                  The index of the Card
     * @post                    Returns the Card index or -1 if not found
     */
    public int indexOf(Card card)
    {
        for(int i = 0; i < size; i++)
        {
            if(internalArray[i].compareTo(card) == 0) return i;
        }
        
        return -1;
    }
    
    /**
     * Inserts the given Card object at the specified index location, shifting over all Card
     * objects.
     *
     * @param index             The index to insert the Card object
     * @param card              The Card object to insert
     * @throws                  IndexOutOfBoundsException if index &lt; 0 or index &gt;= size
     * @pre                     Index given must be within current CardArrayList bounds
     * @post                    Inserts the Card Object at given index
     */
    public void add(int index, Card card)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if( isRoom() )
        {
            for(int i = size; i > index; i--)
                internalArray[i] = internalArray[i - 1];
                
            internalArray[index] = card;
            size++;
        }
        else
        {
            expand();
            add(index, card);
        }
    }
    
    /**
     * Removes and returns the Card object at the given index, and shifts all other Cards down.
     *
     * @param index             The index at which to remove the Card object
     * @return                  The Card Object at given index
     * @throws                  IndexOutOfBoundsException if index &lt; 0 or index &gt;= size
     * @pre                     Index given must be within current CardArrayList bounds
     * @post                    Returns the Card Object at given index
     */
    public Card remove(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
        
        Card card = internalArray[index];
        
        for(int i = index; i < size - 1; i++)
            internalArray[i] = internalArray[i + 1];
            
        size--;
        return card;
    }
    
    /**
     * Sorts the Card objects in descending order.
     */
    public void sort()
    {
        Arrays.sort(internalArray, 0, size);
        reverse();
    }
    
    /**
     * Randomly shuffles the Cards in the CardArrayList.
     */
    public void shuffle()
    {
        int firstIndex;
        int secondIndex;
        
            //  GUARANTEE FIRST CARD SWAPS
        firstIndex  = 0;
        secondIndex = Card.randomInt(1, size - 1);
        swap(firstIndex, secondIndex);
        
            //  GUARANTEE LAST CARD SWAPS
        firstIndex  = size - 1;
        secondIndex = Card.randomInt(0, size - 2);
        swap(firstIndex, secondIndex);
        
            //  SWAP ALL THE THINGS
        for(int i = 0; i < size * 5; i++)
        {
            firstIndex = Card.randomInt(0, size - 1);
            
            do secondIndex = Card.randomInt(0, size - 1);
                while(firstIndex == secondIndex);
                
            swap(firstIndex, secondIndex);
        }
    }
    
    /**
     * Clear the CardArrayList, setting size to 0 and capacity to 10.
     */
    public void clear()
    {
        internalArray = new Card[10];
        size = 0;
    }
    
//=================================================================
//------------------------PRIVATE-HELPERS--------------------------
//=================================================================

    private boolean isRoom() { return internalArray.length > size; }
    
    private void expand()
    {
        Card[] newArray = new Card[internalArray.length * 2];
        
        for(int i = 0; i < size; i++)
            newArray[i] = internalArray[i];
            
        internalArray = newArray;
    }
    
    private void swap(int a, int b)
    {
               Card temp = internalArray[b];
        internalArray[b] = internalArray[a];
        internalArray[a] = temp;
    }
    
    private void reverse()
    {
        Card temp;
        int last = size - 1;
        
        for(int i = 0; i < size / 2; i++)
            swap(i, last - i);
    }
    
//=================================================================
//-----------------------------DEBUG-------------------------------
//=================================================================
    
    /*public void testExpand()
    {
        System.out.println("Internal array's length : " + internalArray.length);
    }*/
} // END CLASS