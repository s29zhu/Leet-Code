package leetcode.medium;
/*
 * 
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class MaximalNetworkRank {
    public static int maximalNetworkRank(int n, int[][] roads) {
        int result=0;
        int [] arr=new int[n];
        HashMap<Integer, HashSet<Integer>> map=new HashMap<Integer, HashSet<Integer>>();
        Arrays.fill(arr, 0);
        for(int i=0; i<roads.length; i++) {
        	arr[roads[i][0]]++;
        	arr[roads[i][1]]++;
        	HashSet<Integer> set=map.getOrDefault(roads[i][0], new HashSet<Integer>());
        	set.add(roads[i][1]);
        	map.put(roads[i][0], set);
        	set=map.getOrDefault(roads[i][1], new HashSet<Integer>());
        	set.add(roads[i][0]);
        	map.put(roads[i][1], set);
        }
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n && j!=i ; j++) {
        		int temp=arr[i]+arr[j];
        		if(map.getOrDefault(i, new HashSet<Integer>()).contains(j)
        				|| map.getOrDefault(j, new HashSet<Integer>()).contains(i)) temp--;
        		result=Math.max(result, temp);
        	}
        }
        return result;
    }
	public static void main(String args[]) {
		int [][]roads= {{0,1},{0,3},{1,2},{1,3},{2,3},{2,4}};
		System.out.print(maximalNetworkRank(5, roads));
	}
}
