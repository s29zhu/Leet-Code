package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
/*
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.
 */

/*
 * NOTE Union Find
 */
public class NumberofIsland {
	
	// exceed time limit
    public static int numIslands(char[][] grid) {
        int m=grid.length;
        if(m==0) return 0;
        int n=grid[0].length;
        int key=0;
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer,ArrayList<String>>();
        for(int i=0; i<m;i++) {
        	for(int j=0;j<n;j++) {
        		if(i==0 && j==0 && grid[i][j]=='1') {
        			ArrayList<String> list= new ArrayList<String>();
        			list.add(String.valueOf(i)+","+String.valueOf(j));
        			map.put(key, list);
        		} else if(i==0 && j >0 && grid[i][j]=='1') {
        			String str=String.valueOf(i)+","+String.valueOf(j-1);
        			int k=contains(map,str);
    				str=String.valueOf(i)+","+String.valueOf(j);
        			if(k==-1) {
        				key++;
        				ArrayList<String> nl=new ArrayList<String>();
        				nl.add(str);
        				map.put(key,nl);
        			}else {
        				map.get(k).add(str);
        			}
        		} else if(i > 0 && j==0 && grid[i][j]=='1') {
        			String str=String.valueOf(i-1)+","+String.valueOf(j);
        			int k=contains(map,str);
    				str=String.valueOf(i)+","+String.valueOf(j);
        			if(k==-1) {
        				key++;
        				ArrayList<String> nl=new ArrayList<String>();
        				nl.add(str);
        				map.put(key,nl);
        			}else {
        				map.get(k).add(str);
        			}
        		}
        		else if(i> 0 && j>0 && grid[i][j]=='1') {
        			String s1=String.valueOf(i-1)+","+String.valueOf(j);
        			String s2=String.valueOf(i)+","+String.valueOf(j-1);
        			int k1=contains(map,s1), k2=contains(map,s2);
    				s1=String.valueOf(i)+","+String.valueOf(j);

        			if(k1==k2 && k1==-1) {
        				key++;
        				ArrayList<String> nl=new ArrayList<String>();
        				nl.add(s1);
        				map.put(key,nl);
        			} else if(k1 != -1 && k2 != -1 && k1 != k2) {
        				merge(map, k1,k2);
        				map.get(k1).add(s1);
        			} else if(k2 != -1) {
        				map.get(k2).add(s1);
        			}else if( k1 != -1){
        				map.get(k1).add(s1);        				
        			}
        		}
        			
        	}
        }
        return map.keySet().size();
    }
    public static void merge(HashMap<Integer, ArrayList<String>> map, int k1, int k2) {
    	for(String str: map.get(k2)) {
    		map.get(k1).add(str);
    	}
    	map.remove(k2);
    }
    public static int contains(HashMap<Integer, ArrayList<String>> map, String str) {
    	int res=-1;
    	for(int key:  map.keySet()) {
    		for(String s: map.get(key)) {
    			if(s.equals(str)) return key;
    		}
    	}
    	return res;
    }

	public static void main(String args[]) {
		/*
		 * char [][] grid= {{'1','1','1','1','0'}, {'1','1','0','1','0'},
		 * {'1','1','0','0','0'}, {'0','0','0','0','0'}};
		 */
		char [][]grid= {{'1'}};
    	System.out.println(numIslands(grid));
    }
}