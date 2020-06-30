package leetcode.easy;

import java.util.HashMap;

/*
 * Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false
 */
public class TwoSumIIIDataStructureDesign {
	
	HashMap<Integer, Integer> map;
    /** Initialize your data structure here. */
    public TwoSumIIIDataStructureDesign() {
    	this.map=new HashMap<Integer,Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number,0)+1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        boolean res=false;
        for(Integer i: map.keySet()) {
        	if((map.keySet().contains(value-i)&&i!=value-i)||(i==value-i&&map.get(i)>1)) return true;
        }
        return res;
    }
}
