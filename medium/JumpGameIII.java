package leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

/*
 * Given an array of non-negative integers arr, you are initially positioned at start index of 
 * the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can 
 * reach to any index with value 0. Notice that you can not jump outside of the array at any time.

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 10^4
0 <= arr[i] < arr.length
0 <= start < arr.length
 */
public class JumpGameIII {
	/*
	 * intuition
	 * dynamic programming
	 * boolean dp[i] represent the index i is visited or not, if stack is empty and c < arr.length, false, 
	 * or out of index range, and the count is less then arr.length, then return false. if c==arr.length, return true;
	 */
    public static boolean canReach(int[] arr, int start) {
        boolean res=false;
        int l=arr.length;
        boolean []dp=new boolean[arr.length];
        Stack<Integer> stack=new Stack<Integer>();
        Arrays.fill(dp, false);
        if(arr[start]==0) return true;     
        dp[start]=true;
        stack.add(start);
        int i=start;
        while(!stack.isEmpty()) {
        	//if dp[i+arr[i]], dp[i-arr[i]]  == false, add to stack
        	i=stack.pop();
        	if(i+arr[i]>=0 && i+arr[i]<l && dp[i+arr[i]]==false) {
        		stack.add(i+arr[i]);
        		if(arr[i+arr[i]]==0) return true;
        		dp[i+arr[i]]=true;
        	}
        	if(i-arr[i]>=0 && i-arr[i]<l && dp[i-arr[i]]==false) {
        		stack.add(i-arr[i]);
        		if(arr[i-arr[i]]==0) return true;
        		dp[i-arr[i]]=true;
        	}
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int[] arr= {3,0,2,1,2};
    	System.out.println(canReach(arr,2));
    }
}
