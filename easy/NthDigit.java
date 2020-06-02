package leetcode.easy;

/*Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,... is a 0, which is part of the number 10.
*/
class NthDigit {
	// time limit exceed
    public static int findNthDigit(int n) {
    	int digit=-1,count=n,length=1,power=1,power_10=(int) Math.pow(10, power);
        for(int i=1; i<=n; i++) {
        	if(i>=power_10){
        		length++;
        		power++;
        		power_10=(int) Math.pow(10, power);
        	}
        	if(count<=length) {
        		digit=String.valueOf(i).charAt(count-1)-'0';
        		return digit;
        	}
        	count-=length;
        }
        return digit;
    }
    public static void main(String args[]) {
    	System.out.print(findNthDigit(1000000000));
    }
}
