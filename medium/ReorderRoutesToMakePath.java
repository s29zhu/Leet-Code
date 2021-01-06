package leetcode.medium;
/*
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

 * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.

 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

 * It's guaranteed that each city can reach the city 0 after reorder.
 */

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * thoughts
 * 1. first iterate through the array, put the connections into a map, from->to, positive number, to->-from, negative number
 * 2. DFS
 * 2.1start from city 0, get all the neighbours of 0, since there were only n-1 roads, every city just has 1 path leading to city 0,
 * which means, if the path between 0 and neighbour is reversed, we need to rearrange it. 
 * 2.2 for each of the neighbour of city 0, get their neighbour, 
 * --- if the direction is reversed, result++, and perform dfs,
 * --- if the direction is correct, perform count+=dfs
 */

public class ReorderRoutesToMakePath {
    public static int minReorder(int n, int[][] con) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0;i < con.length; i++){
            map.putIfAbsent(con[i][0], new ArrayList<>());
            map.get(con[i][0]).add(con[i][1]);// the direction of road
            map.putIfAbsent(con[i][1], new ArrayList<>());
            map.get(con[i][1]).add(-1*con[i][0]);// saving the opp direction off road as -ve value
        }
        return dfs(0,map, 0);        
    }
    static int dfs(int prev,HashMap<Integer, List<Integer>> map,int index){
        int count = 0;
        for(Integer node: map.get(index)){
            if(Math.abs(node) == prev){// if this node is the one we came from we need to ignore
                continue;
            }
            if(node > 0){// We are traveling from 0 to outside but Question is opposite side hence incrementing on +ve side
                count++;
            }
            count += dfs(index, map, Math.abs(node));
        }
        return count;
    }
    
    public static void main(String args[]) {
    	int [][] connections= {{0,1},{1,2}};//,{2,3},{4,0},{4,5}
    	System.out.println(minReorder(3, connections) );
    }
}
