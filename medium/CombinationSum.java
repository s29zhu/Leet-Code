package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
Each element of candidate is unique.
1 <= target <= 500
 */
public class CombinationSum {
	/*
	 * intuition: Back tracking d
	 * 1. sort the candidates first
	 * 2. iterate from first item to last
	 */
	public static List<List<Integer>> helper(int [] candidates, int target, int index){
		List<List<Integer>> res=new ArrayList<List<Integer>>();
		if(target<candidates[0]) return res;
		List<Integer> list=new ArrayList<Integer>();
		
		if(Arrays.binarySearch(candidates, target)>=index) {
			list.add(target);
			res.add(list);
		}
		
		for(int i=index; i<candidates.length; i++) {
			List<List<Integer>> temp=helper(candidates, target-candidates[i], i);
			for(List<Integer> l: temp) {
				l.add(candidates[i]);			
			}
			res.addAll(temp);
		}
		return res;
	}
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        Arrays.parallelSort(candidates);
        res=helper(candidates, target,0);
        return res;
    }
    
    public static void main(String args[]) {
    	int []nums={2,3,5};
    	List<List<Integer>> res=combinationSum(nums,8);
    	System.out.print("done");
    }
}
