package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 

Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */
public class WordSearch {
	public static boolean helper(char [][] board, String word, int[] index, Set<String> seen) {
		if(word.isEmpty()) return true;
		char sec=word.charAt(0);
		int [] new_index=new int[2];
		int R=board.length, C=board[0].length;
		String str="";
		String w=word.substring(1);
		Set<String> s=new HashSet<String>();
		s.addAll(seen);
		
		//left index[1]-1
		new_index[0]=index[0];
		new_index[1]=index[1]-1;
		str=String.valueOf(new_index[0])+"-"+String.valueOf(new_index[1]);
		if(new_index[1]>=0 && new_index[1]<C && board[index[0]][index[1]-1]==sec && !seen.contains(str)) {
			s.add(str);
			if(helper(board, w, new_index, s)) return true;
			s.remove(str);
		}
		//up index[0]-1
		new_index[0]=index[0]-1;
		new_index[1]=index[1];
		str=String.valueOf(new_index[0])+"-"+String.valueOf(new_index[1]);
		if(new_index[0]>=0 && new_index[0]<R&& board[new_index[0]][new_index[1]]==sec && !seen.contains(str)) {
			s.add(str);
			if(helper(board, w, new_index, s)) return true;
			s.remove(str);
		}
		//right index[1]+1
		new_index[0]=index[0];
		new_index[1]=index[1]+1;
		str=String.valueOf(new_index[0])+"-"+String.valueOf(new_index[1]);
		if(index[1]+1>=0&&index[1]+1<C&&board[index[0]][index[1]+1]==sec && !seen.contains(str)) {
			s.add(str);
			if(helper(board, w, new_index, s)) return true;
			s.remove(str);
		}
		//down index[0]+1
		new_index[0]=index[0]+1;
		new_index[1]=index[1];
		str=String.valueOf(new_index[0])+"-"+String.valueOf(new_index[1]);
		if(index[0]+1>=0&&index[0]+1<R&&board[index[0]+1][index[1]]==sec && !seen.contains(str)) {
			s.add(str);
			if(helper(board, w, new_index, s)) return true;
			s.remove(str);
		}
		return false;
	}
    public static boolean exist(char[][] board, String word) {
    	ArrayList<int[]> indexes=new ArrayList<int[]>();
    	int R=board.length, C=board[0].length;
    	
    	if(word.isEmpty()) return true;
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			if(board[i][j]==word.charAt(0)) {
    				int [] index={i, j};
    				indexes.add(index);
    			}
    		}
    	}
    	for(int [] index: indexes) {
    		Set<String> set=new HashSet<String> ();
    		String str=String.valueOf(index[0])+"-"+String.valueOf(index[1]);
    		set.add(str);
    		boolean res=helper(board, word.substring(1),index, set);
    		if(res) return res;
    	}
        return false;
    }
    
    public static void main(String args[]) {
    	char [][]board= {
    			{'A','B','C','E'},
				{'S','F','E','S'},
				{'A','D','E','E'}
				};
    	String word="ABCESEEEFS";
    	boolean res=exist(board, word);
    	System.out.print(res);
    }
    
}
