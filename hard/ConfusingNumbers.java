package leetcode.hard;
import java.util.HashSet;
import java.util.HashMap;

/*
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.

 

Example 1:

Input: 20
Output: 6
Explanation: 
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:

Input: 100
Output: 19
Explanation: 
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 

Note:

1 <= N <= 10^9
 */

/*
 * Analysis:
 * Use set the store the confusing numbers
 * enumerate 0 1 6 8 9, 
 * 1 digit 1^5
 * 2 digits 2^5
 * ...
 * 10 digits?, 10^5 (depends on the system bit)
 */
public class ConfusingNumbers {
    public static int confusingNumberII(int N) {
        int count=0,res=0;
        HashSet<Integer> set=new HashSet<Integer>();
    	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    	map.put(0, 1);
    	map.put(1, 6);
    	map.put(6, 8);
    	map.put(8, 9);
    	map.put(9, 0);
    	HashMap<Integer,Integer> m_r=new HashMap<Integer,Integer>();
    	m_r.put(0, 0);
    	m_r.put(1, 1);
    	m_r.put(6, 9);
    	m_r.put(8, 8);
    	m_r.put(9, 6);
    	int temp=N;
    	while(temp!=0) {
    		temp/=10;
    		count++;
    	}
        for(int i=1;i<=count;i++) {
        	int num=1;
        	res+=set.size();
        	set.clear();
        	for(int j=0;j<i-1;j++) {
        		num*=10;
        	}
        	if(!set.contains(num)&&!rotateEq(num,m_r))set.add(num);
        	num=nextNum(i, num,map);
        	while(num!=-1 && num<=N) {
        		if(!set.contains(num)&&!rotateEq(num,m_r))set.add(num);
        		num=nextNum(i,num,map);
        	}
        }
        res+=set.size();
        //1950627
        return res;
    }
    public static int nextNum(int i, int cur, HashMap<Integer,Integer> map ) {
    	int next=-1, max=(int) Math.pow(10, i);
    	if(cur+1==max) return next;
    	int sd=cur%10;
    	next=(cur/10)*10+map.get(sd);
    	if(next<cur) {
    		next=nextNum(i-1,cur/10,map)*10+map.get(sd);
    	}
    	if(next>=max) next=-1;
    	return next;
    }
    public static boolean rotateEq(int num, HashMap<Integer,Integer> m) {
    	boolean res=false;
    	long num_r=0;
    	int temp=num;
    	while(num!=0) {
    		num_r*=10;
    		num_r+=m.get(num%10);
    		num/=10;
    	}
    	res=(num_r==temp)?true:false;
    	return res;
    }
    public static void main(String args[]) {
    	HashMap<Integer,Integer> m_r=new HashMap<Integer,Integer>();
    	m_r.put(0, 0);
    	m_r.put(1, 1);
    	m_r.put(6, 9);
    	m_r.put(8, 8);
    	m_r.put(9, 6);
    	//System.out.println(rotateEq(888,m_r));
    	System.out.print(confusingNumberII(100000000));
    }
    

}
