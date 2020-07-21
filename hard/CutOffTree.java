package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
In one step you can walk in any of the four directions top, bottom, left and right also when standing in a point which is a tree you can decide whether or not to cut off the tree.

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:

Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
 

Example 2:

Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
 

Example 3:

Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 

Constraints:

1 <= forest.length <= 50
1 <= forest[i].length <= 50
0 <= forest[i][j] <= 10^9
 */
public class CutOffTree {
	
	//BFS
	public static int helper(List<List<Integer>> forest, int[]start, int []end) {
		int R=forest.size(), C=forest.get(0).size();
		int []s={start[0],start[1],0};
		boolean [][]visited=new boolean[R][C];
		Queue<int[]> queue=new LinkedList<int[]>();
		
		for(int i=0; i<R; i++) Arrays.fill(visited[i], false);
		
		queue.add(s);
		visited[start[0]][start[1]]=true;
		if(s[0]==end[0] && s[1]==end[1]) return s[2];
		while(!queue.isEmpty()) {
			int[] cur=queue.poll();
		//left j-1
			if(cur[1]-1>=0 && cur[1]-1<C 
					&& forest.get(cur[0]).get(cur[1]-1)>0 
					&& !visited[cur[0]][cur[1]-1]) {
				int []next={cur[0], cur[1]-1, cur[2]+1};
				visited[next[0]][next[1]]=true;
				if(next[0]==end[0] && next[1]==end[1]) return next[2];
				queue.add(next);
			}
		//up i-1
			if(cur[0]-1>=0 && cur[0]-1<R 
					&& forest.get(cur[0]-1).get(cur[1])>0
					&& !visited[cur[0]-1][cur[1]]) {
				int []next={cur[0]-1, cur[1], cur[2]+1};
				visited[next[0]][next[1]]=true;
				if(next[0]==end[0] && next[1]==end[1]) return next[2];
				queue.add(next);
			}
		//right j+1
			if(cur[1]+1>=0 && cur[1]+1<C 
					&& forest.get(cur[0]).get(cur[1]+1)>0
					&& !visited[cur[0]][cur[1]+1]) {
				int []next={cur[0], cur[1]+1, cur[2]+1};
				visited[next[0]][next[1]]=true;
				if(next[0]==end[0] && next[1]==end[1]) return next[2];
				queue.add(next);
			}
		//down i+1
			if(cur[0]+1>=0 && cur[0]+1<R 
					&& forest.get(cur[0]+1).get(cur[1])>0
					&& !visited[cur[0]+1][cur[1]]) {
				int []next={cur[0]+1, cur[1], cur[2]+1};
				visited[next[0]][next[1]]=true;
				if(next[0]==end[0] && next[1]==end[1]) return next[2];
				queue.add(next);
			}
		}
		return -1;
	}
    public static int cutOffTree(List<List<Integer>> forest) {
    	/*
    	 * intuition: brutal force
    	 * 1. sort the element in the forest, find the min-height tree, 
    	 * cut it, set the value to be 1, and walk to the next min-height tree until all the trees are cut. 
    	 * Time complexity: 
    	 * sort O(nlogn)
    	 * for each of the current position i,  find the next min-height tree, O(1), 
    	 * min-distance from current position i to min-height tree O(n) ???
    	 * in total, it would be O(n^2) ??
    	 * Storage complexity:
    	 * use hashmap to store the tree height and tree position
    	 * use list to save all the trees for sorting
    	 */
        int res=0;
        ArrayList<Integer> list=new ArrayList<Integer>();
        HashMap<Integer, int[]> map=new HashMap<Integer, int[]>();
        int []start={0,0};
        list.sort((item1, item2)->{
        	return item1-item2;
        });
        int i=0;
        for(List<Integer> list1: forest) {
        	int j=0;
        	for(int h: list1) {
        		if(h>1) {
        			list.add(h);
        			int []location={i,j};
        			map.put(h, location);
        		}
        		j++;
        	}
        	i++;
        }
        list.sort((item1, item2)->{
        	return item1-item2;
        });
        for(int k=0; k<list.size(); k++) {
        	int []end={map.get(list.get(k))[0],map.get(list.get(k))[1]};
        	int next_steps=helper(forest, start, end);
        	if(next_steps==-1) return -1;
        	res+=next_steps;
        	start[0]=end[0];
        	start[1]=end[1];
        }
        return res;
    }
    
    public static void main(String args[]) {
    	List<List<Integer>> forest=new ArrayList<List<Integer>>();
    	List<Integer> l1=Arrays.asList(2,3,4);
    	List<Integer> l2=Arrays.asList(0,0,5);
    	List<Integer> l3=Arrays.asList(8,7,6);
    	forest.add(l1);
    	forest.add(l2);
    	forest.add(l3);
    	System.out.print(cutOffTree(forest));
    }
}
