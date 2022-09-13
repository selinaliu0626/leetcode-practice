import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutation {
    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        // Write your code here
        if(k==0) return "YES";
        return dfs(k,A,B,0)==true? "YES":"NO";

    }
    private static void swap(int i, int j, List<Integer> A ){
        int temp =A.get(i);
        A.set(i,A.get(j));
        A.set(j,temp);
    }
    private static boolean dfs(int k, List<Integer> A, List<Integer> B,int index){
        if(index==A.size()){
            if(A.get(index-1)+B.get(index-1)>=k){
                return true;
            }
        }
        for(int i=index;i<A.size();i++){
                swap(i,index,A);
                if(A.get(index)+B.get(index)>=k){
                    if(dfs(k,A,B,index+1)) return true;
                }
                swap(i, index, A);
            }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        int[] a ={2,1,3};
        int[]b ={7,8,9};
        for(int i =0;i<3;i++){
            A.add(a[i]);
            B.add(b[i]);
        }
        int k = 10;
        Collections.sort(A);
        B.sort(Collections.reverseOrder());
        for(int i = 0; i < A.size(); i++){
            if(A.get(i)+B.get(i)<k) System.out.println("No");
        }
        System.out.println("Yes");
        //System.out.println(twoArrays(10,A,B));

    }
}

