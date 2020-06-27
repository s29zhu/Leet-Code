package leetcode.easy;
/*
 * Given an integer array nums, find the contiguous subarray 
 * (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
		-2,1,-2,4,3, 5,6,-1,3
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using 
the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int l=nums.length;
        int res=0;
        res=nums[0];
        for(int i=1;i<l;i++) {
        	nums[i]=Math.max(nums[i], nums[i]+nums[i-1]);
        	res=Math.max(res, nums[i]);
        }
        return res;
    }	
    
    public static void main(String args[]) {
    	int[] nums={-2,1,-3,4,-1,2,1,-5,4};
    	System.out.println(maxSubArray(nums));
    }
}
