package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumOfList {
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Queue<Integer> que=new LinkedList<Integer>();
        HashMap<Integer, List<Integer>> map=new HashMap<Integer, List<Integer>>();
        int [] dp=new int[n];
        dp[headID]=0;
        for(int i=0; i<n; i++){
            List<Integer> list=map.getOrDefault(manager[i], new ArrayList<Integer>());
            list.add(i);
            map.put(manager[i], list);
        }
        que.add(headID);

        while(!que.isEmpty()){
            int id=que.poll();
            List<Integer> list=map.getOrDefault(id, new ArrayList<Integer>());
            for(Integer employee: list){
                que.add(employee);
                dp[employee]=dp[id]+informTime[id];
            }   
        }
        Arrays.sort(dp);
        return dp[n-1];
    }
    
    public static void main(String args[]) {

    	int []manager= {3,3,-1,2};
    	int []infor= {0,0,162,914};
    	System.out.print(numOfMinutes(4,2,manager, infor));
    }
}
