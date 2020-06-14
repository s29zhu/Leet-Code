package leetcode.medium;
/*
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. 
 * The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [
 * 	[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]
 * ]
 *  Output: 3
 *  Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLingOfConsecutiveOneInMatrix {
    public static int longestLine(int[][] M) {
        int res=0;
        int m=M.length;
        if(m==0) return res;
        int n=M[0].length;
        int [][][]dp=new int [m][n][4];
        for(int i=0;i<m;i++) {
        	for(int j=0;j<n;j++) {
        		if(M[i][j] == 1) {
        			dp[i][j][0]+=1;
        			dp[i][j][1]+=1;
        			dp[i][j][2]+=1;
        			dp[i][j][3]+=1;
            		//get the horizontal value
        			dp[i][j][0]+=(j>0)?dp[i][j-1][0]:0;
        			res=Math.max(res, dp[i][j][0]);
        			//get the vertical value
        			dp[i][j][1]+=(i>0)?dp[i-1][j][1]:0;
        			res=Math.max(res, dp[i][j][1]);
        			//get the diagonal value
        			dp[i][j][2]+=(i>0 && j>0)?dp[i-1][j-1][2]:0;
        			res=Math.max(res, dp[i][j][2]);
        			//get the re-diagonal value
        			dp[i][j][3]+=(i>0 && j<n-1)?dp[i-1][j+1][3]:0;
        			res=Math.max(res, dp[i][j][3]);
        		}else {
        			dp[i][j][0]=0;
        		}        		
        	}
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int [][] M= {
    				{0,1,1,0},
    				{1,1,1,1},
    				{0,0,0,1}};
    	System.out.println(longestLine(M));
    }
}
