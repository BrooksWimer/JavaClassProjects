/* File: Histogram.java
 * Author: CS112 Instructor
 * 
 * Purpose: This is a potential solution to the
 * Histogram problem.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 10;            // number of bins in range [0..100]

    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */
    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	    me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );
        sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	return (name+" ").split(" ")[0]; 
    }

    /* 
     * local main test driver
     *
     */
    public static void main(String[] args) {  

	// Connect to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );

        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

        // double[] test = {1, 11, 11.123, 41, 47, 51, 61.7, 71, 81, 91, 2.5, 12, 22, 44.3, 42.9, 52, 62, 72, 82, 92};

        // System.out.print(toString(calculateHistogram(test)));

	    String heading = getHeaderAsString( user );

        // // Print out welcome message
        System.out.println( heading ); 
        
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers( userInput );

	// Call the method to reate the array histogram
	int[] histogram = calculateHistogram( numbers );

	// Print the historgram
    StringBuilder sb = new StringBuilder();
    sb.append("You input "+ numbers.length + " numbers : ");
    sb.append(Arrays.toString(numbers));
    sb.append(System.getProperty("line.separator") );
    sb.append("Histogram of values from decades 0 to 100:");
    System.out.println(sb);
	System.out.println( toString( histogram ) );
    }


     // This method creates and returns an array of integers
    // that represents the resulting histogram from the numbers
    // entered and passed to the method.
    public static int [] calculateHistogram( double [] numbers ) {
        int [] hist = new int[NUM_BINS];
        for (int i = 0; i < numbers.length; i ++){
                if (numbers[i] >= 0 && numbers[i] <= 10){
                    hist[0] += 1;
                }
                else if (numbers[i] > 10 && numbers[i] <= 20){
                    hist[1] += 1;
                }
                else if (numbers[i] > 20 && numbers[i] <= 30){
                    hist[2] += 1;
                }
                else if (numbers[i] > 30 && numbers[i] <= 40){
                    hist[3] += 1;
                }
                else if (numbers[i] > 40 && numbers[i] <= 50){
                    hist[4] += 1;
                }
                else if (numbers[i] > 50 && numbers[i] <= 60){
                    hist[5] += 1;
                }
                else if (numbers[i] > 60 && numbers[i] <= 70){
                    hist[6] += 1;
                }
                else if (numbers[i] > 70 && numbers[i] <= 80){
                    hist[7] += 1;
                }
                else if (numbers[i] > 80 && numbers[i] <= 90){
                    hist[8] += 1;
                }
                else if (numbers[i] > 90 && numbers[i] <= 100){
                    hist[9] += 1;
                }
            }
        return hist;
        }

        public static int findBin( double num ) {
            for (int i = 0; i < NUM_BINS; i++){
                if (num <= (i*NUM_BINS)){
                    return i;
                }
            }

        }


        public static String toString( int [] histogram ) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.getProperty("line.separator") );
            sb.append( System.getProperty("line.separator") );
            sb.append("[");
            for (int i = 0; i < histogram.length; i++){
                sb.append(i*10 + ".." + ((i*10)+10) +"]: ");
                String stars = "";
                for (int y = 0; y < histogram[i]; y++){
                    stars += "*";
                }
                sb.append(stars);
                sb.append( System.getProperty("line.separator") );
                if (i < 9){
                    sb.append("(");
                }
            }
            return sb.toString();
        }
    
    public static boolean validInput( double num ) {
        return num < UPPER_BOUND && num > LOWER_BOUND;
    }

    public static double[] inputNumbers( Scanner scan ) {
        int count = 0;
        double[] vals = new double[MAX_NUMBERS];
        Double num;
        num = scan.nextDouble();
        while (num != SENTINAL && count < MAX_NUMBERS){
            if (validInput(num)){
                vals[count] = num;
                count ++;
            }
            else{
                System.out.println("input out of bounds, try again:");
            }
            num = scan.nextDouble();
        }
        double[] final_vals = new double[count];
        for (int i = 0; i < count; i ++){
            final_vals[i] = vals[i];
        }
        return final_vals;

        
    }


} // end of class
