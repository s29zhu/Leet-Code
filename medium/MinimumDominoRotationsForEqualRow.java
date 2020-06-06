package leetcode.medium;
/*
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.

 

Example 1:



Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation: 
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.
 */

import java.util.HashMap;

/*
 * Analysis:
 * 1. A and B have the same length n
 * 2. hashmaps to count the value number, 
 * 3. i iterate from 0 to l-1, if Both A[i] and B[i] have the same value, only increase one
 * 4. if the maximum value v number n, is less than l, return -1; else proceed to step 5
 * 5. Iterate A again, count the number of V in A, if n<l/2, return n, else return l-n; 
 */
public class MinimumDominoRotationsForEqualRow {
    public static int minDominoRotations(int[] A, int[] B) {
        int res=Integer.MAX_VALUE, l=A.length, min=0;
        HashMap<Integer, Integer> map=new HashMap<Integer,Integer>();
        HashMap<Integer, Integer> mapA=new HashMap<Integer,Integer>(); 
        HashMap<Integer, Integer> mapB=new HashMap<Integer,Integer>();
        if(l==0) return res;
        for(int i=0; i<l;i++) {
        	map.put(A[i], map.getOrDefault(A[i], 0)+1);
        	mapA.put(A[i], mapA.getOrDefault(A[i], 0)+1);
        	mapB.put(B[i], mapB.getOrDefault(B[i], 0)+1);
        	if(A[i]!=B[i]) map.put(B[i],map.getOrDefault(B[i], 0)+1);
        }
        
        for(int i=0;i<l;i++) {
        	if(map.getOrDefault(A[i],0)>=l) {
        		min=Math.min(mapA.getOrDefault(A[i],0), l-mapA.getOrDefault(A[i],0));
        		min=Math.min(min, Math.min(mapB.getOrDefault(A[i],0), l-mapB.getOrDefault(A[i],0)));
        		res=Math.min(res, min);
        	}
        	if(map.get(B[i])>=l) {
        		min=Math.min(mapA.getOrDefault(B[i],0), l-mapA.getOrDefault(B[i],0));
        		min=Math.min(min, Math.min(mapB.getOrDefault(B[i],0), l-mapB.getOrDefault(B[i],0)));
        		res=Math.min(res, min);
        	}
        }        
        if(res==Integer.MAX_VALUE) res=-1;
        return res;
    }
    
    public static void main(String args[]) {
    	int [] A= {2};
    	int [] B= {1};
    	System.out.print(minDominoRotations(A,B));
    }
}
