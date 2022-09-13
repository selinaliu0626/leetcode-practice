import java.util.Arrays;
import java.util.Comparator;

//179. Largest Number
public class LargestNumber {
    public String largestNumber(int[] nums) {

        int n = nums.length;
        String str[] = new String[n];

        for (int i = 0; i < n; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });

        String s = "";
        for (String k : str) {
            s += k;
        }

        return s.charAt(0) == '0' ? "0" : s;
    }

    public static void main(String[] args) {
        LargestNumber ln = new LargestNumber();
        int[] nums={3,30,34,5,9};
        System.out.println(ln.largestNumber(nums));
    }
}
