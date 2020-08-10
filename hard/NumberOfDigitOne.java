package leetcode.hard;
/*
 * https://leetcode.com/problems/number-of-digit-one/
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

Example:

Input: 13
Output: 6 
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {
	/*
	 * intuition
	 * say the n is XXXX, for ith digit in n, check the following
	 * 1. get the digits before ith digit, left
	 * 2. get the digits after ith digit, right
	 * 
	 * if the current digit is 1, res+=left*power+right+1.
	 * -- from 0 to (left-1), the right digits can be anything, power 
	 * -- when left_part == left, the right digits can only be right+1
	 * if the current digit is 0, res+=left * power
	 * -- from 0 to (left-1), the right digits can be anything, power 
	 * if the current digit > 1,  res+=left * power + power
	 */
    public static int countDigitOne(int n) {
        if (n < 0)
            return 0;
        
        long answer = 0;
        long power = 1;
        for (; power <= n; power *= 10) {
            long left = (n / power) / 10;
            long right = n % power;
            
            if ((n / power) % 10 == 1) {  //current digit is 1
                answer += power * left + right + 1;
            } else if((n/power)%10==0){ //current digit is 0
            	answer += power * left;
            }else { // current digit >1
                answer += power * (left+1);
            }
        }
        
        return (int) answer;
    }	   
    
    public static void main(String args[]) {
    	System.out.print(countDigitOne(13));
    }
}