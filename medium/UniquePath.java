package leetcode.medium;

import java.util.Arrays;

/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
 

Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 */
public class UniquePath {
	/*
	 * intuition: dynamic programming 
	 * 1. int [][]dp dp[1][i] and dp[i][1] are 1
	 * 2. dp[2][i]=dp[i][2], dp[2][1]=1, dp[2][3]=dp[2][2]+dp[1][3] => dp[i][j]=d[i-1][j]+dp[i][j-1]
	 */
    public static int uniquePaths(int m, int n) {
    	if(m==0||n==0) return 0;
    	int [][]dp=new int[m+1][n+1];
        Arrays.fill(dp[1],1);
        for(int i=1;i<=m;i++) dp[i][1]=1;
        for(int i=2; i<=m;i++) {
        	for(int j=2; j<=n;j++) {
        		dp[i][j]=dp[i-1][j]+dp[i][j-1];
        	}
        }
        return dp[m][n];
    }
    
    public static void main(String args[]) {
    	System.out.print(uniquePaths(7,3));
    }
}
