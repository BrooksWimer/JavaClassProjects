/*
 * Student First Name: Brooks
 * Student Last Name: Wimer
 * Student BU Number: u39996374
 * Purpose: a program that creates a set and performs operations on them
 */

public class Set  {
    private static final int SIZE = 10; // default size of initial set
                                
    private int[] set;      // array referece to the set
    private int size;       // current size of the set
    private int next;       // index to next available slot in the set array
    
    
    public Set() {
        this.set = new int[SIZE];
        this.size = SIZE;
        this.next = 0;
    }
 
 
    public Set(int[] arr) {
        this.set = new int[SIZE];
        this.size = SIZE;
        if (arr.length > size){
            resize();
        }
        next = 0;
        if (arr.length > 0){
            for (int i = 0; i < arr.length; i ++){
                boolean contains = false;
                for (int x = 0; x < next+1; x ++){
                    if (arr[i] == this.set[x]){
                        contains = true;
                    }
                }
                if (!contains){
                    this.set[next] = arr[i];
                    next++;
                }
            }
        }
    }

    public Set clone() {
        Set copySet = new Set(this.set);
        return copySet;
    }
  
    public void insert(int k) {
        if (!this.member(k) || this.isEmpty()){
            if (next >= this.set.length){
                this.resize();
            }
            this.set[next] = k;
            next++;
        }
    }


    /** 
     * This method reallocates the array set to twice as 
     * big and copies all the elements over.
     * This method is called only by the insert method
     * when it cannot insert another element to the set.
     *
     * Note that this is the reason that in this class
     * the member size is not a class variable (i.e. static)
     * and it is not final, because the set can grow and size
     * will be modified accordingly.
     */
    
    private void resize() {
        size *= 2;

	// Create a new array double the size
        int[] temp = new int[size];

	// Copy all the elements from the current set
	// to the new set
        for(int i = 0; i < set.length; ++i) {
            temp[i] = set[i];
        }

	// Assign to the set reference the newly
	// resized array that represents the set.
        set = temp;
    }


 
    public  String toString()  {
        String str = "[";
        for (int i = 0; i < next; i++){
            str += this.set[i];
            if (i != next -1){
                str += ",";
            }
        }
        str += "]";
        return str;
    } 
    

    public int cardinality() {
        return this.next;
    }

    public  boolean isEmpty() {
        if (next == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean member(int k) {
        boolean bool = false;
        for (int i = 0; i < this.next; i++){
            if (this.set[i] == k){
                bool = true;
            }
        }
        return bool;
    }    

    public  boolean subset(Set S) {
        boolean bool = true;
        if (this.isEmpty()){
            return bool;
        }
        for (int i = 0; i < this.next; i++){
                if (!S.member(this.set[i])){
                    bool = false;
                }
            }
        return bool;
    }

    public boolean equal(Set S) {
        boolean bool = true;
        for (int i = 0; i < this.next; i++){
            if (!this.member(S.set[i])){
                bool = false;
            }
        }
        return bool;
    }
                     

    public void delete(int k) {
        if (this.member(k)){
            int dex = 0;
            for (int i = 0; i < this.next; i++){
                if (this.set[i] == k){
                    dex = i;
                }
            }
            for (int i = dex; i < this.next; i++){
                this.set[i] = this.set[i+1];
            }
            next --;
        }
    }
  
    public Set union(Set S) {
        int[] arr1 = new int[this.next + S.next];
        for (int i = 0; i < this.next; i++){
            arr1[i] = this.set[i];
        }
        for (int i = 0; i < S.next; i++){
            arr1[i+next] = S.set[i];
        }
        Set unionSet = new Set(arr1);
        return unionSet;
    }
    
    public Set intersection(Set S) {
        int [] arr = new int[this.next + S.next];
        int arrIndex = 0;
        for (int i = 0; i < this.next; i ++){
            if (S.member(this.set[i])){
                arr[arrIndex] = this.set[i];
                arrIndex++;
            }
        }
        return new Set(arr);
    }
    
    public Set setdifference(Set S) {
        int [] arr = new int[this.next];
        int arrIndex = 0;
        for (int i = 0; i < this.next; i ++){
            if (!S.member(this.set[i])){
                arr[arrIndex] = this.set[i];
                arrIndex++;
            }
        }
        return new Set(arr);
    }

    public int[] getSet(){
        return this.set;
    }

    public int getSize(){
        return this.size;
    }

    public int getNext(){
        return this.next;
    }
     

}
