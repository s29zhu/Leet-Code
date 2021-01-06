package leetcode.hard;
/*
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {
	/*
	 * intuition: DP
	 * 1. candies[i] stores the # of candies child i has
	 * 2.0 initiate candies[0] with 1 
	 * 2.1 when rating[i] is larger than rating[i-1], candies[i]=candies[i-1]+1
	 * 2.2 when rating[i] is smaller than rating[i-1], increase rating[i-1] and subsequent rating[i-2], rating[i-3] etc...
	 * 2.3 when ratings[i] equals to rating[i-1], set it to be 1
	 */
    public static int candy(int[] ratings) {
        int sum=0, l=ratings.length;
        int [] candies=new int[ratings.length];
        candies[0]=1;
        for(int i=1; i<l; i++) {
        	if(ratings[i]>ratings[i-1]) candies[i]=candies[i-1]+1;
        	else if(ratings[i]<ratings[i-1]) {
        		candies[i]=1;
        		int k=i;
        		while(k>=1&&ratings[k-1]>ratings[k] && candies[k-1]<=candies[k]) {
        			candies[k-1]=candies[k]+1;
        			k--;
        		}
        	}else if(ratings[i]==ratings[i-1]) {
        		candies[i]=1;
        	}
        }
        for(int i=0; i<l; i++) {
        	sum+=candies[i];
        }
        return sum;
    }
    public static void main(String args[]) {
    	int [] ratings= {1,2,3,3,2,1};
    	System.out.print(candy(ratings));
    }
}
