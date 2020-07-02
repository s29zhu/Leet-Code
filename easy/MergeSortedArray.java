package leetcode.easy;

import java.util.Arrays;

/*
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
 

Constraints:

-10^9 <= nums1[i], nums2[i] <= 10^9
nums1.length == m + n
nums2.length == n
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	int []tmp=new int[m+n];
    	for(int i=0, j=0,k=0; i<m || j<n;k++) {
    		int n1=(i<m)?nums1[i]:Integer.MAX_VALUE;
    		int n2=(j<n)?nums2[j]:Integer.MAX_VALUE;
    		if(n1<=n2) { tmp[k]=n1; i++;}
    		else {tmp[k]=n2; j++;}
    	}
    	for(int i=0;i<m+n;i++) {
    		nums1[i]=tmp[i];
    	}
    }
    public static void main(String args[]) {
    	
    }
}
