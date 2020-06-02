/*
 * Given a 2D integer matrix M representing the gray scale of an image, 
 * you need to design a smoother to make the gray scale of each cell becomes
 * the average gray scale (rounding down) of all the 8 surrounding cells and itself. 
 * If a cell has less than 8 surrounding cells, then use as many as you can.
 */
package leetcode.easy;

public class ImageSmoother {
	 public static int[][] imageSmoother(int[][] M) {
		 int row=M.length, column=M[0].length;
		 int [][] new_m= new int [row][column];
		 for (int r=row-1; r>=0; r--) {
			 for(int c=column-1; c>=0; c--) {
				 int num=0;
				 int add=0;
				 for(int i=r-1; i<=r+1;i++)
					 for(int j=c-1; j<=c+1; j++) {
						 if(i>=0 && i<row && j>=0 && j<column) {
							 add+=M[i][j];
							 num++;
						 }						 
					 }
				 new_m[r][c]=add/num;
						 
			 }
		 }
		 return new_m;
	 }
	 
	 public static void main(String [] args) {
		 int[][] M= {{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}};
		 int [][] mat=imageSmoother(M);
		 int row=mat.length, column=mat[0].length;
		 int [][] new_m= new int [row][column];
		 for (int r=row-1; r>=0; r--) {
			 for(int c=column-1; c>=0; c--) {
				 System.out.print(new_m[r][c]);
			 }
			 System.out.print("\n");
		 }
	 }
}