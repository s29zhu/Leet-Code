package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/*
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberOfIslandsII {
	/*
	 * Union Find
	 */
	public static class UnionFind{
	    int count; // number of islands
	    int[] parent;
	    int[] rank;
	    public UnionFind(char[][] grid) { // for problem 200
	        count = 0;
	        int m = grid.length;
	        int n = grid[0].length;
	        parent = new int[m * n];
	        rank = new int[m * n];
	        for (int i = 0; i < m; ++i) {
	          for (int j = 0; j < n; ++j) {
	            if (grid[i][j] == '1') {
	              parent[i * n + j] = i * n + j;
	              ++count;
	            }
	            rank[i * n + j] = 0;
	          }
	        }
	      }
	    
	    public UnionFind(int N) {// for problem 305
	      count = 0;
	      parent = new int[N];
	      rank = new int[N];
	      for (int i = 0; i < N; ++i) {
	        parent[i] = -1;
	        rank[i] = 0;
	      }
	    }

	    public boolean isValid(int i) { 
	      return parent[i] >= 0;
	    }

	    public void setParent(int i) {
	      parent[i] = i;
	      ++count;
	    }

	    public int find(int i) { // path compression
	      if (parent[i] != i) parent[i] = find(parent[i]);
	      return parent[i];
	    }

	    public void union(int x, int y) { // union with rank
	      int rootx = find(x);
	      int rooty = find(y);
	      if (rootx != rooty) {
	        if (rank[rootx] > rank[rooty]) {
	          parent[rooty] = rootx;
	        } else if (rank[rootx] < rank[rooty]) {
	          parent[rootx] = rooty;
	        } else {
	          parent[rooty] = rootx; rank[rootx] += 1;
	        }
	        --count;
	      }
	    }

	    public int getCount() {
	      return count;
	    }
	}
	
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);

        for (int[] pos : positions) {
          int r = pos[0], c = pos[1];
          List<Integer> overlap = new ArrayList<>();

          if (r - 1 >= 0 && uf.isValid((r-1) * n + c)) overlap.add((r-1) * n + c);
          if (r + 1 < m && uf.isValid((r+1) * n + c)) overlap.add((r+1) * n + c);
          if (c - 1 >= 0 && uf.isValid(r * n + c - 1)) overlap.add(r * n + c - 1);
          if (c + 1 < n && uf.isValid(r * n + c + 1)) overlap.add(r * n + c + 1);

          int index = r * n + c;
         if( !uf.isValid(index)) uf.setParent(index);
          for (int i : overlap) uf.union(i, index);
          ans.add(uf.getCount());
        }
        return ans;
    }
    
    public static void main(String args[]) {
    	int [][] positions= {{0,0},{0,1},{1,2},{1,2}};
    	numIslands2(3,3,positions);
    }
}