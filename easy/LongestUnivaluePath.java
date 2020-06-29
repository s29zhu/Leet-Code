package leetcode.easy;
import structures.TreeNode;
/*
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

 

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2

 

Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2

 

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUnivaluePath {
	
    public static int longestUnivaluePath(TreeNode root) {
    	int res=0;
        int []left= {0,0}, right= {0,0};
        if(root==null) return 0;
        left=helper(root.left, root.val);//left[0] with root, left[1] without root
        right=helper(root.right, root.val);
        
        res=Math.max(left[1], right[1]);
        return Math.max(res, left[0]+right[0]);
    }
    
    public static int [] helper(TreeNode node, int parent) {
    	int []res= {0,0},left= {0,0}, right= {0,0};//res[0] with node parent path, res[1] without node parent path length
    	if(node==null) return res;    	
    	left=helper(node.left, node.val);
    	right=helper(node.right, node.val);
    	if(node.val==parent) res[0]=Math.max(left[0], right[0])+1; //get the path include node parent
    	res[1]=Math.max(left[1], right[1]);
    	res[1]=Math.max(res[1], left[0]+right[0]);
    	return res;
    }
    public static void main(String args[]) {
    /*
     * 				1
     * 					1
     * 		  		1		1
     * 			  1	  1    1  1
     */    	
    	TreeNode n0=new TreeNode(1);
    	TreeNode n1=new TreeNode(1);
    	TreeNode n2=new TreeNode(1);
    	TreeNode n3=new TreeNode(1);
    	TreeNode n4=new TreeNode(1);
    	TreeNode n5=new TreeNode(1);
    	TreeNode n6=new TreeNode(1);
    	TreeNode n7=new TreeNode(1);
    	TreeNode n8=new TreeNode(1);	

    	n0.left=n1;
    	n0.right=n2;
    	n1.left=n3;
    	n1.right=n4;
    	n4.right=n5;
    	n5.right=n6;
    	n6.left=n7;
    	n2.right=n8;
		/*
		 * n0.right=n2; n2.left=n3; n2.right=n4; n3.left=n5; n3.right=n6; n4.left=n7;
		 * n4.right=n8;
		 */
    	
    	System.out.print(longestUnivaluePath(n0));
    }
}
