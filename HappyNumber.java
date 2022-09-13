import java.util.HashSet;
import java.util.Set;

//202. Happy Number
public class HappyNumber {
    public boolean isHappy(int n) {
        //when it return false must contain a  circle
        //use set to detect circle
        Set<Integer> seen = new HashSet<>();
        while(n!=1 && !seen.contains(n)){
            seen.add(n);
            n =getNext(n);
        }
        return n==1;
    }

    private int getNext(int n) {
        int sum =0;
        while (n>0){
            int digit = n%10;
            n =n/10;
            sum += digit * digit;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(2));
    }
}
