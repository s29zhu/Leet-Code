package leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

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
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        boolean res=false;
        Queue<Integer> que=new PriorityQueue<Integer>();
        for(int i=0; i<nums.length; i++) {
        	if(que.size()<k) {
        		int diff=(int) Math.abs(nums[i]-que.peek());
        		que.add(nums[i]);
        	}
        	for(int j=i; j<=k && j<nums.length; j++) {
        		dis_max=Math.max(diff, dis_max);
        	}
        	if(dis_max<t) return true;
        }
        return res;
    }
}
