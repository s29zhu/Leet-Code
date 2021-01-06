package leetcode.hard;
/*
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 * The 'closest' is defined as absolute difference minimized between two integers.
 * Example 1:
 * Input: "123"
 * Output: "121"
 * Note:
 * The input n is a positive integer represented by string, whose length will not exceed 18.
 * If there is a tie, return the smaller one as answer.
 */
/*
 * To understand this method, let's start with a simple illustration. Assume that the number given to us is "abcxy". One way to convert this number into a palindrome is to replicate one half of the string to the other half. If we try replicating the second half to the first half, the new palindrome obtained will be "yxcxy" which lies at an absolute of \left|10000(a-y) + 1000(b-x)\right|∣10000(a−y)+1000(b−x)∣ from the original number. But, if we replicate the first half to the second half of the string, we obtain "abcba", which lies at an absolute difference of \left|10(x-b) + (y-a)\right|∣10(x−b)+(y−a)∣. Trying to change cc additionaly in either case would incur an additional value of atleast 100 in the absolute difference.

From the above illustration, we can conclude that if replication is used to generate the palindromic number, we should always replicate the first half to the second half. In this implementation, we've stored such a number in aa at a difference of diff1diff1 from nn.

But, there exists another case as well, where the digit at the middle index is incremented or decremented. In such cases, it could be profitable to make changes to the central digit only since such changes could lead to a palindrome formation nearer to the original digit. e.g. 10987. Using the above criteria, the palindrome obtained will be 10901 which is at a more difference from 10987 than 11011. A similar situation occurs if a 0 occurs at the middle digit. But, again as discussed previously, we need to consider only the first half digits to obtain the new palindrome. This special effect occurs with 0 or 9 at the middle digit since, only decrementing 0 and incrementing 9 at that digit place can lead to the change in the rest of the digits towards their left. In any other case, the situation boils down to the one discussed in the first paragraph.

Now, whenever we find a 0 near the middle index, in order to consider the palindromes which are lesser than nn, we subtract a 1 from the first half of the number to obtain a new palindromic half e.g. If the given number nn is 20001, we subtract a 1 from 200 creating a number of the form 199xx. To obtain the new palindrome, we replicate the first half to obtain 19991. Taking another example of 10000, (with a 1 at the MSB), we subtract a 1 from 100 creating 099xx as the new number transforming to a 9999 as the new palindrome. This number is stored in bb having a difference of diff2diff2 from nn

Similar treatment needs to be done with a 9 at the middle digit, except that this time we need to consider the numbers larger than the current number. For this, we add a 1 to the first half. e.g. Taking the number 10987, we add a 1 to 109 creating a number of the form 110xx(11011 is the new palindrome). This palindrome is stored in cc having a difference of diff3diff3 from nn.

Out of these three palindromes, we can choose the one with a minimum difference from nn. Further, in case of a tie, we need to return the smallest palindrome obtained. For resolving this tie's conflict, we can observe that a tie is possible only if one number is larger than nn and another is lesser than nn. Further, we know that the number bb is obtained by decreasing nn. Thus, in case of conflict between bb and any other number, we need to choose bb. Similarly, cc is obtained by increasing nn. Thus, in case of a tie between cc and any other number, we need to choose the number other than cc.
 */
// test: n is palindrome 0, 11, 101,99,999,191,199999999999999991
// test: 10001
// test:n is not palindrome 100, 1000, 1010, 123, 1234, 1887, 3001
public class FindTheClosestPalindrome {
    public static String nearestPalindromic(String n) {
    	int l=n.length();
    	Long diff1=0L, diff2=0L,diff3=0L;
    	String res1="", res2="", res3="";
    	int first_half=0;
    	/*
    	 * 1. always mirror the first half onto the second half
    	 * 2. The closest palindrome can be by increase the first half by 1
           3. The closest palindrome can be by decrease the first half by 1
    	 * 4. when there are two palindromes of the same distance, take the smaller palindrome
    	 * time complexity: O(l)
    	 * space complexity: N(l)
    	 */    	
    	
    	if(l==0) return "";
    	//possible result 1, when just mirroring itself
    	res1=mirroring(n);
    	diff1=Math.abs(Long.valueOf(res1)-Long.valueOf(n));
    	diff1=(diff1==0)? Long.MAX_VALUE:diff1;
    	// possible solution 2, decrease the first half by 1, highest possibility
   		first_half=Integer.valueOf(n.substring(0,(l+1)/2));
   		int temp=first_half-1;
   		if((String.valueOf(temp).length()<String.valueOf(first_half).length()||temp==0)&&!n.equals("1")){
   			res2=(temp!=0)?String.valueOf(temp):"";
   			res2+='9';
   			res2+=((l+1)/2+1<l)?n.substring((l+1)/2+1):"";
   		}
   		else{
	   		res2=String.valueOf(temp)+n.substring((l+1)/2);
   		}
	   	res2=mirroring(res2);
	   	diff2=Math.abs(Long.valueOf(n)-Long.valueOf(res2));  
	   	//possible solution 3, increase the first half by 1, this one is lowest possibility, 
		first_half=Integer.valueOf(n.substring(0,(l+1)/2));
    	first_half++;
    	res3=String.valueOf(first_half)+n.substring((l+1)/2);
    	res3=mirroring(res3);
   		diff3=Math.abs(Long.valueOf(n)-Long.valueOf(res3));
    	if(diff2<=diff1 && diff2<=diff3)
    		return res2;
    	else if(diff1<=diff2 && diff1<=diff3)
    		return res1;
    	else return res3;
    }
    
    public static String mirroring(String s) {
	    String x = s.substring(0, (s.length()) / 2);
	    return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }
    
    public static void main(String args[]) {
    	//String n="807045053224792883";
    	String n="100";
    	System.out.println(nearestPalindromic(n));
    	//System.out.println((char) ('2'-1));
    	//System.out.println(String.valueOf(Long.MAX_VALUE).length());
    }
}
