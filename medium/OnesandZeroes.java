package leetcode.medium;
/*
 * Given an array, strs, with strings consisting of only 0s and 1s. Also two integers m and n.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

 

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10","0001","1","0".
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 

Constraints:

1 <= strs.length <= 600
1 <= ；strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
 */
public class OnesandZeroes {
    public static int findMaxForm(String[] strs, int m, int n) {
        int [][] matrix=new int[m+1][n+1];
        for(String str: strs) {
        	int l=str.length(),c0=0,c1=0;
        	for(int i=0;i<l;i++) {
        		if(str.charAt(i)=='0') c0++;
        		else if(str.charAt(i)=='1') c1++;
        	}
        	for(int i=m; i>=c0; i--) {
        		for(int j=n; j>=c1; j--) {
        			matrix[i][j]=Math.max(matrix[i][j],1+matrix[i-c0][j-c1]);
        		}        			
        	}
        }
        return matrix[m][n];
    }
    public static void main(String args[]) {
    	String [] strs= {"10","0","1"};
    	System.out.print(findMaxForm(strs,1,1));
    }
}
