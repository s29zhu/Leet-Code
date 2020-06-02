package leetcode.medium;

/*
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
row 5: 0110100110010110
Note:

N will be an integer in the range [1, 30].
K will be an integer in the range [1, 2^(N-1)].
 */

/*
 * 						0
 * 					/       \
 * 				   0         1
 * 				 /	\		/  \
 * 				0    1     1    0
 * 			   / \  / \   / \  / \
 *            0   11   0 1   0 0  1
 * Look at the tree above, when K is even, it has the same value as the parent
 * when k is odd, it is the opposite as the parent
 */
public class KthSymbolInGrammar {
	public static int kthGrammar(int N, int K) {
		int res=0;
    	if(N==1) return 0;
    	if(K%2==1) return kthGrammar(N-1,(K+1)/2);
    	else {
    		res=kthGrammar(N-1,K/2);
    		res=(res==0?1:0);
    		return res;
    	}
    }
	
	// brutal force, this is not going to work, time limit exceed
    public static int kthGrammarB(int N, int K) {
    	int res=-1;    	
   		String s="0", temp="";
   		int l=s.length();
   		for(int i=1;i<N;i++) {
   			l=s.length();
   			temp="";
   			for(int j=0;j<l;j++) {
   				if(s.charAt(j)=='0') temp+="1";
   				else temp+="0";
   			}
			s=s+temp;
			System.out.println(i);
			if(s.length()>=K) res=s.charAt(K-1)-'0'; 
   		}
   		
   		if(s.length()>=K) res=s.charAt(K-1)-'0'; 
    	return res;
    }
    public static void main(String args[]) {
    	System.out.print(kthGrammar(2,1));
    }
}
