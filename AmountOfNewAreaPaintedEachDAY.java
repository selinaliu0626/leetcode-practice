import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class AmountOfNewAreaPaintedEachDAY {
    public int[] amountPainted(int[][] paint) {
         int n = paint.length;
         int[] res = new int[n];
         TreeMap<Integer,Integer> map = new TreeMap<>();

         for(int i = 0; i < n;i++){
             int[] cur = paint[i];
             int start = cur[0];
             int end = cur[1];
             int work=end-start;

             //floorEntry() : It returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.

        //check the key which is greatest less than or equal to the given key,
             //or return null if there is no such key
              Map.Entry<Integer, Integer> floor = map.floorEntry(cur[0]);
             if(floor != null){
                 //如果之前已经完成了当前的工作
                 if(floor.getValue() >= end){
                     continue;
                 }
                 //如果之前的已经完成了一部分，我们就减去已经完成的部分，
                 //并且把start reset成前一个的start
                 if(floor.getValue() >=start){
                     work -=floor.getValue()-start;
                     map.remove(floor.getKey());
                     start =floor.getKey();
                 }
             }

             //ceilingEntry(K Key) : It is used to return a key-value mapping associated with the least key greater than or equal to the given key, or null if there is no such key.

             Map.Entry<Integer,Integer> ceiling = map.ceilingEntry(cur[0]);

             while(ceiling != null && ceiling.getKey() <= end){
                 work -= Math.min(end, ceiling.getValue()) -ceiling.getKey();
                 map.remove(ceiling.getKey());
                 end =Math.max(end,ceiling.getValue());
                 ceiling =map.ceilingEntry(cur[0]);
             }
             res[i] =work;

             //add merge interval tp treemap
             map.put(start,end);
         }
         return res;
     }

    public static void main(String[] args) {
        AmountOfNewAreaPaintedEachDAY npd=new AmountOfNewAreaPaintedEachDAY();
        int[][] paint ={{1,4},{6,8},{10,15},{18,20},{5,20}};
        System.out.println(Arrays.toString(npd.amountPainted(paint)));
    }

}
