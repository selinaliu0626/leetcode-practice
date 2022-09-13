import java.util.ArrayList;
import java.util.List;

public class ChocolateShare {
    public static int birthday(List<Integer> s, int d, int m) {
        // Write your code here
        if(s.size()<m) return 0;
        int start=0,end=0;
        int sum=0,count=0;
        while(end<s.size()){
            while(end-start <=m-1){
                sum+=s.get(end++);
            }
            if(sum==d) count++;
            sum -=s.get(start++);
            sum +=s.get(end++);
            if(end== s.size()){
                if (sum==d) count ++;
            }
        }
        return count;

    }

    public static void main(String[] args) {
        int[] a ={1,2,1,3,2};
        List<Integer> s = new ArrayList<>();
        for(int i=0;i<a.length;i++){
            s.add(a[i]);
        }
        System.out.println(birthday(s,3,2));
    }
}
