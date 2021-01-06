package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 */
public class FindAllNumbersDisappearedinArray {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list=new ArrayList<Integer>();
        int l=nums.length;
        for(int i=0;i<=l-1;i++) list.add(nums[i]);
        for(Integer item: list) nums[item-1]=0;
        list.clear();
        for(int i=0;i<l;i++) {
        	if(nums[i]!=0) {
        		list.add(i+1);
        	}
        }
        
        return list;
    }
    public static void main(String args[]) {
        int [] nums= {1,2,3,3,3,4};
        System.out.print(findDisappearedNumbers(nums));        
    }
}
