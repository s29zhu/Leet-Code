package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. 
 * The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to 
 * any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. 
 * Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place 
 * indexed i in the array.
 * 
 * Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N 
 * using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should 
 * take to get to the place indexed N using minimum coins.
 * 
 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
 * If it's not possible to reach the place indexed N then you need to return an empty array.
 * Example 1:
 * Input: [1,2,4,-1,2], 2
 * Output: [1,3,5]
 * Example 2:
 * Input: [1,2,4,-1,2], 1
 * Output: []
 * 
 * Note:
 * Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i 
 * where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
 * A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
 * Length of A is in the range of [1, 1000].
 * B is in the range of [1, 100].
 */
public class CoinPath {
	/*
	 * Analysis:
	 * 1. minimum coins
	 * 2. lexicographically smaller path
	 * Dynamic programming 
	 * 1. int [] sum to keep the current coins. List<List<Integer>> to keep the path?
	 * time: O(nB)
	 * space: O(n^2)
	 */
    public static List<Integer> cheapestJump(int[] A, int B) {
        int l=A.length;
        int [] sum=new int[A.length];
        Arrays.fill(sum, Integer.MAX_VALUE);
        List<ArrayList<Integer>> lists=new ArrayList<ArrayList<Integer>>();
        
        sum[0]=A[0];
        ArrayList<Integer> list=new ArrayList<Integer>();
        list.add(1);
        lists.add(list);
        
        for(int i=1;i<l;i++) {
        	list=new ArrayList<Integer>();
        	if(A[i]==-1) {
        		lists.add(list);
        		continue;
        	}
        	for(int j=B;j>=1;j--) {
        		if(i-j>=0&&sum[i-j]!=Integer.MAX_VALUE&&sum[i-j]+A[i]<=sum[i]) {
                	list=new ArrayList<Integer>();
            		list.addAll(lists.get(i-j));
            		list.add(i+1);
            		if(sum[i]==Integer.MAX_VALUE) lists.add(list);//first add
            		else if(sum[i-j]+A[i]==sum[i]) {list=compareList(list, lists.get(i)); lists.set(i, list);} // find the lexicophic smaller one
            		else if(sum[i-j]+A[i]<sum[i]) lists.set(i, list); //set the one with smaller sum
        			sum[i]=sum[i-j]+A[i];
        		}
        	}
        	//couldn't find a valid path
        	if(sum[i]==Integer.MAX_VALUE) lists.add(list);
        }
        return lists.get(l-1);
    }
    
    public static ArrayList<Integer> compareList(ArrayList<Integer> l1, ArrayList<Integer> l2){
    	int length1=l1.size(), length2=l2.size();
    	int min=Math.min(length1, length2);
    	for(int i=0;i<min;i++) {
    		if(l1.get(i)<l2.get(i)) return l1;
    		if(l2.get(i)<l1.get(i)) return l2;
    	}
    	if(min==length1) return l1;
    	else return l2;
    }
/*
 * From the solutions discussed above, we can observe that the cost of jumping till the end of the array AA starting from the index ii is only dependent on the elements following the index ii and not the ones before it. This inspires us to make use of Dynamic Programming to solve the current problem.

We again make use of a nextnext array to store the next jump locations. We also make use of a dpdp with the same size as that of the given AA array. dp[i]dp[i] is used to store the minimum cost of jumping till the end of the array AA, starting from the index ii. We start with the last index as the current index and proceed backwards for filling the nextnext and dpdp array.

With ii as the current index, we consider all the next possible positions from i+1i+1, i+2i+2,..., i+Bi+B, and determine the position, jj, which leads to a minimum cost of reaching the end of AA, which is given by A[i]+dp[j]A[i]+dp[j]. We update next[i]next[i] with this corresponding index. We also update dp[i]dp[i] with the minimum cost, to be used by the previous indices' cost calculations.

At the end, we again jump over the indices as per the nextnext array and put these indices in the resres array to be returned.
 */
    public List < Integer > cheapestJumpI(int[] A, int B) {
        int[] next = new int[A.length];
        long[] dp = new long[A.length];
        Arrays.fill(next, -1);
        List < Integer > res = new ArrayList();
        for (int i = A.length - 2; i >= 0; i--) {
            long min_cost = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + B && j < A.length; j++) {
                if (A[j] >= 0) {
                    long cost = A[i] + dp[j];
                    if (cost < min_cost) {
                        min_cost = cost;
                        next[i] = j;
                    }
                }
            }
            dp[i] = min_cost;
        }
        int i;
        for (i = 0; i < A.length && next[i] > 0; i = next[i])
            res.add(i + 1);
        if (i == A.length - 1 && A[i] >= 0)
            res.add(A.length);
        else
            return new ArrayList < Integer > ();
        return res;
    }
    public static void main(String args[]) {
    	int []A={14,86,57,47,99,64,41,77,58,2,54,89,26,99,54,64,37,41,82,50,93,99,49,53,44,65,-1,88,71,42,30,65,98,13,58,13,27,59,35,28,34,75,1,47,91,66,76,49,65,98,77,90,31,8,-1};
    	//int []A={1,2,4,-1,2};
    	System.out.println(A.length);
    	for(Integer i: cheapestJump(A, 1)) {
    		System.out.print(i+" ");
    	};
    }
}
