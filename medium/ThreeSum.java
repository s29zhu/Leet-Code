package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
public class ThreeSum {
	/*
	 * question, does the element need to be in order? can we sort the elements beforehand
	 * Test case: same triplets, [-1,0,1] and [0,-1,1] are the same
	 */
	
	public static boolean find(int []nums, int begin, int end, int target) {
		boolean res=false;
		if(begin>end||(begin==end && target!=nums[begin])) return false;
		if(begin==end && target==nums[begin]) return true;
		while(begin<=end) {
			if(target == nums[(begin+end)/2]) return true;
			else if(target < nums[(begin+end)/2] ) end=(begin+end)/2-1;
			else begin=(begin+end)/2+1;
		}
		return res;
	}
	
	public static List<List<Integer>> twoSum(int []nums, int begin, int sum){
		ArrayList<List<Integer>> res=new ArrayList<List<Integer>>();
		HashSet<Integer> set=new HashSet<Integer>();
		for(int i=begin; i<nums.length-1; i++) {
			int target=sum-nums[i];
			if(target<nums[i] || set.contains(nums[i])) continue;			
			if(find(nums, i+1, nums.length-1, target)) {
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(nums[i]);
				set.add(nums[i]);
				list.add(target);
				res.add(list);
			}
		}
		return res;
	}

    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res=new ArrayList<List<Integer>>();
        int l=nums.length;
        Arrays.parallelSort(nums);
        for(int i=0;i<l;i++) {
        	if(i==l-1 || (i>0&&nums[i]==nums[i-1])) continue;
        	List<List<Integer>> temp=twoSum(nums, i+1,-1*nums[i]);
        	for(List<Integer> list: temp) {
        		List<Integer> l1=new ArrayList<Integer>();
        		l1.add(nums[i]);
        		l1.addAll(list);
        		res.add(l1);
        	}
        }
        return res;
    }
    
    
    /*
     * LeetCode solution, binary search
     * 
     */
    public static List<List<Integer>> threeSumII(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i])
                twoSumII(nums, i, res);
        return res;
    }
    public static void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            /*
             * when to increase lo? 
             * 1. when the low is equal to previous number (which is not nums[i]) 
             * 2. when sum is less than o
             */
            if (sum < 0 || (lo > i + 1 && nums[lo] == nums[lo - 1]))//
                ++lo;
            /*
             * when to increase high?
             * 1. when the high is equal to previous number
             * 2. when the sum is larger than 0
             */
            else if (sum > 0 || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
                --hi;
            else
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
        }
    }
    public static void main(String args[]) {
    	int [] nums={-1, 0, 1, 2, -1, -4};
    //	int [] nums={1,1,1};
    //	int [] nums= {8,2,-10,-10,5};
    	System.out.println(threeSum(nums));
    }
}
