//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #1, 4/5/17

import java.util.Random;

/**
 * <p>This class utilizes the Animal class and creates an Array of them.
 * The amount is determined by <code>NUM_ANIMALS</code>. The animal names
 * are pulled from the <code>ANIMAL_NAMES</code> array and the amount of names
 * must match the number of animals in <code>NUM_ANIMALS</code>.</p>
 *
 * <p>Main prints each Animal to the console, and then starts a loop
 * which runs a set amount of times specified by <code>SCENARIO_LENGTH</code>.
 * This loop moves each animal, then checks their locations to see who are
 * touching and will print their fight to the console. It finishes by printing
 * the animals again to show their final locations</p>
 *
 * @author                  Jacob Landowski
 * @version                 %I% %G%
 * @since                   1.0
 */
public class AnimalMain
{
    private static final int NUM_ANIMALS = 5;
    private static final String[] ANIMAL_NAMES = { "Zebra", "Giraffe", "Gazelle", "Lion", "Hyena" };
    private static final int SCENARIO_LENGTH = 25;
    
    /**
     * <p>Main method.</p>
     * <ul>
     * <li>Creates an Animal array <code>animals</code></li>
     * <li>Creates an Random object <code>rand</code></li>
     * <li>Calls <code>populate()</code></li>
     * <li>Calls <code>listAnimals()</code></li>
     * <li>Calls <code>startScenario()</code></li>
     * <li>Calls <code>printAnimals()</code></li>
     * </ul>
     *
     * @param args          String array of options when starting program
     * @see                 Animal
     * @see                 #populate(Animal[], Random)
     * @see                 #listAnimals(Animal[])
     * @see                 #startScenario(Animal[])
     * @see                 #printAnimals(Animal[])
     */
    public static void main(String args[])
    {
        Animal[] animals = new Animal[NUM_ANIMALS];
        Random rand = new Random();
        
        populate(animals, rand);
        listAnimals(animals);
        startScenario(animals);
        System.out.println("The final locations are...\n");
        printAnimals(animals);
    }
    
    /**
     * Populate the animals array with new animals with random
     * locations (-20 <=> 20) and speeds (2 <=> 5).
     *
     * @param arr           the empty Animal array
     * @param rand          the Random object
     * @see                 Animal
     * @see                 Random
     */
    private static void populate(Animal[] arr, Random rand)
    {
        for(int i = 0; i < arr.length; i++)
        {
            int x = rand.nextInt(41) - 20;
            int y = rand.nextInt(41) - 20;
            int spd = rand.nextInt(4) + 2;
            
            arr[i] = new Animal(ANIMAL_NAMES[i], x, y, spd);
        }
    }
    
    /**
     * Loop through the Animal array and print the animals.
     *
     * @param animals       the populated Animal array
     * @see                 {@link Animal}
     */
    private static void listAnimals(Animal[] animals)
    {
        for(int i = 0; i < animals.length; i++)
        {
            System.out.println("A new animal joins the field: " + animals[i]);
            pause(150);
        }
    }
    
    /**
     * Start the movement/fight loop. Moves animals then checks for fights.
     * printing a line of asterisks per iteration.
     *
     * @param animals       the populated Animal array
     * @see                 {@link Animal}
     * @see                 {@link #moveAnimals()}
     * @see                 {@link #checkFights()}
     */
    private static void startScenario(Animal[] animals)
    {
        String line = "*********************";
        
        System.out.println(line);
        
        for(int i = 0; i < SCENARIO_LENGTH; i++)
        {
            moveAnimals(animals);
            checkFights(animals);
            System.out.println(line);
            pause(150);
        }
    }
    
    /**
     * Loop through the Animal array and call the move() method
     * on each one. Prints movement feedback after moving all animals.
     *
     * @param animals       the populated Animal array
     * @see                 {@link Animal#move()}
     */
    private static void moveAnimals(Animal[] animals)
    {
        for(int i = 0; i < animals.length; i++) animals[i].move();
        System.out.println("Animals moving...\n");
        //printAnimals(animals); FOR TESTING
    }
    
