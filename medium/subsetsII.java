package leetcode.medium;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [],
  [1],
  [2],
  [1,2],
  [2,2],
  [1,2,2]
]
 */
public class subsetsII {
	/*
	 * intuition: 
	 * 0, sort nums, and iterate through nums, 
	 * 1. if nums[i]==nums[i-1], add nums[i] to the previous lists which has added nums[i-1], save the lists which has added num[i] to pre
	 * 2. if nums[i]!=nums[i-1], add nums[i] to every existing lists, save the lists which has added nums[i] to be pre
	 */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
    	Arrays.parallelSort(nums);
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
        List<List<Integer>> pre=new ArrayList<List<Integer>>();
    	ArrayList<Integer> list=new ArrayList<Integer>();
    	res.add(list);
    	
    	for(int i=0; i<nums.length; i++) {
    		List<List<Integer>> temp=new ArrayList<List<Integer>>();
    		temp.addAll(res);
    		if(i==0 || (i>0&&nums[i]!=nums[i-1])){
                pre.clear();
    			for(List<Integer> item: temp) {
    				List<Integer> l=new ArrayList<Integer>();
    				l.addAll(item);
    				l.add(nums[i]);
    				res.add(l);
    				pre.add(l);
    			}
    		}else {//nums[i]==nums[i-1]
    			List<List<Integer>> tmp=new ArrayList<List<Integer>>();
    			for(List<Integer> item: pre) {
    				List<Integer> l=new ArrayList<Integer>();
    				l.addAll(item);
    				l.add(nums[i]);
    				res.add(l);
    				tmp.add(l);
    			}
    			pre.clear();
    			pre.addAll(tmp);
    		}
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	int []nums= {1,2,2};
    	subsetsWithDup(nums);
    }
}
