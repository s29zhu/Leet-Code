package leetcode.easy;

import java.util.HashMap;

/*
 * use map
 */
public class NNumberNTimes {
    public int NNumberNTimes(int[] A) {
        // write your code in Java SE 8
    	int res=0;
    	HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
    	for(int a: A) {
    		map.putIfAbsent(a, 0);
    		map.put(a, map.get(a)+1);
    	}
    	for(int a:map.keySet()) {
    		if(a==map.get(a) && a>res) {
    			res=a;
    		}
    	}
    	return res;
    }
    
    public static void main(String args[]) {
    	
    }
}
