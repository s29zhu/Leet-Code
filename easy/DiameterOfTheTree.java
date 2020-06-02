package leetcode.easy;
/*
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */

/*
Analysis: Pass the root, sum=left_path+right_path
		  not pass the root, left_path > sum or right_path > sum
 */
public class DiameterOfTheTree {
    public static int diameterOfBinaryTree(TreeNode root) {
       int res=0;
       if(root==null || (root.left==null && root.right==null)) return 0;
       int left=diameterOfBinaryTree(root.left);//? is left node in the middle of the path?
       int right=diameterOfBinaryTree(root.right);
       res=Math.max(left,right);
       int l_hp=height(root.left);
       int r_hp=height(root.right);
       
       res=Math.max(res, l_hp+r_hp);
       return res;
    }
    public static int height(TreeNode node) {
    	int res=0;
    	if(node==null) return 0;
    	res=Math.max(height(node.left)+1, height(node.right)+1);
    	return res;
    }
    public static void main(String[] args) {
    	TreeNode n1=new TreeNode(1);
    	TreeNode n2=new TreeNode(2);
    	TreeNode n3=new TreeNode(3);
    	TreeNode n4=new TreeNode(4);
    	TreeNode n5=new TreeNode(5);
    	
    	n1.left=n2;
    	n1.right=n3;
    	n2.left=n4;
    	n2.right=n5;
    	
    	System.out.println(diameterOfBinaryTree(n1));
    }
}
