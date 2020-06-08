package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
/*
 * Given a 2d grid map of '1's (land) and '0's (water), 
 * count the number of islands. An island is surrounded by water and is formed by connecting adjacent
 * lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
import java.util.LinkedList;
import java.util.Queue;

/*0. Iterate from the (0,0) to (M,N), increase res, once encountered a new land
 *1. Traverse the grid, mark the visited '1' the as '2',put the encountered land to Queue,
 *2. poll the land from queue and extend to 4 directions, and put the newly encountered land to Queue, 
 *mark it as '2'
 *3. repeat 1,2 until queue is empty
 */
public class NumberofIsland {

	// exceed time limit
	public static int numIslands(char[][] grid) {
		int res = 0, m = grid.length;
		if(m==0) return res;
		int n = grid[0].length;
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					res++;
					grid[i][j] = '2';
					int[] index = { i, j };
					q.add(index);
					while (!q.isEmpty()) {
						index = q.poll();
						extend(q, index[0], index[1], m, n, grid);
					}
				}
			}
		}
		return res;
	}

	public static void extend(Queue<int[]> q, int i, int j, int M, int N, char[][] grid) {
		// left
		if (j - 1 >= 0 && grid[i][j - 1] == '1') {q.add(new int[] { i, j - 1 }); grid[i][j-1]='2';}
		// up
		if (i - 1 >= 0 && grid[i - 1][j] == '1') {q.add(new int[] { i - 1, j }); grid[i-1][j]='2';}
		// right
		if (j + 1 < N && grid[i][j + 1] == '1') {q.add(new int[] { i, j + 1 }); grid[i][j+1]='2';}
		// down
		if (i + 1 < M && grid[i + 1][j] == '1') {q.add(new int[] { i + 1, j }); grid[i+1][j]='2';}
	}

	public static void main(String args[]) {
		char [][] grid= {{'1','1','1','1','0'}, 
						 {'1','1','0','1','0'},
						 {'1','1','0','0','0'}, 
						 {'0','0','0','0','0'}};
		System.out.println(numIslands(grid));
	}
}