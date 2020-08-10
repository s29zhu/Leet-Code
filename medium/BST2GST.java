package leetcode.medium;

import java.util.Stack;

/*
 * Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the values of the original tree that are greater than or equal to node.val.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Constraints:
The number of nodes in the tree is between 1 and 100.
Each node will have value between 0 and 100.
The given tree is a binary search tree.
 */
public class BST2GST {
	/*
	 * intuition
	 * in order traverse, 
	 */
    public static TreeNode bstToGst(TreeNode root) {
    	Stack<TreeNode> stack=new Stack<TreeNode>();
    	TreeNode p=root;
    	int pre_val=0;
    	while(!stack.isEmpty() || p!=null) {
    		while(p != null) {
    			stack.push(p);
    			p=p.right;
    		}
    		p=stack.pop();
    		p.val+=pre_val;
            pre_val=p.val;
    		p=p.left;
    	}
        return root;
    }
    public static void main(String args[]) {
    	
    }
}
