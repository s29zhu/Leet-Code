package leetcode.medium;
import java.util.ArrayList;

/*
 * Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[4,6],[1,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        ArrayList<int[]> v=new ArrayList<int[]>();
        int l=intervals.length;
        if(l==0)  {int [][] res= {}; return res;}
    	//sort intervals
    	sort(intervals);        
        int [] pre=intervals[0];
    	int[] cur=intervals[0];
        for(int i=1; i<l;i++) {
        	cur=intervals[i];

        	// pre and cur overlap
        	if(cur[0]<=pre[1] && cur[1]>pre[1]) {
        		pre[1]=cur[1];
        	}
        	// pre contains cur
        	if(cur[1]<=pre[1] && cur[0]<=pre[1]) continue;
        	// pre and cur no over lap at all
        	if(cur[0]>pre[1]) {
        		v.add(pre);
        		pre=cur;
        	}
        }
        v.add(pre);
        int[][] res=new int[v.size()][2];
        for(int i=0; i<v.size();i++) {
        	res[i]=v.get(i);
        }
        return  res;
    }
    
    public static void sort(int[][] nums) {
    	int mini=Integer.MAX_VALUE, k=0;
    	int [] temp={};
    	for(int i=0;i<nums.length;i++) {
    		k=i;
    		mini=Integer.MAX_VALUE;
    		for(int j=i;j<nums.length;j++) {
    			if(nums[j][0]<mini) {
        			mini=nums[j][0];
        			k=j;
    			}
    		}
    		temp=nums[i];
    		nums[i]=nums[k];
    		nums[k]=temp;
    	}
    }
    
    public static void main(String args[]) {
    	int [][] intervals= {{2,3},{4,5},{6,7},{1,10}};
    	int[][] v=merge(intervals);

		for(int[] item: v) { System.out.print("["+item[0]+","+item[1]+"]"+" "); }
		
    }
}
