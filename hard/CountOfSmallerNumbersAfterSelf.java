
package leetcode.hard;
import java.util.Arrays;
import java.util.List;

/*
 * You are given an integer array nums and you have to return a new counts array. The counts array has 
 * the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */
public class CountOfSmallerNumbersAfterSelf {
	/*
	 * intuition: binary index tree
	 * sort the array first, then iterate the original array
	 */
	//update(BIT, index, val) update the index value and the parent index value
	public static void update(int[] BIT, int index, int val) {
		while(index < BIT.length) {
			BIT[index] += val;
			index += index & (-index);
		}
	}
	 //query(BIT, index) return the total number of smaller numbers than nums[i]. sum from index 1 to index
	public static int query(int[] BIT, int index) {
		int sum = 0;
		while(index > 0) {
			sum += BIT[index];
			index -= index & (-index);
		}			
		return sum;
	}
	
    public static List<Integer> countSmaller(int[] nums) 
    {
        Integer l = nums.length;
        Integer [] res = new Integer[l];
        int []copy = Arrays.copyOf(nums, l);
        Arrays.parallelSort(copy);
        int []BIT = new int[l+1];
        Arrays.fill(BIT, 0); 
        for(int i=l-1; i>=0;i--) {
        	int query_index=-1, update_index=-1;
            // update index is the number that is right most index of copy and the value is equal to nums[i]
        	update_index=Arrays.binarySearch(copy, nums[i]);
        	while(update_index<l-1 && copy[update_index]==copy[update_index+1]) {
        		update_index++;
        	}
            // query index is the number that is right most index of copy, and the value is smaller than nums[i]
        	query_index=update_index;
        	while(query_index>0 && copy[query_index-1]==copy[query_index]) {
        		query_index--;
        	}
        	update_index+=1;
        	res[i]=query(BIT,query_index);
        	update(BIT,update_index,1);
        }
        return Arrays.asList(res);
    }
    
    public static void main(String args[]) {
    	int []nums={5,2,6,1,1};
    	countSmaller(nums);
    }
}
