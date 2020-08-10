package leetcode.medium;

/*
 * https://leetcode.com/problems/count-number-of-teams/
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

 

Example 1:

Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 
Example 2:

Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.
Example 3:

Input: rating = [1,2,3,4]
Output: 4
 

Constraints:

n == rating.length
1 <= n <= 200
1 <= rating[i] <= 10^5
 */
public class CountNumberOfTeams {
	/*
	 * intuition:
	 *  get the rating smaller than rating[i] on the left side LEFT, rating larger than rating[i] on the right side RIGHT
	 *  the team number will be LEFT*RIGHT
	 */
    public static int numTeams(int[] rating) {
    	int l=rating.length, res=0;
    	int left1=0, left2=0, right1=0, right2=0;
    	//increase order
    	for(int i=0; i<l; i++) {
    		left1=0;
    		left2=0;
    		right1=0;
    		right2=0;
    		for(int j=i-1; j>=0; j--) {
    			if(rating[j] < rating[i]) left1++;
    			if(rating[j] > rating[i] ) left2++;
    		}
    		for(int j=i+1; j<l; j++) {
    			if(rating[j] > rating[i]) right1++;
    			if(rating[j] < rating[i]) right2++;
    		}
    		res+=left1*right1;
    		res+=left2*right2;
    	}
    	//decrease order
        return res;
    }
    
    public static void main(String args[]) {
    	int[] ratings={2,5,3,4,1};
    	System.out.println(numTeams(ratings));
    }
}
