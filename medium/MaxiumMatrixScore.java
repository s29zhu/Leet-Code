package leetcode.medium;

/*
 * We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
 */
public class MaxiumMatrixScore {
	public int _matrixScore(int[][] A) {
		int R = A.length, C = A[0].length;
		int ans = 0;
		for (int c = 0; c < C; ++c) {
			int col = 0;
			for (int r = 0; r < R; ++r)
				col += A[r][c] ^ A[r][0]; // if A[r][0] is 0, it has to been turn to 1, use XOR
			ans += Math.max(col, R - col) * (1 << (C - 1 - c));
		}
		return ans;
	}

	public static int matrixScore(int[][] A) {
		int R = A.length, C = A[0].length, one = 0,value = 0;
		for (int j = 0; j < R; ++j) {		
			if(A[j][0]==0) { for(int i=0; i<C; i++) A[j][i]^=1; }	 
			value++;
		}
		for (int i = 1; i < C; i++) {
			one = 0;
			value *= 2;
			for (int j = 0; j < R; j++) one+=A[j][i];	
			if ((R-one) > one)  value += (R-one); else value += one;
		}
		return value;
	}

	public static void main(String args[]) {
		int[][] A = { { 0, 0, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 0 } };
		System.out.print(matrixScore(A));
	}
}
