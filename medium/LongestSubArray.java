import java.util.Arrays;

public class LongestSubArray {
    public static int longestSubarray(int[] nums, int limit) {
        int max=1, begin=0, end=begin;
        for(int i=0; i<nums.length;i++){
            end=i;
            int j=0;
            for(j=begin; j<end; j++){
               if(Math.abs(nums[j]-nums[end])>limit){
                   begin=j+1;
               }
            }
            if(j==end && end-begin+1>max) {
                max=end-begin+1;
            } 
        }
        return max;
    }
    
    public static void main(String args[]) {
    	int []nums= {4,3,2,1,20};
    	Arrays.parallelSort(nums);
    	System.out.print(longestSubarray(nums, 5));
    }
    
}
