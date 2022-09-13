import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleinHistogram {
    //naive approach
//    public int largestRectangleArea(int[] heights) {
//        int n = heights.length;
//        int max=0;
//        for(int i =0;i<n;i++){
//            int minHeight = Integer.MAX_VALUE;
//            for(int j =i;j<n;j++){
//                minHeight = Math.min(minHeight,heights[j]);
//                int width =j-i+1;
//                max =Math.max(max,minHeight*width);
//            }
//        }
//        return max;
//    }
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(-1);
        stack.offerFirst(0);
        int n = heights.length;
        int res =0;
        for(int i=1;i<n;i++){
            while ( stack.peekFirst()!=-1 && heights[i] < heights[stack.peekFirst()]) {
                int h = heights[stack.pollFirst()];
                int start = stack.peekFirst();
                res = Math.max(res,h*(i-start-1));
            }
            stack.offerFirst(i);
        }
        while(stack.peekFirst() != -1){
            int h = heights[stack.pollFirst()];
            int start = stack.peekFirst();
            res = Math.max(res,h*(n-start-1));

        }
        return res;

    }

    public static void main(String[] args) {
        LargestRectangleinHistogram lrh =new LargestRectangleinHistogram();
        int[] heights ={2,1,5,6,2,3};
        System.out.println(lrh.largestRectangleArea(heights));
    }
}
