package leetcode.easy;
import java.util.HashMap;

public class TwoSum {
	static public int[] twoSum(int[] nums, int target) {
        int ret[]= {0,0};
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        for (int i=0; i<nums.length; i++) {
        	if(map.containsKey(target-nums[i])) {
        		ret[0]=i;
        		ret[1]=map.get(target-nums[i]);
        	};
        	/*Put the element into the map later, so that if nums={3,3}, target=6,
        	 *  it will still work*/
            map.put(nums[i], i); 
        }
        return ret;
    }
	public static void main(String args []) {
		int [] nums= {1,23,4,5,8,15};
		
		twoSum(nums, 6);
	}
}
