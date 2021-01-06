package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {

    public static List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> res=new  ArrayList<List<Integer>>();
        Arrays.sort(nums); 
        int l=nums.length;
        List<Integer> l1=Arrays.stream(nums).boxed().collect(Collectors.toList());
        res.add(l1);
        while(true) {
        	int i=l-1;
        	for(i=l-1; i>=0; i--) {
			   int min_max=i;
			   for(int j=i;j<l;j++) {
				   if(nums[j]>nums[i]) {
					   min_max=j;
				   }
			   }
			   if(min_max!=i) {
				   int temp=nums[i];
				   nums[i]=nums[min_max];
				   nums[min_max]=temp;
				   Arrays.sort(nums,i+1,l);
	        	   l1=Arrays.stream(nums).boxed().collect(Collectors.toList());
	        	   res.add(l1);
	        	   break;
			   }
	        }
        	if(i<0) break;
        }	
        return res;
    }
    
    public static void main(String args[]) {
    	int []nums={1,3,2,0};
    	//Arrays.parallelSort(nums, 1, 3);
    	//System.out.print("hello\n");
    	permute(nums);
    }
}
