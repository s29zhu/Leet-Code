package leetcode.hard;
/*
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, 
each piece consists of some consecutive chunks.

Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

Example 1:

Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
Example 2:

Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.
Example 3:

Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]

Constraints:
0 <= K < sweetness.length <= 10^4
1 <= sweetness[i] <= 10^5
 */
public class DivideChocolate {
	/*
	 * Dynamic Programming
	 * O(K*N^2)
	 * O(K*N+N)
	 */
    private int[] prefixSum;
    
    private int getRangeSum(int startIdx, int endIdx) {
        if (startIdx == 0) {
            return prefixSum[endIdx];
        } else {
            return prefixSum[endIdx] - prefixSum[startIdx - 1];
        }
    }
    
    public int maximizeSweetnessI(int[] sweetness, int K) {
        int N = sweetness.length;
        int[][] dp = new int[K + 1][N + 1];
        this.prefixSum = new int[N];
        
        prefixSum[0] = sweetness[0];
        for(int i=1; i<N; ++i) {
            prefixSum[i] = prefixSum[i - 1] + sweetness[i];
        }
        
        for (int i=0; i < K + 1; ++i) { //i is the # of cut
            for (int j=0; j < N + 1; ++j) { // j is the number of chocolate chunks
                if (j <= i) {
                    dp[i][j] = 0;
                } else {
                    if (i > 0) {
                        int maxSweetness = 0;
                        for (int k = j - 1; k >= i; --k) {
                            int currentRangeSum = getRangeSum(k, j - 1);
                            int currentMin = Math.min(currentRangeSum, dp[i-1][k]);
                            maxSweetness = Math.max(maxSweetness, currentMin);
                        }
                        dp[i][j] = maxSweetness;
                    } else {
                        dp[i][j] = dp[i][j - 1] + sweetness[j - 1];
                    }
                }
            }
        }
        return dp[K][N];
    }
	/*
	 * Binary Search
	 * O(NlogN)
	 * O(1)
	 */
    public static int maximizeSweetness(int[] sweetness, int K) {
		// Minimum and maximum level of sweetness to use in binary search.
        int l = 0;
        int hi = 0;
        
        for (int s : sweetness) {
            hi += s;
        }
        
        while (l <= hi) {
		    // hypothesize that we can cut into at least `k + 1`  parts, each has `level` or more sum of sweetness.
            int level = (l + hi) / 2;
            
			// Test if we can cut into `k + 1`  or more parts
            if (canCutIntoK(sweetness, level, K + 1)) {
				// hypothesis was correct, try again with greater level of sweetness
                l = level + 1;
            } else {
				// hypothesis was false, try again with smaller level of sweetness
                hi = level -1;
            }
        }
        
		// `l` increases by 1 after every correct sweet level, so the final correct answer must be `l - 1`
        return l - 1;
    }
    
    public static boolean canCutIntoK(int[] sweetness, int level, int K) {
        int cnt = 0;
        int curr = 0;
        
        for (int i = 0; i < sweetness.length; i++) {
            curr += sweetness[i];
            
            if (curr >= level) {
                curr = 0;
                cnt++;
            }
        }
        
        return cnt >= K;
    }
    public static void main(String args[]) {
    	
    }
}
