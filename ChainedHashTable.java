/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name:
 *     email:
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
    private LLList keys;
    private int key_iter = 0;

    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    
    public ChainedHashTable(int size){
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        table = new Node[size];
        keys = new LLList();
        numKeys = 0;

    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key);
        Node trav = table[i];
        while (trav != null){
            if (trav.key.equals(key)){
                trav.values.insert(value);
                return true;
            }
            else{
                trav = trav.next;
            }
        }
        Node current = table[i];
        Node insert = new Node(key, value);
        insert.next = current;
        table[i] = insert;
        keys.addItem(insert.key, 0);
        key_iter++;
        numKeys++;

        return true;

    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int i = h1(key);
        
        if (i == -1 || table[i] == null) {
            return null;
        } else {
            Node trav = table[i];
            while (trav != null){
                if (trav.key.equals(key)){
                    
                    return trav.values;
                }
                else{
                    trav = trav.next;
                }
            }
            return null;
        }
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key);
        
        if (i == -1 || table[i] == null) {
            return null;
        } else {
            Node trav = table[i];
            Node prev = null;
            if (trav.key.equals(key)){
                table[i] = trav.next;
                numKeys--;
                return trav.values;
            }
            while (trav != null){
                if (trav.key.equals(key)){
                    prev.next = trav.next;
                    numKeys--;
                    return trav.values;
                }
                else{
                    prev = trav;
                    trav = trav.next;
                }
            }
            return null;
        }
        
    }
    
    
    /*** Add the other required methods here ***/
    
    public int getNumKeys(){
        return numKeys;
    }
    
    public double load(){
        return (double)numKeys / table.length;
    }
    
    public Object[] getAllKeys(){
        Object[] shortened_keys = new Object[key_iter];
        for (int i = 0; i < numKeys; i ++){
            shortened_keys[i] = keys.getItem(i);
        }

        return shortened_keys;
    }

    public void resize(int new_size){
        Node[] temp = table;
        int temp_size = temp.length;
        table = new Node[new_size];
        keys = new LLList();
        key_iter = 0;
        numKeys = 0;
        for (int i = 0; i < temp_size; i ++){
                Node trav = temp[i];
                while (trav != null){
                    while(!(trav.values.isEmpty())){
                        insert(trav.key, trav.values.remove());
                    }
                    trav = trav.next;
                }
                
            }
        }
        
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
        Scanner in = new Scanner(System.in);
        
        System.out.println("Testing insert:");
        System.out.println();
        try {
            System.out.println("what size table would you like? input positive integer:");
            String s = in.nextLine();
            int t = Integer.valueOf(s);
            ChainedHashTable table = new ChainedHashTable(t);
            System.out.println("would you like to insert values? (type 'yes' or 'no'):");
            String y = in.nextLine();
            while (y.equals("yes")){
                System.out.println("Insert key, pair into table. Type your key:");
                String k = in.nextLine();
                System.out.println("Insert cooresponding value:");
                String v = in.nextLine();
                int i = Integer.valueOf(v);
                table.insert(k, i);
                System.out.println("would you like to insert another value? (type 'yes' or 'no')");
                y = in.nextLine();
            }
            System.out.println("your table is:");
            System.out.println(table);
            
        } catch (IllegalArgumentException e) {
                System.out.println("Invalid input, must be positive integer");
            }
            
            System.out.println();
            try {
                System.out.println("what size table would you like? input positive integer:");
                String s = in.nextLine();
                int t = Integer.valueOf(s);
                ChainedHashTable table = new ChainedHashTable(t);
                System.out.println("would you like to insert values? (type 'yes' or 'no'):");
                String y = in.nextLine();
                while (y.equals("yes")){
                    System.out.println("Insert key, pair into table. Type your key:");
                    String k = in.nextLine();
                    System.out.println("Insert cooresponding value:");
                    String v = in.nextLine();
                    int i = Integer.valueOf(v);
                    table.insert(k, i);
                    System.out.println("would you like to insert another value? (type 'yes' or 'no')");
                    y = in.nextLine();
                }
                System.out.println("your table is:");
                System.out.println(table);
                System.out.println("what value would you like to search for? (type key)");
                String k_string = in.nextLine();
                
                System.out.println("these are the cooresponding values: ");
                System.out.println(table.search(k_string));
                
            } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input, must be positive integer");
                }
                System.out.println();
                System.out.println("Testing Remove:");
                System.out.println();
                    try {
                        System.out.println("what size table would you like? input positive integer:");
                        String s = in.nextLine();
                        int t = Integer.valueOf(s);
                        ChainedHashTable table = new ChainedHashTable(t);
                        System.out.println("would you like to insert values? (type 'yes' or 'no'):");
                        String y = in.nextLine();
                        while (y.equals("yes")){
                            System.out.println("Insert key, pair into table. Type your key:");
                            String k = in.nextLine();
                            System.out.println("Insert cooresponding value:");
                            String v = in.nextLine();
                            int i = Integer.valueOf(v);
                            table.insert(k, i);
                            System.out.println("would you like to insert another value? (type 'yes' or 'no')");
                            y = in.nextLine();
                        }
                        System.out.println("your table is:");
                        System.out.println(table);
                        System.out.println("what value would you like to remove from table? (type key)");
                        String k_string = in.nextLine();
                        System.out.println("these are the values you removed:");
                        Queue<Object> removed = table.remove(k_string);
                        System.out.println(removed);
                        System.out.println("this is the table with the values removed: ");
                        System.out.println(table);
                        
                    } catch (IllegalArgumentException e) {
                            System.out.println("Invalid input, must be positive integer");
                        }
                    System.out.println();
                    System.out.println();
                        System.out.println("Testing Resize()/ getNumKeys()/ getAllKeys()/ load():");
                        System.out.println();
                            try {
                                System.out.println("what size table would you like? input positive integer:");
                                String s = in.nextLine();
                                int t = Integer.valueOf(s);
                                ChainedHashTable table = new ChainedHashTable(t);
                                System.out.println("would you like to insert values? (type 'yes' or 'no'):");
                                String y = in.nextLine();
                                while (y.equals("yes")){
                                    System.out.println("Insert key, pair into table. Type your key:");
                                    String k = in.nextLine();
                                    System.out.println("Insert cooresponding value:");
                                    String v = in.nextLine();
                                    int i = Integer.valueOf(v);
                                    table.insert(k, i);
                                    System.out.println("would you like to insert another value? (type 'yes' or 'no')");
                                    y = in.nextLine();
                                }
                                System.out.println("your table is:");
                                System.out.println(table);
                                System.out.println("what size would you like to grow the table to? (type int):");
                                String k_string = in.nextLine();
                                int val = Integer.valueOf(k_string);
                                table.resize(val);
                                System.out.println("this is the grown table: ");
                                System.out.println(table);
                                System.out.println("would you like to get number of keys? (type 'yes' or 'no')");
                                String yn = in.nextLine();
                                if (yn.equals("yes")){
                                    System.out.println("number of keys:");
                                    System.out.println(table.getNumKeys());
                                }
                                System.out.println("would you like to display all the keys? (type 'yes' or 'no')");
                                String ynk = in.nextLine();
                                if (ynk.equals("yes")){
                                    System.out.println("keys:");
                                    System.out.println(Arrays.toString(table.getAllKeys()));
                                }
                                System.out.println("would you like to see the load factor of the hash function? (type 'yes' or 'no')");
                                String ynl = in.nextLine();
                                if (ynl.equals("yes")){
                                    System.out.println("load factor:");
                                    System.out.println(table.load());
                                }
                            } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid input, must be positive integer");
                                }
    
    }
}
