package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

import structures.EmployeeInterval;

/*
 * We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, 
also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists 
or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not 
defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

 

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

Constraints:

1 <= schedule.length , schedule[i].length <= 50
0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class EmployeeFreeTime {
	/*
	 * Intuition: 1
	 * 1. Get all the working time span, sort and merge the list
	 * 2. Get the non-working time span and return them
	 * Time Complexity: O(nlogn)
	 * Space Complexity: O(n)
	 */
	public static List<EmployeeInterval> merge(List<EmployeeInterval> busy, EmployeeInterval val) {
		EmployeeInterval cur = val;
		List<EmployeeInterval> res = new ArrayList<EmployeeInterval>();
		for (int i = 0; i < busy.size(); i++) {
			EmployeeInterval interval = busy.get(i);
			if (interval.end < cur.start) {
				res.add(interval);
				if (i == busy.size() - 1) res.add(cur);
				continue;
			} else if (interval.start > cur.end) {
				res.add(cur);
				cur=new EmployeeInterval(interval.start, interval.end);
				if(i == busy.size() - 1) res.add(cur);
				continue;
			}
			cur.start = Math.min(cur.start, interval.start);
			cur.end = Math.max(cur.end, interval.end);
			if (i == busy.size() - 1)
				res.add(cur);
		}
		return res;
	}

	public static List<EmployeeInterval> employeeFreeTime(List<List<EmployeeInterval>> schedule) {
		List<EmployeeInterval> busy = new ArrayList<EmployeeInterval>();
		List<EmployeeInterval> res = new ArrayList<EmployeeInterval>();

		if (schedule.isEmpty())
			return busy;
		busy.addAll(schedule.get(0));
		for (int i = 1; i < schedule.size(); i++) {
			for (int j = 0; j < schedule.get(i).size(); j++) {
				busy = merge(busy, schedule.get(i).get(j));
			}
		}
		int l = busy.size();
		for (int i = 0; i < l - 1; i++) {
			EmployeeInterval interval = new EmployeeInterval();
			interval.start = busy.get(i).end;
			interval.end = busy.get(i + 1).start;
			res.add(interval);
		}
		return res;
	}

	public static void main(String args[]) {
		List<List<EmployeeInterval>> schedule = new ArrayList<List<EmployeeInterval>>();
		List<EmployeeInterval> l1 = new ArrayList<EmployeeInterval>();
		List<EmployeeInterval> l2 = new ArrayList<EmployeeInterval>();
		List<EmployeeInterval> l3 = new ArrayList<EmployeeInterval>();
		// [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
		// [[[1,2],[5,6]],[[1,3]],[[4,10]]]
		// [[[45,56],[89,96]],[[5,21],[57,74]]]
		EmployeeInterval i1 = new EmployeeInterval();
		i1.start = 45;
		i1.end = 56;
		EmployeeInterval i2 = new EmployeeInterval();
		i2.start = 89;
		i2.end = 96;
		EmployeeInterval i3 = new EmployeeInterval();
		i3.start = 5;
		i3.end = 21;
		EmployeeInterval i4 = new EmployeeInterval();
		i4.start = 57;
		i4.end = 74;
		
		//EmployeeInterval i5=new EmployeeInterval(); i5.start=9; i5.end=12;
		
		l1.add(i1);
		l1.add(i2);
		l2.add(i3);
		l2.add(i4); // l3.add(i5);
		schedule.add(l1);
		schedule.add(l2);
		//schedule.add(l3);
		employeeFreeTime(schedule);
	}
}
