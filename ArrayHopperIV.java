//LAICODE 91. Array Hopper
import mymethod.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ArrayHopperIV {
    public int minJump(int[] array, int index) {
        int[] m = new int[array.length];
        Arrays.fill(m, -1);
        m[index] = 0;
        int step = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(index);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int j = 1; j <= array[cur]; j++) {
                    if (cur + j < array.length && m[cur + j] == -1) {
                        m[cur + j] = step;
                        queue.offer(cur + j);
                    }
                    if (cur - j >= 0 && m[cur - j] == -1) {
                        queue.offer(cur - j);
                        m[cur - j] = step;
                    }
                }
            }
            step++;
        }
        return m[array.length - 1];
    }

    public static void main(String[] args) {
        ArrayHopperIV ah4 =new ArrayHopperIV();
        int[] array = {1,2,0};
        int res = ah4.minJump(array,0);
        System.out.println(res);
    }
}















//        if(array[index]+index>= array.length-1) return 1;
//        int maxlength=0;
//        int start=0;
//        Pair[] dp=new Pair[array.length];
//        for(int i =0;i< array.length;i++){
//           if(i+array[i]>maxlength){
//               start=i;
//               maxlength=i+array[i];
//           }
//            Pair<Integer,Integer> pair = new Pair<>(maxlength,start);
//           dp[i]=pair;
//        }
//        if((int)dp[array.length-1].first<array.length-1)return -1;
//        int jump=1;
//        boolean could= true;
//        if((int)dp[index].second==(array[index]+index)){
//            int cur1=index;
//            for(int i=index;i<array.length;i++){
//           if((int)dp[i].second>cur1){
//               cur1=(int)dp[i].second;
//               jump++;
//           }else if(array[cur1]==0){
//               could=false;
//               break;
//           }
//        }
//
//        if((int)dp[index].first>(array[index]+index)){
//            int back=(int)dp[index].second;
//            int[] pre = new int[index];
//            int step=0;
//            int cur=index;
//            for(int i=index-1;i>=0;i--){
//                if(cur-i>=array[cur]){
//                   cur= index-array[cur];
//                    step++;
//                }else if( array[cur]==0){
//                   while(i>=0){
//                       pre[i--]=-1;
//                   }
//                }
//                pre[i]=step;
//            }
//
//           int count=index;
//          for (int i=pre[(int)dp[index].first];i>=0;i--){
//              int distance=array[count]+count;
//              count=distance;
//          }
//          if(count<(int)dp[index].first){
//
//          }
//
//        }


