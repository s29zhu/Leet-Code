package leetcode.hard;

import java.util.Arrays;

/*
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 */
public class CountOfRangeSum {
	/*
	 * Binary Index Tree
	 * update(int[] nums, int index, int val), update index value and all the parental value
	 * query(int[] nums, int index) get the sum from 0 to index
	 * Time Complexity:O(nlogn)
	 */
	public static void update(long []BIT, int index, int val) {
		while(index<BIT.length) {
			BIT[index]+=(long)val;
			index += index&(-index);
		}
	}
	
	public static long query(long []BIT, int index) {
		long sum=0;
		while(index>0) {
			sum+=(long)BIT[index];
			index-=index&(-index);
		}
		return sum;
	}
	//corner case, nums[i] is Interger.MIN_VALUE or Integer.MAX_VALUE
    public  static int countRangeSum(int[] nums, int lower, int upper) {
    	int l=nums.length, res=0;
    	long []BIT=new long[l+1];
    	long []r=new long[l];
    	Arrays.fill(BIT, 0);
    	for(int i=0; i<l; i++){ //O(nlog(n))
    		update(BIT,i+1, nums[i]); 
    	}
    	long range=0;
    	for(int i=0;i<l;i++) { //O(n^2)
    		r[i]=query(BIT,i+1);
    		if(r[i]<=upper&&r[i]>=lower) res++;
    		for(int j=0; j<i; j++) {
    			range=r[i]-r[j];
    			if(range>=lower && range<=upper) res++;
    		}
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	int []nums={-2147483647,0,-2147483647,2147483647};
    	System.out.print(countRangeSum(nums,-564,3864));
    	//System.out.print(-2147483647l-2147483647l);
    }
}
