package leetcode.hard;
/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
 */
public class DecodeWaysII {
    /*intuition
    * dynamic programming 
    
    */
    public static int numDecodings(String s) {
       int MOD=(int)Math.pow(10,9)+7;
        int l=s.length();
        long []dp=new long[l+1];
        int num=s.charAt(0)-'0';
        dp[0]=1l;
        if(num==0) dp[1]=0l;
        else if(num>0 && num<=9) dp[1]=1;
        else dp[1]=9l;
        
        for(int i=1; i<l; i++){
            num=s.charAt(i)-'0';
            if(num>=0 && num<=9) { //digits
                //decode the single digit as a letter
                if(num!=0) dp[i+1]=(dp[i+1]+dp[i])%MOD;
                // combine the digit with previous digit
                if(s.charAt(i-1)=='1'|| (s.charAt(i-1)=='2'&&num>=0 && num<=6) ) 
                    dp[i+1]=(dp[i+1]+dp[i-1])%MOD; 
                else if(s.charAt(i-1)=='*'){
                    dp[i+1]=(dp[i+1]+dp[i-1])%MOD;
                    if(num>=0&&num<=6) dp[i+1]=(dp[i+1]+dp[i-1])%MOD;
                }
            }else{ //*
                //decode the * as a single letter
                dp[i+1]=(dp[i+1]+(9*dp[i])%MOD)%MOD;
                //combine the digit with previous digit
                if(s.charAt(i-1)=='1') 
                    dp[i+1]=(dp[i+1]+(dp[i-1]*9)%MOD)%MOD;
                else if(s.charAt(i-1)=='2')
                    dp[i+1]=(dp[i+1]+(dp[i-1]*6)%MOD)%MOD;
                if(s.charAt(i-1)=='*'){
                    dp[i+1]=(dp[i+1]+((dp[i-1]*9)%MOD + (dp[i-1]*6)%MOD)%MOD)%MOD;
                }
                
            }
            
        }
        return (int)dp[l];
    }
    
    public static void main(String args[]) {
    	System.out.print(numDecodings("********************"));
    }
}
