package leetcode.easy;
/*
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14
Note: The input number n will not exceed 100,000,000. (1e8)
 */
public class PerfectNumber {
    public static boolean checkPerfectNumber(int num) {
    	if(num==0) return false;
        int temp=0;
        for(int i=1; i<num && (num/i>=i);i++) {
        	if(num%i==0) {
        		temp+=i;
        		int d=num/i;
        		temp+=(d==num||d==i)?0:d;
        	}
        }
        return temp==num;
    }
    public static void main(String args[]) {
    	System.out.print(checkPerfectNumber(27));
    }
}
