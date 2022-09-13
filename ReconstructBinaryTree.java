import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//213. Reconstruct Binary Tree With Preorder And Inorder
public class ReconstructBinaryTree {
    public TreeNode reconstruct1(int[] inOrder, int[] preOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return helper(inOrder, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1, map);
    }

    private TreeNode helper(int[] in, int inleft, int inRight,
                            int[] pre, int preleft, int preRight,
                            Map<Integer, Integer> map) {
        if (inleft > inRight) return null;
        TreeNode root = new TreeNode(pre[preleft]);
        int leftSize = map.get(root.key) - inleft;
        int mid = map.get(root.key);
        root.left = helper(in, inleft, mid - 1, pre, preleft + 1, preleft + leftSize, map);
        root.right = helper(in, mid + 1, inRight, pre, preleft + leftSize + 1, preRight, map);
        return root;
    }

    //214 214. Reconstruct Binary Tree With Postorder And Inorder
    public TreeNode reconstruct2(int[] inOrder, int[] postOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return postHelper(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1, map);
    }

    private TreeNode postHelper(int[] in, int inLeft, int inRight,
                                int[] post, int postleft, int postRight, Map<Integer, Integer> map) {
        if (postleft>postRight) return null;
        TreeNode root = new TreeNode(post[postRight]);
        int mid = map.get(root.key);
        int rightsize = inRight-mid;
        root.right = postHelper(in,mid+1,inRight,post,postRight-rightsize,postRight-1,map);
        root.left = postHelper(in, 0,mid-1,post,postleft,postRight-rightsize-1,map);

        return root;
    }

    public TreeNode reconstruct3(int[] inOrder, int[] levelOrder) {
        //level order convert to linked list,
        List<Integer> level = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<levelOrder.length;i++){
            level.add(levelOrder[i] );
            map.put(inOrder[i],i );
        }
        return helper3(map,level);
    }

    private TreeNode helper3(Map<Integer, Integer> map, List<Integer> level) {
        if(level.isEmpty()){
            return null;
        }
        TreeNode root = new TreeNode(level.remove(0));

        List<Integer> left = new LinkedList<>();
        List<Integer>right = new LinkedList<>();
        for(int num:level){
            if(map.get(num)<map.get(root.key)){
                left.add(num);
            }else{
                right.add(num);
            }
        }
        root.left = helper3(map,left);
        root.right = helper3(map,right);
        return root;
    }


    public static void main(String[] args) {
        ReconstructBinaryTree reconstructBinaryTree = new ReconstructBinaryTree();
//        int[]post ={6,7,5,1,9,10,4};
//        int[]in ={1,6,5,7,4,10,9};
//        TreeNode root = reconstructBinaryTree.reconstruct2(in,post);
//        TreePrinter.print(root);
        int[]in ={1,6,5,7,4,10,9};
        int[]level ={4,1,10,5,9,6,7};
        TreeNode root = reconstructBinaryTree.reconstruct3(in,level);
        TreePrinter.print(root);

    }
}


