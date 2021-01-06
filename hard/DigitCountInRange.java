package leetcode.hard;

import java.util.Arrays;

/*
 * Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds, respectively. Return the number of times that d occurs as a digit in all integers between low and high, including the bounds low and high.
 

Example 1:

Input: d = 1, low = 1, high = 13
Output: 6
Explanation: 
The digit d=1 occurs 6 times in 1,10,11,12,13. Note that the digit d=1 occurs twice in the number 11.
Example 2:

Input: d = 3, low = 100, high = 250
Output: 35
Explanation: 
The digit d=3 occurs 35 times in 103,113,123,130,131,...,238,239,243.
 

Note:

0 <= d <= 9
1 <= low <= high <= 2×10^8
 */
public class DigitCountInRange {
	/*
	 * return count(high) - count(low-1)
	 * test case, 
	 * d=0, low=2,  high=999
	 */
	public static int count(int d, int []dp, int num) {
		int res=0;
		String str=String.valueOf(num);
		int l=str.length();
		for(int i=l-1; i>=1; i--) {
			int temp=str.charAt(l-1-i)-'0';
			for(int j=1; j<=temp; j++) {
				//when d is not 0
				if(j==d && j==temp) res+=Integer.valueOf(str.substring(l-i))+1;
				else if(j==d && j<temp) res+=Math.pow(10, i);
				res+=dp[i]; 
				
				//when d is 0
				if(j<temp) { 
	        		if(d==0) {
	        			for(int k=1; k<=i-1; k++) res+=Math.pow(10,k); //important, the middle digit can be 0
	        		}
				}else if(j==temp) {
					for(int k=l-i; k<l-1; k++) {
						int n1=str.charAt(k)-'0';
						if(n1==0 && d==0) res+=Integer.valueOf(str.substring(k+1))+1;
						else if(n1!=0 && d==0) {
							res+=Math.pow(10, l-k-1);
						}
					}
				}
			}
		}
		if((num%10)>=d) res+=dp[1];
		return res;
	}
	//
    public static int digitsCount(int d, int low, int high) {
        /*
         * dp[1]. dp[1] to save from 0 to Math.pow(10,1)-1 digit occurrence, dp[1]=1 
         * dp[2] from 0 to Math.pow(10,2)-1, 0-99 first digit iterate through 1 to 9, second digit iterate through 0 to 9
         * dp[3] from 0 to Math.pow(10,3]-1, 0-999
         * dp[4] from 0 to Math.pow(10,4)-1, 0-9999 etc
         */
    	int []dp=new int[10];
    	Arrays.fill(dp, 0);
    	dp[0]=0;
    	dp[1]=1; // range from 0 - 9
    	for(int i=2; i<=9; i++) { //try to fill dp[i], i range from 2 to 9
    		//count number of d from pow(10,i) to pow(10,i+1)-1
    		for(int first_d=1; first_d<=9; first_d++) { // first digit range from 1 to 9
    			//if first digit equals to d, it will occur 10^(i-1) times later
    			if(first_d==d) dp[i]+=Math.pow(10, i-1);
    			//from second digit to last digit
    			dp[i]+=dp[i-1]; 
        		if(d==0) {
        			for(int j=1; j<=i-2; j++) dp[i]+=Math.pow(10,j);  //important, the middle digit can be 0
        		}
    		}
    		dp[i]+=dp[i-1]; //add the count from 0-Math.pow(10,i-1)-1 
    	}
    	//int res=count(d, dp, low);
    	int res=count(d, dp, high)-count(d, dp, low-1);
    	return res;
    }
    /* LeetCode Solution C++
     * class Solution {
public:
    int digitsCount(int d, int low, int high) {
        function<int(int, int)> f; 
        f = [](int n, int d) {
            if (n == 0) return d ? 0 : 1;  
            int base = 1, suffix = 0, ans = 0, curr_digit, count_d = 0, count_0 = 1; 
            while (n) {
                curr_digit = n % 10;          
                if (d || n > 9){
                    ans += curr_digit * count_d;
                    if (curr_digit == d) ans += suffix + 1; 
                    else if (curr_digit > d) ans += base;
                    suffix += curr_digit * base;
                    count_0 = 9 * count_d + count_0; 
                    count_d =10 * count_d + base; 
                } else {
                    ans += count_0;  
                    if (curr_digit > 0) ans += (curr_digit - 1) * count_d; 
                }
                n /= 10; 
                if (n) base *= 10;
            }
            return ans;             
        }; 
        return f(high, d) - f(low-1, d); 
    }
};
     */
    public static void main(String args[]) {
    	int low=1080,high=2160,d=0;
    	System.out.println(digitsCount(d, low, high));
    	//0-9:2, 0-99->10, 0-999->190, 0-9999 ->2890, 0-99999->38890, 0-999999->488890
    	int count0=0;
    	for(int i=low; i<=high; i++) {
    		if(i==d) count0++;
    		else {
    			int num=i;
    			while(num>0) {
    				if( num%10==d ) count0++;
    				num=num/10;
    			}
    		}    		
    	}
    	System.out.println(count0);
    }
}