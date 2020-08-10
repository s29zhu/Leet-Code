package leetcode.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: A = [[0,1],[1,0]]
Output: 1
Example 2:

Input: A = [[0,1,0],
			[0,0,0],
			[0,0,1]]
Output: 2
Example 3:

Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Constraints:

2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
 */
public class ShortestBridge {
	/*
	 * intuition
	 * 1. BFS first to find out island 1
	 * 2. Start to extend island 1, util meet the island 2, BFS
	 */
    public static int shortestBridge(int[][] A) {
    	int res=0;
    	int m=A.length, n=A[0].length;
    	boolean flag=false;
    	Queue<int[]> que=new LinkedList<int[]>();
    	Queue<Integer> length=new LinkedList<Integer>();
    	Set<String> set=new HashSet<String>();
    	for(int i=0; i<m; i++) {
    		for(int j=0; j<n; j++) {
    			if(A[i][j]==1) {
    				int []position=new int[2];
    				position[0]=i;
    				position[1]=j;
    				que.add(position);
    				String str=String.valueOf(i)+"-"+String.valueOf(j);
    				set.add(str);
    				flag=true;
    				break;
    			}
    		}
    		if(flag) break;
    	}
    	
    	//find all island 1 nodes
    	while(!que.isEmpty()) {
    		int []cur=que.poll();
    		//up
    		if(cur[1]>0 && A[cur[0]][cur[1]-1]==1) {
    			int []position= {cur[0], cur[1]-1};
    			String str=String.valueOf(cur[0])+"-"+String.valueOf(cur[1]-1);
    			if(!set.contains(str)) { que.add(position); set.add(str); }
    		}
    		//right
    		if(cur[0]<m-1 && A[cur[0]+1][cur[1]]==1) {
    			int []position= {cur[0]+1, cur[1]};
    			String str=String.valueOf(cur[0]+1)+"-"+String.valueOf(cur[1]);
    			if(!set.contains(str)) { que.add(position); set.add(str); }
    		}
    		//down
    		if(cur[1]<n-1 && A[cur[0]][cur[1]+1]==1) {
    			int []position= {cur[0], cur[1]+1};
    			String str=String.valueOf(cur[0])+"-"+String.valueOf(cur[1]+1);
    			if(!set.contains(str)) { que.add(position); set.add(str); }
    		}
    		//left
    		if(cur[0]>0 && A[cur[0]-1][cur[1]]==1) {
    			int []position= {cur[0]-1, cur[1]};
    			String str=String.valueOf(cur[0]-1)+"-"+String.valueOf(cur[1]);
    			if(!set.contains(str)) { que.add(position); set.add(str); }
    		}
    	}
    	
    	//extend island 1
    	for(String str: set) {
    		int index=str.indexOf('-');
    		int []position={Integer.valueOf(str.substring(0,index)),Integer.valueOf(str.substring(index+1))};
    		que.add(position);
    	}
    	for(int i=0;i<set.size(); i++) length.add(0);
    	while(!que.isEmpty()) {
    		int []cur=que.poll();
    		int l=length.poll();
    		//up
			String str=String.valueOf(cur[0])+"-"+String.valueOf(cur[1]-1);
    		if(cur[1]>0 && A[cur[0]][cur[1]-1]==0) {
    			int []position= {cur[0], cur[1]-1};
    			if(!set.contains(str)) { 
    				que.add(position); 
    				set.add(str); 
    				length.add(l+1);
    			}
    		}else if(cur[1]>0 && A[cur[0]][cur[1]-1]==1 && !set.contains(str)) return l;
    		//right
			str=String.valueOf(cur[0]+1)+"-"+String.valueOf(cur[1]);
    		if(cur[0]<m-1 && A[cur[0]+1][cur[1]]==0) {
    			int []position= {cur[0]+1, cur[1]};
    			if(!set.contains(str)) { que.add(position); set.add(str); length.add(l+1);}
    		}else if(cur[0]<m-1 && A[cur[0]+1][cur[1]]==1 && !set.contains(str)) return l;
    		//down
			str=String.valueOf(cur[0])+"-"+String.valueOf(cur[1]+1);
    		if(cur[1]<n-1 && A[cur[0]][cur[1]+1]==0) {
    			int []position= {cur[0], cur[1]+1};
    			if(!set.contains(str)) { que.add(position); set.add(str); length.add(l+1);}
    		}else if(cur[1]<n-1 && A[cur[0]][cur[1]+1]==1 && !set.contains(str)) return l;
    		//left
			str=String.valueOf(cur[0]-1)+"-"+String.valueOf(cur[1]);
    		if(cur[0]>0 && A[cur[0]-1][cur[1]]==0) {
    			int []position= {cur[0]-1, cur[1]};
    			if(!set.contains(str)) { que.add(position); set.add(str); length.add(l+1);}
    		}else if(cur[0]>0 && A[cur[0]-1][cur[1]]==1 && !set.contains(str)) return l;
    	}
    	
        return res;
    }
    
    
    public static void main(String args[]) {
    	int [][]A= {{0,1,0},{0,0,0},{0,0,1}};
    	System.out.println(shortestBridge(A));
    }
}
