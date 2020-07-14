package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumbersOccursMoreThanNDivid3 {
	   public static List<Integer> majorityElement(int[] nums) {
	        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
	        List<Integer> res=new ArrayList<Integer>();
	        int c=nums.length/3;	        
	        for(int i=0; i<nums.length; i++){
	            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
	        }
	        
	        for(Integer key: map.keySet()){
	            if(map.get(key)<=c) map.remove(key);
	        }
	        
	        res.addAll(map.keySet());
	        return res;
	   }
	   
	   public static void main(String args[]) {
		   int []nums={3,2,3};
		   majorityElement(nums);
	   }
}
