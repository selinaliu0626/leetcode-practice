import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperations {
    List<String> res;
    char[] nums;
    int n;
    long target;
    char[] chs;// store the expression
    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        nums =num.toCharArray();
        n = nums.length;
        this.target = target;
        chs = new char[n+n];
        long value =0;
        int chsptr =0;
        for(int i =0;i<n;i++){
            value= value*10+nums[i]-'0';
            chs[chsptr++]= nums[i];
            helper(i+1,chsptr,0,value);

            if(value ==0) break;
        }
        return res;

    }
    private void helper(int numptr, int chsptr, long cur, long pre){
        if(numptr == n){
            if(cur+pre == target){
                res.add(new String(chs,0,chsptr));
            }
            return;
        }
        long value=0;
        int op =chsptr++;
        for(int i =numptr;i<n;i++){
            value =value*10+nums[i]-'0';
            chs[chsptr++]= nums[i];
            chs[op]='+';
            helper(i+1,chsptr,cur+pre,value);
            chs[op]='-';
            helper(i+1,chsptr,cur+pre,-value);
            chs[op]='*';
            helper(i+1,chsptr,cur,pre*value);
            if(value ==0) break;
        }

    }

    public static void main(String[] args) {
        ExpressionAddOperations eao = new ExpressionAddOperations();
        String num ="123";
        System.out.println(eao.addOperators(num,6));

    }
}
