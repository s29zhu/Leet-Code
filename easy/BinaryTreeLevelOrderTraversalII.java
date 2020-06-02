package leetcode.easy;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
/*
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 */
public class BinaryTreeLevelOrderTraversalII {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
    	List<List<Integer>> res= new ArrayList<List<Integer>>();
    	Queue<TreeNode> q1=new LinkedList<TreeNode>(), q2=new LinkedList<TreeNode>();
    	if(root != null) q1.add(root);
    	TreeNode node=null;
    	while(!q1.isEmpty() || !q2.isEmpty()) {   
    		List<Integer> temp1=new ArrayList<Integer>();
    		while(!q1.isEmpty()) {
    			node=q1.poll();
    			temp1.add(node.val);
        		if(node.left != null)q2.add(node.left);
        		if(node.right != null)q2.add(node.right);
    		}
    		if(!temp1.isEmpty()) res.add(temp1);
    		List<Integer> temp2=new ArrayList<Integer>();
    		while(!q2.isEmpty()) {
    			node=q2.poll();
    			temp2.add(node.val);
        		if(node.left != null)q1.add(node.left);
        		if(node.right != null)q1.add(node.right);
    		}
    		if(!temp2.isEmpty()) res.add(temp2);
    	}
    	Collections.reverse(res);
    	return res;
    }
    public static void main(String args[]) {
    	TreeNode root= new TreeNode(1);
    	TreeNode l= new TreeNode(2);
    	TreeNode r= new TreeNode(2);
    	TreeNode l1= new TreeNode(3);
    	TreeNode l2= new TreeNode(4);
    	TreeNode r1= new TreeNode(4);
    	TreeNode r2= new TreeNode(3);
    	root.left=l;
    	root.right=r;
    	l.left=l1;
    	l.right=r1;
    	r.left=l2;
    	r.right=r2;
    	l1.left=null;
    	l1.right=null;
    	r1.left=null;
    	r1.right=null;
    	l2.left=null;
    	l2.right=null;
    	r2.left=null;
    	r2.right=null;
    	List<List<Integer>> res=levelOrderBottom(root);
    	System.out.print(res);
    }
}
