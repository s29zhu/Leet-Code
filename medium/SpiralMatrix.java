package leetcode.medium;

import java.util.List;
import java.util.ArrayList;
/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
/*
 say the matrix size is M X N
 number of rounds: (M+1)/2
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list=new ArrayList<Integer>();
        int M=matrix.length, N=0;
        if(M==0) return list;
        if(M!=0) N=matrix[0].length;        
        for(int i=0;i<(M+1)/2 && i<(N+1)/2;i++) {
        	int j=i;
        	//up horizontal
        	for(j=i;j<N-i-1;j++) list.add(matrix[i][j]);
        	//when there is only one horizontal line, the last element of that line is not added to the list
        	if(i==M-i-1) {list.add(matrix[i][j]); break;}
        	//right vertical
        	for(j=i;j<M-i-1;j++) list.add(matrix[j][N-i-1]);
        	//when there is only one vertical line, the last element of the vertical is not added to the list
        	if(i==N-i-1) {list.add(matrix[j][i]); break;}
        	//bottom horizontal
        	for(j=N-i-1;j>i;j--) list.add(matrix[M-i-1][j]);
        	//left vertical
        	for(j=M-i-1;j>i;j--) list.add(matrix[j][i]);        	
        }
        return list;
    }
    
    public static void main(String args[]) {
    	int [][]matrix={{1,2},
    					{4,5},
    					{7,8},
    					{10,11}};
    	List<Integer> list=spiralOrder(matrix);
    	for(Integer i: list) {
    		System.out.print(i+" ");
    	}
    }
}