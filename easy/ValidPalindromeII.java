package leetcode.easy;
/*
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII {
	public boolean helper(String s, int begin, int end) {
		for(int i=begin,j=end; i<=j; i++,j--) {
			if(s.charAt(i)!=s.charAt(j)) return false;
		}
		return true;
	}
    public boolean validPalindrome(String s) {
        boolean res=true;
        int l=s.length();
        for(int i=0,j=l-1; i<=j; i++,j--) {
        	if(s.charAt(i)!=s.charAt(j)) { // if there is one character not match
        		res=helper(s, i+1, j) || helper(s, i, j-1);
        		return res;
        	}
        }
        return res;
    }
    
    public static void main(String args[]) {
    	
    }
}
