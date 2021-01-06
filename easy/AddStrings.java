package leetcode.easy;
/*
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {
    public static String addStrings(String num1, String num2) {
        StringBuilder res= new StringBuilder();
        int carry=0;
        int l1=num1.length(), l2=num2.length();
        if(l1==0 && l2!=0) return num2;
        if(l1!=0 && l2==0) return num1;
        for(int i=l1-1, j=l2-1; (i>=0)||(j>=0);i--,j--) {
        	int temp=0;
        	if(i>=0 && j>=0)  temp=num1.charAt(i)-'0'+num2.charAt(j)-'0'+carry;
        	else if(i>=0 && j<0) temp=num1.charAt(i)-'0'+carry;
        	else if(j>=0 && i<0) temp=num2.charAt(j)-'0'+carry;
        	carry=temp/10;
        	temp%=10;
        	res.append(temp);
        }
        if(carry==1) res.append(carry);
        return res.reverse().toString();
    }
    public static void main(String args[]) {
    	String num1="9", num2="99";
    	System.out.print(addStrings(num1,num2));;
    }
}
