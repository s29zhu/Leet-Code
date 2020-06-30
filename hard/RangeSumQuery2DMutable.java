package leetcode.hard;
/*
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
class RangeSumQuery2DMutable {
    private int[][] presum;
    private int[][] matrix; // Important
    
    public RangeSumQuery2DMutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;  // Important
        
        this.matrix = matrix;
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        presum = new int[rows][cols + 1];
        
        for (int i = 0; i < rows; i++) 
            for (int j = 1; j <= cols; j++)
                presum[i][j] = presum[i][j - 1] + matrix[i][j - 1];
    }
    
    public void update(int row, int col, int val) {
        
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        
        col++; // Important
        for (int c = col; c < presum[row].length; c++)
            presum[row][c] += diff;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) 
            sum += presum[r][col2 + 1] - presum[r][col1]; 
        
        return sum;
    }
}
