import java.util.*;

public class ArrayTest {
    static int s_var = 10;
 
    public static int[] foo(int[] a) {
       s_var--;
       System.out.println(s_var);
       int[] b = new int[a.length];
 
       for (int i = 0; i < b.length; i++)
        // System.out.println(a[i]++);
        b[i] = a[i]++;
       
 
       System.out.println(Arrays.toString(b));
       return b;
    }
 
    public static void main(String[] args) {
       int i = ++s_var;
       System.out.println(i);
       i+=5;
       int[] a = {2, 4, 6, 8};
       int[] b = new int[a.length];
       int[] c = a;
 
       c = foo(a);
       System.out.println(Arrays.toString(c));
    }
 }