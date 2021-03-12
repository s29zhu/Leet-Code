package leetcode.medium;

import java.util.List;

/*
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 
    public int maxLength(List<String> arr) {
        
    }
Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
 */
public class MaxLengthOfUniqueCharacterStr {
	
    public int maxLength(List<String> arr) {
        int [] masks = new int [arr.size()];
        int max = maxRecur(arr, arr.size()-1, 0, masks);
        return max;
    }
    
    private int maxRecur(List<String> arr, int n, int set,int [] masks){
        if(n < 0) return 0;
        
        int currmask  = masks[n];
        if(currmask == 0){
            masks[n] = getStringMask(arr.get(n));
            currmask  = masks[n];
            if(currmask == -1){
                return maxRecur(arr, n-1, set, masks);
            }
        }
        
        int exclusive = maxRecur(arr, n-1, set, masks);
        int inclusive = 0;
        if((set & currmask) == 0){
            inclusive =  arr.get(n).length() + maxRecur(arr, n-1, set | currmask, masks);
        }
        return Math.max(exclusive, inclusive);
    }
    
    private int getStringMask(String s){
        int m = 0;
        for(char c : s.toCharArray()){
            int mask = 1 << (c - 'a');
            if ((m & mask) > 0){
                return -1;
            }
            m = m | mask;
        }
        return m;
    }
}
