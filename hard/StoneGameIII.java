package leetcode.hard;

import java.util.Arrays;

/*
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.

The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.

 

Example 1:

Input: values = [1,2,3,7]
Output: "Bob"
Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
Example 2:

Input: values = [1,2,3,-9]
Output: "Alice"
Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.
Example 3:

Input: values = [1,2,3,6]
Output: "Tie"
Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
Example 4:

Input: values = [1,2,3,-1,-2,-3,7]
Output: "Alice"
Example 5:

Input: values = [-1,-2,-3]
Output: "Tie"
 

Constraints:

1 <= values.length <= 50000
-1000 <= values[i] <= 1000
 */
public class StoneGameIII {
	/*
	 * intuition, dynamic programming, and the stone value could be negative
	 * three variables
	 * 1. players
	 * 2. stone values
	 */
	public static void helper(int [][]dp, int begin, int[] stones) {
		int l=stones.length;
		if(begin>=l || dp[begin][0]!=Integer.MIN_VALUE) return;		
		int max1=Integer.MIN_VALUE;
		for(int i=1; i<=3; i++) {
			int temp_max=0;
			for(int j=0;j<i && begin+j<l;j++) temp_max+=stones[begin+j];
			int new_begin=begin+i;
			if(new_begin<l) {
				helper(dp, new_begin, stones);
				temp_max+=dp[new_begin][1];
				if(temp_max>max1) {
					max1=temp_max;
					dp[begin][0]=max1;
					dp[begin][1]=dp[new_begin][0];
				}
			}else {
				if(temp_max>max1) {
					max1=temp_max;
					dp[begin][0]=max1;
					dp[begin][1]=0;
				}
			}
		}
	}
    public static String stoneGameIII(int[] stoneValue) {
        int l=stoneValue.length;
        int [][]dp=new int[l][2];
        dp[l-1][0]=stoneValue[l-1];
        dp[l-1][1]=0;
        for(int i=0;i<l;i++) {
        	Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        helper(dp, 0,stoneValue);
        if(dp[0][0]==dp[0][1]) return "Tie";
        else if(dp[0][0]>dp[0][1]) return "Alice";
        else return "Bob";
    }
    
    public static void main(String args[]) {
    	int[] stones={-5,-12,15};
    	System.out.print(stoneGameIII(stones));
    }
}
