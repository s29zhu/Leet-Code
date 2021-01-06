package leetcode.medium;
/*
 * https://leetcode.com/problems/integer-break/
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the 
 * product of those integers. Return the maximum product you can get.
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        if(n==1)
            return 0;
        else if(n==2)
            return 1;
        else if(n==3)
            return 2;
        else if(n==4)
            return 4;
        else if(n==5)
            return 6;
        else if(n==6)
            return 9;
        
        if(n%3 == 0) 
            return (int)Math.pow(3,n/3);
        else if (n%3==1)
            return (int)Math.pow(3,n/3-1)*4;
        else
            return (int)Math.pow(3,n/3)*2;
            
    }
}
