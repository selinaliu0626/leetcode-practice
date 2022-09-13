import java.util.ArrayDeque;
import java.util.Queue;

public class SnakesandLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        //quchong
        boolean[] visited = new boolean[n * n + 1];

        for (int move = 0; !queue.isEmpty(); move++) {
            //记录每一次移动，需要遍历的点的个数
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                //已经看过，忽略
                if (visited[num]) continue;
                //没看过的话
                visited[num] = true;
                if (num == n * n) return move;
                //六步之内所有可能的点
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    //如果是存在snake and ladder， 转换next
                    if (value > 0) next = value;

                    if (!visited[next]) queue.offer(next);
                }
            }
        }
        return -1;
    }
    //得到board 上的值
    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;  //比如 2 ， 那么就是知道他在最下面一行
        int x = n - 1 - r;   //但是board是自下而上，所以是最后一行
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num; //get col
        return board[x][y];
    }

    public static void main(String[] args) {
        SnakesandLadders sal = new SnakesandLadders();
        int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        System.out.println(sal.snakesAndLadders(board));
    }
}
