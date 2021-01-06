package leetcode.hard;

import java.util.Arrays;

/*
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */
public class InterleaveString {
	
	/*
	 * brutal force: exceed time limit
	 * O(n)=2*O(n-1) -> O(2^n)
	 * O(1)
	 */
    public static boolean isInterleaveI(String s1, String s2, String s3) {
        boolean res=false;
        if((s1.length()+s2.length())!=s3.length()) return false;
        if((s1.isEmpty()&&s3.equals(s2)) || (s2.isEmpty()&&s3.equals(s1))) return true;
        else if(s1.isEmpty() || s2.isEmpty()) return false;
        
        if(s1.charAt(0)==s3.charAt(0)){
            res=isInterleaveI(s1.substring(1),s2, s3.substring(1));
            if(res) return res;
        }
        if(s2.charAt(0)==s3.charAt(0)){
            res=isInterleaveI(s1, s2.substring(1),s3.substring(1));
            if(res) return res;
        }
        return res;           
    }
    /*
     * Intuition: dynamic programming
     * dp[l1+1][l2+1] to determine if it is true;
     * dp[0][0]=true
     * dp[i][j]=dp[i-1][j] || dp[i][j-1]
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if((s1.length()+s2.length())!=s3.length()) return false;
        
        boolean [][]dp=new boolean[s1.length()+1][s2.length()+1];
        for(int i=0; i<s1.length(); i++)  Arrays.fill(dp[i], false);
        
        for(int i=0; i<=s1.length(); i++) { // iterate through s1
        	for( int j=0; j<=s2.length(); j++) { // iterate through s2
        		if(i==0 && j==0) dp[i][j]=true;
        		if(i>0 && s1.charAt(i-1)==s3.charAt(i+j-1)) dp[i][j]=dp[i][j]||dp[i-1][j];
        		if(j>0 && s2.charAt(j-1)==s3.charAt(i+j-1)) dp[i][j]=dp[i][j]|| dp[i][j-1];
        	}
        }
    	return dp[s1.length()][s2.length()];
    }
    
    public static void main(String args[]) {
	 //  String s1="bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
	 //  String s2="babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
	 //  String s3="babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
    	String s1="aabcd", s2="abadd", s3="aabadabcdd";
    	System.out.println(isInterleave(s1, s2, s3));
    }
}
