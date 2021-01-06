package leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:
i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all 
indices k between i and j (More formally min(i, j) < k < max(i, j)).
You can choose any index of the array and start jumping. Return the maximum number of indices you can
visit.
Notice that you can not jump outside of the array at any time.

Example 1
Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly You cannot jump from index 3 to index 2 or index 1.

Example 2:
Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You always cannot jump to any index.

Example 3:
Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indicies. 

Example 4:
Input: arr = [7,1,7,1,7,1], d = 2
Output: 2

Example 5:
Input: arr = [66], d = 1
Output: 1
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
 */
public class JumpGameV {
	
    private static int dfs(int[] arr, int d, int curr, int[] mem) {
        if(mem[curr] != 0) return mem[curr];
        int maxJumps = 1;
		// Iterate left part.
        for(int i = curr - 1; i >= 0 && i >= curr - d; i--) {
            if(arr[i] < arr[curr]) {
                maxJumps = Math.max(maxJumps, dfs(arr, d, i, mem) + 1);
            }
            else break;
        }
		// Iterate right part.
        for(int i = curr + 1; i < arr.length && i <= curr + d; i++) {
            if(arr[i] < arr[curr]) {
                maxJumps = Math.max(maxJumps, dfs(arr, d, i, mem) + 1);
            }
            else break;
        }
        mem[curr] = maxJumps;
        return maxJumps;
    }
    public static int maxJumps(int[] arr, int d) {
        int[] mem = new int[arr.length];
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(dfs(arr, d, i, mem), max);
        }
        return max;
    }
    

	/*
	 * intuition
	 * dynamic programming
	 * stack to perform DFS
	 */
    public static int maxJumpsI(int[] arr, int d) {
        int res=1, l=arr.length;
        int []dp=new int[l];
        Queue<Integer> que=new LinkedList<Integer>();
        Queue<Integer> steps=new LinkedList<Integer>();
        
        Arrays.fill(dp, -1);
        for(int j=0; j<l; j++) {
            que.add(j);
            steps.add(1);
            //6,4,14,6,8,13,9,7,10,6,12
	        while(!que.isEmpty()) {  
	        	int index=que.poll();
	        	int step=steps.poll();
	        	int count=0;
	        	for(int i = 1; i <= d; i++) {
	        		if( index-i < 0 || arr[index-i] >= arr[index]) break;
        			if(dp[index-i]!=-1) dp[j]=Math.max(dp[j], dp[index-i]+step);
        			else {
        				que.add(index-i);
        				steps.add(step+1);
        			}
    				count++;	        		
	        	}
	        	for(int i = 1; i <= d; i++) {
	        		if(index+i >= l || arr[index+i] >= arr[index]) break;
        			if(dp[index+i] != -1 ) dp[j]=Math.max(dp[j], dp[index+i]+step);
        			else {
	        			que.add(index+i);
	        			steps.add(step+1);
        			}
        			count++;
	        	}
	        	if(count==0) {
	        		dp[j]=Math.max(dp[j], step);
	        		dp[index]=1;
	        	}
	        }
            res=Math.max(res, dp[j]);
        }
        
        return res;
    }
    
    public static void main(String args[]) {
    	int []nums= {66};
    	System.out.println(maxJumps(nums,1));
    }
}
