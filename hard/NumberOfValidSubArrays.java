package leetcode.hard;

import java.util.Stack;

/*
 * Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:
The leftmost element of the subarray is not larger than other elements in the subarray.
Example 1:

Input: [1,4,2,5,3]
Output: 11
Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
Example 2:

Input: [3,2,1]
Output: 3
Explanation: The 3 valid subarrays are: [3],[2],[1].
Example 3:

Input: [2,2,2]
Output: 6
Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].
 

Note:
1 <= A.length <= 50000
0 <= A[i] <= 100000
 */
public class NumberOfValidSubArrays {
	/*
	 * Analysis:
	 * Time Complexity: O(n)
	 * Space Complexity:O(n)
	 */
	 public int validSubarraysI(int[] nums) {
			Stack<Integer> stack = new Stack<>();
			int count = 0;
			for (int num: nums) {
				while (!stack.isEmpty() && stack.peek() > num) {
					stack.pop();
				}
				stack.add(num);
				count += stack.size();
			}
			return count;
		}
	 
	 /*
	  * Analysis:
	  * Time Complexity: O(n^2)??
	  */
    public static int validSubarrays(int[] nums) {
    	int res=0, l=nums.length;
    	for(int i=0;i<l;i++) {// iterate through each of the item from array
    		for(int j=i; j<l && nums[j]>=nums[i];j++) {
    			res++;
    		}    		
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	int [] nums={2,2,2};
    	System.out.println(validSubarrays(nums));
    }
}
