package leetcode.hard;
/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */
public class MaximumRectangle {
    public int maximalRectangle(char[][] matrix) {
    	//up, left
    	int m=matrix.length, n=matrix[0].length, res=0;
    	String[][] ma=new String[m][n];
    	for(int i=0; i<m; i++) {
    		for(int j=0; j<n; j++) {
    			if(matrix[i][j]=='1') ma[i][j]="1*1";
    			else ma[i][j]="0";
    		}
    	}
    	for(int i=0; i<m; i++) {
    		for(int j=0; j<n; j++) {
    			int h1=0, w1=0, h2=0, w2=0, v1=0, v2=0;
    			if(!ma[i][j].equals("0")) {
    				//up 
    				if(i>0 && !ma[i-1][j].equals("0")) {  
    					int index=ma[i-1][j].indexOf('*');
    					w1=Integer.valueOf(ma[i-1][j].substring(index));
    					h1=Integer.valueOf(ma[i-1][j].substring(index+1));
    					v1=w1*h1;
    			    }
    				//left
    				if(j>0 && !ma[i][j-1].equals("0")) {
    					int index=ma[i-1][j].indexOf('*');
    					w2=Integer.valueOf(ma[i-1][j].substring(index));
    					h2=Integer.valueOf(ma[i-1][j].substring(index+1));   
    					v2=w2*h2;
    				}
    				if(h2==h1+1 && w1==w2+1) ma[i][j]=String.valueOf(w1)+"*"+String.valueOf(h2);
    				
    			}else {
    				ma[i][j]="0";
    			}
    		}
    	}
        return 1;
    }
}
