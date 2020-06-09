package leetcode.medium;
/*
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.

 

Example 1:

Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5

Example 2:

Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5

Example 3:

Input: [1,2,3,4,4,5]
Output: False
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Define another array boolean used[nums.length]
 * Define a helper function with used and nums, try to separate the first 3-element subset, and recursion with nums and used
 */
public class SplitArrayIntoConsecutiveSubsets {
	//using a queue to store the current sub sequence
	//when encounter a new element, try to attach it to a shortest set
	 public static boolean isPossible(int[] nums) {
	        Integer prev = null; //previous number
	        int prevCount = 0; //count of previous number
	        Queue<Integer> starts = new LinkedList<Integer>();// queue is to store all the possible start numbers
	        int anchor = 0; //start of a repeated number
	        for (int i = 0; i < nums.length; ++i) {
	            int t = nums[i];
	            if (i == nums.length - 1 || nums[i+1] != t) {// when encounter a new number
	                int count = i - anchor + 1;
	                if (prev != null && t - prev != 1) {
	                    while (prevCount-- > 0)
	                        if (prev < starts.poll() + 2) return false;
	                    prev = null;
	                }

	                if (prev == null || t - prev == 1) {
	                    while (prevCount > count) {
	                        prevCount--;
	                        if (t-1 < starts.poll() + 2)
	                            return false;
	                    }
	                    while (prevCount++ < count)
	                        starts.add(t);
	                }
	                prev = t;
	                prevCount = count;
	                anchor = i+1;
	            }
	        }

	        while (prevCount-- > 0)
	            if (nums[nums.length - 1] < starts.poll() + 2)
	                return false;
	        return true;
	}

	//brutal force solution, time limit exceed
    public static boolean isPossibleI(int[] nums) {
        boolean[] used=new boolean[nums.length];
        Arrays.fill(used, false);
        return helper(nums, used);
    }
    public static boolean helper(int[] nums, boolean [] used) {
    	boolean res=false;
    	int l=nums.length, cur=nums[0];
    	if(nums.length<3) return false;
    	int count_unused=0;
    	for(int i=0;i<l;i++) {
    		if(!used[i]) count_unused++;
    	}
    	if(count_unused==0) return true;
    	for(int i=3; i<=count_unused; i++) {
    		boolean []t_used=Arrays.copyOf(used, used.length);
    		for(int j=0; j<i; j++) {
    			if(j==0) {
	    			for(int k=0;k<l;k++) {
	    				if(!t_used[k]) { cur=nums[k]; t_used[k]=true; break;}
	    			}
    			}else {
	    			int nextIndex=find(nums, t_used, cur+1);
	    			if(nextIndex!=-1) {
	    				cur=nums[nextIndex];
	    				t_used[nextIndex]=true;
	    			}else {
	    				return false; // cannot construct sub set with i continuous items
	    			}   
    			}
    		}    
    		if(helper(nums,t_used)) return true;
    	}
    	return res;
    }
    public static int find(int[] nums, boolean []used, int target) {
    	//find the target that is not used, return the index, otherwise return -1
    	for(int i=0;i<nums.length;i++) {
    		if(nums[i]==target && used[i]==false) return i;
    		if(nums[i]>target) return -1;
    	}
    	return -1;
    }
    public static void main(String args[]) {
    	//int[] nums= {5,6,6,7,8,8,9,9,10,10};
    	int [] nums= {10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13};
    	System.out.println(isPossible(nums));
    }
}
