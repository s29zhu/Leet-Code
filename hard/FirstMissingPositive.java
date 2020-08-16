package leetcode.hard;

import java.util.HashSet;

/*
 * Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Follow up:

Your algorithm should run in O(n) time and uses constant extra space.
 */
public class FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        HashSet<Integer> set=new HashSet<Integer>();
        for(int i=0; i<nums.length; i++) {
        	if(nums[i]>0) {
        		set.add(nums[i]);
        		if(nums[i] > max) max=nums[i];
        		if(nums[i] < min ) min=nums[i]; 
        	}       	
        }
        if(min>1) return 1;
        for(int i=min; i<=max; i++) {
        	if(!set.contains(i)) return i;
        }
        return max+1;
    }
    
    public static void main(String args[]) {
    	int[] nums= {-1,2,5};
    	System.out.print(firstMissingPositive(nums));
    }
}
