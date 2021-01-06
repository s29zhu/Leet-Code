package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.

 

Example 1:


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]
Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]
Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]
Example 5:


Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]
 

Constraints:

Each tree has at most 5000 nodes.
Each node's value is between [-10^5, 10^5].
 */
public class AllElementsIn2BST {
    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res=new ArrayList<Integer>();
        Stack<TreeNode> s=new Stack<TreeNode>();
        TreeNode p=root1; 
        while(!s.isEmpty() || p!=null) {
        	while(p!=null) {
        		s.push(p);
        		p=p.left;
        	}
        	p=s.pop();
        	res.add(p.val);
        	p=p.right;
        }
        s.clear();
        p=root2;
        while(!s.isEmpty() || p!=null) {
        	while(p!=null) {
        		s.push(p);
        		p=p.left;
        	}
        	p=s.pop();
        	res.add(p.val);
        	p=p.right;
        }
        res.sort((i1,i2)->{
        	return i1-i2;
        });
        return res;
    }
    
    public static void main(String args[]) {
    	
    }
}
