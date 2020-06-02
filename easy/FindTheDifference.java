package leetcode.easy;

/*Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.
*/
public class FindTheDifference {

	public static char findTheDifference(String s, String t) {
		char dif=' ';
		int length=t.length();
		for(int i=0; i< length; i++) {
			if(s.contains(t.substring(i, i+1))){
				s=s.replaceFirst(t.substring(i,i+1), "");
			}else {
				dif=t.charAt(i);
			}					
		}
		return dif;
	}
	public static void main(String[] args) {
		String s="a", t="aa";
		System.out.println(findTheDifference(s,t));		
	}
}
