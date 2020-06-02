package leetcode.easy;
/*
 * Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 20 = 1
Example 2:

Input: 16
Output: true
Explanation: 24 = 16
Example 3:

Input: 218
Output: false
 */
public class PowerofTwo {
    public static boolean isPowerOfTwo(int n) {
        boolean res=false;
        if(n<=0) return res;
        else if(n==1) return true;
        else if(n%2==0) return isPowerOfTwo(n/2);
        else return res;
    }
    public static void main(String args[]) {
    	int n=2;
    	System.out.print(isPowerOfTwo(n));
    }
}
