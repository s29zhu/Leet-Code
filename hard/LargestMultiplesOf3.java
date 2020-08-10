package leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/largest-multiple-of-three/
 * Given an integer array of digits, return the largest multiple of three that can be formed by concatenating 
 * some of the given digits in any order.
Since the answer may not fit in an integer data type, return the answer as a string.
If there is no answer return an empty string.

Example 1:

Input: digits = [8,1,9]
Output: "981"
Example 2:

Input: digits = [8,6,7,1,0]
Output: "8760"
Example 3:

Input: digits = [1]
Output: ""
Example 4:

Input: digits = [0,0,0,0,0,0]
Output: "0"
 

Constraints:

1 <= digits.length <= 10^4
0 <= digits[i] <= 9
The returning answer must not contain unnecessary leading zeros.
 */
public class LargestMultiplesOf3 {
	/*
	 * intuition: 
	 * 1. sort digit 
	 * 2. find the maximum number of digits that has sum of multiples of 3 
	 * 2.1 one queue to save the mod3 = 1 
	 * 2.2 one queue to save the mod3 = 2 
	 * 3. Concatenate the digits from big to small order
	 */
    public static String largestMultipleOfThree(int[] digits) {
    	String res="";
    	Queue<Integer> q1=new LinkedList<Integer>();
    	Queue<Integer> q2=new LinkedList<Integer>();
    	Set<Integer> set=new HashSet<Integer>();
    	int sum=0;
    	Arrays.parallelSort(digits);
    	for(int i=0; i<digits.length; i++) {
    		sum+=digits[i];
    		if(digits[i]%3==1) q1.add(i);
    		else if(digits[i]%3==2) q2.add(i);
    	}
        if(sum==0) return "0";
    	if(sum<=2
    	|| (sum%3==1 && q1.size()<=0 && q2.size()<2)
    	|| (sum%3==2 && q2.size()<=0 && q1.size()<2)) return res;

    	if(sum%3==1 && q1.size()>0) {
    		set.add(q1.poll());
    	}else if(sum%3==1 && q2.size()>1) {
    		set.add(q2.poll());
    		set.add(q2.poll());
    	}else if(sum%3==2 && q2.size()>0) {
    		set.add(q2.poll());
    	}else if(sum%3==2 && q1.size()>1) {
    		set.add(q1.poll());
    		set.add(q1.poll());
    	}
    	
    	for(int i=digits.length-1; i>=0; i--) {
    		if(!set.contains(i)) res+=String.valueOf(digits[i]);
    	}
        return res;
    }
    
    public static void main(String args[]) {
    	int []digits= {8,1,9};
    	System.out.print(largestMultipleOfThree(digits));
    }
}
