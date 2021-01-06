package leetcode.medium;
import java.util.List;
import java.util.ArrayList;

/*
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Triangle {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int res=Integer.MAX_VALUE, l=triangle.size();
        int []dp=new int[l];
        dp[0]=triangle.get(0).get(0);
        for(int i=1; i<l;i++) {
        	int pre_j_1=0, pre_j=0;
        	res=Integer.MAX_VALUE;
        	for(int j=0; j<=i; j++) {
        		if(j<i) {
        			pre_j=dp[j];
        			dp[j]=pre_j+triangle.get(i).get(j);
        		}
        		if(j>0 && j<i) {
        			int temp=pre_j_1+triangle.get(i).get(j);
        			dp[j]=Math.min(dp[j], temp);
        		}else if(j==i) {
        			dp[j]=pre_j_1+triangle.get(i).get(j);
        		}
        		
        		pre_j_1=pre_j;

        		if(dp[j]<res) res=dp[j];
        	}
        }
        return res;
    }
    public static void main(String args[]) {
    	List<Integer> l1=new ArrayList<Integer>();
    	l1.add(2);
    	List<Integer> l2=new ArrayList<Integer>();
    	l2.add(3);
    	l2.add(4);
    	List<Integer> l3=new ArrayList<Integer>();
    	l3.add(6);
    	l3.add(5);
    	l3.add(7);
    	List<Integer> l4=new ArrayList<Integer>();
    	l4.add(4);
    	l4.add(1);
    	l4.add(8);
    	l4.add(3);
    	List<List<Integer>> triangle=new ArrayList<List<Integer>>();
    	triangle.add(l1);
    	triangle.add(l2);
    	triangle.add(l3);
    	triangle.add(l4);

    	System.out.print(minimumTotal(triangle));
    }
}
