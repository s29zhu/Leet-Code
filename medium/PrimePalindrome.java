package leetcode.medium;

import java.util.ArrayList;

/*
 * Find the smallest prime palindrome greater than or equal to N.

Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1. 

For example, 2,3,5,7,11 and 13 are primes.

Recall that a number is a palindrome if it reads the same from left to right as it does from right to left. 

For example, 12321 is a palindrome.

 

Example 1:

Input: 6
Output: 7
Example 2:

Input: 8
Output: 11
Example 3:

Input: 13
Output: 101
 

Note:

1 <= N <= 10^8
The answer is guaranteed to exist and be less than 2 * 10^8.
 */
public class PrimePalindrome {
	
	public static boolean isPrime(int n) {
		int sqrt=(int)Math.sqrt(n);
        if(n==1) return false;
        for(int i=2; i<=sqrt; i++) {
        	if(n%i==0) return false;
        }
		return true;
	}
	
	public static boolean isPalindrome(int n) {
		int tmp=n, ans=0;
		while(n>0) {
			ans=ans*10+n%10;
			n/=10;
		}
		if(ans==tmp) return true;
		else return false;
	}
	
	public static int nextPalindrome(int n) {
		if(isPalindrome(n)) return n;
		String str=String.valueOf(n);
		int l=str.length();
		//l is odd
		// 919 -> 92->929
		//999 -> 99 -> 100 -> 10001
		StringBuilder left_s=new StringBuilder(str.substring(0,(l+1)/2));
		StringBuilder right_s=new StringBuilder(str.substring((l+1)/2));
		int left_i=Integer.valueOf(left_s.toString());
		int right_i=Integer.valueOf(right_s.toString());
		if(l%2==1) //l is odd
		{
			StringBuilder right_str=new StringBuilder(left_s.substring(0,left_s.length()-1)).reverse();
			str=left_s.toString()+right_str.toString();
			if(Integer.valueOf(str) > n) return Integer.valueOf(str);
			else if(String.valueOf(left_i+1).length()==String.valueOf(left_i).length()) {
				String temp=String.valueOf(left_i+1);
				temp=temp.substring(0,temp.length()-1);
				right_str=new StringBuilder(temp);
				right_str=right_str.reverse();
				str=String.valueOf(left_i+1)+right_str.toString();
			}else {
				String temp=String.valueOf(left_i+1);
				temp=temp.substring(0,temp.length()-1);
				StringBuilder left_str=new StringBuilder(temp);			
				str=left_str.toString()+left_str.reverse().toString();
			}
		} else {	// l is even	
			//1001 -> 1111
			//99 -> 101
			str=left_s.toString()+left_s.reverse().toString();
			if(Integer.valueOf(str) > n) return Integer.valueOf(str);
			else if(String.valueOf(left_i+1).length()==String.valueOf(left_i).length()) {
				StringBuilder right_str=new StringBuilder(String.valueOf(left_i+1));
				str=String.valueOf(left_i+1)+right_str.reverse().toString();
			}else {
				StringBuilder left_str=new StringBuilder(String.valueOf(left_i+1));			
				StringBuilder right_str=new StringBuilder(left_str.substring(0,left_str.length()-1));
				right_str=right_str.reverse();
				str=left_s + right_str.toString();
			}
		}
		return Integer.valueOf(str);
	}

    public static int primePalindrome(int N) {
    	N=nextPalindrome(N);
    	while(true) {
    		if(isPrime(N)) return N;
    		N=nextPalindrome(N+1);
    	}
    }
    
    public static void main(String args[]) {
    	System.out.println(primePalindrome(23345) );
    }
}
