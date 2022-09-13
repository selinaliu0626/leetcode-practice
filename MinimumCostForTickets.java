public class MinimumCostForTickets {
    int[] days, costs;
    Integer[] memo;
    int[] durations = new int[]{1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];

        return dp(0);
    }

    public int dp(int i) {
        if (i >= days.length)
            return 0;
        if (memo[i] != null)
            return memo[i];

        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }

    public static void main(String[] args) {
        MinimumCostForTickets mcft = new MinimumCostForTickets();
        int[] days ={1,2,3,4,5,6,7,8,9,10,30,31,32,40};
        int[] costs ={2,7,15};
        System.out.println(mcft.mincostTickets(days,costs));
    }
}

