package leetcode.easy;
/*
 * There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
 */
public class PaintFence {
	// no more than 2 has the same color
	/*
	 * 
1. the first post has k colors
same1:0
diff1: k
2. the second post could be the same and different
same2: k
diff2: k*(k-1)
3. the third post
same3: same2+diff2
diff3: (same2+diff2)*(k-1)
...
...
n. the nth post
same-n: same-(n-1)+diff(n-1)
diff-n: (same-(n-1)+diff(n-1))*(k-1)

return same-n+diff-n
	 */
    public static int numWays(int n, int k) {
        int diff = k;
        int same = 0;
        if (n == 0) {
            return 0;
        }
        for(int i=2; i<=n; i++) {
        	int pre_same=same;
        	int pre_diff=diff;
        	same=pre_diff+pre_same;
        	diff=(pre_same+pre_diff)*(k-1);
        }
        return same+diff;
    }
    
    public static void main(String args[]) {
    	System.out.println(numWays(3,2));
    }
}