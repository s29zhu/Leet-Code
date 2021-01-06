package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/*
 * On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20
 */
public class UniquePathIII {
	
	public static int helper(int[][] grid, int [] start, int[] end, Set<String> set) {
		int res=0, m=grid.length, n=grid[0].length;
		int r1=start[0], c1=start[1];
		int r2=end[0], c2=end[1];
		//left
		int [] new_end={r2,c2-1};
		String str=String.valueOf(new_end[0])+"-"+String.valueOf(new_end[1]);
		boolean pre_flag=false;
		if(new_end[0]==r1&&new_end[1]==c1&&set.isEmpty()) return 1;
		else if(r2>=0&&r2<m&&(c2-1)>=0&&(c2-1)<n&&set.contains(str)) {
			pre_flag=true;
			set.remove(str);
			res+=helper(grid, start, new_end,set);		if(pre_flag) set.add(str);

		}else pre_flag=false;
		if(pre_flag) set.add(str);

		
		//right
		new_end[1]=c2+1;
		str=String.valueOf(new_end[0])+"-"+String.valueOf(new_end[1]);
		if(new_end[0]==r1&&new_end[1]==c1&&set.isEmpty()) return 1;
		if(r2>=0&&r2<m&&(c2+1)>=0&&(c2+1)<n&&set.contains(str)) {
			pre_flag=true;
			set.remove(str);
			res+=helper(grid, start, new_end,set);
		}else pre_flag=false;
		if(pre_flag) set.add(str);

		//up
		new_end[1]=c2;
		new_end[0]=r2-1;
		str=String.valueOf(new_end[0])+"-"+String.valueOf(new_end[1]);
		if(new_end[0]==r1&&new_end[1]==c1&&set.isEmpty()) return 1;
		else if((r2-1)>=0&&(r2-1)<m&&c2>=0&&c2<n&&set.contains(str)) {
			pre_flag=true;
			set.remove(str);
			res+=helper(grid, start, new_end,set);
		}else pre_flag=false;
		if(pre_flag) set.add(str);

		//down
		new_end[0]=r2+1;
		str=String.valueOf(new_end[0])+"-"+String.valueOf(new_end[1]);
		if(new_end[0]==r1&&new_end[1]==c1&&set.isEmpty()) return 1;
		else if((r2+1)>=0&&(r2+1)<m&&c2>=0&&c2<n&&set.contains(str)) {
			pre_flag=true;
			set.remove(str);
			res+=helper(grid, start, new_end,set);
		}else pre_flag=false;		
		if(pre_flag) set.add(str);
		
		return res;
	}
	
    public static int uniquePathsIII(int[][] grid) {
    	int m=grid.length, n=grid[0].length;
    	int []start=new int[2], end=new int[2];
    	Set<String> set=new HashSet<String>();
    	
    	for(int i=0; i<m; i++) {
    		for(int j=0; j<n; j++) {
    			if(grid[i][j]==1) {start[0]=i; start[1]=j;}
    			else if(grid[i][j]==2) {end[0]=i; end[1]=j;}
    			else if(grid[i][j]==0) {
    				String str=String.valueOf(i)+"-"+String.valueOf(j);
    				set.add(str);
    			}
    		}    			
    	}
    	
    	return helper(grid, start, end, set);
    }
    
    public static void main(String args[]) {
    	int [][]grid= {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
    	System.out.print(uniquePathsIII(grid));
    }
}
