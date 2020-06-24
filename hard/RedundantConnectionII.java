package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for 
 * which all other nodes are descendants of this node, plus every node has exactly one parent, except for the 
 * root node which has no parents.
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), 
 * with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not 
 * an edge that already existed.
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed 
 * edge connecting nodes u and v, where u is a parent of child v.
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers,
 * return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3

Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnectionII {
	/*
	 * Analysis: 
	 * extra edge happens, 
	 * 1. when 1 node has two different parents, 
	 * 2. a node becomes its own parent
	 * 3. combination of 1 and 2
	 */
	public static class UnionFind{
		HashMap<Integer, Integer> map;
		ArrayList<int[]> results;
		int [][] edges_;
		public UnionFind(int[][] edges){
			int l=edges.length;
			if(l!=0) {
				this.map=new HashMap<Integer, Integer>();
				this.results=new ArrayList<int[]>();
				edges_=new int[l][2];
			}
			for(int i=0;i<l;i++) {
				edges_[i][0]=edges[i][0];
				edges_[i][1]=edges[i][1];
			}
			for(int i=0;i<l;i++) {
				map.put(edges[i][1], -1);
			}

		}		
		public int findValidParent(int key, int value) {
			int res=0;
			if(!this.map.keySet().contains(value)) return value;
			else {
				res=value;
				while(map.containsKey(res)&&map.get(res)>0&&map.get(res)!=value) {
					res=map.get(res);
				}
			}
			return res;
		}
		
		public boolean updateMap(int [] edge) {
			boolean res=true;
			for(Integer key: this.map.keySet()) {
				int parent=findValidParent(key, map.get(key));
				if(parent==key) {
					boolean not_in_results=true;
					for(int [] item: results) {
						if(item[0]==edge[0]&&item[1]==edge[1]) {
							not_in_results=false;
							break;
						}
					}
					if(not_in_results) results.add(edge);
					res=false;
				}
				else {
					map.put(key,parent);
				}
			}
			return res;
		}
		
		public boolean union(int []edge) {
			boolean success=true;
			//1. the node is already in the tree and has a parent
			if(map.get(edge[1])>0) {
				results.add(edge);
				int []edge_=new int[2];
				edge_[1]=edge[1];
				for(int i=0; i<edges_.length;i++) {
					if(edges_[i][1]==edge[1]) {edge_[0]=edges_[i][0]; break;}
				}
				results.add(edge_);
				success=false;
			}
			this.map.put(edge[1], edge[0]);
			//2. the node's parent is itself
			success=success?updateMap(edge):success;
			return success;
		}        	

	}
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int[] res={0,0};
        UnionFind uf=new UnionFind(edges);//all of the non-root node added to the map, but they have no parent
        for(int[] edge: edges) {
        	uf.union(edge);
        }
    	ArrayList<int[]> potential=new ArrayList<int[]>();
    	potential.addAll(uf.results);
    	ArrayList<int[]> remove=new ArrayList<int[]>();
    	for(int[] result: potential) {
    		UnionFind uf1=new UnionFind(edges);
    		for(int[] edge: edges) {
    			if(!(edge[0]==result[0]&&edge[1]==result[1])&&!uf1.union(edge)) {
    				remove.add(result); 
    				break;
    			}
    		}
    	}
    	if(remove.size()==0) res=potential.get(0);
    	else {
    		potential.removeAll(remove);
    		res=(potential.size()>0)?potential.get(0):res;
    	}
    	return res;
    }
    public static void main(String args[]) {
		/*
		 * Test case:
		 * Input: [[3,4],[4,1],[1,2],[2,3],[5,1]]
		 *  Output: [4,1]
    	 */
    	//int [][]edges= {{4,3},{2,3}, {1,2}, {3,4},{1,5}};
    	//int [][] edges= {{1,2}, {2,3}, {3,1}};
    	//int [][] edges= {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
    	//int [][] edges= {{2,3},{1,2}, {1,3}};
    	//int [][] edges= {{3,4},{4,1},{1,2},{2,3},{5,1}};
    	int [][] edges={{37,30},{21,34},{10,40},{8,36},{18,10},{50,11},{13,6},{40,7},{14,38},{41,24},{32,17},{31,15},{6,27},
    			{45,3},{30,42},{43,26},{9,4},{4,31},{1,29},{5,23},{44,19},{15,44},{49,20},{26,5},{23,50},{48,41},{47,22},{3,46},
    			{11,16},{12,35},{33,50},{34,45},{38,2},{2,32},{24,49},{35,37},{29,13},{46,48},{28,12},{7,21},{27,18},{17,39},
    			{42,14},{20,47},{36,1},{22,9},{25,8},{39,25},{16,28},{19,43}};
    	int [] res=findRedundantDirectedConnection(edges);
    	System.out.print(res[0]+" "+res[1]);
    }
}
