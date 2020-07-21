package leetcode.medium;
/*
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    /*
     * intuition, iterate from start to end, charAt(i) larger than charAt(i+1) remove charAt(i)
     *  num = "1432219", k = 1 -> 132219
     *  num = "1234567", k = 1 -> 123456
     *  
     *  corner cases:
     *  1. string with '0' at the beginning
     *  2. string is all '0's
     *  
     *  O(Kn)
     */
    public String removeKdigits(String num, int k) {
    	String res="";
    	int l=num.length();
    	if(k==0) return num;
    	for(int i=0; i<num.length(); i++) {
    		char c1=num.charAt(i);
    		char c2=' ';
    		if(i+1<num.length()) c2=num.charAt(i+1);
    		if(c2!=' ' && c1>c2) {
    			if(i==0) num=num.substring(1);
    			else if( i>0 && i<l-1) num=num.substring(0,i)+num.substring(i+1);
    			else if(i==l-1) num=num.substring(0,l-1);
    			res=removeKdigits(num, k-1);
    			break;
    		} else if(c2==' ') {
    			num=num.substring(0,l-1);
    			res=removeKdigits(num, k-1);
    			break;
    		}
    	}
    	//handle corner cases, when res starts with 0s
    	int index=-1;
    	for(int i=0; i<res.length(); i++) {
    		if(res.charAt(i)!='0') break;
    		else index=i;
    	}
    	res=res.substring(index+1);
    	//if res is empty
    	if(res.isEmpty()) res="0";
    	StringBuilder str=new StringBuilder(123);
    	
    	str.reverse();
    	return res;
    }
}
