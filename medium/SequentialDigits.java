package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Note: low starts from 10
high is less than 10^9
 */
public class SequentialDigits {
	/*
	 * Intuition: Back Tracking 
	 */
    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list=new ArrayList<Integer>();
        Queue<Integer> que=new LinkedList<Integer>();
        String str_low=String.valueOf(low);
        int length=str_low.length();
        int add=1;
        for(int i=1; i<length; i++) { add=add*10+1; }       
        int temp_low=1;
        for(int i=1; i<length; i++) { temp_low=temp_low*10+(i+1);}
        while(temp_low%10!=0) {
        	que.add(temp_low);
        	temp_low+=add;
        }
        while(!que.isEmpty()) {
        	int temp=que.poll();
        	if(temp>high) break;
        	if(temp>=low && temp<=high) list.add(temp);
        	if(temp%10==9) {
        		add=1;
        		length++;
                for(int i=1; i<length; i++) { add=add*10+1; }       
                temp_low=1;
                for(int i=1; i<length; i++) { temp_low=temp_low*10+(i+1);}
                while(temp_low%10!=0) {
                	que.add(temp_low);
                	temp_low+=add;
                }
        	}
        }
        return list;
    }
    
    public static void main(String args[]) {
    	sequentialDigits(54, 123);
    	System.out.print("done");
    }
}
