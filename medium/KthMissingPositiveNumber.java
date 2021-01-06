package leetcode.medium;

import java.util.Collections;
import java.util.PriorityQueue;

/*https://leetcode.com/problems/kth-missing-positive-number/
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

 

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length

 */

/*
 * thoughts
 * 
 */
public class KthMissingPositiveNumber {
    public static int findKthPositive(int[] arr, int k) {
        int res=0;
        int l=arr.length;
        PriorityQueue<Integer> que=new PriorityQueue<Integer>(k, Collections.reverseOrder());
        int j=0;
        for(int i=1; i<arr[l-1]; i++) {
        	if(i == arr[j]) j++;
        	else if(i < arr[j]) {
        		if(que.size()==k-1) return i;
        		que.add(i);
        	}
        }
    	int i=arr[l-1]+1;
        while(que.size()<k) {
        	que.add(i);
        	i++;
        }
        res=que.poll();
        return res;
    }
    public static void main(String args[]) {
    	int [] arr= {1,2,3};
    	System.out.print(findKthPositive(arr,5));
    }
}
