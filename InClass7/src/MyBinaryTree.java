import java.util.NoSuchElementException;

/****************************************************
 * Main Class MyBinaryTree
 ****************************************************/

public class MyBinaryTree<E extends Comparable<E> >
{


       /****************************************************
        * Helper Class TreeNode
        * Note that this is a class inside the tree
        ****************************************************/
        private class TreeNode<E>
        {
          public E payload;
          public TreeNode<E> left;
          public TreeNode<E> right;
          
          public TreeNode (E data)
          {
            payload = data;
          }
        }


  // Root of the Main tree
  private TreeNode<E> root;
  
  /************************************
   * Constructor (Default)
   ************************************/
   
  public MyBinaryTree()
  {
    root = null;
  }
  
  /*****************************************************
   * toString Method (starter method)
   *****************************************************/
 
  public String toString()
  {
    if (root == null)
    {
      return "";
    }
    
    return toString(root);
   }

  /*****************************************************
   * toString Method (recursive method)
   *****************************************************/
  private String toString(TreeNode<E> tempRoot)
  {
     if (tempRoot == null)  return "";
     
     return toString(tempRoot.left) + " "
          + tempRoot.payload.toString() + " "
          + toString(tempRoot.right);
          
    
  }
  /*****************************************************
   * printSideways Method (starter method)
   ***************************************************/
public void printSideways()
{
  if (root == null)
  {
     System.out.println("Null Tree");
     return;
  }
  printSideways(root,"");
}
  /*****************************************************
   * printSideways Method (recursive method)
   ***************************************************/

private void printSideways(TreeNode<E> tempRoot, String indent)
{
  if (tempRoot == null) return;
  printSideways(tempRoot.right,"    "+indent);
  System.out.println(indent + tempRoot.payload.toString() );
  printSideways(tempRoot.left,"    "+indent);
}
  
  
  
  /*****************************************************
   * add Method (starter method)
   ***************************************************/
  public void add(E data)
  {
    root = add(root, data);
  }
  
  /*****************************************************
   * printSideways Method (recursive method)
   ***************************************************/

  
  private TreeNode<E> add(TreeNode<E> tempRoot, E data)
  {
      if (tempRoot == null)
      {
        TreeNode<E> temp = new TreeNode<E>(data);
        return temp;
      }
      
      if (tempRoot.payload.compareTo(data) > 0)
      {
        tempRoot.left = add(tempRoot.left,data);
      }
      if (tempRoot.payload.compareTo(data) < 0)
      {
        tempRoot.right = add(tempRoot.right,data);
      }
      return tempRoot;
  }
  /*****************************************************
   * contains Method (starter method)
   ***************************************************/
  public boolean contains(E lookFor)
  {
    return contains(lookFor, root);
  }
 
  /*****************************************************
   * contains Method (recursive method)
   ***************************************************/

  public boolean contains(E lookFor, TreeNode<E> temproot)
  {
    if(temproot == null) return false;
    
    int compare = lookFor.compareTo(temproot.payload);
    
    if(compare == 0) return true;
    
    return (compare < 0) ? contains(lookFor, temproot.left) : contains(lookFor, temproot.right);
  }

  /*****************************************************
   * getMin Method (starter method)
   ***************************************************/
  public E getMin()
  {
      return getMin(root);
  }
 
  /*****************************************************
   * remove Method (recursive method)
   ***************************************************/

  public E getMin(TreeNode<E> temproot)
  {
      if(temproot.left == null) return temproot.payload;
      
      return getMin(temproot.left);
  }

  /*****************************************************
   * remove Method (starter method)
   ***************************************************/
  public void remove(E lookFor)
  {
      root = remove(lookFor, root);
  }
 
  /*****************************************************
   * remove Method (recursive method)
   ***************************************************/

  public TreeNode<E> remove(E lookFor, TreeNode<E> temproot)
  {
      if(temproot.payload == lookFor)
      {
                // if leaf remove
          if(temproot.left == null && temproot.right == null) return null;
          
                // if both children
          if(temproot.left != null && temproot.right != null)
          {
              temproot.payload = getMin(temproot.right);
              temproot.right = remove(temproot.payload, temproot.right);
              return temproot;
          }
          
          return (temproot.left == null) ? temproot.right : temproot.left;
      }
      
    if(lookFor.compareTo(temproot.payload) < 0)
        temproot.left = remove(lookFor, temproot.left);
    else
        temproot.right = remove(lookFor, temproot.right);
        
    return temproot;
  }
  
  /*****************************************************
   * size Method (starter method)
   ***************************************************/
  public int size()
  {
      return size(root);
  }
 
  /*****************************************************
   * size Method (recursive method)
   ***************************************************/

  public int size(TreeNode<E> temproot)
  {
      int count = 0;
      
      if(temproot == null) return 0;
      if(temproot.left != null) count += size(temproot.left);
      if(temproot.right != null) count += size(temproot.right);
     
      return count + 1;
  }
     
}


