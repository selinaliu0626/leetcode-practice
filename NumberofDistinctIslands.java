import java.util.*;

//694. Number of Distinct Islands
public class NumberofDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        int rows =grid.length;
        int cols =grid[0].length;
        boolean[][]visited = new boolean[rows][cols];
        Set<List<List<Integer>>> set = new HashSet<>();
        for(int i =0;i<rows;i++){
            for(int j =0;j<cols;j++){
                if(grid[i][j] ==1 && !visited[i][j]){
                    // use this coordinate as the start for this island
                    List<List<Integer>> list = new ArrayList<>();
                    dfs(grid,i,j,i,j,list,visited);
                    set.add(list);
                }
            }
        }
        return set.size();

    }

    private void dfs(int[][] grid, int x, int y, int startx, int starty, List<List<Integer>> list, boolean[][] visited) {
        if(x<0 || x>= grid.length ||y<0 ||y >=grid[0].length ||visited[x][y] ||grid[x][y]==0){
            return;
        }
        visited[x][y]=true;
        list.add(Arrays.asList(x-startx,y-starty));
        for(int[] dir:DIRS){
            dfs(grid,x+dir[0], y+dir[1],startx,starty,list,visited);
        }
    }
    private final int[][] DIRS =new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
}
