package leetcode.medium;
/*
 * 
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
 */
public class MaximumBinaryTree {
	public static TreeNode helper(int []nums, int s, int e) {
		int max=Integer.MIN_VALUE, index=s;
		if(s>e || e<0 || e>=nums.length || s<0 || s>=nums.length) return null;
		if(s==e) {
			TreeNode node=new TreeNode(nums[s]);
			node.left=null;
			node.right=null;
			return node;
		}
		for(int i=s; i<=e; i++) 
			if(nums[i]>max) { max=nums[i]; index=i;}
		
		TreeNode node=new TreeNode(max);
		TreeNode left=helper(nums, s, index-1);
		TreeNode right=helper(nums, index+1, e);
		node.left=left;
		node.right=right;
		return node;
	}
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode res=helper(nums,0,nums.length-1);
        return res;
    }
    public static void main(String args[]) {
    	int []nums= {3,2,1,6,0,5};
    	constructMaximumBinaryTree(nums);
    }
}
