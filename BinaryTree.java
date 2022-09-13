import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTree {
    public int findHeight(TreeNode root) {
        if(root == null) return 0;
        int left =findHeight(root.left);
        int right = findHeight(root.right);
        return Math.max(left,right)+1;
    }
    //check if is balanced
    //check height的时候就可以看是否平衡
    public boolean isBalanced(TreeNode root){
        if(root== null) return true;
        int height =balancedHelper(root);
        return height==-1?false:true;

    }

    private int balancedHelper(TreeNode root) {
        if(root == null) return 0;
        int left =balancedHelper(root.left);
        int right =balancedHelper(root.right);
        if(Math.abs(left-right)>1) return -1;
        if(left==-1 ||right==-1)return -1;
        return Math.max(left,right)+1;
    }
    //check if it is binary search tree
    public boolean isBST(TreeNode root){
        if(root==null) return true;
        return isBSThelper(root,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }
    private boolean isBSThelper(TreeNode root,int min, int max){
        if(root == null) return true;
        if(root.key<= min ||root.key>=max)return false;
        return isBSThelper(root.left,min,root.key) &&isBSThelper(root.right,root.key,max);

    }
    public TreeNode search(TreeNode root, int target){
        if(root == null ||root.key ==target)return root;
        if(root.key<target){
            return search(root.right,target);
        }else{
            return search(root.left,target);
        }
    }
    //-------------------------------------------------
    //delete node in a tree
    //consider it`s kids
    public TreeNode deleteNode(TreeNode root, int target){
        if(root == null) return root;
        if(root.key ==target){
            if(root.left==null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }else{
                //has two kids
                //case 1: the node need to replace for root has no kids or only has right kids
                if(root.right.left==null ){
                    root.right.left=root.left;
                    return root.right;
                }else{
                    //the node has left kids, we need to search for the minimum one to replace itself
                    TreeNode replace= deleteMin(root.right);
                    replace.left =root.left;
                    replace.right=root.right;
                    return replace;
                }

            }

        }else if(root.key>target){
            root.left=deleteNode(root.left,target);
        }else{
            root.right=deleteNode(root.right,target);
        }
        return root;

    }
    private TreeNode deleteMin(TreeNode node){
        while(node.left.left!= null){
            node = node.left;
        }
        TreeNode min= node.left;
        node.left=node.left.right;
        return min;
    }
    //---------------------------------------
    //preorder traversal, iteration
    public List<Integer> preOder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        //Corner case
        if(root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while(!stack.isEmpty()){
            TreeNode cur= stack.pollFirst();
            res.add(cur.key);
            if(cur.right!= null){
                stack.offerFirst(cur.right);
            }
            if(cur.left!= null){
                stack.offerFirst(cur.left);
            }
        }
        return res;
    }
    //-----------------------------------------
    //inorder traversal,iteration
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        // corner case
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        //traverse
        TreeNode cur = root;
        while(cur!= null ||!stack.isEmpty()){
            if(cur!=null){
                stack.offerFirst(cur);
                cur=cur.left;
            }else{
                // left side is empty
                cur =stack.pollFirst();
                res.add(cur.key);
                cur=cur.right;
            }
        }
        return res;
    }
    //----------------------------------------
    //postorder recursion
    public List<Integer> post(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        posthelper(root,res);
        return res;
    }
    private void posthelper(TreeNode root, List<Integer> res){
        if(root== null) return;
        posthelper(root.left,res);
        posthelper(root.right,res);
        res.add(root.key);
        return;

    }

    //------------------------------------------------
    //postorder iteration
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //corner case
        if(root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        TreeNode pre =null;

        while(!stack.isEmpty()){
            TreeNode cur= stack.peekFirst();
            //考虑能不能往下走
                 //可以往下走,
            if(pre == null ||cur ==pre.left ||cur ==pre.right){
                //check first kid first, each time deal with one kid
                if(cur.left!= null){
                    stack.offerFirst(cur.left);
                }else if(cur.right!= null){
                    //if left kids has been deal with or has no left kid,then this time deal with right one
                    stack.offerFirst(cur.right);
                }else{
                    //no kids for this node, should be added to the res
                    stack.pollFirst();
                    res.add(cur.key);
                }
                //如果不能往下走
                    //case1：从右边回来，或者这个节点没有右边的孩子
            }else if(pre ==cur.right ||pre ==cur.left && cur.right==null){
                stack.pollFirst();
                res.add(cur.key);
            }else{
                // case2:此节点有右孩子
                stack.offerFirst(cur.right);
            }
            pre =cur;
        }
        return res;

    }
    public static void main(String[] args) {
        BinaryTree bt= new BinaryTree();
        TreeNode root = new TreeNode(5);
        root.left=new TreeNode(3);
        root.right=new TreeNode(8);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(4);
        root.right.left=new TreeNode(7);
        root.right.right=new TreeNode(11);
        root.right.right.left=new TreeNode(9);
        root.right.right.right=new TreeNode(13);
        List<Integer>postRecursion = bt.post(root);
        System.out.println(postRecursion);
        List<Integer> postInteration = bt.postOrder(root);
        System.out.println(postInteration);
    }
}
