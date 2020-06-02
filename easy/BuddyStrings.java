package leetcode.easy;
import java.lang.String;
import java.util.ArrayList;

/*
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 */
public class BuddyStrings {
    public static boolean buddyStrings(String A, String B) {
        int length=A.length(),checker=0;
        boolean redundant=false;
        ArrayList<Integer> set=new ArrayList<Integer>();
        if((A.length()!=B.length())||(A.length()<2)||(B.length()<2)) return false;
        for(int i=0; i<length;i++) {
        	if(A.charAt(i)!=B.charAt(i)) {
        		set.add(i);
        	}
        	int bit=checker&(1<<(A.charAt(i)-'a'));
        	if((bit^(1<<(A.charAt(i)-'a')))==0) redundant=true;
        	checker=checker^(1<<(A.charAt(i)-'a'));
        }
        if(set.size()==0 && redundant) return true;
        else if(set.size()==2 && A.charAt(set.get(0))==B.charAt(set.get(1)) &&
        		A.charAt(set.get(1))==B.charAt(set.get(0))) return true;
        else return false;
    }
    public static void main(String[] args) {
    	boolean res=buddyStrings("aa","aa");
    	System.out.print(res);
    }
}