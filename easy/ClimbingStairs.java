package leetcode.easy;
/*
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */

public class ClimbingStairs {
    public static int climbStairs(int n) {
        int []res=new int[n+1];
        res[1]=1;
        if(n>1) res[2]=2;
        for(int i=3; i<=n; i++) {
        	res[i]=res[i-1]+res[i-2];
        }
        return res[n];
    }
    public static void main(String []args) {
    	System.out.print(climbStairs(44));
    }
}
