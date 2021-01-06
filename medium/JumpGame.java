package leetcode.medium;

import java.util.Arrays;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the 
 * array. Each element in the array represents your maximum jump length at that position. Determine 
 * if you are able to reach the last index.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, 
which makes it impossible to reach the last index.

Constraints:
1 <= nums.length <= 3 * 10^4
0 <= nums[i][j] <= 10^5
 */
public class JumpGame {
	/*
	 * intuition
	 * dynamic programming
	 */
    public static boolean canJump(int[] nums) {
        boolean [] dp=new boolean[nums.length];
        Arrays.fill(dp, false);
        dp[0]=true;
        for(int i=0; i<nums.length; i++) {
        	if(dp[i]) {
        		for(int j=i+1; j<=i+nums[i] && j<nums.length; j++) dp[j]=true;
        	}else {
                break;
        	}
        }
        return dp[nums.length-1];
    }
    public static void main(String args[]) {
    	
    }
}
