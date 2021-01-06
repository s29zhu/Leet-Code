package leetcode.medium;

import java.util.HashMap;

/*
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRooms {
    public static int minMeetingRooms(int[][] intervals) {
        int res=0, l=intervals.length;
        HashMap<Integer, int[]> map=new HashMap<Integer, int[]>();
       	if(l==0) return res;
       	
       	sort(intervals,0,l-1);
       	res++;
       	map.put(1, new int[] {intervals[0][0], intervals[0][1]});
       	for(int i=1;i<l;i++) {
       		int j=1;
       		boolean flag=false;
       		for(; j<=map.size();j++) {
       			if(intervals[i][0]>=map.get(j)[1]||intervals[i][1]<=map.get(j)[0]) {
       				map.put(j, intervals[i]);
       				flag=true;
       				break;
       			}
       		}
       		if(!flag) {
       			res++;
       			map.put(res, intervals[i]);
       		}
       	}
       	return map.size();
    }
    
    public static void sort(int[][] m, int low, int high) {
    	int [] temp;
    	if(low>=high) return;
    	int []pivot=new int []{m[low][0],m[low][1]};
    	int pointer=high;
    	for(int i=high; i>low; i--) {
    		if(pivot[0]<m[i][0]) {
    			temp=m[i];
    			m[i]=m[pointer];
    			m[pointer]=temp;
    			pointer--;
    		}
    	}
		temp=m[low];
		m[low]=m[pointer];
		m[pointer]=temp;
    	sort(m,low, pointer-1);
    	sort(m,pointer+1, high);
    }
    
    public static void main(String args[]) {
    	int[][] intervals= {{11,12},{6,17},{8,9},{6,9},{7,23}};
    	
    	//11,6,8,6,7 i=4
    	//7,6,8,6,11 i=3
    	//7,6,8,6,11 i=2
    	//
    	sort(intervals,0,intervals.length-1);
    	for(int i=0;i<intervals.length;i++) {
    		System.out.println(intervals[i][0]+" "+intervals[i][1]);
    	}
    	//System.out.print(minMeetingRooms(intervals));
    }
}
