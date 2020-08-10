package leetcode.medium;
/*
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
public class SpiralMatrixII {
	
    public static int[][] generateMatrix(int n) {
        int layer=(n+1)/2;
        int num=1;
        int [][]res=new int[n][n];
        boolean flag=false;
        for(int l=0; l<layer; l++){
            int i=l, j=l;
            //increase j to n-l-1
            for(j=l; j<n-l; j++) {
                res[i][j]=num;
                if(num==n*n) { flag=true; break;}
                num++;
            }
            if(flag) break;
            j--;
            //increase i to n-l-1
            for(i=l+1; i<n-l; i++) {
                res[i][j]=num;
                if(num==n*n) { flag=true; break;}
                num++;
            }
            if(flag) break;
            i--;
            //decrease j to l
            for(j=j-1; j>=l; j--) {
                res[i][j]=num;
                if(num==n*n) { flag=true; break;}
                num++;
            }
            if(flag) break;
            j++;
            //decrease i to l
            for(i=i-1; i>l; i--) {
                res[i][j]=num;
                if(num==n*n) { flag=true; break;}
                num++;
            }
            i++;
            if(flag) break;
        }
        return res;
    }
    public static void main(String args[]) {
    	generateMatrix(4);
    }
}
