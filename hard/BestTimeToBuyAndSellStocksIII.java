package leetcode.hard;
/*
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

/*1 2 3 4 5 1 5 

brutal force
l, 
j iterate from 0 to l-1
0,j ->m1, j+1,l-1 ->m2, max(m1+m2);

dynamic programming
p[l][2],

p[l][0] 1 buy 1 sell, p[l][1] 2 buys 2 sells

p[0]0]=0
p[0][1]=0
b1=0 is the position of first buy 
b2=-1 is the position of second buy
for(int j =1; j<l;j++){
	b1=(prices[j]<prices[b1])?j;b1;
	profit[j][0]=Math.max(profit[j-1][0],prices[j]-prices[b1]);
	
	if(b2!=-1) {
		profit[j][1]=Math.max(profit[prices[j]-price[b2]+profit[b2][0],profit[j-1][1]);
		profit[j][1]=Math.max(profit[j][0],profit[j][1]);
	}else {
		for(
	}
	 if(b2!=-1 && && prices[j]<=prices[b2]){
		profit[j][1]=Math.max(profit[j][0],profit[j-1][1]);
		b2=j;
	}
}
return p[l-1][1]
*/
public class BestTimeToBuyAndSellStocksIII {
    public static int maxProfit(int[] prices) {
        int res=0, l=prices.length, b1, s2;
        int [] p1=new int[l],p2=new int[l];
        if(l==0||l==1) return res;
        p1[0]=0;
        b1=prices[0];
        for(int j=1;j<l;j++) {
        	b1=(prices[j]<b1)?prices[j]:b1;
        	p1[j]=Math.max(prices[j]-b1, p1[j-1]);        	
        }
        
        p2[l-1]=0;
        s2=prices[l-1];
    	for(int i=l-2;i>=0;i--) {
        	s2=(prices[i]>s2)?prices[i]:s2;
    		p2[i]=Math.max(s2-prices[i], p2[i+1]);
    		res=Math.max(res, p2[i]+p1[i]);
    	}
    	
		/*
		 * for(int i=0; i<l;i++) { res=Math.max(res, p2[i]+p1[l-i-1]); }
		 */
        return res;
    }
    
    public static void main(String args[]) {
        int [] prices= {1,3,1,4,3,2,1,6};
        System.out.print(maxProfit(prices));
    }
}
