/*
 * Palindrome.java
 */
   
public class Palindrome {
    // Add your definition of isPal here.

    public static Boolean isPal(String s){
        if (s == null){
            throw new IllegalArgumentException();
        }
        LLQueue<Character> q = new LLQueue<Character>();
        LLStack<Character> stack = new LLStack<Character>();
        for (int i = 0; i < s.length(); i ++){
            if ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')){
                q.insert(Character.toUpperCase(s.charAt(i)));
                stack.push(Character.toUpperCase(s.charAt(i)));
            }
        }
        if (q.isEmpty()){
            return false;
        }
        while (!(q.isEmpty())){
            if (q.remove() != stack.pop()){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
       
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */

        System.out.println("(1) Testing on \"a\"");
        try {
            boolean results = isPal("a");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(2) Testing on \"cat\"");
        try {
            boolean results = isPal("cat");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(3) Testing on \"aa\"");
        try {
            boolean results = isPal("aa");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(4) Testing on \"\"");
        try {
            boolean results = isPal("");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        System.out.println("(5) Testing on \"2344444444\"");
        try {
            boolean results = isPal("2344444444");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

    }
}