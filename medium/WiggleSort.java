package leetcode.medium;

import java.util.Arrays;

/*
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 */

/*
 * Analysis
 * 1 2 3 4 5 6 7
 * 1 3 2 5 4 7 6
 * 
 * 1 2 3 4 
 * 1 3 2 4
 */
public class WiggleSort {
	/**
	 * O(n)
	 * O(1)
	 */
	public static void wiggleSort(int[] nums) {
	    for (int i = 0; i < nums.length - 1; i++) {
	        if (((i % 2 == 0) && nums[i] > nums[i + 1])
	                || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
	            int temp=nums[i];
	            nums[i]=nums[i+1];
	            nums[i+1]=temp;
	        }
	    }
	}
	/**
	 * O(nlogn)
	 * O(1)
	 */
    public static void wiggleSortI(int[] nums) {
    	int l=nums.length;
        Arrays.sort(nums);
        
        for(int i=1; i<l-1;i=i+2) {
        	int temp=nums[i];
        	nums[i]=nums[i+1];
        	nums[i+1]=temp;
        }
    }
    
    public static void main(String args[]) {
    	int []nums={1,2,3,4,5,6};
    	wiggleSort(nums);
    	for(int i=0;i<nums.length;i++) {
    		System.out.print(nums[i]+" ");
    	}
    }
}
