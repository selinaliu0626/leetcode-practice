import java.util.ArrayDeque;
import java.util.Deque;

//209. Minimum Size Subarray Sum
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int start =0;
        int min=0;
        int windowSum=0;
        for(int end=0;end<nums.length;end++){
            windowSum +=nums[end];
            while (windowSum-target>=nums[start]){
                windowSum -=nums[start];
                start++;
            }
            if(windowSum>=target){
                if(min==0 ||min>end-start+1){
                    min=end-start+1;
                }
            }
        }
        return min;

    }


    public static void main(String[] args) {
        MinimumSizeSubarraySum msss =new MinimumSizeSubarraySum();
        int[] nums ={2,3,1,2,4,3};
        int res = msss.minSubArrayLen(7,nums);
        System.out.println(res);
    }
}
