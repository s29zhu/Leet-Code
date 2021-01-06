package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 * Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
};

public class EncodeNaryTreeToBinaryTree {
	public TreeNode EncodeHelper(List<Node> children) {
		if(children.isEmpty()) return null;
		TreeNode res=new TreeNode(children.get(0).val);
		TreeNode p=res;
		for(int i=1; i<children.size();i++) {
			p.left=new TreeNode(children.get(i).val);
			p.right=EncodeHelper(children.get(i-1).children);
			p=p.left;
		}
		p.left=null;
		p.right=EncodeHelper(children.get(children.size()-1).children);
		return res;
	}
    // Encodes an n-ary tree to a binary tree.
	/*
	 * Analysis: put the neighbor nodes to left child, and children to right side
	 */
    public TreeNode encode(Node root) {
        TreeNode root_b=new TreeNode(root.val);
        root_b.left=null;
        root_b.right=EncodeHelper(root.children);
        return root_b;
    }
	
    public List<Node> decodeHelper(TreeNode n){
    	if(n.right==null) return null;
    	List<Node> list=new ArrayList<Node>();
    	TreeNode p=n.right;
    	while(p!=null) {
    		Node node=new Node(p.val);
    		list.add(node);
    		node.children=decodeHelper(p);
    		p=p.left;
    	}
    	return list;
    }
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        Node root_n=new Node(root.val);
        root_n.children=decodeHelper(root);
        return root_n;
    }
    
    public static void main(String args[]) {
    	EncodeNaryTreeToBinaryTree test=new EncodeNaryTreeToBinaryTree();
    	List<Node> l1=new ArrayList<Node>();
    	Node n1=new Node(1,l1);
    	List<Node> l2=new ArrayList<Node>();
    	Node n2=new Node(2,l2);
    	List<Node> l3=new ArrayList<Node>();
    	Node n3=new Node(3,l3);
    	List<Node> l4=new ArrayList<Node>();
    	Node n4=new Node(4,l4);
    	List<Node> l5=new ArrayList<Node>();
    	Node n5=new Node(5,l5);
    	List<Node> l6=new ArrayList<Node>();
    	Node n6=new Node(6,l6);
    	List<Node> l7=new ArrayList<Node>();
    	Node n7=new Node(7,l7);
    	n1.children.add(n2);
    	n1.children.add(n3);
    	n1.children.add(n4);
    	n2.children.add(n5);
    	n2.children.add(n6);
    	n3.children.add(n7);
    	
    	TreeNode bt=test.encode(n1);
    	Node t=test.decode(bt);
    	System.out.print("done with testing");
    }
}
