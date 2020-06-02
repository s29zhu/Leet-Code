package leetcode.easy;

import java.util.Arrays;
/*
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
 */
public class RelativeRanks {
	public static String[] findRelativeRanks(int[] nums) {
		int l=nums.length, c=0;
		String [] res=new String[l];
		
		for(int i=0;i<l;i++) {
			c=0;
			for(int j=0;j<l;j++) {
				if(nums[j]>nums[i]) c++;
			}
			if(c==0) res[i]="Gold Medal";
			else if(c==1) res[i]="Silver Medal";
			else if(c==2) res[i]="Bronze Medal";
			else res[i]=Integer.toString(c+1);
		}
		return res;
	}
    public static String[] findRelativeRanksII(int[] nums) {
        int l=nums.length;
        int []A=new int[l];
        String [] res=new String[nums.length];
        for(int i=0;i<l;i++) {
        	A[i]=nums[i];
        }       
        Arrays.sort(A);
        for(int i=0;i<l;i++) {
        	for(int j=0;j<l;j++) {
        		if(nums[i]==A[j]) {
        			if(j==l-1) 
        				res[i]="Gold Medal";
        			else if(j==l-2)
        				res[i]="Silver Medal";
        			else if(j==l-3)
        				res[i]="Bronze Medal";
        			else
        				res[i]=Integer.toString(l-j);
        		}
        	}
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int [] array= {1,3,8,5,2,9};
    	for(int i=0;i<array.length;i++) {
    		System.out.println(findRelativeRanks(array)[i]);
    	}
    }
}
