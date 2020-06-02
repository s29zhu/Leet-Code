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
    	Node nc=null, c=null, res=nc;
    	LinkedList<Node> q=new LinkedList<Node>();
    	HashMap<Integer, Node> map=new HashMap<Integer, Node>();
    	
    	if(node==null) return null;
    	
    	q.add(node);  
    	nc=new Node(node.val);
    	map.put(node.val, nc);
    	res=nc;
    	while(!q.isEmpty()) {
    		c=q.poll();
			if(c.neighbors==null) continue;
			for(Node item: c.neighbors) {
				if(map.getOrDefault(item.val,null)==null) {
					q.add(item);
					nc=new Node(item.val);
					map.put(item.val,nc);
					map.get(c.val).neighbors.add(nc);
				}else {
					nc=map.get(item.val);
					map.get(c.val).neighbors.add(nc);
				}
			}    		
    	}
    	map.clear();
    	return res;
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
