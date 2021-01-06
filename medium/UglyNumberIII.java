package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Write a program to find the n-th ugly number.

Ugly numbers are positive integers which are divisible by a or b or c.

 

Example 1:

Input: n = 3, a = 2, b = 3, c = 5
Output: 4
Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
Example 2:

Input: n = 4, a = 2, b = 3, c = 4
Output: 6
Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
Example 3:

Input: n = 5, a = 2, b = 11, c = 13
Output: 10
Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
Example 4:

Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
Output: 1999999984
 

Constraints:

1 <= n, a, b, c <= 10^9
1 <= a * b * c <= 10^18
It's guaranteed that the result will be in range [1, 2 * 10^9]
 */
public class UglyNumberIII {
	/*
	 * O(log(n))
	 *  binary search, the largest number is 2*10^9, 
	 *  the result range from 1 to 2 * 10 ^ 9, 
	 *  result will be multiples of a , b, c 
	 *  res/a + res/b + res/c - (res/(a*b) + res/(a*c) +res/(b*c)) + res/(a*b*c)
	 */
    public static int nthUglyNumber(int n, int a, int b, int c) {
        long l = 0, r = 2_000_000_000;
        long ab = lcm((long) a, (long) b), ac = lcm((long) a, (long) c), bc = lcm((long) b, (long) c), abc = lcm(a, bc);
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (count(mid, a, b, c, ab, ac, bc, abc) < n) l = mid + 1;
            else r = mid;
        }
        return (int) l;
    }
    
    public static long count(long num, int a, int b, int c, long ab, long ac, long bc, long abc) {
        return num / a + num / b + num / c 
            - num / ab - num /ac - num / bc
            + num / abc;
    } 
    
    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
    
    public static long gcd(long a, long b) {
        if (a < b) return gcd(b, a);
        while (b > 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    
	/*
	 * intuition, 
	 *  next ugly number is multiples of a, b or c
	 *  O(n)
	 */
    public static int nthUglyNumberI(int n, int a, int b, int c) {
        int am = 1, bm = 1, cm = 1, cnt = 0;
		long min = 1;
        while (cnt++ < n) {
            long an = (long)am * a, bn = (long) bm * b, cn = (long)cm * c;
            min = Math.min(an, Math.min(bn, cn));
            if (an == min) am++;
            if (bn == min) bm++;
            if (cn == min) cm++;
        }
        return (int)min;
    }
    
    /*
     * time limit exceed
     */
    public static int nthUglyNumberII(int n, int a, int b, int c) {
    	int []m = {a,b,c};
    	Arrays.parallelSort(m);
        int p0=m[0],p1=m[1],p2=m[2];
    	int res=m[0];
    	for(int i=1; i<=n; i++) {
    		if(p0 < p1 && p0 < p2) {
    			res=p0;
    			p0+=m[0];
    			while(p0 == p1 || p0 == p2) {
    				p0+=m[0];
    			}
    			if(p0<0) p0=Integer.MAX_VALUE;
    		}else if(p1<p0 && p1<p2) {
    			res=p1;
    			p1+=m[1];
    			while(p1 == p2 || p1 == p0) {
    				p1+=m[1];
    			}
    			if(p1<0) p0=Integer.MAX_VALUE;
    		}else {
    			res=p2;
    			p2+=m[2];
    			while(p2 == p1 || p2 == p0) {
    				p2+=m[2];
    			}
    			if(p2<0) p0=Integer.MAX_VALUE;
    		}
    	}
        return res;
    }
    public static void main(String args[]) {
        System.out.print(nthUglyNumber(300,2,3,5));
    }
}
