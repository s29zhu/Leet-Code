package leetcode.hard;
/*
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.

 

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
 

Constraints:

1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length
 */
public class ValidPalindromeIII {
	public static boolean isPalindrome(String s) {
		int l=s.length();
		for(int i=0,j=l-1; i<=j; i++,j--) {
			if(s.charAt(i)!=s.charAt(j)) return false;
		}
		return true;
	}
    public static boolean isValidPalindrome(String s, int k) {
        boolean res=false;
        if(isPalindrome(s)==true) return true;
        if(!isPalindrome(s)&&k<=0) return false;
        int l=s.length();
        if(s.charAt(0)==s.charAt(l-1)&& 1<l&&l-1>0&& 1<=l-1) {
        		res=isValidPalindrome(s.substring(1,l-1),k);
        		if(res) return res;
        }
        else {
        		res=isValidPalindrome(s.substring(1,l),k-1);
        		if(res) return res;
        		res=isValidPalindrome(s.substring(0,l-1),k-1);
        		if(res) return res;
        }
        return res;
    }
    
    public static void main(String args[]) {
    	String s="aabcdecaa";
    	System.out.print(isValidPalindrome(s, 2));
    }
}
