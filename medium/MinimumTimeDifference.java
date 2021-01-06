package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
 */
public class MinimumTimeDifference {
    public static int findMinDifference(List<String> timePoints) {
    	int length=timePoints.size();
    	int [] nums=new int[length];
    	int retval=Integer.MAX_VALUE;
    	int largest_diff=24*60;
    	for(int i=0; i<length; i++) {
    		String str=timePoints.get(i);
    		nums[i]=Integer.valueOf(str.substring(0,2))*60+Integer.valueOf(str.substring(3));
    	}
    	
    	Arrays.parallelSort(nums);
    	for(int i=0; i<length; i++) {
			if(i==length-1) {
				retval=Math.min(retval, largest_diff+nums[0]-nums[i]);				
			}else {
				retval=Math.min(retval, nums[i+1]-nums[i]);
			}
    	}
        return retval;
    }
    
    public static void main(String args[]) {
    	ArrayList<String> list=new ArrayList<String>();
    	list.add("05:31");
    	list.add("22:08");
    	list.add("00:35");
    	
    	System.out.println(findMinDifference(list));
    }
}
