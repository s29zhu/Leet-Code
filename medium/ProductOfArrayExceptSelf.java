package leetcode.medium;
/*
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
	/*
	 * Test case, when array contains 0
	 */
    public static int[] productExceptSelf(int[] nums) {
    	int l=nums.length;
        int [] res=new int[l];
        int [] postfix=new int[l];
        res[0]=1;
        postfix[l-1]=1;
        for(int i=1;i<l;i++) res[i]=res[i-1]*nums[i-1];
        for(int i=l-2; i>=0; i--) postfix[i]=postfix[i+1]*nums[i+1];
        for(int i=0;i<l;i++) res[i]=res[i]*postfix[i];
        return res;
    }
    
    public static void main(String args[]) {
    	int []nums= {1,2,3,4};
    	productExceptSelf(nums);
    }
}
