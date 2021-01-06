package leetcode.easy;

import java.util.HashMap;
/*
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:

Input:
[[0,0],[1,0],[2,0]]
0 1 4
1 0 1
4 1 0
Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public static int numberOfBoomerangs(int[][] points) {
        int res=0, n=points.length;
        
        for(int i=0; i<n;i++) {
        	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        	for(int j=0; j<n; j++) {
        		int distance=(int) (Math.pow(points[i][0]-points[j][0], 2)+Math.pow(points[i][1]-points[j][1], 2));
        		map.put(distance,map.getOrDefault(distance, 0)+1);
        	}
        	for(int value: map.values()) {
        		res+=value*(value-1);
        	}
        }
        return res;
    }
    public static void main(String args[]) {
    	int[][] points= {{0,0},{1,0},{2,0}};
    	System.out.print(numberOfBoomerangs(points));
 //   	System.out.print((int)Math.pow(10, 2));
    }
}
