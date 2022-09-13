
import mymethod.ListNode;

//211. Reconstruct Binary Search Tree With Postorder Traversal
public class BuildBinarySearchTree {
    public TreeNode reconstruct(int[] post) {
        int[] index = {post.length-1};
        return helper(index,post,Integer.MIN_VALUE);
    }

    private TreeNode helper(int[] index, int[] post, int min) {
        //base case
        //如果越界或者当前值小于min
        if(index[0]<0 || post[index[0]]<=min){
            return null;
        }

        TreeNode root = new TreeNode(post[index[0]--]);
        //右子树的值应当大于根结点
        root.right =helper(index,post,root.key);
        //左子树则可以无限小
        root.left = helper(index,post,min);
        return root;
    }


    //2-level order
    //211
    public TreeNode reconstruct2(int[] level) {
        TreeNode root = null;
      for(int i =0;i< level.length;i++) {
          root = helper2(root, level[i]);
      }
      return root;

    }

    private TreeNode helper2(TreeNode root, int key) {
        if(root == null){
            root =getNode(key);
            return root;
        }
        if(key <root.key){
            root.left =helper2(root.left,key);
        }else{
            root.right=helper2(root.right,key);
        }
        return root;

    }
    private TreeNode getNode(int key){
        TreeNode node = new TreeNode(key);
        node.left =null;
        node.right=null;
        return node;

    }


    //3 preorder
    //210
    public TreeNode reconstruct3(int[] pre) {
        int[] index = {0};
        return helper3(pre,index,Integer.MAX_VALUE);

    }

    private TreeNode helper3(int[] pre, int[] index, int max ){
        if(index[0]>=pre.length ||pre[index[0]]>=max){
            return null;
        }
        TreeNode root = new TreeNode(pre[index[0]++]);
        root.left = helper3(pre,index,root.key);
        root.right=helper3(pre,index,max);
        return root;
    }


    //4 sort array
    public TreeNode sortedArrayToBST(int[] num) {
        TreeNode root = findroot(num,0,num.length-1);
        return root;

    }
    private TreeNode findroot(int[]a, int left,int right){
        //base case
        if(left>right)return null;
        int mid =right-(right-left)/2;
        TreeNode root = new TreeNode(a[mid]);
        root.left=findroot(a,left,mid-1);
        root.right=findroot(a,mid+1,right);
        return root;
    }
    public TreeNode sortedListToBST(ListNode head) {
        // Write your solution here
        if(head == null) return null;
       ListNode mid =findMid(head);
       TreeNode root = convert(mid);
       if(head ==mid){
           return root;
       }
       root.left=sortedListToBST(head);
       root.right=sortedListToBST(mid.next);
       return root;

    }

    private ListNode findMid(ListNode head) {
        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }
    private TreeNode convert(ListNode node){
        TreeNode root = new TreeNode(node.value);
        return root;
    }


    public static void main(String[] args) {
        BuildBinarySearchTree bbst = new BuildBinarySearchTree();
//        TreeNode root =bbst.reconstruct2(new int[]{5,3,8,1,4,11});
//        TreePrinter.print(root);
//        TreeNode root =bbst.reconstruct3(new int[]{5,3,1,4,8,11});
//        TreePrinter.print(root);
//        TreeNode root = bbst.sortedArrayToBST(new int[]{1,3,4,5,8,11});
//        TreePrinter.print(root);
        ListNode head = ListNode.fromArray(new int[]{1,3,4,5,8,11});
        TreeNode root = bbst.sortedListToBST(head);
        TreePrinter.print(root);

    }
}
