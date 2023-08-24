/* File: MyArrays
 * Date: Fall 2022
 * Author:  CS1112 
 * Purpose:  This class allows the user to input array values and compute statistics
 * Brooks Wimer
 * bwimer@bu.edu
 */

import java.util.*;                    

public class MyArray  {

    private static final int SENTINEL = -999;  // Sentinel value used to indicate end of input
    private static final int DEFAULT_SIZE = 20;  // the default size to use if one is not specified
    private int[] arr; // Data member to reference an array of integers
    private int numElements; // the number of elements entered into the array
    private int sum; // represents the sum of all the elements in the array
    private int min;// represents the minimum value of all elements in the array
    private int max; // represents the maximum value of all the elements in the array
    private double avg; // represents the average of all the elements in the array
    private int med; // represents the median value of all the elements in the array

// CONSTRUCTORS
    // Initializes a MyArray object using default members
    public MyArray() {
       arr = new int[DEFAULT_SIZE];
       numElements = 0;
    }
    // Initializes a MyArray object of length n
    public MyArray( int n ) {
        arr = new int [n];
        numElements = 0;
    
    }
    // Initializes a MyArray object that is a copy of the input array
    public MyArray( int[] arr ) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            this.arr[i] = arr[i];
        }
        numElements = arr.length;
        this.computeStatistics();
    
    }
    // allows the user to input up to 20 numbers for a MyArray object
    public void inputElements() {
        this.arr = new int[this.arr.length];
        Scanner userInput = new Scanner( System.in );
        System.out.println("Enter up to " + arr.length + " integers and " + SENTINEL + " to end user input: ");
        int num;
        int count = 0;
        num = userInput.nextInt();
        while ((num != SENTINEL) && (count < arr.length)){
            this.arr[count] = num;
            count++;
            num = userInput.nextInt();
        }
        numElements = count;
        this.computeStatistics();
    }

    // creates and returns a string representation of the array
    public String toString() {
        int[] arr2 = new int[this.numElements];
        for (int i = 0; i < numElements; i ++){
            arr2[i] = arr[i];
        }
        return Arrays.toString(arr2);
    
    }

    // computes statistics on the array
    public void computeStatistics() {
        sum = 0;
        max = arr[0];
        min = arr[0];
        for (int i = 0; i < numElements; i ++){
            sum += arr[i];
            if (max < arr[i]){
                max = arr[i];
            }
            if (min > arr[i]){
                min = arr[i];
            }
        }

        
        med = arr[numElements/2];
        
        avg = (double)sum / (double)numElements;
    
    }

    public int numOccurences(int n) {
        int occ = 0;
        for (int i= 0; i < numElements; i ++){
            if (arr[i] == n){
                occ++;
            }
        }
        return occ;
    
    }
    public boolean insert(int n, int position) {
        if ((position >= 0 && position <= numElements) && (numElements < this.arr.length)){
            MyArray shifted = new MyArray(this.arr.length);
            for (int i = 0; i < this.arr.length; i++){
                if (i < position){
                    shifted.arr[i] = this.arr[i];
                }
                else if (i == position){
                    shifted.arr[i] = n;
                }
                else if (i > position){
                    shifted.arr[i] = this.arr[i-1];
                }
            }
            numElements++;
            this.arr = shifted.arr;
            this.computeStatistics();
            return true;
        }
        else{
            return false;
        }
    
    }


    public boolean remove(int position) {
        if (position < 0 || position > numElements){
            return false;
        }
        else{
            for (int i = position; i < numElements; i++){
                this.arr[i] = this.arr[i+1];
            }
            numElements--;
            this.computeStatistics();
            return true;
        }

    }
    public boolean grow(int n) {
        if (n < 0){
            return false;
        }
        else{
            int[] newarr = new int[arr.length +n];
            for (int i = 0; i < numElements; i++){
                newarr[i] = arr[i];
            }
            arr = newarr;
            this.computeStatistics();
            return true;
        }
        
    
    }

    public int getSum() {
        return this.sum;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public double getAvg() {
        return this.avg;
    }
    
    public int getMed() {
        return this.med;
    }
    
    public int [] getArr() {
        return this.arr;
    }


    public static void main(String [] args) {
        System.out.println("\nUnit Test for MyArray.\n");
        int[] test = {1,2,3,4,5,6,7,8};
        MyArray r1 = new MyArray(test);
        System.out.println(r1.toString());
        r1.inputElements();

        // System.out.println(r1.numElements);
        // System.out.println(r1.arr.length);
        // r1.grow(1);
        System.out.println(Arrays.toString(r1.arr));
        // System.out.println(r1.numElements);
        // System.out.println(r1.arr.length);
        // System.out.println(r1.toString());
        // r1.remove(0);
        // System.out.println(r1.arr.length);
        // System.out.println(r1.numElements);
        r1.insert(5, 2);
        // System.out.println(r1.numElements);
        // System.out.println(r1.arr.length);
        // System.out.println(r1.numElements);
        // System.out.println(r1.arr.length);
        // System.out.println(r1.numElements);
        System.out.println(r1.toString());
        // System.out.println(r1.remove(0));
        // System.out.println(r1.arr.length);
        // // r1.computeStatistics();
        // // System.out.println(r1.numElements);
        System.out.println(Arrays.toString(r1.arr));
        System.out.println(r1.getAvg());
        System.out.println(r1.getMed());
        System.out.println(r1.getMax());
        System.out.println(r1.getMin());
        System.out.println(r1.getSum());
        System.out.println(Arrays.toString(r1.getArr()));

	// TBA

    }
}

