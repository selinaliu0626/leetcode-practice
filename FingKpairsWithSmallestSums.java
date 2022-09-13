import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FingKpairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result= new ArrayList();
//        PriorityQueue<int []> que = new PriorityQueue<>((a, b)->(b[0]+b[1])-(a[0]+a[1])); 和大的优先级高
        PriorityQueue<int []> minHeap = new PriorityQueue<>((a,b) -> a[0]+a[1]-b[0]-b[1]);
        for(int i=0;i<Math.min(nums1.length,k);i++)
        {
            minHeap.offer(new int [] {nums1[i],nums2[0],0});
        }
        while (k>0 &&!minHeap.isEmpty()){
            int[] v= minHeap.poll();
            List<Integer> list =  new ArrayList<>();
            list.add(v[0]);
            list.add(v[1]);
            result.add(list);
        if(v[2]+1<nums2.length){
            minHeap.offer(new int[]{v[0],nums2[v[2]+1],v[2]+1});
        }
        k--;
        }
        return result;

    }
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            k--;
            res.add(List.of(nums1[cur[0]], nums2[cur[1]]));
            if (k == 0){
                return res;
            }
            if (cur[1] + 1 < nums2.length) {
                minHeap.offer(new int[]{cur[0], cur[1] + 1, nums1[cur[0]] + nums2[cur[1]+1]});
            }
        }
        return res;

    }

    public static void main(String[] args) {
        FingKpairsWithSmallestSums fkss =new FingKpairsWithSmallestSums();
        int[] a ={1,7,11};
        int[] b ={2,4,6};
        System.out.println(fkss.kSmallestPairs(a,b,3));
    }
}
