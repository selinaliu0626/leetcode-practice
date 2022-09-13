import java.util.ArrayList;
import java.util.List;
//305 number of islands 2
public class NumberofIslands2 {
    //use union find

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UnionFind uf = new UnionFind(m*n);
        for(int[] pos :positions){
            int x=pos[0], y=pos[1];
            List<Integer> overlap = new ArrayList<>();
            //对于每个位置查看一下他们上下左右是否是有效的
            if(x-1>=0 && uf.isValid((x-1)*n+y)) overlap.add((x-1)*n+y);
            if(x+1<m && uf.isValid((x+1)*n+y)) overlap.add((x+1)*n+y);
            if(y-1>=0 && uf.isValid(x*n+y-1)) overlap.add(x*n+y-1);
            if(y+1<n &&uf.isValid(x*n+y+1)) overlap.add(x*n+y+1);

            //找到他们在rank 和parent里面的坐标
            int index=x*n+y;
            uf.setParent(index);
            //union 各个点
            for(int i: overlap) uf.union(i,index);
            res.add(uf.getCount());
        }
        return res;
    }
}
