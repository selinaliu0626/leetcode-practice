import java.util.ArrayList;
import java.util.List;

public class DecodeWAYS {
    public int numDecodings(String s) {
        if(s.charAt(0)=='0') return 0;
        int[]dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=1;
        for(int i =2;i<=s.length();i++){
            //choose 1 digit
            if(s.charAt(i-1)!='0'){
                dp[i]=dp[i-1];
            }
            //choose 2 digits
            int twodigit=(s.charAt(i-2)-'0')*10+(s.charAt(i-1)-'0');
            if(twodigit>=10 &&twodigit<=26){
                dp[i]+=dp[i-2];
            }

        }
        return dp[s.length()];

    }
    //METHOD 0:DFS
    public int numDecodings2(String s) {
        if(s.charAt(0)=='0') return 0;
        List<String>res = new ArrayList<>();
        StringBuilder sb= new StringBuilder();
        dfs(res,sb,0,s);
        return res.size();
    }

    private void dfs(List<String> res, StringBuilder sb, int index, String s) {
        if(index==s.length()){
            res.add(sb.toString());
            return;
        }
        // choose one digit
        if(s.charAt(index)!='0'){
            int num = s.charAt(index)-'0';
            char cur=(char)('A'-1+num);
            sb.append(cur);
            dfs(res,sb,index+1,s);
            //backtrack
            sb.deleteCharAt(sb.length()-1);
        }

        //choose two digits
        if(index+1<s.length()){
            int num =(s.charAt(index)-'0')*10+(s.charAt(index+1)-'0');
            if(num>=10 && num<=26){
                char cur =(char)('A'+num-1);
                sb.append(cur);
                dfs(res,sb,index+2,s);
                //backtrack
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }


    public static void main(String[] args) {
        DecodeWAYS dw =new DecodeWAYS();
        String s="100";
        int res = dw.numDecodings2(s);
        System.out.println(res);
    }
}
