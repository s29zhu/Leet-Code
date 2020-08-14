package leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
 */
public class UglyNumberII {
	/*
	 * intuition: calculate the nth ugly number
	 */
    public static int nthUglyNumber(int n) {
    	Queue<Integer> que=new PriorityQueue<Integer>();
    	que.add(1);
    	for(int i=1; i<n; i++) {
    		int num=que.poll();
    		long p=((long) num)*2;
    		if(!que.contains((int)p) && p<(long)Integer.MAX_VALUE) que.add((int)p);
    		p=((long) num)*3;
    		if(!que.contains((int)p) && p<(long)Integer.MAX_VALUE) que.add((int)p);
    		p=((long) num)*5;
    		if(!que.contains((int)p) && p<(long)Integer.MAX_VALUE) que.add((int)p);
    	}
        return que.peek();
    }
    
    public static void main(String args[]) {
    	System.out.print(nthUglyNumber(1548));
    }
}
