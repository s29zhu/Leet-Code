package leetcode.hard;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/handshakes-that-dont-cross/
 * You are given an even number of people num_people that stand around a circle and each person shakes hands with someone else, so that there are num_people / 2 handshakes total.

Return the number of ways these handshakes could occur such that none of the handshakes cross.

Since this number could be very big, return the answer mod 10^9 + 7
 */
public class HandShakeThatDontCross {
    public static int numberOfWays(int num_people) {
        //intuition: dynamic programming
        //dp[0]=0,when there is 0 people, 0 handshake
        //dp[2]=1
        //dp[4]=dp[2]*2 = 2
        //dp[6]=d[4]*2+dp[2]*dp[2]=4+1=5
        //dp[8]=dp[6]*2+dp[4]*dp[2]*2=10+4=14
        //dp[10]=dp[8]*2+dp[6]*dp[2]*2+dp[4]*dp[4]
        //dp[2i]=dp[2(i-1)]*2+dp[2(i-2)]*dp[2]*2 +...+dp[2(i-j)]*dp[2j-2]*2  till 2(i-j)<2j-2
    	Integer MOD=1000000007;
        long [] dp=new long[num_people+1];
        Arrays.fill(dp, 0);
        dp[0]=1;
        dp[2]=1;
        dp[4]=2;
        for(int i=6; i<=num_people; i=i+2) {
        	for(int j=1; i-2*j>=2*j-2; j++) {
        		if(i-2*j==2*(j-1)) { dp[i]=(dp[i]+dp[i-2*j]*dp[2*j-2]%MOD)%MOD; continue; }
        		dp[i]=(dp[i]+dp[i-2*j]*dp[2*j-2]*2%MOD)%MOD;
        	}
        }
        
        return (int)dp[num_people];
     }
    
    public static void main(String args[]) {
    	System.out.println(numberOfWays(44));
    }
}
