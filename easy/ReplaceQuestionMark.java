package leetcode.easy;
/*
 * Given a string s containing only lower case English letters and the '?' character, convert all the '?' characters 
 * into lower case letters such that the final string does not contain any * consecutive repeating characters. 
 * You cannot modify the non '?' characters.

It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.

Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution, 
return any of them. It can be shown that an answer is always possible with the given constraints.
 */
/*
 * Thoughts
 * iterate string s, 
 * - pre to save the previous character
 * - next to save the next character
 * - cur to save the current character, if cur is ?, set ? to be pre+1%z+a, if (pre+1)%z+a==next, set ? to be (next+1)%z+a,
 */
public class ReplaceQuestionMark {
    public static String modifyString(String s) {
    	int length=s.length();
    	String res="";
    	char pre='a', cur=' ', next=' ';
    	if(length==0) return res;
    	for(int i=0; i<length; i++) {
    		cur=s.charAt(i);
    		if(cur=='?') {
    			if(pre=='z') cur='a'; else cur=(char) (pre+1);
    			if(i+1<length) {
    				next=s.charAt(i+1);
    				if(cur==next && next=='z') cur='a';
    				else if(cur==next) cur=(char)(next+1);
    			}
    		}
    		pre=cur;
    		res+=pre;
    	}
    	return res;
    }
    public static void main(String args[]) {
    	String s="?a??";
    	System.out.println(modifyString(s));
    }
}
