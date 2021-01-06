package leetcode.medium;
/*
 * https://leetcode.com/problems/rotting-oranges/
 * In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, 
return -1 instead.
 */
public class RottenOranges {
    public static int orangesRotting(int[][] grid) {
    	/*
    	 * test case
    	 * 1. there are all 0s, return 0
    	 * 2. there are cases when 1 sits in the corner and cannot be turned to 2, return -1
    	 */
        int res=0;
        int m=grid.length;
        if(m==0) return -1;
        int n=grid[0].length;
        int count2=0, count1=0;
        while(true) {
        	count2=0;
        	count1=0;
	        for(int i=0; i<m; i++) {	        	
	        	for(int j=0;j<n; j++) {
	        		if(grid[i][j]==2) {
	        			grid[i][j]=0;
	        			if(i>0&&grid[i-1][j]==1) grid[i-1][j]=3;
	        			if(j>0&&grid[i][j-1]==1) grid[i][j-1]=3;
	        			if(i<m-1&&grid[i+1][j]==1) grid[i+1][j]=3;
	        			if(j<n-1&&grid[i][j+1]==1) grid[i][j+1]=3;
	        			count2++;
	        		}
	        		if(grid[i][j]==1) count1++;
	        	}
	        }
	        for(int i=0; i<m; i++) {	        	
	        	for(int j=0;j<n; j++) {
	        		grid[i][j]=(grid[i][j]==3)?2:grid[i][j];
	        	}
	        }
	        if(count2==0) break;
	        res++;
        }
        if(count1==0 && res==0) return 0;
        if(count1!=0) return -1;
        return res-1;
    }
    
    public static void main(String args[]) {
    	//int [][]grid={{2,1,1},{1,1,0},{0,1,1}};
    	//int [][]grid={{0,0}};
    	int[][] grid= {{2,1,1},{1,1,0},{0,1,1}};
    	System.out.println(orangesRotting(grid));
    }
}
