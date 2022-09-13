import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    public int calculate(String s) {
        Deque<Integer> num = new ArrayDeque<>();
        Deque<Integer> sign = new ArrayDeque<>();
        int curNum=0;
        int preSign=1;
        int curSum=0;
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                curNum = 10 * curNum + (c - '0');
            }
            if(c=='(' || c==')' ||i==s.length()-1 ||c=='+' ||c=='-'){
                if (c == '(') {
                    sign.offerFirst(preSign);
                    num.offerFirst(curSum);
                    curSum =0;
                    preSign=1;
                } else if (c == ')') {
                    curSum += (curNum * preSign);
                    if (num.isEmpty()) curNum = 0;
                    if (!num.isEmpty() && !sign.isEmpty()) {
                        curNum = num.pollFirst();
                        preSign = sign.pollFirst();
                    }
                    curSum = curNum + curSum * (preSign);

                } else if (c == '+' || c == '-') {
                    curSum +=curNum*(preSign);
                    preSign = c == '+' ? 1 : -1;
                }else{
                    curSum +=curNum*(preSign);
                }
                curNum=0;
            }
        }
        return curSum;
    }

    public static void main(String[] args) {
        BasicCalculator bc =new BasicCalculator();
//        String s ="(1+(4+5+2)-3)+(6+8)";
        String s ="-(3+(4+5))";
        System.out.println(bc.calculate(s));
    }
}
