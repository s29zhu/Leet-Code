package leetcode.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
 * otherwise return -1. put(key, value) - Set or insert the value if the key is not already 
 * present. When the cache reached its capacity, it should invalidate the least recently used 
 * item before inserting a new item.
 * The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */
class LRU extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRU(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
	
    public static void main(String args[]) {
    	LRU cache=new LRU(2);
    	//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
    	cache.put(1, 1);
    	cache.put(2, 2);
    	System.out.println(cache.get(1)); 
    	cache.put(3, 3);
    	System.out.println(cache.get(2)); 
    	cache.put(4, 4);
    	System.out.println(cache.get(1));  
    	System.out.println(cache.get(3));
    	System.out.println(cache.get(4));
    }
}
