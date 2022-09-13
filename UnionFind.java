public class UnionFind {
        int count;
        int[] parent;
        int[] rank;
        public UnionFind(int total){
            count=0;
            parent= new int[total];
            rank=new int[total];
            for(int i =0;i<total;i++){
                parent[i]=-1;
                rank[i]=0;
            }
        }
        public boolean isValid(int i){
            return parent[i]>=0;
        }

        public void setParent(int i){
            parent[i] = i;
            ++count;
        }

        public int findroot(int i){
            if(parent[i] !=i){
                parent[i]=findroot(parent[i]);

            }
            return parent[i];
        }

        //union的时候谁的孩子多就选谁做root；
        //union之后要减少个数
        public void union(int x, int y) {
            int rootx = findroot(x);
            int rooty = findroot(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rooty] = parent[rootx];
                } else {
                    parent[rooty] = parent[rootx];
                    rank[rootx] += 1;
                }
                --count;
            }
        }
        public int getCount(){
            return count;
        }
}
