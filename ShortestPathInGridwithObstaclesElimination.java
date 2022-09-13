import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class ShortestPathInGridwithObstaclesElimination {
    static class StepState{
        public int steps,row,col,k;
        public StepState(int steps, int row, int col, int k){
            this.steps =steps;
            this.row =row;
            this.col=col;
            this.k =k;
        }
        @Override
        public int hashCode(){
            return (this.row+1)*(this.col+1)*this.k;
        }
        @Override
        public boolean equals(Object other){
            if(!(other instanceof  StepState)){
                return false;
            }
            StepState newstate =(StepState) other;
            return (this.row == newstate.row) &&(this.col == newstate.col) &&(this.k == newstate.k);
        }
        @Override
        public String toString(){
            return String.format("%d %d %d, this.row,this.col,this.k");
        }
    }
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length,cols = grid[0].length;
        int[] target ={rows-1,cols-1};
        if(k>=rows+cols-2){
            return rows+cols-2;
        }
        Deque<StepState> queue = new ArrayDeque<>();
        HashSet<StepState> seen = new HashSet<>();

        StepState start = new StepState(0,0,0,k);
        queue.offerLast(start);
        seen.add(start);
        while(!queue.isEmpty()){
            StepState cur = queue.pollFirst();

            if(target[0]==cur.row &&target[1]==cur.col){
                return cur.steps;
            }
            int[] nextSteps ={cur.row, cur.col+1, cur.row+1,cur.col, cur.row,cur.col-1,cur.row-1,cur.col};
            for(int i =0;i<nextSteps.length;i+=2){
                int nextRow = nextSteps[i];
                int nextCol =nextSteps[i+1];

                if(0>nextRow ||nextRow ==rows || 0>nextCol ||nextCol ==cols){
                    continue;
                }
                int nextElimination = cur.k -grid[nextRow][nextCol];
                StepState  newState= new StepState(cur.steps+1,nextRow,nextCol,nextElimination);

                if(nextElimination>=0 && !seen.contains(newState)){
                    seen.add(newState);
                    queue.offerLast(newState);
                }
            }
        }
        return -1;
    }
}
