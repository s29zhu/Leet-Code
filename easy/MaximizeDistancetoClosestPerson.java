package leetcode.easy;
/*
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
 */

/*
 Analysis:
 an array to save the distance when scan from beginning to end
 then scan from end to beginning, compare the distance,
 */
public class MaximizeDistancetoClosestPerson {
    public static int maxDistToClosest(int[] seats) {
        int l=seats.length,max_d=0,pre_1=0;
        int [] distances=new int[l];
        
        //scan from beginning to end
        pre_1=-1;
        for(int i=0;i<l;i++) {
        	if(seats[i]==1) {
        		distances[i]=0;
        		pre_1=i;
        	}else {
        		distances[i]=i-pre_1;
        	}
        }        
        //scan from end to beginning
        pre_1=l;
        for(int i=l-1; i>=0;i--) {
        	if(seats[i]==1) {
        		pre_1=i;
        	}else {
        		if(i==l-1) {
        			distances[i]=distances[i];
        		}else if(i==0) {
        			distances[i]=pre_1-i;
        		}else {
        			distances[i]=Math.min(distances[i], pre_1-i);
        		}        			
        		max_d=(distances[i]>max_d)? distances[i]:max_d;
        	}
        }
        return max_d;
    }
    public static void main(String args[]) {
    	int [] seats= {0,0,0,0,1,0,0,0};
    	System.out.print(maxDistToClosest(seats));;
    }
}
