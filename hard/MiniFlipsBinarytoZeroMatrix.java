package leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.

Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.

Binary matrix is a matrix with all cells equal to 0 or 1 only.

Zero matrix is a matrix with all cells equal to 0.

 

Example 1:


Input: mat = [
		[0,0],
		[0,1]
	]
Output: 3
Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
Example 2:

Input: mat = [[0]]
Output: 0
Explanation: Given matrix is a zero matrix. We don't need to change it.
Example 3:

Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
Output: 6
Example 4:

Input: mat = [[1,0,0],[1,0,0]]
Output: -1
Explanation: Given matrix can't be a zero matrix
 

Constrain
m == mat.length
n == mat[0].length
1 <= m <= 3
1 <= n <= 3
mat[i][j] is 0 or 1
 */
public class MiniFlipsBinarytoZeroMatrix {
	/*
	 * intuition: BFS
	 * use one queue to store all the matrix, another queue to store the number of flips
	 * for each of the position in the matrix, flip it, count increase 1, until we find all 0 matrix
	 */	
	public static void flip(int [][]m, int i, int j) {
		int R=m.length, C=m[0].length;
		m[i][j]=(m[i][j]==1)?0:1;
		//left
		if(j-1>=0 && j-1<C) m[i][j-1]=(m[i][j-1]==1)?0:1;
		//up
		if(i-1>=0 && i-1<R) m[i-1][j]=(m[i-1][j]==1)?0:1;
		//right
		if(j+1>=0&&j+1<C) m[i][j+1]=(m[i][j+1]==1)?0:1;
		//down
		if(i+1>=0&&i+1<R) m[i+1][j]=(m[i+1][j]==1)?0:1;
	}
	
	public static String convertToString(int [][]m) {
		String res="";
		int R=m.length, C=m[0].length;	
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				res+=String.valueOf(m[i][j]);
			}
		}
		return res;
	}
    public static int minFlips(int[][] mat) {
		int R=mat.length, C=mat[0].length;
		int [][]All0=new int[R][C];
		Queue<int[][]> que=new LinkedList<int[][]>();
		Queue<Integer> count=new LinkedList<Integer>();
		Set<String> seen=new HashSet<String>();
		seen.add(convertToString(All0));
		que.add(All0);
		count.add(0);
		if(convertToString(All0).equals(convertToString(mat))) return 0;

		while(!que.isEmpty()) {
			int [][]cur=que.poll();
			int res=count.poll();
			res++;
			for(int i=0;i<R; i++) {
				for(int j=0; j<C; j++) {
					int [][]next=new int[R][C];
					for(int k=0; k<R; k++) next[k]=Arrays.copyOf(cur[k], C);
					flip(next,i,j);
					String str=convertToString(next);
					if(str.equals(convertToString(mat))) return res;
					if(!seen.contains(str)) {
						seen.add(str);
						que.add(next);
						count.add(res);
					}
				}
			}
		}
        return -1;
    }
    
    public static void main(String args[]) {
    	int [][]mat={{1,0,0},{1,0,0}};
    	System.out.print(minFlips(mat));
    }
}
