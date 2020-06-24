package leetcode.hard;
/*
 * https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it is able to trap after raining.
 * 
 * Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
public class TrappingTrainWater {
	/*
	 * Intuition:
	 * two pointers
	 */
    public int trapI(int[] height) {
        if(height ==null || height.length <=2) return 0;
        int ans =0;
        int left_max = 0; 
        int right_max = 0; 
        
        int i = 0;//left pointer
        int j = height.length -1;//right pointer
        while(i< j){
            left_max = Math.max(left_max, height[i]);//update left_max
            right_max = Math.max(right_max, height[j]);//update right_max
            if(left_max < right_max){
                ans += left_max-height[i];
                i++;
            }else{
                ans += right_max - height[j];
                j--;
            }
        }
        return ans;
    }
	/*
	 * Intuition:
	 * for each of the bar, find the highest on its left, and highest on the right, 
	 * the water it can store is max(0,min(left,right)-bar)
	 * Time complexity: O(n^2)
	 * Space complexity: O(1)
	 */
    public static int trap(int[] height) {
    	int left=0, right=0,res=0, l=height.length;
        for(int i=0;i<l;i++) {
        	if(height[i]>=left) {
        		left=height[i];
        		continue;
        	}
        	right=0;
        	for(int j=i+1;j<l;j++) right=Math.max(right, height[j]);
        	res+=Math.max(0, Math.min(left, right)-height[i]);
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int [] height={0,1,0,2,1,0,1,3,2,1,2,1};
    	System.out.print(trap(height));
    }
}
