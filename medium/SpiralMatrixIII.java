package leetcode.medium;
/*
 * https://leetcode.com/problems/spiral-matrix-iii/
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.) 

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.
 */
public class SpiralMatrixIII {
    public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int [][]res=new int[R*C][2];
        res[0][0]=r0;
        res[0][1]=c0;
        int layer=0;
        int []cur= {r0,c0};
        boolean flag=false;
       for(int i=1; i<R*C;){
           layer++;
           int []p=new int[2];
           //downer right part
           p[0]=cur[0];
           p[1]=cur[1]+1;
           for(int r=cur[0]; r<r0+layer; r++) {
        	   p[0]=r;
               if(p[0]>=0 &&p[0]<R&&p[1]>=0 &&p[1]<C) {
                   res[i][0]=p[0];
                   res[i][1]=p[1];
                   i++;
                   if(i==R*C) { flag=true; break;}
               }
           }
           if(flag) break;
                              
           //down
           p[0]=r0+layer;
           p[1]=c0+layer;
           for(int j=c0+layer; j>c0-layer; j--){
                p[1]=j;
                if(p[0]>=0 &&p[0]<R&&p[1]>=0 &&p[1]<C){
                   res[i][0]=p[0];
                   res[i][1]=p[1];
                   i++;
                   if(i==R*C) { flag=true; break; }
               }
           }
           if(flag) break;
           
           //left
           p[0]=r0+layer;
           p[1]=c0-layer;
           for(int j=r0+layer; j>r0-layer; j--){
                p[0]=j;
                if(p[0]>=0 &&p[0]<R&&p[1]>=0 &&p[1]<C){
                   res[i][0]=p[0];
                   res[i][1]=p[1];
                   i++;
                   if(i==R*C) { flag=true; break; }
               }
           }
           if(flag) break;

           //up
           p[0]=r0-layer;
           p[1]=c0-layer;
           for(int j=c0-layer; j<=c0+layer; j++){
                p[1]=j;
                if(p[0]>=0 &&p[0]<R&&p[1]>=0 &&p[1]<C){
                   res[i][0]=p[0];
                   res[i][1]=p[1];
                   i++;
                   if(i==R*C) { flag=true; break; }
               }
           }
           if(flag) break; 
           cur[0]=r0-layer;
           cur[1]=c0+layer;
       }
       return res;
    }
    
    public static void main(String args[]) {
    	spiralMatrixIII(5,6,2,2);
    	/*
    	 * 000
    	 * X00
    	 * [1,0] [1,1] [0,0] [0,1]
    	 */
    }
}
