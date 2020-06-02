package leetcode.medium;
import java.util.ArrayList;

/*
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 */
public class ValidSoduku {
    public static boolean isValidSudoku(char[][] board) {
        boolean res=true;
        ArrayList<Character> row=new ArrayList<Character>(),
        		column=new ArrayList<Character>(),
        		grid=new ArrayList<Character>();
        for(int i=0;i<9;i++) { 
        	for(int j=0; j<9; j++) {
        		if(row.contains(board[i][j]) && board[i][j]!='.') return false;
        		else if(board[i][j]!='.') row.add(board[i][j]);
        		if(column.contains(board[j][i]) && board[j][i]!='.') return false;
        		else if(board[j][i]!='.') column.add(board[j][i]);
        	}
        	row.clear();
        	column.clear();
        }
        for(int i=0;i<9;i+=3) {
        	 for(int j=0;j<9;j+=3) {
        		 for(int a=i; a<i+3; a++) {
        			 for(int b=j;b<j+3; b++) {
                		 if(grid.contains(board[a][b]) && board[a][b]!='.') return false;
                 		 else if(board[a][b]!='.') grid.add(board[a][b]);
        			 }
        		 }
        		 grid.clear();
        	 }
        	 
        }

        return res;
    }
    public static void main(String args[]) {
    	char [][] board={
    			{'.','2','.','.','.','5','.','.','.'},
    			{'.','.','.','.','.','4','.','.','.'},
    			{'.','.','.','9','.','.','7','.','.'},
    			{'.','.','8','.','.','.','.','.','.'},
    			{'.','.','.','.','.','.','.','.','.'},
    			{'.','.','.','.','7','4','3','.','9'},
    			{'.','.','.','.','.','.','9','.','.'},
    			{'.','7','.','.','6','.','.','.','5'},
    			{'3','.','.','7','.','.','.','.','.'}};

    	System.out.print(isValidSudoku(board));
    }
}
