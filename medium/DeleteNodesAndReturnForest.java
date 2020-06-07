package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
/*
 * Analysis:
 * Tranverse the tree and match the tree node with to-be-deleted value, put the left and right into list
 * challenge is the find the parent node and set the child of parent node to be null;
 */
public class DeleteNodesAndReturnForest {
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res=new ArrayList<TreeNode>();
        if(root==null) return res;
        if(!isDelete(to_delete, root.val) ) res.add(root);

        res.addAll(helper(root.left, to_delete));
        if(root.left!=null && isDelete(to_delete,root.left.val) && !isDelete(to_delete, root.val)) {
        	root.left=null;
        }else if(root.left!=null && !isDelete(to_delete,root.left.val) && isDelete(to_delete, root.val)) {
        	res.add(root.left);
        }
        res.addAll(helper(root.right, to_delete));
        if(root.right!=null && isDelete(to_delete, root.right.val)&& !isDelete(to_delete, root.val)) {
        	root.right=null;
        }else if(root.right!=null && !isDelete(to_delete,root.right.val) && isDelete(to_delete, root.val)) {
        	res.add(root.right);
        }
        return res;
    }
    public static List<TreeNode> helper(TreeNode root, int[] to_delete){
        List<TreeNode> res=new ArrayList<TreeNode>();
        if(root==null) return res;
        
        res.addAll(helper(root.left, to_delete));
        if(root.left!=null && isDelete(to_delete,root.left.val) && !isDelete(to_delete, root.val)) {
        	root.left=null;
        }else if(root.left!=null && !isDelete(to_delete,root.left.val) && isDelete(to_delete, root.val)) {
        	res.add(root.left);
        }
        res.addAll(helper(root.right, to_delete));
        if(root.right!=null && isDelete(to_delete, root.right.val)&& !isDelete(to_delete, root.val)) {
        	root.right=null;
        }else if(root.right!=null && !isDelete(to_delete,root.right.val) && isDelete(to_delete, root.val)) {
        	res.add(root.right);
        }
        return res;
    }
    
    public static boolean isDelete(int[] to_delete, int val) {    	
    	for(int i=0;i<to_delete.length;i++) {
    		if(to_delete[i]==val) return true;
    	}
    	return false;
    }
    
    public static void main(String args[]) {
    	TreeNode n1=new TreeNode(1);
    	TreeNode n2=new TreeNode(2);
    	TreeNode n3=new TreeNode(3);
    	TreeNode n4=new TreeNode(4);
    	TreeNode n5=new TreeNode(5);
    	TreeNode n6=new TreeNode(6);
    	TreeNode n7=new TreeNode(7);

    	n1.left=n2;
    	n1.right=n3;
    	n2.left=n4;
    	n2.right=n5;
    	n3.left=n6;
    	n3.right=n7;
    	n4.right=null;
    	n4.left=null;
    	n5.left=null;
    	n5.right=null;
    	n6.left=null;
    	n6.right=null;
    	n7.left=null;
    	n7.right=null;
    	
    	int[] to_delete= {3,5};
    	 delNodes(n1, to_delete);
    }
}
