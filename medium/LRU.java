package leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
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
public class LRU {
	int size;
	HashMap<Integer, Integer> map;
	Queue<Integer> que;
    public LRU(int capacity) {
        this.size=capacity;
        que=new LinkedList<Integer>();
        map=new HashMap<Integer, Integer>();
    }
    
    public int get(int key) {
    	if(map.keySet().contains(key)) {
    		que.remove(key);
    		que.add(key);
    		return this.map.get(key);
    	}else return -1;
    }
    
    public void put(int key, int value) {
        if(!map.keySet().contains(key) && map.size()<size) {
        	que.add(key);
            map.put(key, value);
        }else if(map.keySet().contains(key)) {
        	map.put(key, value);
        	que.remove(key);
        	que.add(key);
        }else if(!map.keySet().contains(key) && map.size()==size) {
        	int remove_key=que.poll();
        	que.add(key);
        	map.remove(remove_key);
        	map.put(key, value);
        }
    }
    
    public static void main(String args[]) {
    	LRU cache=new LRU(2);
    	cache.put(2, 1);
    	cache.put(1, 1);
    	cache.put(2, 3);
    	cache.put(4, 1);
    	System.out.println(cache.get(1));  
    	System.out.println(cache.get(2));
    }
}
