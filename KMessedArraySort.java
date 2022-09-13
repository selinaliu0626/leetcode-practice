import java.util.Arrays;

public class KMessedArraySort {
    static int[] sortKMessedArray(int[] arr, int k) {

         //your code goes here
        //corner case
        if(arr.length<=1) return arr;
        for(int i=0;i<arr.length;i++){
            int j =i;
            while(j<arr.length&&j<=i+k){
                if(arr[j]<arr[i]) swap(arr,i,j);
                j++;
            }
        }
        return arr;
    }

    private static void swap(int[]arr, int i,int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    //method 1: time n*k;

    //method 2 :min heap time:n*log(K) and space:O(k)
//    function sortKMessedArray(arr, k):
//    n = arr.length
//
//    # create an empty min-heap
//            h = new MinHeap()
//
//    # build the min-heap from the first k+1 elements of arr
//    h.buildHeap(arr[0,...,k])
//
//            for i from k+1 to n-1:
//            # extract the top element from the min-heap and
//        # assign it to the next available array index
//    arr[i-(k+1)] = h.extractMin()
//
//            # push the next array element into the min-heap
//        h.insert(arr[i])
//
//            # extract all remaining elements from the min-heap
//    # and assign them to the next available array index
//    for i from 0 to k:
//    arr[n-k-1 + i] = h.extractMin()
//
//            return arr

    public static void main(String[] args) {
        int[]arr ={1,4,5,2,3,7,8,6,10,9};
        int[]res =sortKMessedArray(arr,2);
        System.out.println(Arrays.toString(res));
    }
}
