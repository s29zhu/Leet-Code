package leetcode.hard;

import java.util.Arrays;

/*
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:

 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:

 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation: 
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:

 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation: 
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */

public class EditDistance {
	
    public static int helper(String word1, String word2, int [][]dp) {  
    	int res=Integer.MAX_VALUE;
    	int l1=word1.length(), l2=word2.length();
    	if(word1.equals(word2)) {
    		dp[l1][l2]=0;
    		return dp[l1][l2];
    	}
    	if( l1==0 || l2==0 ) {
    		dp[l1][l2]=Math.max(l1, l2);
    		return dp[l1][l2];
    	}
    	if(word1.charAt(0)==word2.charAt(0)) {
    		dp[l1][l2]=(dp[l1-1][l2-1]!=-1)?dp[l1-1][l2-1]:helper(word1.substring(1), word2.substring(1),dp);
    		return dp[l1][l2];
    	}
    	else {
    		//inserting the word2 first character into word1
    		res=(dp[l1][l2-1]!=-1)?dp[l1][l2-1]:helper(word1, word2.substring(1),dp);
    		//replacing the word1 first character as word2's first character
    		int temp=(dp[l1-1][l2-1]!=-1)?dp[l1-1][l2-1]:helper(word1.substring(1), word2.substring(1),dp);
    		res=Math.min(res, temp);
    		//deleting the first character the of word1
    		temp=(dp[l1-1][l2]!=-1)?dp[l1-1][l2]:helper(word1.substring(1), word2,dp);
    		res=Math.min(res, temp);
    		dp[l1][l2]=res+1;
    	}
    	return dp[l1][l2];
    }
	/*
	 * Intuition, compare word1 and word2 from beginning to end,
	 * 1. If the first letter matches, get the minDistance(w1.substring(1),w2.substring(1)). 
	 * 2. If the first letter doesn't match, get the minimum, replace the word1 first char, insert char, delete char 
	 * and try to get the smallest number
	 * 
	 *Time Complexity: O(mn)
	 *Space Complexity: O(mn)
	 */
    public static int minDistance(String word1, String word2) {  
    	if(word1.equals(word2)) return 0;
    	int l1=word1.length(), l2=word2.length();
    	int [][]dp=new int[l1+1][l2+1];
    	for(int i=0; i<=l1;i++) {
    		Arrays.fill(dp[i], -1);
    	}
    	return helper(word1, word2, dp);
    }
    public static void main(String args[]) {
    	String s1="dinitrophenylhydrazine",s2="acetylphenylhydrazine"; 
    //	String s1="enylhydrazine", s2="ne";
    	System.out.print(minDistance(s1,s2));
    }
}