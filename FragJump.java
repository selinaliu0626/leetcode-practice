import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//403. Frog Jump
public class FragJump {
    public boolean canCross(int[] stones) {
        if (stones[1] - stones[0] != 1) return false;
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[1][1] = true;
        if (stones.length == 2) return true;
        for (int i = 2; i < n; i++)
            for (int j = i - 1; j >= 1; j--) {
                int k = stones[i] - stones[j];
                if (k > j + 1) break;
                dp[i][k] = dp[j][k] || dp[j][k - 1] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k] == true) return true;
            }
        return false;
    }


    public boolean canCross2(int[] stones) {
        if(stones[1]-stones[0]!=1) return false;
        Map<Integer, Set<Integer>> map = new HashMap<>();
       for(int i=1;i< stones.length;i++){
           map.put(stones[i],new HashSet<>());
       }
       map.get(stones[1]).add(1);
        for(int i=1;i<stones.length;i++){
            for(int k:map.get(stones[i])){
                for(int gap=k-1;gap<=k+1;gap++){
                    if(gap>0 && map.containsKey(stones[i]+gap)){
                        map.get(stones[i]+gap).add(gap);
                    }
                }
            }
        }
        return map.get(stones[stones.length-1]).size()>0;

        }

    public static void main(String[] args) {
        FragJump fj =new FragJump();
        int[]stones ={0,1,2,3,4,8,9,11};

        boolean res =fj.canCross(stones);
        System.out.println(res);
    }
}
