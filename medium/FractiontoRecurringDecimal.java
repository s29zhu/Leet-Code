package leetcode.medium;

import java.util.HashMap;

/*
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */
/*
 Analysis:
 For Number problems, be careful of the integer boundaries
 */
public class FractiontoRecurringDecimal {
    public static String fractionToDecimal(int numerator, int denominator) {
        String res="";
        if((numerator<0&&denominator>0)||(numerator>0&&denominator<0)) res+="-";
        res+=String.valueOf(Math.abs((long)numerator/denominator));
        if (numerator%denominator!=0) {
        	res+=".";
        	res+=fraction(Math.abs(numerator%denominator),Math.abs((long) denominator));
        }
        return res;
    }
    
    public static String fraction(long n, long d) {
    	String res="";
    	int temp=0;
        HashMap<Long, Integer> map=new HashMap<Long, Integer>();
        do {
            res+=String.valueOf(n*10/d);
        	map.put(n,temp);
            n=(n*10)%d;
            temp++;
        }while(n!=0 && !map.containsKey(n));
        if(n==0) return res;
        res=res.substring(0,map.get(n))+"("+res.substring(map.get(n))+")";
        return res;
    }
    public static void main(String []args) {
    	int d=1, n=-2147483648;
    	//System.out.print(Math.abs((long)d));
    	System.out.print(fractionToDecimal(n,d));
    }
}
