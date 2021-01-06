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

/*
 * thoughts:
 * 1. if 1 doesn't exist, return 1
 * 2. if 1 already exist, change all other negatives and 0 into 1, update the number 
 * 3. 
 */
public class FirstMissingPositive {
    public static int firstMissingPositiveI(int[] nums) {
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
    
    // solution from leetcode
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int contains = 0;
        for (int i = 0; i < n; i++)
          if (nums[i] == 1) {
            contains++;
            break;
          }

        if (contains == 0)
          return 1;

        // nums = [1]
        if (n == 1)
          return 2;

        // Replace negative numbers, zeros,
        // and numbers larger than n by 1s.
        // After this convertion nums will contain 
        // only positive numbers.
        for (int i = 0; i < n; i++)
          if ((nums[i] <= 0) || (nums[i] > n))
            nums[i] = 1;

        // Use index as a hash key and number sign as a presence detector.
        // For example, if nums[1] is negative that means that number `1`
        // is present in the array. 
        // If nums[2] is positive - number 2 is missing.
        for (int i = 0; i < n; i++) {
          int a = Math.abs(nums[i]);
          // If you meet number a in the array - change the sign of a-th element.
          // Be careful with duplicates : do it only once.
          if (a == n)
            nums[0] = - Math.abs(nums[0]);
          else
            nums[a] = - Math.abs(nums[a]);
        }

        // Now the index of the first positive number 
        // is equal to first missing positive.
        for (int i = 1; i < n; i++) {
          if (nums[i] > 0)
            return i;
        }
        if (nums[0] > 0)
          return n;

        return n + 1;
      }

    public static void main(String args[]) {
    	int[] nums= {-1,2,5,1};
    	System.out.print(firstMissingPositive(nums));
    }
}
