package leetcode.hard;

import java.util.HashSet;
/*
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.

While entering a password, the last n digits entered will automatically be matched against the correct password.

For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.

Return any password of minimum length that is guaranteed to open the box at some point of entering it.

 

Example 1:

Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:

Input: n = 2, k = 1
output: "00"
pow(k,n)+n-1 -> 2
  
Input: n = 2, k = 2   
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
pow(k,n)+n-1 -> 5
 
Input: n=2, k=3
Output: "0011021220"
All posibilities: pow(k,n), string length is pow(k,n)+n-1
Input: n=2, k=4
Output: "00110212203132330"  

Input: n=3, k=1
output: "000"  1^3+3-1=3

Input: n=3, k=2
output: "00011"  2^3+3-1=10
000 001 010
101 011 111
110 100

Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.
 */

/*
 Analysis:
 1. 
 2
 */
public class CrackTheSafe {
    public static String crackSafeII(int n, int k) {
        String res="";
        String [] dp=new String[k];
        for(int i=0;i<n;i++) res+="0";
        dp[0]=res;
        String sub="";
        for(int i=1;i<k;i++) {
        	res=dp[i-1];
        	int l=res.length(), newl=(int) (Math.pow(i+1,n)-Math.pow(i,n)-2);
        	sub=String.valueOf(i);
        	for(int j=l-1-newl;j<l-1;j++) {
        		sub+=String.valueOf(res.charAt(j)-'0'+1);
        	}
        	if(sub.length()<newl+2) sub+=res.charAt(l-1);
        	dp[i]=res+sub;
        }
        return dp[k-1];
    }
    
	public static String crackSafe(int n, int k) {
		String res="", cur="";
        HashSet<String> set=new HashSet<String>();
        for(int i=0;i<n;i++) res+="0";
        set.add(res);
        while(set.size()<Math.pow(k, n)) {
        	cur=res.substring(res.length()-n+1, res.length());
        	for(int i=k-1;i>=0;i--) { // i has to be iterated from large to small, so that i won't end up in endless loop
        		cur+=String.valueOf(i);
        		if(!set.contains(cur)) {
        			set.add(cur);
        			res+=String.valueOf(i);
        			break;
        		}
        	}
        }
        return res;
	}
	
	//NOT WORKING for N>K

    public static void main(String args[]) {
    	System.out.println(crackSafe(2,1));
    //	System.out.println(crackSafe(2,2));
    //	System.out.println(crackSafe(3,2));
    //	1,1 -> pow(1,1)+1-1=1
    //	1,2 -> pow(2,1)+1-1=2
    }
}
