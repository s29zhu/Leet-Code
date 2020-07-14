package leetcode.hard;

import java.util.Stack;

/*
 * We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value 
of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node
 is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.

example 1
Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

example 2
Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

example 3
Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]
 

Note:

The number of nodes in the original tree is between 1 and 1000.
Each node will have a value between 1 and 10^9.
 */
public class RecoverATreeFromPreOrder {
	/*
	 * intuition: 
	 * 1. using a stack to keep the parent nodes, use count to note the current number of "-". 
	 * 2. walk through the string s, if it is -, count++, if it is digit count=0, the size of stack is count+1,
	 * create a node when encounter a number.
	 * 3. pop the parent node when is has both children or count+1 is smaller than the size
	 */
    public static TreeNode recoverFromPreorder(String S) {
        TreeNode root=null;
        Stack<TreeNode> stack=new Stack<TreeNode>();
        int d_count=0;
        int value=0;
        while(!S.isEmpty()) {
        	//if it is a number, the couting of - is done
        	if(S.charAt(0)>='0' && S.charAt(0)<='9' ) {
        		value=value*10+S.charAt(0)-'0';
        		S=S.substring(1);
        	}else if(S.charAt(0)=='-') { //if it is a -, that means it is the end of a number
        		if(value!=0) { // first - of the a new node
        			TreeNode new_node=new TreeNode(value); 
        			if(stack.isEmpty()) stack.add(new_node);
        			else {
        				while(d_count<stack.size()) stack.pop();
        				if(stack.peek().left==null) {
        					stack.peek().left=new_node;
        					stack.add(new_node);
        				}
        				else if(stack.peek().right==null) {
        					stack.peek().right=new_node; 
        					if(root==null && stack.size()==1) root=stack.peek();
        					stack.add(new_node);
        				}
        			}
        			value=0;
        			d_count=0;
        		}        		
        		d_count++;
        		S=S.substring(1);
        	}
        }
        //for the last node
		TreeNode new_node=new TreeNode(value);
		if(stack.isEmpty()) stack.add(new_node);
		else {
			while(d_count<stack.size()) stack.pop();
			if(stack.peek().left==null) {
				stack.peek().left=new_node;
				stack.add(new_node);
			}
			else if(stack.peek().right==null) {
				stack.peek().right=new_node; 
				if(root==null && stack.size()==1) root=stack.pop();
				stack.add(new_node);
			}
		}
		if(root==null) root=stack.get(0);
		return root;
    }
    
    public static void main(String args[]) {
    	String S="1-2--3--4-5--6--7";
    	TreeNode root=recoverFromPreorder(S);
    	System.out.println("done");
    }
}
