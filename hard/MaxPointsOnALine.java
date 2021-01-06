package leetcode.hard;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
/*
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
	public class MaxPointsOnALine {

	    public static int maxPoints(int[][] points){
	        int result = 0;
	        HashMap<BigDecimal, Integer> map = new HashMap<BigDecimal, Integer>();
	        if(points.length < 3)
	            result = points.length;
	        else{
	            for(int i = 0; i < points.length; i++){
	                int a = 0, b = 0;
	                BigDecimal c, x, y,zero;
	                map.clear();
	                zero=new BigDecimal(0);
	                for(int j = 0; j < points.length; j++){
	                    x = new BigDecimal(points[j][0] - points[i][0]);
	                    y = new BigDecimal(points[j][1] - points[i][1]);
	                    if(x.equals(zero) && y.equals(zero)) // duplicate points
	                        a++;
	                    else if(x.equals(zero)) //vertical lines，no slope
	                        b++;
	                    else{ //lines with slope
	                        c = y.divide(x, 20, RoundingMode.CEILING);
	                        map.put(c, map.getOrDefault(c, 0)+1);
	                    }
	                }
	                result = Math.max(result, b+a);
	                int max=0;
	                if(!map.values().isEmpty()) max=Collections.max(map.values()) + a;
	                result = Math.max(result, max);
	            }
	        }
	        return result;
	    }
	    
	    public static void main(String args[]) {
	    	//int [][]points= {{1,1},{2,2},{3,3}};
	    	int [][]points={{0,0},{94911151,94911150},{94911152,94911151}};
	    	System.out.println(maxPoints(points));
		/*
		 * BigDecimal x1=new BigDecimal(0.0), y1=new BigDecimal(0.0); x1=new
		 * BigDecimal(94911150); BigDecimal temp=new BigDecimal(94911151);
		 * x1=x1.divide(temp,20, RoundingMode.CEILING); y1=new BigDecimal(94911151);
		 * temp=new BigDecimal(94911152); y1=y1.divide(temp, 20, RoundingMode.CEILING);
		 * System.out.println(x1); System.out.println(y1);
		 */	    	
	    }
	}
