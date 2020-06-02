package leetcode.easy;
/*
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */

public class ArrangingCoins {
    public static int arrangeCoins(int n) {
    	long sum=0;
    	int res=0;
    	for(int i=1; i<=n;i++) {
    		sum+=i;
    		if(sum==n) {res=i; break;}
    		else if(sum>n) {res=i-1; break;}
    	}
    	return res;
    }
    public static void main(String[]args) {
    	System.out.print(arrangeCoins(2147483647));
    }
}