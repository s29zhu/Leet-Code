package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/*
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
 */
public class MajorNumII {
    public static List<Integer> majorityElement(int[] nums) {
        //there at most two numbers will appear more than n/3 times

        List<Integer> res=new ArrayList<Integer>();
        int l=nums.length;
        if(l<3) {
        	for(int i=0; i<l; i++) {
        		if(!res.contains(nums[i])) res.add(nums[i]);
        	}
        	return res;
        }
        int num1=0, num2=0, count1=0, count2=0;
        for(int i=0; i<nums.length; i++) {
        	if(count1==0) {
        		num1=nums[i];
        		count1++;
        	}else if(nums[i]!=num1 && count2==0){
        		num2=nums[i];
        		count2++;
        	}else if(nums[i]==num1) {
        		count1++;
        	}else if(nums[i]==num2) {
        		count2++;
        	}else {
        		//at most two numbers in the res list, say if there are two numbers, they would occupy more than 2/(3*n) of the array. 
        		count2--;
        		count1--;
        	}
        }
        
        count1=0;
        count2=0;
        for(int i=0; i<nums.length; i++) {
        	if(nums[i]==num1) count1++;
        	else if(nums[i]==num2) count2++;
        }
        if(count1>(nums.length/3)) res.add(num1);
        if(count2>(nums.length/3)) res.add(num2);
        
        return res;
    }
    
    public static void main(String args[]) {
    	int []nums= {1,2,2,3,2,1,1,3};
    	majorityElement(nums);
    }
}
