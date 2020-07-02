package leetcode.easy;
/*
 * Given a year Y and a month M, return how many days there are in that month.

 

Example 1:

Input: Y = 1992, M = 7
Output: 31
Example 2:

Input: Y = 2000, M = 2
Output: 29
Example 3:

Input: Y = 1900, M = 2
Output: 28
 

Note:

1583 <= Y <= 2100
1 <= M <= 12
 */
public class NumberOfDaysInAMonth {
    public static int numberOfDays(int Y, int M) {
        //Y is leap year, M is Feb,
    	int res=0;
    	boolean leap_year=false;
    	if(Y%4==0) {
    		if(Y%400==0) leap_year=true;
    		else if(Y%100==0) leap_year=false;
    		else leap_year=true;
    	}
    	if(leap_year&&M==2) return 29;
    	else if(M==2) return 28;
    	if(M%2==0) return 30;
    	else if(M%2==1) return 31;
    	return res;
    }
    
    public static void main(String args[]) {
    	
    }
}
