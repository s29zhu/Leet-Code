package leetcode.easy;
import java.util.HashSet;
/*
 * Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
    	int m=0, l=0;
    	HashSet<Integer> set=new HashSet<Integer>();
    	if (n==1) return true;
    	while(m != 1) {
    		m=0;
    		set.add(n);
	    	while(n != 0) {
	    		l=n%10;
	    		m+=l*l;
	    		n/=10;
	    	}
	    	n=m;
	    	if (set.contains(n)) return false;	    	
    	}
    	return true;

    }
    public static void main(String args[]) {
    	System.out.print(isHappy(191));
    }
}
