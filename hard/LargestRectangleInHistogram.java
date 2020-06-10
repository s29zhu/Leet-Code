package leetcode.hard;

import java.util.Stack;

/*
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Given n non-negative integers representing the histogram's 
 * bar height where the width of each bar is 1, 
 * find the area of largest rectangle in the histogram.
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        int res=0, l=heights.length;
        for(int i=0;i<l;i++) {
        	res=Math.max(res, extend(i, heights));
        }
        return res;
    }
    public static int extend(int i, int[] heights) {
    	int res=0;
    	for(int j=i;j>=0;j--) {
    		if(heights[j]>=heights[i]) {
    			res+=heights[i];
    		}else break;
    	}
    	return res;
    }
    
    public static int largestRectangleAreaI(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }
    public static void main(String args[]) {
    	int []nums={1,2,3,4,5,6};
    	System.out.println(largestRectangleAreaI(nums));
    }
}
