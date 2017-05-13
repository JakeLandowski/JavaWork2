/**
 * Jacob Landowski
 * CS145 : Spring 2017 : Section 2723
 * Inclass Assignment WeeK #6 : 5/2/17
 * Stacks and Queues
 *
 * This program hsa 2 methods to modify a queue of values.
 * Stutter method makes a copy of each value in a queue :
 *      [1, 2, 3] => [1, 1, 2, 2, 3, 3]
 *
 * Mirror method appends a reversed copy of a queue to itself :
 *      [a, b, c] => [a, b, c, c, b, a]
 *
 */
 
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Stack;
 
 public class StackQueuePractice
 {
    public static void main(String[] args)
    {
        Queue<Integer> nums = new LinkedList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        
        System.out.println("Stutter:");
        System.out.println("Old queue : " + nums);
        stutter(nums);
        System.out.println("New queue : " + nums);
        
        System.out.println("====================================");
        
        Queue<String> words = new LinkedList<String>();
        words.add("a");
        words.add("b");
        words.add("c");
        
        System.out.println("Mirror:");
        System.out.println("Old queue : " + words);
        mirror(words);
        System.out.println("New queue : " + words);
    }
    
    private static void stutter(Queue<Integer> nums)
    {
            //  ISOLATE LENGTH NECESSARY
        int len = nums.size();
        
        for(int i = 0; i < len; i++)
        {
                //  ADD COPY OF FRONT TO BACK
                //  [1, 2, 3] => [1, 2, 3, 1]
            nums.add( nums.peek() );
            
                //  MOVE FRONT TO BACK
                //  [1, 2, 3, 1] => [2, 3, 1, 1]
            nums.add( nums.remove() );
        }
    }
    
    private static void mirror(Queue<String> words)
    {
            //  ISOLATE LENGTH NECESSARY
        int len = words.size();
        
        Stack<String> tempStack = new Stack<String>();
        Queue<String> tempQueue = new LinkedList<String>();
        
            //  COPY FRONT TO STACK | MOVE FRONT TO NEW QUEUE
            //  [a, b]   => stack[a] queue[a]
            //  [b]      => stack[a, b] queue[a, b]
            //  []         => stack[a, b, c] queue[a, b, c]
        for(int i = 0; i < len; i++)
        {
            tempStack.push( words.peek() );
            tempQueue.add( words.remove() );
        }
        
            //  DUMP TEMP QUEUE TO MAIN QUEUE
            //  temp[a, b, c] => [a, b, c]
        for(int i = 0; i < len; i++)
        {
            words.add( tempQueue.remove() );
        }
        
            //  DUMP TEMP STACK TO MAIN QUEUE
            //  temp[a, b, c] => [a, b, c, c, b, a]
        for(int i = 0; i < len; i++)
        {
            words.add( tempStack.pop() );
        }
        
    }
    
    
    
    
    
 }