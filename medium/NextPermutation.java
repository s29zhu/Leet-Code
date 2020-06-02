package leetcode.medium;

import java.util.Arrays;

/*
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

1234-> 1243 -> 1324 -> 1342 -> 1423 -> 1432 

4321->1234
 */
/*
Analysis
int i range from l-1 to 0, , find a min nums[j], i<j<l and nums[j] > nums[i], 
exchange nums[i] and nums[j], and sort nums array from i+1 to l
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
       int l=nums.length, temp=0, min_max=0, digit=Integer.MAX_VALUE;
       for(int i=l-1;i>=0;i--) {
		   min_max=i;
    	   for(int j=i;j<l;j++) {
    		   if(nums[j]>nums[i] && nums[j]<=digit) {
    			   min_max=j;
    			   digit=nums[j];
    		   }
    	   }
		   if(min_max!=i) {
			   temp=nums[i];
			   nums[i]=nums[min_max];
			   nums[min_max]=temp;
			   sort(nums,i+1,l-1);
		    	return;
		   }
       }

       for(int i=0,j=l-1; i<j; i++,j--) {
    	   temp=nums[i];
    	   nums[i]=nums[j];
    	   nums[j]=temp;
       }
    }

    public static void sort(int[] nums, int i, int j) {
    	if(i==j) return;
    	int min=i, temp=0;
    	for(int k=i;k<=j;k++) {
    		if(nums[k]<nums[min]) min=k;
    	}
    	temp=nums[i];
    	nums[i]=nums[min];
    	nums[min]=temp;
    	sort(nums,i+1,j);
    }
    
    public static void main(String args[]) {
    	//int [] nums={4,2,0,2,3,2,0};//[4,2,0,3,0,2,2]
    	int [] nums={16,27,25,23,25,16,12,9,1,2,7,20,19,23,16,0,6,22,16,11,8,27,9,2,20,2,13,7,25,29,12,12,18,29,27,13,16,1,22,9,3,21,29,14,7,8,14,5,0,23,16,1,20};
    	nextPermutation(nums);
    	for(int i=0; i<nums.length;i++)
    		System.out.print(nums[i]+" ");
    }
}
