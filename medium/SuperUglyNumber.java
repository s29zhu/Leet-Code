package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

Example:

Input: n = 12, primes = [2,7,13,19]
Output: 32 
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
             super ugly numbers given primes = [2,7,13,19] of size 4.
Note:

1 is a super ugly number for any given primes.
The given numbers in primes are in ascending order.
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
public class SuperUglyNumber {
	
	//exceed time limit
    public static int nthSuperUglyNumberI(int n, int[] primes) {
       int  k=primes.length, next=Integer.MAX_VALUE;
       int [] uglyNums=new int[n];
       uglyNums[0]=1;
       for(int i=1;i<n;i++) {
    	   int min=Integer.MAX_VALUE;
    	   for(int j=0; j<i; j++) { // previous ugly numbers
    		  for(int h=0; h<k; h++) { // iterate through primes
    			  next=primes[h]*uglyNums[j];
    			  if(next>uglyNums[i-1]) break; 
    			  else next=Integer.MAX_VALUE;
    		  }
    		  min=Math.min(min, next);
    	   }
    	   uglyNums[i]=min;
       }
       return uglyNums[n-1];
    }
    
    //answer from leetcode
	/*
	 * index[j] is an index points to an element in res array, 
	 * where exist res[Index[j]]*primary[j] == res[i],
	 * for a certain res[i], 
	 * there could be multiple combinations satisfy res[Index[j]]*primary[j] == res[i]
	 * 
	 * So the next potential ugly number could be res[Index[j]+1]*primary[j]
	 * 
	 * Index[j] initialized as 0, it evolves as i grows
	 * 
	 */
    public static int nthSuperUglyNumber(int n, int[] primes) {
    	int[] res=new int[n];
    	res[0]=1;
    	int [] index=new int[primes.length];

    	for(int i=1; i<n;i++) {
    		res[i]=Integer.MAX_VALUE;
    		for(int j=0;j<primes.length; j++) {
    			res[i]=Math.min(res[i], primes[j]*res[index[j]]); //get the next element
    		}
    		
    		//prepare the index for getting next next element
    		for(int j=0;j<primes.length; j++) {
    			if(primes[j]*res[index[j]]==res[i]) { // there always will be an combination to make it == res[i]
    				index[j]++;//
    			}
    		}
    	}
    	return res[n-1];
    }
    
    public static void main(String args[]) {
    	int [] primes={2,7,13,19};
    	//{1,2,4,5,7,8,10,11,13,14,16,17,20,22,23,26,28,29,32,34,40,43,44,48,52,53...}
    	System.out.print(nthSuperUglyNumber(10, primes));
    }
}
