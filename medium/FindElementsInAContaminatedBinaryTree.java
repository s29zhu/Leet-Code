package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
 * Given a binary tree with the following rules:

root.val == 0
If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

You need to first recover the binary tree and then implement the FindElements class:

FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
bool find(int target) Return if the target value exists in the recovered binary tree.
 */
public class FindElementsInAContaminatedBinaryTree {
	TreeNode root;
	/*
	 * BFS
	 */
    public FindElementsInAContaminatedBinaryTree(TreeNode root) {
        Queue<TreeNode> que=new LinkedList<TreeNode>();
        root.val=0;
        que.add(root);
        this.root=root;
        while(!que.isEmpty()) {
        	TreeNode cur=que.poll();
        	if(cur.left!=null) {
        		cur.left.val=cur.val*2+1;
        		que.add(cur.left);
        	}
        	if(cur.right!=null) {
        		cur.right.val=cur.val*2+2;
        		que.add(cur.right);
        	}
        }
    }
    
    /*
     * ith layer, 2 * i
     */
    public boolean find(int target) {
    	Stack<Integer> stack=new Stack<Integer>();
    	TreeNode cur=root;
        stack.add(target);
    	while(target != 0) {
    		if((target&1)==1) { target/=2; stack.add(target);}
    		else{ target=(target-2)/2; stack.add(target);}
    	}
        
        target=stack.pop();
    	while(!stack.isEmpty()) {
    		target=stack.pop();
    		if(cur.left!=null && cur.left.val==target) cur=cur.left;
    		else if(cur.right!=null && cur.right.val==target) cur=cur.right;
    		else return false;
    	}
        return true;
    }
    
    public static void main(String args[]) {
    	TreeNode n1=new TreeNode(-1);
    	TreeNode n2=new TreeNode(-1);
    	n1.left=null;
    	n1.right=n2;
    	n2.left=null;
    	n2.right=null;
    	FindElementsInAContaminatedBinaryTree tree=new FindElementsInAContaminatedBinaryTree(n1);
    	tree.find(1);
    }
}
