/* 
 * ArrayBag.java
 * 
 * Computer Science 112, Boston University
 */

import java.util.*;

/*
 * An implementation of a bag data structure using an array.
 */
public class LLBag implements Bag {
    // Inner class for a node.  We use an inner class so that the LLList
    // methods can access the instance variables of the nodes.
    private class Node {
        private Object item;
        private Node next;
        
        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }
    
    // fields of the LLList object
    private Node head;     // dummy head node
    private int length;    // # of items in the list
    private int numItems;
  
    
    /*
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public LLBag() {
        head = new Node(null, null);
        length = 0;
    }
    
    /* 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public LLBag(Object[] initItems) {
        head = new Node(null, null);
        
        Node prevNode = head;
        for (int i = 0; i < initItems.length; i++) {
            Node nextNode = new Node(initItems[i], null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }
        numItems = initItems.length;
        length = initItems.length;
    }
    
    /*
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return numItems;
    }

    private Node getNode(int i) {
        Node trav = head;
        int travIndex = -1;
        
        while (travIndex < i) {
            travIndex++;
            trav = trav.next;
        }
        
        return trav;
    }
    
    /* 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } 
        else {
            Node newNode = new Node(item, null);
            Node prevNode = head;       
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            
            length++;
            numItems++;
            return true;
        }
    }
    
    /* 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        if (item == null){
            throw new IllegalArgumentException("item must be non-null");
        }
        Node trav = head.next;
        Node prev = head;
        while (trav != null){
            if (trav.item.equals(item)){
                numItems--;
                length--;
                prev.next = trav.next;
                return true;
            }
            prev = trav;
            trav = trav.next;
        }
        
        return false;  // item not found
        }
        
        
    

    
    /*
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        if (item == null){
            throw new IllegalArgumentException("item must be non-null");
        }
        Node trav = head.next;
        while (trav != null){
            if (item.equals(trav.item)){
                return true;
            }
            trav = trav.next;
        }

        
        return false;
    }
    
    /*
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * numItems);
        Node grabbed = getNode(whichOne);
        return grabbed.item;
    }
    
    /*
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[numItems];
        Node trav = head.next;
        int dex = 0;
        while(trav != null) {
            copy[dex] = trav.item;
            dex ++;
            trav = trav.next;
        }
        
        return copy;
    }
    
    /*
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        Node trav = head.next;
        while (trav.next != null) {
            str = str + trav.item;
            str += ", ";
            trav = trav.next;
        }
        str += trav.item;
        
        str = str + "}";
        return str;
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        // System.out.print("number of items in bag 1: ");
        // int numItems = scan.nextInt();
        // Bag bag1 = new ArrayBag(numItems);
        // scan.nextLine();    // consume the rest of the line
        
        // // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        // for (int i = 0; i < numItems; i++) {
        //     System.out.print("item " + i + ": ");
        //     itemStr = scan.nextLine();
        //     bag1.add(itemStr);
        // }
        // System.out.println("bag 1 = " + bag1);
        // System.out.println();
        Object[] test = {"1","2","3","4","5","6"};
        Bag bag1 = new LLBag(test);
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        // bag1.contains(itemStr);
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.print(bag1.numItems());
        System.out.println();
    }
}
