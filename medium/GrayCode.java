package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:

Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2

For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.

00 - 0
10 - 2
11 - 3
01 - 1
Example 2:

Input: 0
Output: [0]
Explanation: We define the gray code sequence to begin with 0.
             A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
             Therefore, for n = 0 the gray code sequence is [0].
 */
public class GrayCode {
	/*
	 * Intuition
	 * n=1
	 * 0,1
	 * n=2
	 * 00 0+0   0
	 * 01 1+0   1
	 * 11 1+2   3
	 * 10 0+2   2
	 * n=3
	 * 000     0
	 * 001     1
	 * 011     3
	 * 010     2
	 * 110     2+4
	 * 111     3+4
	 * 101     1+4 
	 * 100     0+4
	 */
    public static List<Integer> grayCode(int n) {
        List<Integer> res=new ArrayList<Integer>();
        if(n==0) { res.add(0); return res;}
        List<Integer> temp=grayCode(n-1);
        res.addAll(temp);
        for(int i=temp.size()-1; i>=0; i--) {
        	res.add(temp.get(i)+(1<<(n-1)));
        }
        return res;
    }
    public static void main(String args[]) {
    	List<Integer> list=grayCode(3);
    	
    	System.out.print(1+(1<<0));
    }
}
