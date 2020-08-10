package leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
If there is no non-empty subarray with sum at least K, return -1.
Example 1:
Input: A = [1], K = 1
Output: 1
Example 2:
Input: A = [1,2], K = 4
Output: -1
Example 3:
Input: A = [2,-1,2], K = 3
Output: 3
Note:
1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
 */
public class ShortestSubArraySumAtLeastK {	
	/*
	 * intuition:
	 * dynamic programming
	 * dp[i] stores from A[0] to A[i-1] sum
	 */
	public static int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N+1];
        for (int i = 0; i < N; i++)
            P[i+1] = P[i] + (long) A[i];
        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N+1; // N+1 is impossible
        //A linear collection that supports element insertion and removal at both ends. The name deque is short for "double ended queue" and is usually pronounced "deck".
        Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P
        for (int y = 0; y < P.length; y++) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
        	
        	//keep the y with smaller sum P[y] in the queue
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                monoq.removeLast();
            
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());
            
            //add y regardless the value of P[y]
            monoq.addLast(y);
        }
        return ans < N+1 ? ans : -1;
    }
	
	//intuition:
	//dynamic programming
	//dp[i][j] means the sum from A[i] to A[j]
	//dp[i][j]=dp[0][j] - dp[0][i-1]
	//Time O(n^2) 
	//Memory O(n^2)
	public static int shortestSubarrayII(int[] A, int K) {
		int res=Integer.MAX_VALUE, l=A.length;
		int [][]dp=new int[l][l];
		dp[0][0]=A[0];
		if(dp[0][0]>=K) return 1;
		for(int j=1; j<l; j++) {
			for(int i=0; i<=j; i++) {
				if(i==j) dp[i][j]=A[j];
				else dp[i][j]=dp[i][j-1]+A[j];
				if(dp[i][j]>=K) {
					res=Math.min(res, j-i+1);
				}
			}
		}
		if(res==Integer.MAX_VALUE) return -1;
		else return res;
	}
	/*
	 * intuition
	 * 1. sum=A[0], begin=0, end=1 (inclusive)
	 * when sum<=0, begin=end, end++
	 * when K>sum>0, end++
	 * when sum>=K, begin++, to shorten the sub-array and re-get the sum, so that we can get the shortest array with sum>=K
	 * Time complexity: worst case scenario is O(n^2)
	 * Time limit exceed
	 */
	
    public static int shortestSubarrayI(int[] A, int K) {
        int res=Integer.MAX_VALUE;
        int l=A.length;
        int begin=0, end=1, sum=A[begin];
        if(sum>=K) return end-begin;
        //int []A= {11,47,97,35,-46,59,46,51,59,80,14}; 282
        for(end=1; end<l; end++) {
        	if(sum<0) { begin=end; sum=A[end]; }
        	else if(sum>=0 && sum<K) {
        		sum+=A[end];
        	}else if(sum>=K) {
        		//two purposes here:
        		//shorten the span and try to find the sum>=K and span is shortest
        		//locate the new begin, which has the next largest sum
        		int temp=begin, temp_sum=sum, max_sum=0;
        		while(temp<end) {
        			if(temp_sum >= K || temp_sum > max_sum) {
        				if(temp_sum>=K) res=Math.min(end-temp, res);  
        				begin=temp; 
        				max_sum=temp_sum;
        			}
        			temp_sum-=A[temp];
        			temp++;
        		}    
        		sum=max_sum+A[end];
        	}
        }
        
        if(sum<K && res==Integer.MAX_VALUE) return -1;
        else if(sum>=K) {
    		int temp=begin, temp_sum=sum;
    		while(temp<end) {
    			if(temp_sum >= K) { res=Math.min(end-temp, res); }        			
    			temp_sum-=A[temp];
    			temp++;
    		} 
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int []A= {11,47,97,35,-46,59,46,51,59,80,14};
    	System.out.print(shortestSubarray(A,282));
    }
}