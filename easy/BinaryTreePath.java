package leetcode.easy;

import java.util.List;
import java.util.ArrayList;
import java.lang.String;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
 * Given a binary tree, return all root-to-leaf paths.
 * Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */

public class BinaryTreePath {
    public static List<String> binaryTreePaths(TreeNode root) {
    	List<String> ret=new ArrayList<String>();
    	if(root==null)
    		return ret;
    	String r_s=String.valueOf(root.val);
    	
        if(root.left==null && root.right==null) {        	
        	ret.add(r_s);
        }
        if(root.left!=null) {
        	List<String>  l_ret=binaryTreePaths(root.left);
        	l_ret.forEach(item->ret.add(r_s.concat("->").concat(item)));
        }
        if(root.right!=null) {
        	List<String> r_ret=binaryTreePaths(root.right);
        	r_ret.forEach(item->ret.add(r_s.concat("->").concat(item)));
        }

        return ret;
    }
    
    public static void main(String args[]) {
    	List<String> ret=new ArrayList<String>();
    	TreeNode root=new TreeNode(5);
    	TreeNode l_1=new TreeNode(4);
    	TreeNode r_1=new TreeNode(3);
    	TreeNode r_2=new TreeNode(2);
    	TreeNode l_2=new TreeNode(1);
    	root.left=l_1;
    	root.right=r_1;
    	l_1.right=l_2;
    	l_1.left=r_2;
    	ret=binaryTreePaths(root);
        ret.forEach(item->System.out.println(item));
    }
}
