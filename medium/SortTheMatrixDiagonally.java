package leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
 */
public class SortTheMatrixDiagonally {
	/*
	 * intuition
	 * Traverse the first column and first row, use the priority queue to store the diagonal values and store them back
	 */
    public static int[][] diagonalSort(int[][] mat) {
        int m=mat.length, n=mat[0].length;
        Queue<Integer> q=new PriorityQueue<Integer>();
        int j=0, i=0;
        for(i=m-1; i>=0; i--) {
        	j=0;
        	int _i=i;
        	while(_i<m && j<n) {
        		q.add(mat[_i][j]);
        		_i++;
        		j++;
        	}
        	j=0;
        	_i=i;
        	while(_i<m && j<n) {
        		mat[_i][j]=q.poll();
        		_i++;
        		j++;
        	}
        }
        q.clear();
        for(j=1; j<n; j++) {
        	i=0;
        	int _j=j;
        	while(i<m && _j<n) {
        		q.add(mat[i][_j]);
        		i++;
        		_j++;
        	}
        	i=0;
        	_j=j;
        	while(i<m && _j<n) {
        		mat[i][_j]=q.poll();
        		i++;
        		_j++;
        	}
        }
        return mat;
    }
    public static void main(String args[]) {
    	
    }
}
