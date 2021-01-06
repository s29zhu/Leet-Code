package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/*
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.

 

Example 1:

Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: "AAABBC"
Output: 188
 

Note:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
 */
public class LetterTilePossibilities {
    public static int numTilePossibilities(String tiles) {
        Set<String> set=new HashSet<String>();
        for(int i=0; i<tiles.length(); i++){
            Set<String> temp=new HashSet<String>();
            temp.addAll(set);
            String str=tiles.substring(i,i+1), n_str="", left_str="", right_str="";
            if(!set.contains(str)) set.add(str);
            for(String s: temp){
                for(int k=0; k<=s.length(); k++){
                    left_str=s.substring(0,k);
                    if(k==s.length()) right_str="";
                    else right_str=s.substring(k);
                    n_str=left_str+str+right_str;
                    if(!set.contains(n_str)) set.add(n_str);
                }
            }
        }
        return set.size();
    }
    public static void main(String args[]) {
    	String tiles="AAB";
    	System.out.println(numTilePossibilities(tiles));
    }
}
