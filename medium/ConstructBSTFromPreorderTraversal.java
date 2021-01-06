package leetcode.medium;
/*
 * Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

 

Constraints:

1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
The values of preorder are distinct.
 */
public class ConstructBSTFromPreorderTraversal {
	public static TreeNode helper(int[] preorder, int s, int e) {
		TreeNode res=null;
		if(s>e) return res;
		else if(s==e) {
			res=new TreeNode(preorder[s]);
			return res;
		}
		res=new TreeNode(preorder[s]);
		int i=0;
		for(i=s+1; i<=e; i++) {
			if(preorder[i]>preorder[s]) break;
		}
		res.left=helper(preorder,s+1,i-1);
		res.right=helper(preorder,i,e);
		return res;
	}
    public static TreeNode bstFromPreorder(int[] preorder) {
    	TreeNode tree=helper(preorder, 0, preorder.length-1);
        return tree;
    }
    public static void main(String args[]) {
    	int [] preorder= {3,1,4};
    	bstFromPreorder(preorder);
    }
}
