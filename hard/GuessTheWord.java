package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;

/*
 * This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 */
class Master{
	String secret;
	Master(String s){
		this.secret=s;
	}
	int guess(String a) {
		return matchNum(a, this.secret);
	}
	int matchNum(String a, String b) {
    	int res=0;
    	for(int i=0;i<a.length();i++) {
    		if(a.charAt(i)==b.charAt(i)) res++;
    	}
    	return res;
    }
}

public class GuessTheWord {
    public static void findSecretWord(String[] wordlist, Master master) {
        HashMap<String, Integer> tested=new HashMap<String, Integer>();
        HashSet<String> p1=new HashSet<String>(), remove=new HashSet<String>();
        boolean f=false;
        int m=-1, round=1;
        for(int i=0;i<wordlist.length;i++) {
        	p1.add(wordlist[i]);
        }

        do{
            String gs=p1.iterator().next();
            m=master.guess(gs);
            if(m==6) return;
        	tested.put(gs, m);  
        	System.out.println("Guess:"+gs+": "+m);
        	for(String s: p1) {
        		System.out.print(s+" ");
        	}
        	System.out.println("");
        	for(String s: p1) {
        		f=true;
        		for(String t: tested.keySet()) {
        			if(matchNum(s,t)!=tested.get(t)) f=false;
        		}
        		if(!f) remove.add(s);
        	}
        	p1.removeAll(remove);
        	remove.clear();
        	round++;
        	System.out.println("Round:"+round);
        	for(String s: p1) {
        		System.out.print(s+" ");
        	}
        	System.out.println("");
        }while (m!=6);
        
        return;
    }
    
    public static int matchNum(String a, String b) {
    	int res=0;
    	for(int i=0;i<a.length();i++) {
    		if(a.charAt(i)==b.charAt(i)) res++;
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	String [] wordlist= {"acckzz","ccbazz","eiowzz","abcczz","aaaaaa","accbhu","acdkuz","accnzz"};
    	Master m=new Master("eiowzz");
    	findSecretWord(wordlist,m);    	
    }
}