    /**
     * Loops through the Animal array, selecting each Animal
     * and then comparing that against each Animal in the
     * array. Does not check if the current Animal <code>isfighting</code>
     * The loop will not check if the indexes are the same so it does not
     * compare the same Animal against itself. It then calls the <code>touching()</code>
     * method on each comparison to check if they are in the same position.
     * If true, they are both set to <code>isfighting = true</code> and their
     * index is saved in the <code>indexes</code> array to mark them as already
     * being in a fight, to prevent being chosen again in the following loop checks.
     * If a <code>fightHappened</code> then the marked Animals have their names
     * appended to the <code>fighting</code> array and <code>printFight()</code>
     * is called.
     *
     * @param animals       the populated Animal array
     * @see                 {@link Animal#isfighting}
     * @see                 {@link Animal#touching()}
     * @see                 {@link Animal#getName()}
     * @see                 {@link Animal#setFighting()}
     * @see                 {@link #printFight()}
     */
    private static void checkFights(Animal[] animals)
    {
            //  HOLDS MAX NUM OF ENCOUNTERS POSSIBLE
        String[] fighting = new String[NUM_ANIMALS];
        
        for(int i = 0; i < animals.length; i++)
        {
            Animal ani = animals[i];
            
            if( !ani.isFighting() )
            {
                int numFighting = 0;
                
                    //  KEEPS TRACK OF INDEXES OF ANIMALS PUT IN FIGHTS
                int[] indexes = new int[NUM_ANIMALS];
                boolean fightHappened = false;
                
                for(int j = 0; j < animals.length; j++)
                {
                    Animal other = animals[j];
                    
                    if( (i != j) && !other.isFighting() &&
                        ani.touching(other) )   //  IF NOT ITSELF, AND OTHER NOT IN
                    {                           //  A FIGHT, AND BOTH TOUCHING
                        indexes[numFighting] = j;
                        numFighting++;              //  TRACK OTHER
                        ani.setFighting(true);      //  BOTH SET TO FIGHTING
                        other.setFighting(true);
                        fightHappened = true;
                    }
                }
                    
                if(fightHappened)
                {
                    indexes[numFighting] = i;   //  ALSO TRACK THIS ANIMAL
                    numFighting++;
                    
                    for(int j = 0; j < numFighting; j++)
                    {                   //  GET THE NAME OF EACH ANIMAL BEING TRACKED
                        fighting[j] = animals[indexes[j]].getName();
                    }
                    
                    printFight(fighting, numFighting);
                }
            }
        }
                //  RESET ANIMALS TO NOT FIGHTING
        setAnimalsNotFighting(animals);
    }
    
    /**
     * Prints "FIGHT : " along with the animal names passed in the array
     *
     * @param fighting      The array of names from each Animal involved
     *                      in a single fight
     * @param numFighting   The number of animals in the fight, used for
     *                      looping the correct amount of times
     * @see                 {@link #checkFights()}
     */
    private static void printFight(String[] fighting, int numFighting)
    {
        System.out.print("FIGHT : " + fighting[0]);
        
        for(int i = 1; i < numFighting; i++)
        {
            System.out.print(" vs " + fighting[i]);
        }
            
        System.out.println("\n");
    }
    
    /**
     * Loop through each Animal and mark them as no longer fighting
     *
     * @param animals       The populated Animal array
     * @see                 {@link Animal#isfighting}
     * @see                 {@link Animal#setFighting()}
     */
    private static void setAnimalsNotFighting(Animal[] animals)
    {
        for(int i = 0; i < animals.length; i++) animals[i].setFighting(false);
    }
    
    /**
     * Loop through each Animal and print them.
     *
     * @param animals       The populated Animal array
     */
    private static void printAnimals(Animal[] animals)
    {
        for(int i = 0; i < animals.length; i++)
        {
            System.out.println(animals[i]);
        }
    }
    
    /**
     * Cause the program/thread to sleep for n milliseconds.
     *
     * @param ms        The time in milliseconds to pause the program
     */
    private static void pause(int ms)
    {
        try { Thread.sleep(ms); }
        
        catch(InterruptedException err)
        {
            Thread.currentThread().interrupt();
        }
    }
}