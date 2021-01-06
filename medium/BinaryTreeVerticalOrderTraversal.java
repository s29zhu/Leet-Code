package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */
public class BinaryTreeVerticalOrderTraversal {
	/*
	 * Intuition:
	 * 1. Consider the root is in column i, then the left child is in column i-1, and the right child will be column i+1
	 * 2. organize the element from top to bottom, get the level number to determine from top to bottom
	 * 3. left to right (column number will help this). 
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 */
	public static HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> helper(
																	HashMap<Integer, HashMap<Integer,ArrayList<Integer>>> map,
																	TreeNode node, 
																	int column,
																	int level)
	{
		if(node==null) return map;
		HashMap<Integer, ArrayList<Integer>> m=map.getOrDefault(column, new HashMap<Integer,ArrayList<Integer>>());
		ArrayList<Integer> list=m.getOrDefault(level, new ArrayList<Integer>());
		list.add(node.val);
		m.put(level, list);
		map.put(column, m);
		map=helper(map, node.left, column-1, level+1);
		map=helper(map, node.right, column+1, level+1);
		return map;
	}	
	
    public static List<List<Integer>> verticalOrder(TreeNode root) {
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
    	if(root==null) return res;    	
    	HashMap<Integer, HashMap<Integer,ArrayList<Integer>>> map=new HashMap<Integer, HashMap<Integer,ArrayList<Integer>>>();
    	HashMap<Integer, ArrayList<Integer>> m=new HashMap<Integer,ArrayList<Integer>>();
    	ArrayList<Integer> list=new ArrayList<Integer>();
    	list.add(root.val);
    	m.put(0, list);
    	map.put(0, m);
    	map=helper(map, root.left, -1, 1);
    	map=helper(map, root.right, 1, 1);    	
    	// Organize the maps, start from left to right, organize the key value from small to large
    	// Within the internal map, start from top to bottom, organize the key from small to large
    	int []columns=new int[map.size()];
    	int c=0;
    	for(Integer k: map.keySet()) {
    		columns[c]=k;
    		c++;
    	}
    	Arrays.parallelSort(columns);
    	for(c=0;c<columns.length;c++) {
    		m=map.get(columns[c]);
    		int[]levels=new int[m.size()];
    		int l=0;
    		for(Integer k: m.keySet()) { levels[l]=k; l++;}
        	Arrays.parallelSort(levels);
        	list=new ArrayList<Integer>();
        	for(l=0;l<m.size();l++) {list.addAll(m.get(levels[l]));}
        	res.add(list);
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	//[3,9,8,4,0,1,7,null,null,null,2,5]
    	TreeNode n1=new TreeNode(3);
    	TreeNode n2=new TreeNode(9);
    	TreeNode n3=new TreeNode(8);
    	TreeNode n4=new TreeNode(4);
    	TreeNode n5=new TreeNode(0);
    	TreeNode n6=new TreeNode(1);
    	TreeNode n7=new TreeNode(7);
    	TreeNode n8=new TreeNode(2);
    	TreeNode n9=new TreeNode(5);
    	n1.left=n2;
    	n1.right=n3;
    	n2.left=n4;
    	n2.right=n5;
    	n3.left=n6;
    	n3.right=n7;
    	n4.left=null;
    	n4.right=null;
    	n5.right=n8;
    	n5.left=null;
    	n6.left=n9;
    	n6.right=null;
    	n7.left=null;
    	n7.right=null;
    	n8.right=null;
    	n8.left=null;
    	n9.left=null;
    	n9.right=null;
    	verticalOrder(n1);
    }
}
