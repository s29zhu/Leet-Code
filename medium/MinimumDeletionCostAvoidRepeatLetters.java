package leetcode.medium;
/*
 * Given a string s and an array of integers cost where cost[i] is the cost of deleting the ith character in s.
 * Return the minimum cost of deletions such that there are no two identical letters next to each other.
 * Notice that you will delete the chosen characters at the same time, in other words, after deleting a character, 
 * the costs of deleting other characters will not change.
 */
/*
 * thoughts, walk through the string, 
 * pre to store the previous character, 
 * c to store the current character,
 * sum to store the sum cost of the repetition characters
 * max the store the sum cost the 
 * if the current character is different than the previous character, sum=cost[i], set max to be cost[i], result=result+(sum-max)
 * if the current character is same as the previous character, sum=sum+cost[i], set max to max(max, cost[i])
 * 
 */
public class MinimumDeletionCostAvoidRepeatLetters {
    public static int minCost(String s, int[] cost) {
        int result=0;
        int length= s.length(), max=0, sum=0;
        char pre=' ', cur=' ';
        if(length==0) return 0;
        
        pre=s.charAt(0);
        sum=cost[0];
        max=cost[0];
        for(int i=1; i<length; i++) {
        	cur=s.charAt(i);
        	if(cur!=pre) {
        		result=result+(sum-max);
        		sum=cost[i];
        		max=cost[i];
        		pre=cur;
        	}else {
        		sum=sum+cost[i];
        		max=Math.max(max, cost[i]);
        		pre=cur;
        	}
        }
        result=result+(sum-max);
        return result;
    }
    
    public static void main(String args[]) {
    	String s="aabaa";
    	int [] cost= {1,2,3,4,1};
    	System.out.print(minCost(s, cost));	
    }
}
