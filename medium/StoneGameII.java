package leetcode.medium;
/*
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.

 

Example 1:

Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 
 

Constraints:

1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
 */
public class StoneGameII {
	/*
	 * intuition: dynamic programming
	 * int dp[l][2]
	 * dp[i][0] begin index i, Alex get the maximum stones
	 * dp[i][1] begin index i, Lee get the maximum stones
	 */
	public static void helper(int M, int dp[][][], int[] piles, int begin) {
		int max = 0;
		int l = piles.length;
		if(begin>=l || dp[M][begin][0]>0) return;
		if( begin + 2*M >= l) {
			for(int i=begin; i<l; i++) max += piles[i];
			dp[M][begin][0] = max;
			dp[M][begin][1] = 0;
			return;
		}
		
		for(int i = 1; i <= 2*M && i+begin-1<l; i++) {
			int temp_max=0;
			int new_M = Math.max(M, i);
			int new_begin= begin+i; //(inclusive)	
			max = dp[M][begin][0];
			for(int j=0; j<i && begin+j<l; j++) temp_max+=piles[begin+j];
			helper(new_M,dp,piles,new_begin);//returns the begin of next person;
			temp_max+=dp[new_M][new_begin][1];
			if(temp_max>max) {
				max=temp_max;
				dp[M][begin][0]=max;
				dp[M][begin][1]=dp[new_M][new_begin][0];
			}
		}
	}
	
    public static int stoneGameII(int[] piles) {
        int M=1, l=piles.length, begin=0;
        int [][][]dp=new int[l+1][l][2];
        dp[M][l-1][0]=piles[l-1];
        dp[M][l-1][1]=0;
        if(l>=2) {
        	dp[M][l-2][0]=piles[l-1]+piles[l-2];
        	dp[M][l-2][1]=0;
        }
        helper(M, dp, piles, begin);
        return dp[1][0][0];
    }
    
    public static void main(String args[]) {
    	int[] piles={2,7,9,4,4};
    	System.out.print(stoneGameII(piles));
    }
}
