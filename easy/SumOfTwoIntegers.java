package leetcode.easy;
/*
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1
 */
public class SumOfTwoIntegers {

	public static void main(String args[]) {
		// int a=1, b=3;
		// int a=2, b=2;
		 int a=1, b=2;
		while((a&b)!=0) {
			int temp1=(a&b)<<1; 
			int temp2=(a^b);// 10
			a=temp1;
			b=temp2;
		}
		System.out.println(a|b);
	}
}
