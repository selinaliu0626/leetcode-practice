import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeInKLists {
    static class Cell{
        int value;
        int x;
        int y;
        Cell(int data, int row, int col){
            value=data;
            x=row;
            y=col;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b)-> ( a.value - b.value));
        int rows=nums.size();
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i =0;i<rows;i++){
            Cell a = new Cell(nums.get(i).get(0),i,0);
             minHeap.offer(a);
            if(a.value>max){
                max=a.value;
            }
        }
        int range =Integer.MAX_VALUE;
        while(!minHeap.isEmpty()){
            Cell b=minHeap.poll();
            int x =b.x;
            int y=b.y;
            if((max-b.value)<range){
                range= max-b.value;
                 min=b.value;
            }

            if(range==0) break;
            if(y+1<nums.get(x).size()){
                Cell c =new Cell(nums.get(x).get(y+1),x,y+1);
                minHeap.offer(c);
                if(c.value>max){
                    max=c.value;
                }

            }

            if(y+1>=nums.get(x).size()){
                break;
            }
        }
        return new int[]{min, min+range};
    }
   public List<Integer> convertToList(int[] a){
        List<Integer> res =new ArrayList<>();
     for(int i =0;i<a.length;i++){
         res.add(a[i]);
     }
     return res;
    }
    public static void main(String[] args) {
        SmallestRangeInKLists srikl = new SmallestRangeInKLists();
        int[] a = {-5, -4, -3, -2, -1};
        int[] b = {1, 2, 3, 4, 5};
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list1 = srikl.convertToList(a);
        List<Integer> list2 = srikl.convertToList(b);
        nums.add(list1);
        nums.add(list2);

        System.out.println(Arrays.toString(srikl.smallestRange(nums)));
    }


}
