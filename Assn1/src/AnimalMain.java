//	Jacob Landowski, CS145, Spring 2017, Section 2723
//	Programming Assignment #1, 4/5/17

import java.util.Random;

public class AnimalMain
{
    private final int NUM_ANIMALS = 5;
    private final int ANIMAL_NAMES = { "Zebra", "Giraffe", "Gazelle", "Lion", "Hyena" };
    
    public static void main(String args[])
    {
        Animal[] animals = new Animal[NUM_ANIMALS];
        Random rand = new Random();
        populate(animals, rand);
    }
    
    private void populate(Animal[] arr, Random rand)
    {
        for(int i = 0; i < arr; i++)
        {
            int x = rand.nextInt(-20, 21);
            int y = rand.nextInt(-20, 21);
            int spd = rand.nextInt(2, 6);
            
            arr[i] = new Animal(ANIMAL_NAMES[i], x, y, spd);
        }
    }
}