public class PairFinder{

    public static void findPairSums(int k, int[] arr){
        if (arr.equals(null)){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < arr.length; i++){
            for (int x = i; x < arr.length; x++){
                if (x!= i){
                    if (arr[x] + arr[i] == k){
                        System.out.println(arr[i] + " + " + arr[x] + " = " +  k);
                    }
                }
            }
        }
    }

    public static void findPairSumsFaster(int k, int[] arr){
        if (arr.equals(null)){
            throw new IllegalArgumentException();
        }
        Sort.quickSort(arr);
        int hk = k/2;
        int[] temp = new int[arr.length];
        int dex = 0;
        int f = 0;
        int i;
        for (i = 0; i < arr.length; i++){
            System.out.println(++f);
            // if (arr[i] == arr[i+1]){
            //     i++;
            // }
            if (arr[i] < hk){
                temp[dex] = k- arr[i];
                dex++;
            }
            if (arr[i] == hk){
                if (dex > 0){
                    if (arr[i] != temp[dex-1]){
                        temp[dex] = arr[i];
                        dex++;
                    }
                }
            }
            if (arr[i] >= hk){
                int y = dex -1;
                if (arr[i] > temp[0]){
                    break;
                }
                if (arr[i] <= temp[y]){
                    if (arr[i] == temp[y] && y == 0){
                        System.out.println(temp[y] + " + " + (k - temp[y]) + " = " + k);
                        break;
                    }
                    else if (arr[i] == temp[y]){
                        System.out.println(temp[y] + " + " + (k - temp[y]) + " = " + k);
                        dex--;
                    }
                }
                if (dex == 0){
                    System.out.print('f');
                    break;
                }
            }
        }

    }
        
    

    public static void main(String[] args){
        int[] arr = {10, 4, 7, 7, 8, 5, 15};
        findPairSums(15, arr);
        findPairSumsFaster(14, arr);
    }
}