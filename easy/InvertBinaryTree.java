package leetcode.easy;
import structures.TreeNode;
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(root==null || (root.left==null&&root.right==null)) return root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode p=root.left;
        root.left=root.right;
        root.right=p;
        return root;
    }
}
