package leetcode.easy;

/*
 * Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?
 */
public class PowerOfThree {
	/*
	 * We can use mathematics as follows
	 * n = 3^i 
	 * i = log_3(n)
	 * i = log_b(n)}/{log_b(3)
	 * n is a power of three if and only if i is an integer. 
	 * In Java, we check if a number is an integer by taking the decimal part (using % 1) and checking if it is 0.
	 */
    public static boolean isPowerOfThreeI(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
    public static boolean isPowerOfThreeII(int n) {
        boolean res=false;
        if(n<=0) return res;
        else if(n==1) return true;
        else if(n%3==0) return isPowerOfThreeII(n/3);
        else return res;
    }
    public static void main(String args[]) {
    	int n=8;
    	System.out.print(isPowerOfThreeI(n));
    }
}
