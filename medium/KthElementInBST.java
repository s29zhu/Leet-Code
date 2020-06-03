package leetcode.medium;

import java.util.Stack;

import leetcode.medium.TreeNode;
/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 

Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
public class KthElementInBST {
    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack=new Stack<TreeNode>();
        TreeNode np=root;
        while(!stack.isEmpty() || np!=null) {
        	while(np!=null) {
        		stack.push(np);
        		np=np.left;
        	}
        	np=stack.pop();
        	k--;
        	if(k==0) return np.val;
        	np=np.right;
        }
        return -1;
    }
    
    public static void main(String args[]) {
    	TreeNode n1= new TreeNode(1);
    	TreeNode n2= new TreeNode(2);
    	TreeNode n3= new TreeNode(3);
    	TreeNode n4= new TreeNode(4);
    	
    	n3.left=n1;
    	n3.right=n4;
    	n1.right=n2;
    	n1.left=null;
    	n2.left=null;
    	n2.right=null;
    	n4.left=null;
    	n4.right=null;
    	
    	System.out.print(kthSmallest(n3,4));
    }
}
