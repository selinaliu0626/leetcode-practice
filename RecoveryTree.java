

//morris travesal
public class RecoveryTree {
    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.key;
        a.key = b.key;
        b.key = tmp;
    }

    public void recoverTree(TreeNode root) {
        // predecessor is a Morris predecessor.
        // In the 'loop' cases it could be equal to the node itself predecessor == root.
        // pred is a 'true' predecessor,
        // the previous node in the inorder traversal.
        TreeNode x = null, y = null, pred = null, predecessor = null;

        while (root != null) {
            // If there is a left child
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step left
                // and then right till you can.
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root)
                    predecessor = predecessor.right;

                // set link predecessor.right = root
                // and go to explore left subtree
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // break link predecessor.right = root
                // link is broken : time to change subtree and go right
                else {
                    // check for the swapped nodes
                    if (pred != null && root.key < pred.key) {
                        y = root;
                        if (x == null) x = pred;
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                // check for the swapped nodes
                if (pred != null && root.key < pred.key) {
                    y = root;
                    if (x == null) x = pred;
                }
                pred = root;

                root = root.right;
            }
        }
        swap(x, y);
    }

    public static void main(String[] args) {
        RecoveryTree rt = new RecoveryTree();
        TreeNode root =new TreeNode(3);
        root.left=new TreeNode(1);
        root.right =new TreeNode(4);
        root.right.left = new TreeNode(2);
        rt.recoverTree(root);
        TreePrinter.print(root);

    }
}
