//291. Ternary Expression Tree
public class TernaryExpressionTree {
    static class ExpNode {
        public char symnol;
        public ExpNode left;
        public ExpNode right;

        public ExpNode(char symnol) {
            this.symnol = symnol;
        }
    }
    public ExpNode tree(String exp) {
        if(exp==null ||exp.length()==0) return null;
        int[]index = new int[1];
        return dfs(exp,index);
    }
    private ExpNode dfs(String exp,int[] index){
        ExpNode root = new ExpNode(exp.charAt(index[0]));
        index[0]+=2;
        if(index[0]-1<exp.length() && exp.charAt(index[0]-1)=='?'){
            root.left =dfs(exp,index);
            root.right=dfs(exp,index);
        }
        return root;
    }

    public static void main(String[] args) {
        TernaryExpressionTree tet = new TernaryExpressionTree();
        String exp ="a?b?c:d:e";
        ExpNode root= tet.tree(exp);

    }
}

