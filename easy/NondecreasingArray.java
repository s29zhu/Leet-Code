package leetcode.easy;

/*
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

Example 1:

Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:

Input: nums = [4,2,1]
Output: false
Explanation: You can't get a non-decreasing array by modify at most one element.
 */
public class NondecreasingArray {
    public static boolean checkPossibility(int[] nums) {
    	int size=nums.length,count=1;    	
    	for(int i=0;i<=size-2;i++) {
    		if(nums[i]>nums[i+1]) {
    			count--;
    			if(count < 0) return false;
    			if(i==0) nums[i]=nums[i+1];
    			else if (nums[i+1]<=nums[i-1]) nums[i+1]=nums[i];
    			else nums[i]=nums[i-1];
    		}
    	}
        return true;
    }
    
    public static void main(String []args) {
    	int[] nums= {2,4,2,3};
    	//[-1,4,-2,4]
    	//[6,5,6,7]
    	//[1,5,6,7]
    	System.out.print(checkPossibility(nums));
    }
}
