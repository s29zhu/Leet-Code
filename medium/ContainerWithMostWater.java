package leetcode.medium;
/*
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */
public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
	     int res=0;
	     int i=0,j=height.length-1;
	     for(i=0;i<=j;i++ ) {
	    	for(j=height.length-1;j>=i;j--) {
	    	 res=Math.max(res, (j-i)*Math.min(height[i], height[j]));
	    	}
	    	j=height.length-1;
	     }
	     return res;
    }
    
    public static void main(String args[]) {
    	int []height={2,3,10,5,7,8,9};
    	System.out.print(maxArea(height));
    }
}