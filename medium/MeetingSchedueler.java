package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.  

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

 

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 

Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6 
 */
public class MeetingSchedueler {
    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res=new ArrayList<Integer>();
        ArrayList<int[]> l1=new ArrayList<int[]>();
        ArrayList<int[]> l2=new ArrayList<int[]>();
        //put the slots from slots1 and slots2 into slots
        for(int i=0; i<slots1.length;i++) l1.add(slots1[i]);
        for(int i=0; i<slots2.length;i++) l2.add(slots2[i]);
        //sort slots based on the first element
        l1.sort((slot1, slot2)->{
        	return slot1[0]-slot2[0];
        });
        l2.sort((slot1, slot2)->{
        	return slot1[0]-slot2[0];
        }); 
     
        for(int i=0; i<l1.size();i++) {
        	for(int j=0;j<l2.size();j++) {
        		// possible overlaps, h1-l2, h2-l1, h2-l2, h1-l1
        		int overlap=Integer.MAX_VALUE;
        		int start=0;
        		int temp=l1.get(i)[1]-l2.get(j)[0];
        		if(temp < duration) break;
        		overlap=temp;
        		start=l2.get(j)[0];
        		temp=l2.get(j)[1]-l1.get(i)[0];
        		if(temp<duration) continue;
        		if(temp < overlap ) {
        			start=l1.get(i)[0];
        			overlap=l2.get(j)[1]-l1.get(i)[0];
        		}
        		temp=l1.get(i)[1]-l1.get(i)[0];
        		if(temp < duration) break;
        		if(temp < overlap) {
        			start=l1.get(i)[0];
        			overlap=l1.get(i)[1]-l1.get(i)[0];
        		}
        		temp=l2.get(j)[1]-l2.get(j)[0];
        		if(temp < duration) continue;
        		if(temp < overlap) {
        			start=l2.get(j)[0];
        			overlap=l2.get(j)[1]-l2.get(j)[0];
        		}
        		res.add(start);
        		res.add(start+duration);
        		return res;
        	}
        }
        return res;
    }
    
    public static void main(String args[]) {
    	int[][] slots1= {{7,13}},    					
    			slots2= {{9,15}};
    	minAvailableDuration(slots1, slots2, 2);
    }
}
