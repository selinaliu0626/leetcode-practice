//leetcode 4 median of two sorted arrays
public class MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        if(m>n){
            return findMedianSortedArrays(nums2,nums1);
        }
        int left=0;
        int right=m;
        int k= (m+n+1)/2;
        while(left<right){
            int mid1= left+(right-left)/2;
            int mid2= k-mid1;
            if(nums1[mid1]<nums2[mid2-1]){
                left=mid1+1;
            }else{
                right=mid1;
            }
        }
        int m1=left;
        int m2=k-left;
        int median1= Math.max(m1<=0?Integer.MIN_VALUE:nums1[m1-1],
                m2<=0?Integer.MIN_VALUE:nums2[m2-1]);
        if((n+m)%2==1){
            return median1;
        }
        int median2=Math.min(m1>=m?Integer.MAX_VALUE:nums1[m1],
                m2>=n?Integer.MAX_VALUE:nums2[m2]);

        return(median1+median2)*0.5;

    }

    public static void main(String[] args) {
        MedianOfTwoSortedArray mo2s= new MedianOfTwoSortedArray();
        int[]a1={1,2};
        int[]a2={3,4};
        double res=mo2s.findMedianSortedArrays(a1,a2);
        System.out.println(res);
    }
}

