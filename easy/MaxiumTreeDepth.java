package leetcode.easy;

public class MaxiumTreeDepth {
    public int maxDepth(TreeNode root) {
    	int results=0,left=0, right=0;
    	if(root==null) return results;
    	left=maxDepth(root.left);
    	right=maxDepth(root.right);
    	if(left>=right) results=left+1;else results=right+1;
    	return results;
    }
    public static void main(String []args) {
    	
    }
}
