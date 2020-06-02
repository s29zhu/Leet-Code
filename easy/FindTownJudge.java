package leetcode.easy;

import java.util.HashMap;
import java.util.Map.Entry;

public class FindTownJudge {
    public static int findJudge(int N, int[][] trust) {
        int res=-1, count=0;
        int trust_r=trust.length;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int j=1; j<=N; j++) {
        	map.put(j, 0);
        }
        for(int i=0; i < trust_r; i++) {
        	for(int j=1; j <=N; j++) {
        		if(trust[i][1]==j) {
        			map.put(j, map.get(j)+1);
        			break;
        		}
        	}
        }
        for(Entry<Integer, Integer> item : map.entrySet()) {
        	if(item.getValue() == (N-1)) {
        		count++;
        		res=item.getKey();
        	}
        }
        if(count != 1) return -1;
        for(int i=0; i < trust_r; i++) {
        	if(trust[i][0]==res) return -1;
        }
        
        return res;
    }
    
    public static void main(String []args) {
    	int [][] trust= {{1,2},{3,2},{3,1}};
    	int res=findJudge(3, trust);
    	System.out.print(res);
    }
}
