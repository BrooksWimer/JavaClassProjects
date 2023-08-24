/*
 * Brooks Wimer
 * bwimer@bu.edu
 * cs112
 */


import java.util.Arrays;

/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }

    public BigInt(int[] arr){
        if ((arr == null) || (arr.length == 0) || (arr.length > SIZE)){
            throw new IllegalArgumentException();
        }
        this.numSigDigits = 0;
        this.digits = new int[SIZE];
        int arrdex = 0;
        boolean leading = true;
        for (int i = (SIZE- arr.length); i < SIZE; i++){
            if (arr[arrdex] != 0 && leading == true){
                leading = false;
                this.numSigDigits++;
            }
            else if (leading == false){
                this.numSigDigits++;
            }
            if (arr[arrdex] > 9){
                throw new IllegalArgumentException();
            }
            this.digits[i] = arr[arrdex];
            arrdex++;
        }
    }

    public String toString(){
        boolean leading = true;
        String bigInt = "";
        for (int i = 0; i < SIZE; i++){
            if (this.digits[i] != 0 && leading == true){
                leading = false;
                bigInt += this.digits[i];
            }
            else if (leading == false){
                bigInt += this.digits[i];
            }
        }
        if (bigInt.equals("")){
            bigInt = "0";
        }
        return bigInt;
    }

    public int getNumSigDigits(){
        return this.numSigDigits;
    }

    public BigInt(int n){
        
        if (n < 0 ){
            throw new IllegalArgumentException();
        }
        else if (n== 0){
            numSigDigits = 1;
            this.digits = new int[SIZE];
        }
        else{
            int[] arr = new int[SIZE];
            int next = n;
            int i = 19;
            do {
                int ones = next % 10;
                arr[i] = next % 10;
                next = (next-ones)/10;
                i--;
            } while(next > 10);
            arr[i] = next;
            this.numSigDigits = 20-i;
            this.digits = arr;
        }
    }
    public int compareTo(BigInt other){
        if (other == null){
            throw new IllegalArgumentException();
        }
        else if (other.numSigDigits > this.numSigDigits){
            return -1;
        }
        else if (other.numSigDigits < this.numSigDigits){
            return 1;
        }
        else{
            for (int i = SIZE-this.numSigDigits; i < SIZE; i++){
            
                if (other.digits[i] > this.digits[i]){
                    return  -1;
                }
                else if (other.digits[i] < this.digits[i]){
                    return 1;
                }
            }
        }
        
        return 0;
    }

    public BigInt add(BigInt other){
        if (other == null){
            throw new IllegalArgumentException();
        }
        int[] added = new int[SIZE];
        int[] carry = new int[SIZE];
            for (int i = 19; i >= 0; i--){
                int add = other.digits[i] + this.digits[i] + carry[i];
                if (i == 0 && add>9){
                    throw new ArithmeticException();
                }
                if (add > 9){
                    added[i] = (add % 10);
                    carry[i-1] += 1;

                }
                else{
                    added[i] = add;

            }
        }
    return new BigInt(added);
}

public BigInt bigger(BigInt other){
    if (this.numSigDigits > other.numSigDigits){
        return this;
    }
    else{
        return other;
    }
}

public BigInt smaller(BigInt other){
    if (this.numSigDigits > other.numSigDigits){
        return other;
    }
    else{
        return this;
    }
}

public BigInt mul(BigInt other){
    if (other == null){
        throw new IllegalArgumentException();
    }
    int[][] carrys = new int[SIZE][SIZE];
    int[] added = new int[SIZE];
    BigInt addedBig = new BigInt(added);
    BigInt bigger = bigger(other);
    BigInt smaller = smaller(other);
    int j = 0;
    for (int x = 19; x >= 0; x--){
            for (int i = 19; i >= j; i--){
                int multiply = bigger.digits[i] * smaller.digits[x];
                if (i == 0 && multiply + carrys[x][i] > 9){
                    throw new ArithmeticException();
                }
                if (multiply > 9){
                    carrys[x][i-(j+1)] += (multiply- (multiply % 10))/10;
                    if((carrys[x][i-j] + (multiply % 10)) > 9){
                        carrys[x][i-j] = (carrys[x][i-j] + (multiply % 10))%10;
                        carrys[x][i-(j+1)] += ((carrys[x][i-j] + (multiply % 10))-((carrys[x][i-j] + (multiply % 10))%10))/10;
                    }
                    else{
                        carrys[x][i-j] += multiply%10;
                    }
                    
                }
                else{
                    if((carrys[x][i-j] + (multiply)) > 9){
                        carrys[x][i-j] = (carrys[x][i-j] + multiply)%10;
                        carrys[x][i-(j+1)] += ((carrys[x][i-j] + multiply)-((carrys[x][i-j] + multiply )%10))/10;
                    }
                    else{
                        carrys[x][i-j] += multiply;
                    }
                }
        }
        // System.out.println(carrys[x]);
        j++;
    }
    for (int i = 19; i > 0; i--){
        int[] added2 = carrys[i];
        BigInt addedBig2 = new BigInt(added2);
        addedBig = addedBig.add(addedBig2);
    }
    return addedBig;
}

    public int[] getDigits(){
        return this.digits;
    }

    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();
        System.out.println(12 / 10);

       
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2, 6};
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println(Arrays.toString(b1.digits));
         
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

      
     
        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();
        
   

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();
 /*
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */
        
        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();
 
        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        
    }
}
