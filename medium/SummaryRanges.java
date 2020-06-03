package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
public class SummaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> list=new ArrayList<String>();
        int l=nums.length;
        if(l==0) return list;
        String s=String.valueOf(nums[0]);
        for(int i=1; i<l; i++) {
        	if(nums[i]-nums[i-1]==1) continue;
        	else if(Integer.valueOf(s)==nums[i-1]){
        		list.add(s);
        		s=String.valueOf(nums[i]);
        	}else {
        		s+="->"+String.valueOf(nums[i-1]);
        		list.add(s);
        		s=String.valueOf(nums[i]);
        	}

        }
        if(l-2>=0 && nums[l-1]-nums[l-2]==1 ) s+="->"+String.valueOf(nums[l-1]);
        list.add(s);
        return list;
    }
    
    public static void main(String args[]) {
    	int [] nums= {15};
    	List<String> list=summaryRanges(nums);
    	for(String l: list) {
    		System.out.print(l+" ");
    	}
    }
}
