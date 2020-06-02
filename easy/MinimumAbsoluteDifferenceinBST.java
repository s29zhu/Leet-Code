package leetcode.easy;
/*
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 

Note:

There are at least two nodes in this BST.
 */
/*
 * Binary search trees keep their keys in sorted order (lesser keys to the left and greater keys to the right)
 * min(difference(root, most right node of left tree), difference(root, most left node of right tree))
 */
public class MinimumAbsoluteDifferenceinBST {
    public static int getMinimumDifference(TreeNode root) {
    	int res=0;
    	TreeNode node=null;
       if(root==null || (root.left==null && root.right==null)) return Integer.MAX_VALUE; 
       int left=getMinimumDifference(root.left);
       int right=getMinimumDifference(root.right);
       res=Math.min(left, right);
       
       if(root.right!=null) {
    	   node=root.right;
    	   while(node.left!=null)
    		   node=node.left;
    	   res=Math.min(Math.abs(root.val-node.val), res);
       }
       if(root.left!=null) {
    	   node=root.left;
    	   while(node.right!=null)
    		   node=node.right;
    	   res=Math.min(Math.abs(root.val-node.val), res);
       }
       return res;
    }
    
    public static void main(String args[]) {
    	TreeNode n1=new TreeNode(4);
    	TreeNode n2=new TreeNode(2);
    	TreeNode n3=new TreeNode(3);
    	
    	n2.right=n3;
    	n2.left=null;
    	n3.right=n1;
    	n3.left=null;
    	n1.left=null;
    	n1.right=null;
    	
    	System.out.println(getMinimumDifference(n2));
    }
}
