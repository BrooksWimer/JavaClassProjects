import java.util.*;
public class DoubleAllStack{


    public static void doubleAllStack(Stack<Object> stack, Object item){
        LLQueue<Object> doubled = new LLQueue<Object>();   
        while (!(stack.isEmpty())){
            Object temp = stack.pop();
            if (temp.equals(item)){
                doubled.insert(temp);
                doubled.insert(temp);
            }
            else{
                doubled.insert(temp);
            }
        }
        System.out.print(doubled.toString());
        while (!(doubled.isEmpty())){
            System.out.println(doubled.peek());
            stack.push(doubled.remove());
            
        }
    }

    public static void doubleAllQueue(Queue<Object> queue, Object item){
        LLQueue<Object> doubled = new LLQueue<Object>();   
        while (!(queue.isEmpty())){
            Object temp = queue.remove();
            if (temp.equals(item)){
                doubled.insert(temp);
                doubled.insert(temp);
            }
            else{
                doubled.insert(temp);
            }
        }
        System.out.print(doubled.toString());
        while (!(doubled.isEmpty())){
            System.out.println(doubled.peek());
            queue.insert(doubled.remove());
            
        }
    }

    public static boolean search(Stack<Object> S, Queue<Object> Q, Object I){
        Boolean flag = false;
        while(!(S.isEmpty())){
            Object temp = S.pop();
            Q.insert(temp);
            if (temp.equals(I)){
                flag = true;
            }
        }
        while(!(Q.isEmpty())){
            S.push(Q.remove());
        }
        return flag;
    }

    public static int[] arrayMystery1(int[] a){
        int [] b = arrayMystery2(a, a[0]+a[a.length-2]);
        return (b);
    }
    public static int[] arrayMystery2(int[] b, int scale){
        int [] c = b;
        System.out.println(scale++);
        for (int i = 0; i <b.length; i++)
            c[i] = b[i] + scale;
            System.out.println(++scale);
        for (int i= 0; i < c.length; i++)
            c[i]+= scale;

        return(c);

    }

    public static void main(String[] args){
        // int[] arr = {1,2,3,4,5,6,2,7,8};
        // ArrayStack<Object> test = new ArrayStack<Object>(18);
        // for (int i = 0; i < arr.length; i++){
        //     test.push(arr[i]);
        // }
        // ArrayQueue<Object> q = new ArrayQueue<Object>(10);
        // System.out.println(test);
        // doubleAllStack(test, 6);
        // System.out.print(test);
        String a = "ji";
        String b = "ji";
        b = "b";
        System.out.println(a);
        System.out.println(b);
    }

}