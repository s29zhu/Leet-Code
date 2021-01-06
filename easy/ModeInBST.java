package leetcode.easy;
import structures.TreeNode;

import java.util.HashMap;
import java.util.ArrayList;

/*
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2
 

return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class ModeInBST {
    public static int[] findMode(TreeNode root) {
    	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    	ArrayList<Integer> list=new ArrayList<Integer>();
    	treeTravansal(root,map);
    	int max=0;
    	for(Integer item: map.keySet()) {
    		if(map.get(item)>max) max=map.get(item);
    	}
    	for(Integer item: map.keySet()) {
    		if(map.get(item)==max) list.add(item);
    	}
    	int [] res=new int[list.size()];
    	for(int i=0; i<list.size();i++) {
    		res[i]=list.get(i);
    	}
    	return res;
    }
    public static void treeTravansal(TreeNode root, HashMap<Integer,Integer> map) {
    	if(root==null) return ;
    	map.put(root.val,map.getOrDefault(root.val, 0)+1);
    	treeTravansal(root.left,map);
    	treeTravansal(root.right,map);
    }
    public static void main(String args[]) {
    	TreeNode n0=new TreeNode(1);
    	TreeNode n1=new TreeNode(2);
    	TreeNode n2=new TreeNode(2);
    	TreeNode n3=new TreeNode(4);
    	TreeNode n4=new TreeNode(3);
    	TreeNode n5=new TreeNode(4);
    	TreeNode n6=new TreeNode(3);
    	TreeNode n7=new TreeNode(4);
    	TreeNode n8=new TreeNode(1);	

    	n0.left=n1;
    	n0.right=n2;
    	n1.left=n3;
    	n1.right=n4;
    	n4.right=n5;
    	n5.right=n6;
    	n6.left=n7;
    	n2.right=n8;
    	
    	int [] res=findMode(n0);
    	for(int i=0; i<res.length;i++) {
    		System.out.println(res[i]);
    	}
    }
}
