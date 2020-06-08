package leetcode.medium;
/*
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.
Example 1:
Input: grid = [ [1,1,1,1,1,1,1,0],
				[1,0,0,0,0,1,1,0],
				[1,0,1,0,1,1,1,0],
				[1,0,0,0,0,1,0,1],
				[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:

Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */

import java.util.LinkedList;
import java.util.Queue;
/*
 Analysis:
 start from (0,0), iterate through, encounter 0, mark 2, then check itself, i=0,m-1, j=0,n-1? ->false
 */
public class NumberOfClosedIsland {
	public static int closedIsland(int[][] grid) {
		int res=0, m=grid.length, n=grid[0].length;
		boolean flag=false;
		Queue<int[]> q=new LinkedList<int[]>();

		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(grid[i][j]==0) {
					flag=true;
					grid[i][j]=2;
					int [] index= {i,j};
					q.add(index);
					while(!q.isEmpty()) {
						index=q.poll();
						if(index[0]==0||index[0]==m-1 || index[1]==0||index[1]==n-1) flag=false;
						if(index[0]<(m-1) && grid[index[0]+1][index[1]]==0) q.add(helper(grid, index[0]+1, index[1]));							
						if(index[1]<(n-1) && grid[index[0]][index[1]+1]==0)	q.add(helper(grid, index[0], index[1]+1));
						if(index[0]>0 && grid[index[0]-1][index[1]]==0) q.add(helper(grid,index[0]-1, index[1]));
						if(index[1]>0 && grid[index[0]][index[1]-1]==0) q.add(helper(grid,index[0],index[1]-1));
					}
					res=flag?res+1:res;
				}
			}
		}
		return res;
	}
	public static int[] helper(int [][]grid, int i, int j) {
		int [] res= {i,j};
		grid[i][j]=2;
		return res;
	}
	public static void main(String []args) {
		int [][]grid={{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
		System.out.print(closedIsland(grid));
		//func(grid);
		//for(int i=0; i<grid.length;i++) System.out.print(grid[i][0]+" ");
	}

}
