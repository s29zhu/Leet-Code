package leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;

/*
 * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 */
public class CloneGraph {
    public static Node cloneGraph(Node node) {
    	Node c=null;
    	LinkedList<Node> q=new LinkedList<Node>();
    	HashMap<Integer, Node> map=new HashMap<Integer, Node>();
    	
    	if(node==null) return null;
    	
    	q.add(node);  
    	map.put(node.val, new Node(node.val));
    	while(!q.isEmpty()) {
    		c=q.poll();
    		for(Node n: c.neighbors) {
    			if(!map.containsKey(n.val)) {
    				map.put(n.val, new Node(n.val));
    				q.add(n);
    			}
				map.get(c.val).neighbors.add(map.get(n.val));
    		}   		
    	}

    	return map.get(node.val);
    }
    
    public static void main(String arg[]) {
    	Node n1=new Node(1);
    	Node n2=new Node(2);
    	Node n3=new Node(3);
    	
    	n1.neighbors.add(n2);
    	n1.neighbors.add(n3);
    	n2.neighbors.add(n1);
    	n2.neighbors.add(n3);
    	n3.neighbors.add(n1);
    	n3.neighbors.add(n2);
    	
    	cloneGraph(n1);
    }
}
