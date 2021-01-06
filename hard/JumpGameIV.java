package leetcode.hard;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
/*
 * Given an array of integers arr, you are initially positioned at the first index of the array. 
 * In one step you can jump from index i to index i+1, i-1 or j: 
 * - i + 1 where: i + 1 < arr.length.
 * - i - 1 where: i - 1 >= 0.
 * - j where: arr[i] == arr[j] and i != j.
 * 
 * Return the minimum number of steps to reach the last index of the array.
 * Notice that you can not jump outside of the array at any time.
Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
Example 4:

Input: arr = [6,1,9]
Output: 2
Example 5:

Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3
 

Constraints:

1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8
 */
public class JumpGameIV {
	/*
	 * intuition
	 * dp to save the index that has been visited before
	 * queue for BFS
	 * map to map the number, and the indexes
	 */
    public static int minJumps(int[] arr) {
       int l=arr.length;
        int []dp=new int[l];
        Queue<Integer> que=new LinkedList<Integer>();
        HashMap<Integer, ArrayList<Integer>> map=new HashMap<Integer, ArrayList<Integer>>();
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        que.add(l-1);
        dp[l-1] = 0;
        for(int i=0; i<arr.length; i++) {
        	ArrayList<Integer> list= map.getOrDefault(arr[i], new ArrayList<Integer>());
        	list.add(i);
        	map.put(arr[i], list);
        }
        // every element stored in stack already has the steps calculated to the end
        // when to add element into stack? answer: when the dp[i] value changes
        while(!que.isEmpty()) {
        	int index=que.poll();
        	
        	if(index+1<l && dp[index]+1<dp[index+1]) {
        		que.add(index+1);
        		dp[index+1]=dp[index]+1;
        	}
    		
        	if(index-1 >=0  && dp[index]+1<dp[index-1]) {
        		que.add(index-1);
        		dp[index-1]=dp[index]+1;
        	}
    		
        	ArrayList<Integer> list=map.getOrDefault(arr[index], new ArrayList<Integer>());
        	for(int num: list) {
    			if(dp[num] > dp[index]+1) {
    				que.add(num); 
    				dp[num]=dp[index]+1;
    			}
    			map.remove(arr[num]);
        	}
        }
        return dp[0];
    }
    
    public static void main(String args[]) {
    	int []nums= {100,-23,-23,404,100,23,3,404};
    	System.out.println(minJumps(nums));
    }
}
