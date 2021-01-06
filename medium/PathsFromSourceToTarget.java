package leetcode.medium;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
/*
 * Given a directed acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Constraints:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class PathsFromSourceToTarget {
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    	ArrayList<List<Integer>> res=new ArrayList<List<Integer>>();
    	ArrayList<List<Integer>> potentials=new ArrayList<List<Integer>>();
    	Queue<Integer> que=new LinkedList<Integer>();
    	ArrayList<Integer> list=new ArrayList<Integer>();
    	list.add(0);
    	potentials.add(list);
    	int last=graph.length-1;
    	que.add(0);
    	while(!que.isEmpty()) {
    		int val=que.poll();
    		ArrayList<List<Integer>> temp=new ArrayList<List<Integer>>();
    		temp.addAll(potentials);
    		for(int k=0; k<temp.size(); k++) {
    			List<Integer> l=temp.get(k);
    			if(l.get(l.size()-1)==val) {
    				for(int i: graph[val]) {
    					List<Integer> nl=new ArrayList<Integer>();
    					nl.addAll(l);
    					nl.add(i);
    					potentials.add(nl);
    					que.add(i);
    				}
    				if(val!=last) potentials.remove(k);
    				break;
    			}
    		}
    	}
    	for(List<Integer> l: potentials) {
    		if(l.get(l.size()-1)==last) res.add(l);
    	}
        return res;
    }
    public static void main(String args[]) {
    	//[[1,2,3],[2],[3],[]]
    	List<List<Integer>> graph=new ArrayList<List<Integer>>();
    	ArrayList<Integer> list=new ArrayList<Integer>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	graph.add(list);
    	ArrayList<Integer> list1=new ArrayList<Integer>();
    	list1.add(2);
    	graph.add(list1);
    	ArrayList<Integer> list2=new ArrayList<Integer>();
    	list2.add(3);
    	graph.add(list2);
    	ArrayList<Integer> list3=new ArrayList<Integer>();
    	graph.add(list3);
    	int [][]graph1= {{2},{3},{1}};
    	allPathsSourceTarget(graph1);
    }
}
