package leetcode.hard;

import java.util.HashMap;

/*
 * https://leetcode.com/problems/integer-to-english-words/
 * Convert a non-negative integer to its english words representation. 
 * Given input is guaranteed to be less than 2^31 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToHardWords {
	/*
	 * testcase: 
	 * 1. consider 0, 10,102, 120,100, 602000, 100001
	 * 2. The space between each word
	 * 3. numbers between 10 and 20
	 */
	public static String HundredsI2W(int num) {
		HashMap<Integer, String> m1=new HashMap<Integer, String>();
		HashMap<Integer, String> m2=new HashMap<Integer, String>();
		HashMap<Integer, String> m3=new HashMap<Integer, String>();
		m1.put(0, "");
		m1.put(1, " One");
		m1.put(2, " Two");
		m1.put(3, " Three");
		m1.put(4, " Four");
		m1.put(5, " Five");
		m1.put(6, " Six");
		m1.put(7, " Seven");
		m1.put(8, " Eight");
		m1.put(9, " Nine");
		m2.put(0, "");
		m2.put(2, " Twenty");
		m2.put(3, " Thirty");
		m2.put(4, " Forty");
		m2.put(5, " Fifty");
		m2.put(6, " Sixty");
		m2.put(7, " Seventy");
		m2.put(8, " Eighty");
		m2.put(9, " Ninety");
		m3.put(10, " Ten");
		m3.put(11, " Eleven");
		m3.put(12, " Twelve");
		m3.put(13, " Thirteen");
		m3.put(14, " Fourteen");
		m3.put(15, " Fifteen");
		m3.put(16, " Sixteen");
		m3.put(17, " Seventeen");
		m3.put(18, " Eighteen");
		m3.put(19, " Nineteen");
		String res="";
		int r=0;
		int save=num;
		while(num>0) {
			int n=num%10;
			if(r==0) res=m1.get(n)+res;
			else if(r==1 && n==1) {
				save=save%100;
				res=m3.get(save);
			}
			else if(r==1 && n>1) {
				res=m2.get(n)+res;
			}
			else if(r==2) res=m1.get(n)+" Hundred"+res;
			num/=10;
			r++;
		}
		return res;		
	}
    public static String numberToWords(int num) {
    	String res="";
    	int rounds=0;
    	if(num==0) return "Zero";
    	HashMap<Integer, String> map=new HashMap<Integer, String>();
    	map.put(0,"");
    	map.put(1," Thousand");
    	map.put(2, " Million");
    	map.put(3, " Billion");
    	
		while(num>0) {
			int number=num%1000;
			String s=HundredsI2W(number);
			if(!s.isBlank()) res=s+map.get(rounds)+res;
			rounds++;
			num/=1000;
		}
		res=(res.isEmpty())?res:res.substring(1);
		return res;
    }
    
    public static void main(String args[]) {
    	System.out.println(numberToWords(1000));
    }
}
