package leetcode.easy;
/*
 * We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example :

Input: n = 10, pick = 6
Output: 6
 */
public class GuessNumberHigherOrLower {
    public static int guessNumber(int n) {
    	int low=1, high=n;
    	while(low<=high) {
    		// pay attention here, use (low+high)/2 will cause timelimit error, 
    		//especially when n is very large
            int mid=low+(high-low)/2; 
            int res=guess(mid,6);
    		if(res==0) return mid;
    		else if(res==-1) high=mid-1;
    		else low=mid+1;
    	}
    	return -1;
    }
    public static int guess(int num, int target) {
    	int res=0;
    	if(num<target) res=-1;
    	if(num>target) res=1;
    	if(num==target) res=0;
    	return res;
    }
    
    public static void main(String args[]) {
    	System.out.print(guessNumber(10));
    }
}
