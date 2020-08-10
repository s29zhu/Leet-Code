package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.
[61,13,46,null,null,null,56,72]
            61
      13        46
    n    n     n   56
                  72 
 */
public class SumOfNodesWithEvenValuedGrandParent {
    public static int sumEvenGrandparent(TreeNode root) {
    	Queue<TreeNode> q=new LinkedList<TreeNode>();
    	int sum=0;
    	q.add(root);
    	while(!q.isEmpty()) {
    		TreeNode cur=q.poll();
    		if(cur.val%2==0) {
    			if(cur.left!=null) {
    				if(cur.left.left!=null) sum+=cur.left.left.val;
    				if(cur.left.right!=null) sum+=cur.left.right.val;
    				
    			}
    			if(cur.right!=null) {
    				if(cur.right.left!=null) sum+=cur.right.left.val;
    				if(cur.right.right!=null) sum+=cur.right.right.val;
    			}
    		}
    		if(cur.left!=null) q.add(cur.left);
    		if(cur.right!=null) q.add(cur.right);
    	}
        return sum;
    }
    
    public static void main(String args[]) {
    	
    }
}
