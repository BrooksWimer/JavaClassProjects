import java.util.Arrays;

public class MergeApproach{


    public static int[] union(int[] a1, int[] a2){
        if (a1 == null || a2 == null){
            throw new IllegalArgumentException();
        }
        Sort.quickSort(a1);
        Sort.quickSort(a2);

        int[] temp = new int[a1.length + a2.length];

        int a1i = 0;
        int a2i = 0;
        int ti = 0;
        if (a1[a1i] <  a2[a2i]){
            temp[ti] = a1[a1i];
            ti++;
            a1i++;
        }
        else if (a2[a2i] < a1[a1i]){
            temp[ti] = a2[a2i];
            ti++;
            a2i++;
        }
        else if (a1[a1i] == a2[a2i]){
            temp[ti] = a1[a1i];
            ti++;
            a1i++;
            a2i++;
        }
        while (a1i < a1.length && a2i < a2.length){
            if (a1[a1i] == temp[ti -1]){
                a1i++;
            }
            else if (a2[a2i] == temp[ti -1]){
                a2i++;
            }
            else if (a1[a1i] == a2[a2i]){
                temp[ti] = a1[a1i];
                ti++;
                a1i++;
                a2i++;
            }
            else if (a1[a1i] <  a2[a2i]){
                temp[ti] = a1[a1i];
                ti++;
                a1i++;
            }
            else if (a2[a2i] < a1[a1i]){
                temp[ti] = a2[a2i];
                ti++;
                a2i++;
            }
        }
        while (a1i < a1.length){
            temp[ti] = a1[a1i];
            ti++; a1i++;
        }
        while (a2i < a2.length){
            temp[ti] = a2[a2i];
            ti++; a2i++;
        }
        return temp;
    }

    public static int[] intersect(int[] a1, int[] a2){
        int[] t1;
        int[] t2;
        if (a1.length >= a2.length) {
            t2 = a2;
            t1 = a1;
        } else {
            t2 = a1;
            t1 = a2;
        }
        int[] intersect = new int[t2.length];
        Sort.quickSort(t2);
        Sort.quickSort(t1);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < t2.length && j < t1.length) {
            if (k > 0) {
                boolean skipround = false;
                if (t2[i] <= intersect[k - 1]) {
                    i++;
                    skipround = true;
                }
                if (t1[j] <= intersect[k - 1]) {
                    j++;
                    skipround = true;
                }
                if (skipround) {
                    continue;
                }
            }
            if (t2[i] == t1[j]) {
                intersect[k] = t2[i];
                i++;
                j++;
                k++;
            } else {
                if (t2[i] < t1[j]) {
                    i++;
                }
                else {
                    j++;
                }
            } 
        }
        return intersect;
    
    }

    public static void main(String[] args){
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = union(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = union(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));
    }
}