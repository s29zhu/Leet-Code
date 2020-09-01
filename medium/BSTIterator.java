package leetcode.medium;

import java.util.ArrayList;
import java.util.Stack;
/*
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class BSTIterator {
/*
 * get the left most nodes and save them in the stack
 * next() pop out the smallest value from the stack, and push the right child tree's left nodes 
 * hasNext return true when stack is not empty, 
 */
	Stack<TreeNode> stack=new Stack<TreeNode>();
    public BSTIterator(TreeNode root) {
        TreeNode p=root; 
    	while(p!=null) {
    		stack.push(p);
    		p=p.left;
    	}
    }
    
    /** @return the next smallest number */
    public int next() {
    	TreeNode p=stack.pop();
    	int retval=p.val;
    	p=p.right;
    	while(p!=null) {
    		stack.push(p);
    		p=p.left;
    	}

    	return retval;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
    	if(stack.isEmpty()) return false;
    	else return true;
    }
	
}
