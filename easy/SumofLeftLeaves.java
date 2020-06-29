package leetcode.easy;
import structures.TreeNode;

/*
 * Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumofLeftLeaves {
	public int _sum(TreeNode root, boolean left) {
        int sum=0;
        if(root.left==null && root.right==null && left) sum+=root.val;
        if(root.left != null) sum+=_sum(root.left, true);
        if(root.right != null) sum+=_sum(root.right, false);
        return sum; 
	}
    public int sumOfLeftLeaves(TreeNode root) {
        int sum=0;
        if(root == null || (root.left==null && root.right==null)) return sum;
        if(root.left != null) sum+=_sum(root.left, true);
        if(root.right != null) sum+=_sum(root.right, false);
        return sum;
    }
    public static void main(String []args) {
    	
    }
}
