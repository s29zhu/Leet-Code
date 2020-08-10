package leetcode.hard;

import java.util.Arrays;

/*
 * Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile.  On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n. Return True if and only if Alice wins the game otherwise return False, assuming both players play optimally.

 

Example 1:

Input: n = 1
Output: true
Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
Example 2:

Input: n = 2
Output: false
Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
Example 3:

Input: n = 4
Output: true
Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
Example 4:

Input: n = 7
Output: false
Explanation: Alice can't win the game if Bob plays optimally.
If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0). 
If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -> 6 -> 2 -> 1 -> 0).
Example 5:

Input: n = 17
Output: false
Explanation: Alice can't win the game if Bob plays optimally.
 

Constraints:

1 <= n <= 10^5
 */
public class StroneGameIV {
	/*
	 * intuition:
	 * dynamic programming 
	 * dp[n+1], dp[0]=false,dp[1]=true, dp[2]=false, dp[3]=true;
	 * 1. if n is a square number, return true; 
	 * 2. if n is not a square number, return !winnerSquareGame(n-i), i is a square number < n
	 */
	
	public static void helper(int []dp,int n) {
		if(dp[n]>=0) return;
		for(int i=n; i>=1; i--) {
			int sqrt=(int)Math.sqrt(i);
			if(sqrt*sqrt==i) {
				helper(dp, n-i);
				if(dp[n-i]==0) {dp[n]=1; return;}
			}
		}
		dp[n]=0;
	} 
    public static boolean winnerSquareGame(int n) {
        int []dp=new int[n+1];
        Arrays.fill(dp, -1);
        dp[0]=0;
        helper(dp, n);
        return (dp[n]==1)?true:false;
    }
    
    public static void main(String args[]) {
    	System.out.println(winnerSquareGame(500000));
    }
}
