import java.util.List;

//https://leetcode.com/problems/number-of-islands/
public class NumberofIslands1 {
    static class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int r = grid.length;
            int c = grid[0].length;
            parent = new int[r * c];
            rank = new int[r * c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * r + j] = i * r + j;
                        ++count;
                    }
                    rank[i * r + j] = 0;
                }
            }
        }
    }
    public int numIslands1(char[][] grid) {
        if(grid ==null ||grid.length==0 || grid[0].length==0){
            return 0;
        }
        int count =0;
        int rows =grid.length;
        int cols =grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j =0;j<cols;j++){
                //如果当前是1， 并且从未被访问过，证明他是孤立于别的岛屿而存在的
                if(grid[i][j]=='1' &&!visited[i][j]){
                    count++;
                    //每次访问到一个小岛屿，我们就看看上下左右是否有邻居
                    dfs(grid,i,j,visited);
                }
            }
        }
        return count;

    }

    //dfs
    private void dfs(char[][] grid, int x, int y, boolean[][]visited){
        //base case
        //越界或者已经访问，或者此块地是海洋
        if(x<0 ||x>= grid.length ||y<0 ||y>=grid[0].length||visited[x][y] ||grid[x][y]=='0'){

            return;
        }
        visited[x][y]=true;
        dfs(grid, x+1, y, visited);
        dfs(grid, x-1, y, visited);
        dfs(grid, x, y+1, visited);
        dfs(grid,x,y-1,visited);

    }


}
