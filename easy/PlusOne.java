package leetcode.easy;
/*
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
 */
public class PlusOne {
    public static int[] plusOne(int[] digits) {
        int [] res = new int[digits.length+1];
        int carry=0,l=digits.length;
        digits[l-1]+=1;
        for(int i=l-1;i>=0;i--) {
        	digits[i]=digits[i]+carry;
        	carry=digits[i]/10;
        	digits[i]%=10;
        }
        if(carry==1)res[0]=1;
        else res=digits;
        return res;
    }
    public static void main(String args[]) {
    	int [] num= {1,9,9};
    	int []res=plusOne(num);
    	for(int i=0; i<res.length;i++) {
    		System.out.print(res[i]);
    	}
    }
}
