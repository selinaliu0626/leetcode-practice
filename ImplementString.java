public class ImplementString {
    public int strStr(String haystack, String needle) {
        //corner case
        if (needle == null || needle.length() == 0) return 0;

        char[] hay = haystack.toCharArray();
        char[] need = needle.toCharArray();

        int count = 0;
        int p = 0;
        int m =hay.length;
        int n =need.length;
        int[][] dp =new int[m+1][n+1];
        for(int i= 0;i<m;i++){
            for(int j =0;j<n;j++){
                if(hay[i] == need[j]){
                    dp[i+1][j+1] = dp[i][j]+1;
                    if(dp[i+1][j+1] == n){
                        return i-n+1;
                    }
                }
            }

        }
        return -1;

    }

    public static void main(String[] args) {
        ImplementString is =new ImplementString();
        System.out.println(is.strStr("mississippi","issip"));
    }
}
