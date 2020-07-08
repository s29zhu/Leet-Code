package leetcode.hard;

import java.util.Arrays;

/*
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
 */
/*
 * C++
 * class Solution {
void update(vector<int>& BIT, int index, int val)
{
    while (index > 0) {
        BIT[index] += val;
        index -= index & (-index);
    }
}

int query(vector<int>& BIT, int index)
{
    int sum = 0;
    while (index < BIT.size()) {
        sum += BIT[index];
        index += index & (-index);
    }
    return sum;
}
int reversePairs(vector<int>& nums)
{
    int n = nums.size();
    vector<int> nums_copy(nums);

    sort(nums_copy.begin(), nums_copy.end());

    vector<int> BITS(n + 1, 0);
    int count = 0;
    for (int i = 0; i < n; i++) {
        count += query(BITS, lower_bound(nums_copy.begin(), nums_copy.end(), 2LL * nums[i] + 1) - nums_copy.begin() + 1);
        update(BITS, lower_bound(nums_copy.begin(), nums_copy.end(), nums[i]) - nums_copy.begin() + 1, 1);
    }
    return count;
}
}
 */
public class ReversePairs {
    /*
    *Intuition:
    1. brutal force, O(n^2), time limit exceed
    */
    public static int reversePairsI(int[] nums) {
    	int res=0, l=nums.length;
    	long [] temp=new long [nums.length];
    	for(int i=0; i<l; i++) temp[i]=(long)2*nums[i];
    	for(int i=0; i<l; i++) {
    		for(int j=l-1; j>i; j--) {
    			if(nums[i]>temp[j]) res++;
    		}
    	}
    	return res;
    }
    
    /*
     * Binary Index Tree, explaining https://www.youtube.com/watch?v=WbafSgetDDk
     * 1. Update, The parent node is the current index-lowbit, increase the 
     * 2. Query, the parent node is the current index+lowbit, 
     *  So, the main idea is to count the number of elements greater than 2∗nums[i] in range (i,size - 1]
     *  as we iterate from 0 to size-1. 
     *  The steps are as follows:
     *  - Create a copy of nums, say nums_copy and sort nums_copy.  This array is actually used for creating the Binary indexed tree.
     *  - Initialize count=0 and BIT array of size size(nums)+1 to store the BIT. 
     *  - Iterate over i from 00 to size(nums)−1 : 
     *  Search the index of element not less than 2∗nums[i]+1 in nums_copy array. query the obtained index+1 in the BIT, 
     *  and add the result to count. Search for the index of element not less than nums[i] in nums_copy. We need to update the BIT 
     *  for this index by 1. This essentially means that 1 is added to this index(or number of elements greater than this index is incremented). 
     *  The effect of adding 11 to the index is passed to the ancestors as shown in update algorithm
 	*/
    public static int query(int[] BIT, int index)
    {
        int sum = 0;
        while (index < BIT.length) {
            sum += BIT[index];
            index += index & (-index);
        }
        return sum;
    }
    public static void update(int[] BIT, int index, int val)
    {
        while (index > 0) {
            BIT[index] += val;
            index -= index & (-index); //index&(-index) will get the most right bit 1
        }
    }
    
    //find the left most index with value not less than target
    public static int helper(int[] copy, int target) {
    	int res=0, begin=0, end=copy.length-1;
    	while(begin<=end) {
    		int middle=(begin+end)/2;
    		if(copy[middle]==target) {
    			while(middle>0 && copy[middle-1]==copy[middle]) middle--;
    			return middle;
    		}
    		if(copy[middle]<target) begin=middle+1;
    		else if(copy[middle]>target) end=middle-1;
    	}
    	if(end<copy.length&&end>=0&&copy[end]>=target) res=end;
    	else if(begin<copy.length && begin >= 0 && copy[begin]>=target) res=begin;
    	else res=-1;
    	return res;
    }
    /*
     * test case: Integer.MAX_VALUE and Integer.MIN_VALUE
     */
    public static int reversePairs(int [] nums) {
    	int l=nums.length, res=0;
    	int[] copy=new int[l];
    	int [] BIT=new int[l+1];
    	copy=Arrays.copyOf(nums, l);
    	Arrays.parallelSort(copy);
    	for(int i=0; i<l; i++) { // iterate from 0 to l-1 in nums[i] 
    		// update_index, in copy array, the first index has value nums[i] 
    		int query_index=-1, update_index=-1,target=0;
			update_index=Arrays.binarySearch(copy, nums[i]);
			while(update_index>0 && copy[update_index-1]==copy[update_index]) update_index--;
			update_index=update_index+1;
			//query_index is the left most index with value no less than 2*nums[i]+1
			target=2*nums[i]+1;
			query_index=helper(copy, target);
			//overflow, when the 2*nums[i]+1 
			if(nums[i]<0 && target>0) {query_index=0;}
			//overflow or cannot find value no less than target, giving up the query
			if((nums[i]>0 && target<0 ) || query_index<0) { 
				update(BIT, update_index,1);
				continue;
			}
    		query_index=query_index+1;
    		res+=query(BIT, query_index);
    		update(BIT, update_index, 1);
    	}
    	return res;
    }
    public static void main(String args[]) {
    	int[] nums={2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
    	int res=reversePairs(nums);
    	System.out.println(res);
    	//System.out.println(helper(nums,5));
    	//System.out.println(helper(nums,-1));
    }
}
