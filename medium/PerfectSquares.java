package leetcode.medium;
/*
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */

//NOTE: Dynamic Program
public class PerfectSquares {
	
    public static int numSquares(int n) {
    	//Matrix[i] stores the minimum squares numbers sum up to i
        int [] matrix=new int [n+1];
        for(int i=1; i<=n;i++) {
        	matrix[i]=i;
        	for(int j=1; j*j<=i;j++) {
        		matrix[i]=Math.min(matrix[i], matrix[i-j*j]+1);
        	}
        }
        return matrix[n];
    }
    public static void main(String args[]) {
    	System.out.print(numSquares((int)Math.pow(2, 10)));
    }
}
