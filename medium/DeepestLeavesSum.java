package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Given a binary tree, return the sum of values of its deepest leaves.
 */
public class DeepestLeavesSum {
    public int deepestLeavesSum(TreeNode root) {
    	Queue<TreeNode> que=new LinkedList<TreeNode>();
    	Queue<Integer> layer=new LinkedList<Integer>();
    	Map<Integer, List<Integer>> map=new HashMap<Integer, List<Integer>>();
    	int max_layer=0;
    	int res=0;
    	if(root!=null) { que.add(root); layer.add(1);}
    	while(!que.isEmpty()) {
    		TreeNode cur=que.poll();
    		int l=layer.poll();
    		if(cur.left==null && cur.right==null) {
    			List<Integer> list=map.getOrDefault(l, new ArrayList<Integer>());
    			list.add(cur.val);
    			map.put(l, list);
        		max_layer=Math.max(max_layer, l);
    		}else {
    			if(cur.left!=null) {que.add(cur.left); layer.add(l+1);}
    			if(cur.right!=null) {que.add(cur.right); layer.add(l+1);}
    		}
    	}
    	List<Integer> deep_list=map.get(max_layer);
    	for(int val: deep_list) {
    		res+=val;
    	}
    	
        return res;
    }
}
