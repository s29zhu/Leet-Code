package leetcode.medium;
/*
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 

Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 */
public class ZeroOneMatrix {
    public static int[][] updateMatrix(int[][] matrix) {
        int m=matrix.length, n=matrix[0].length, max=10000;
        int [][]res=new int[m][n];
        for(int i=0;i<m;i++) {
        	for(int j=0;j<n;j++) {
        		if(i==0 && j==0) res[i][j]=(matrix[i][j]==0)?0:max;
        		if(i==0 && j > 0) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i][j-1]+1, max);
        		}
        		if(j==0 && i > 0) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i-1][j]+1, max);
        		}
        		if(i > 0 && j > 0) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i-1][j]+1, res[i][j-1]+1);
        		}
        	}
        }
        for(int i=m-1;i>=0;i--) {
        	for(int j=n-1;j>=0;j--) {
        		if(i == m-1 && j == n-1) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i][j], max);
        		}
        		if(i == m-1 && j < n-1) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i][j], Math.min(res[i][j+1]+1, max));
        		}
        		if(j == n-1 && i < m-1) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i][j], Math.min(res[i+1][j]+1, max));
        		}
        		if(i < m-1 && j < n-1) {
        			if(matrix[i][j]==0) res[i][j]=0;
        			else res[i][j]=Math.min(res[i][j], Math.min(res[i+1][j]+1, res[i][j+1]+1));
        		}
        	}
        }
        return res;
    }
    public static void main(String args[]) {
    	int[][] matrix= {{0,0,1,0,1,1,1,0,1,1},{1,1,1,1,0,1,1,1,1,1},{1,1,1,1,1,0,0,0,1,1},{1,0,1,0,1,1,1,0,1,1},{0,0,1,1,1,0,1,1,1,1},{1,0,1,1,1,1,1,1,1,1},{1,1,1,1,0,1,0,1,0,1},{0,1,0,0,0,1,0,0,1,1},{1,1,1,0,1,1,0,1,0,1},{1,0,1,1,1,0,1,1,1,0}};
    	int [][] res=updateMatrix(matrix);
    	int m=res.length, n=res[0].length;
    	for(int i=0;i<m;i++) {
    		for(int j=0;j<n;j++) {
    			System.out.print(res[i][j]+" ");
    		}
    		System.out.print("\n");
    	}
    }
}
