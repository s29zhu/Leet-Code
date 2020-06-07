package leetcode.medium;
/*
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 

 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 

Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
 */
/*
 * Analysis:
 * 1. hashmap to store letter and count ?? sassa, sasa
 */
public class ExpressiveWords {
    public static int expressiveWords(String S, String[] words) {
    	int res=0;
    	for(int i=0;i<words.length;i++) {
    		res+=isStretchy(S,words[i]);
    	}
    	return res;
    }
    public static int isStretchy(String a, String b) {
    	int res=0, la=a.length(), lb=b.length();
    	int []count_a=new int[la];
    	int []count_b=new int[lb];
    	count_a[la-1]=1;
    	for(int i=la-1;i>0;i--) {
    		if(a.charAt(i)==a.charAt(i-1)) count_a[i-1]=count_a[i]+1;
    		else count_a[i-1]=1;
    	}
    	count_b[lb-1]=1;
    	for(int i=lb-1;i>0;i--) {
    		if(b.charAt(i)==b.charAt(i-1)) count_b[i-1]=count_b[i]+1;
    		else count_b[i-1]=1;
    	}
    	for(int i=0,j=0;i<la&&j<lb;) {
    		char ca=a.charAt(i);
    		char cb=b.charAt(j);
    		if(ca==cb && ((count_a[i]>=3 && count_b[j]<=count_a[i])||count_a[i]==count_b[j])){
    			i+=count_a[i];
    			j+=count_b[j];
    			if(i==la && j==lb) return 1;
    			if((i==la && j!=lb) ||(i!=la &&j==lb)) return 0;
    		}else {
    			return 0;
    		}
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	String a="ggkyyyyffffbbhddddrxxsiixccqqqqkmmmiiiiiivvvyyuuujccuuuhhhhwssssnnttoyuuuussggttttfeeeebbbbeedddddqq";
    	String [] wordlist= {"ggkyyfffbbhddrxxsiixccqqkmmmiiiivvvyyuujccuuuhhhwsnnttoyuuussggtttfebeedddddqq",
    	                      "ggkyyyyffbbhdrxxsiixccqkmmiiiivyyujccuhhwsssnnttoyuuussggtfebeeddddqq",
    	                      "ggkyyfffbbhdddrxxsiixccqkmmmiiiiivyyujccuuhhwsssnnttoyuuussggtfebbeeddddqq"};
    	
    	for(String s: wordlist) {
    		System.out.println(isStretchy(a,s));
    	}
    }
}
