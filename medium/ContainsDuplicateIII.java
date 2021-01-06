package leetcode.medium;

import java.util.TreeSet;

/*
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute 
 * difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */
public class ContainsDuplicateIII {
	/*
	 * understanding, 
	 * find pair i and j, j-i<=K, so that |nums[i]-nums[j]|<=t
	 * 
	 * intuition
	 * put first K elements in the tree, get the k+1-th element, and put it in the tree too, get the neigbours of k+1th element, 
	 * and get the difference, it the difference is less than t, return true;
	 */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        boolean res=false;
        TreeSet<Integer> treeset=new TreeSet<Integer>();
        if(nums.length<=0) return res;
        for(int i=0; i<nums.length; i++) {
        	Integer s_neighbour=treeset.floor(nums[i]), b_neighbour=treeset.ceiling(nums[i]);
        	if((s_neighbour!=null && (long)nums[i]-(long)s_neighbour<=t)
        			|| (b_neighbour!=null &&(long)b_neighbour-(long)nums[i]<=t)) return true;
        	treeset.add(nums[i]);
        	if(treeset.size()>k)
        		treeset.remove(nums[i-k]);
        }
        return res;
    }
    public static void main(String args[]) {
    	int []nums= {1,2,3,1}; 
    	System.out.println(containsNearbyAlmostDuplicate(nums,3,0));
    }
}
