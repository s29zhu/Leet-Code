package leetcode.medium;
/*
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares (" ").
The first player always places "X" characters, while the second player always places "O" characters.
"X" and "O" characters are always placed into empty squares, never filled ones.
The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Example 1:
Input: board = ["O  ", "   ", "   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX", " X ", "   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XXX", "   ", "OOO"]
Output: false

Example 4:
Input: board = ["XOX", "O O", "XOX"]
Output: true
Note:

board is a length-3 array of strings, where each string board[i] has length 3.
Each board[i][j] is a character in the set {" ", "X", "O"}.
 */
public class ValidTicTacToeState {
    public static boolean validTicTacToe(String[] board) {
    	/*
    	 * intuition: 4 scenarioes when the state is invalid
    	 * 1. X_num-O_num=0 or 1
    	 * 2. cannot be x_win and o_win at the same time
    	 * 3. cannot be x_win and x_num==o_num
    	 * 4. cannot be o_win and x_num=o_num+1
    	 */
    	int x_num=0, o_num=0;
    	String str0="", str1="", str2="", str3="", str4="";
    	boolean x_win=false, o_win=false;
    	for(int i=0; i<3; i++) {
    		for(int j=0; j<3; j++) {
    			if(board[i].charAt(j)=='X') x_num++;
    			if(board[i].charAt(j)=='O') o_num++;
    			if(j==0) str0+=board[i].substring(0,1);
    			if(j==1) str1+=board[i].substring(1,2);
    			if(j==2) str2+=board[i].substring(2,3);
                if(i==j) str3+=board[i].substring(j,j+1);
                if(i+j==2) str4+=board[i].substring(j, j+1);
    			if(board[i].equals("XXX")) x_win=true;
    			if(board[i].equals("OOO")) o_win=true;
                
    		}
    	}
    	if(str0.equals("XXX")||str1.equals("XXX")||str2.equals("XXX") || str3.equals("XXX") || str4.equals("XXX")) x_win=true;
    	if(str0.equals("OOO")||str1.equals("OOO")||str2.equals("OOO") || str3.equals("OOO") || str4.equals("OOO")) o_win=true;
    	
    	if(x_num-o_num<0 
           || x_num-o_num>1 
           || (x_win&&o_win) 
           || (x_win&&(x_num-o_num==0))
           || (o_win&&(x_num-o_num==1))) return false;
        
    	return true;
    }
    public static void main(String args[]) {
    	/*
    	 * ["OXX",
    	 *  "XOX",
    	 *  "OXO"]
    	 */
    }
}
