package leetcode.medium;
/*
 * Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */

/*
 * notes:
 * 1. n could be the smallest negative number -2147483648, use long power to get the absolute number of n
 */

public class PowerXN {
    public static double myPow(double x, int n) {
    	double res=1;
        long power=(n<0)?-1*(long)n:n;
        if(n==0) return 1.0;
        res=myPow(x,(int)(power/2));
        res=(power%2==0)? res=res*res:res*res*x;
        if(n < 0) res=1/res;
        return res;
    }

    public static void main(String args[]) {
    	System.out.println(myPow(2,-2147483648));
    }
}
