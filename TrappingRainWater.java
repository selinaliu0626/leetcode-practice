import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {
    public int trap(int[] height) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(height[0]);
        int max =0;
        int index=1;
        int res=0;

        while(index<height.length){
            while(!deque.isEmpty() && deque.peekLast()< height[index]){
                max= Math.min(deque.peekFirst(),height[index]);
                res +=(max-deque.pollLast());
            }
            deque.offerLast(height[index++]);

        }

        return res;
    }

    public static void main(String[] args) {
        TrappingRainWater tw =new TrappingRainWater();
        int[] height ={0,1,0,2,1,0,1,3,2,1,2,1 };
        System.out.println(tw.trap(height));

    }

}