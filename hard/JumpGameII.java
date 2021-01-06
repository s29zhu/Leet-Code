package leetcode.hard;

import java.util.Arrays;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index of 
 * the array. Each element in the array represents your maximum jump length at that position. 
 * Your goal is to reach the last index in the minimum number of jumps.

Example:
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */
public class JumpGameII {
    public static int jump(int[] nums) {
        int []dp=new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=0; i<nums.length; i++) {
        	for(int j=1; j<=nums[i] && i+j<nums.length; j++) {
        		dp[i+j]=Math.min(dp[i+j], dp[i]+1);
        	}
        }
        return dp[nums.length-1];
    }
    public static void main(String args[]) {
    	int []nums= {2,3,1,1,4};
    	System.out.print(jump(nums));
    }
}
