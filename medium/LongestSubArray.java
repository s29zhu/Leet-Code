package leetcode.medium;

public class LongestSubArray {
    public static int longestSubarray(int[] nums, int limit) {
    	/*
    	 * use max and min to record the min and max in subarray
    	 * when expanding the new subarray,update the max and min, 
    	 * 1. if |max-min|<=limit, continue
    	 * 2. if|max-min| > limit, scan from right to left to identify the new beginning of the subarray and get the new max and min
    	 * 2.1 break the scan, when |max-min|>limit
    	 * 2.2 update the max and min, when |max-min|<=limit
    	 */
        int res=0, begin=0, end=begin, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(int i=0; i<nums.length;i++){
            end=i;
            min=Math.min(min, nums[end]);
            max=Math.max(max, nums[end]);
            if(max-min<=limit) { 
                if(end-begin+1>res) res=end-begin+1;
            	continue;
            }
            min=nums[end];
            max=nums[end];
            int j=0;
            for(j=end; j>=begin; j--){
	            if(Math.abs(nums[j]-max)>limit||Math.abs(nums[j]-min)>limit){
	                begin=j+1;
	                break;
	            }else {
	            	min=Math.min(min, nums[j]);
	            	max=Math.max(max, nums[j]);
	            }
            } 
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int []nums= {8,2,4,7};
    	System.out.print(longestSubarray(nums, 4));
    }
    
}
