package leetcode.hard;

import java.util.Arrays;

/*
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.

You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.

Return the maximum number of cherries collection using both robots  by following the rules below:

From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
When both robots stay on the same cell, only one of them takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in the grid.

example 1
Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.

example 2
Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.

Example 3:
Input: grid = [[1,0,0,3],
				[0,0,0,3],
				[0,0,3,3],[9,0,3,3]]
Output: 22

Example 4:
Input: grid = [[1,1],[1,1]]
Output: 4

Constraints:
rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100 
 */
public class CherryPickupII {
	
	/*
	 * intuition, challenges, 
	 * 1. robot could enter the same cell, 
	 * 2. the current maximum won't guarantee the next maximum
	 * 
     *  dp[0,0,n-1] stores the last result, 
     *      first dimension is the row, 
     *      second dimension is column of robot1
     *      third dimension is column of robot2
	 */
	
	public static int helper(int [][][]dp, int [][]grid, int r, int c1, int c2) {
		int res=0, m=grid.length, n=grid[0].length;
		if(r>=m || c1>=n || c2>=n || r<0 || c1<0 || c2<0) return 0;
		res=dp[r][c1][c2];
		if(res!=-1) return res;
		int cur=(c1!=c2) ? grid[r][c1]+grid[r][c2] : grid[r][c1];
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				res=Math.max(res, cur+helper(dp, grid, r+1, c1+i, c2+j));
			}
		}
		dp[r][c1][c2]=res;
		return res;
	}
	
    public static int cherryPickup(int[][] grid) {
        int m=grid.length, n=grid[0].length; 
        int [][][]dp=new int[m][n][n];
        for(int i=0; i<m; i++) {
        	for(int j=0; j<n; j++) {
        		Arrays.fill(dp[i][j], -1);
        	}
        }       	
        return helper(dp,grid, 0,0,n-1);
    }
    
    public static void main(String args[]) {
    	int [][]nums={{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
    	System.out.println(cherryPickup(nums));
    }
}
