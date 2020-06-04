package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LettercombinationOfPhoneNumber {
    public static List<String> letterCombinations(String digits) {
    	Character [][] n2l= {{' ',' ',' ',' '},
    						 {' ',' ',' ',' '},
    						 {'a','b','c',' '},
    						 {'d','e','f',' '},
    						 {'g','h','i',' '},
    						 {'j','k','l',' '},
    						 {'m','n','o',' '},
    						 {'p','q','r','s'},
    						 {'t','u','v',' '},
    						 {'w','x','y','z'}};
    	List<String> list=new ArrayList<String>(), l1=new ArrayList<String>();
    	if(digits.isEmpty()) return list;
    	String sub=digits.substring(0,1);
    	
    	for(int i=0;i<4;i++) {
    		char c=n2l[Integer.valueOf(sub)][i];
    		if( c!=' ') l1.add(String.valueOf(c));
    	}
    	
    	List<String> l2=letterCombinations(digits.substring(1));
    	for(String s1: l1) {
    		String s=s1;
    		if(l2.isEmpty()) {list.add(s);}
    		for(String s2: l2) {
    			s=s1+s2;
    			list.add(s);
    		}
    	}
    	return list;
    }
    
    public static void main(String args[]) {
    	String digits="2";
    	List<String> list=letterCombinations(digits);
    	for(String l:list) {
    		System.out.print(l+" ");
    	}
    }
}
