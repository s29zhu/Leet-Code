package leetcode.easy;

import java.util.Vector;

/*
 * Implement an iterator to flatten a 2d vector.
For example,
Given 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten2DVector {
	public static Vector<Integer> flatten2DVector(Vector<Vector<Integer>> input) {
		Vector<Integer> v= new Vector<Integer>();
		input.forEach(sub->{
			sub.forEach(i->{
				v.add(i);
			});
		});
		return v;
	}
	
	public static void main(String args[]) {
		Vector<Integer> v1= new Vector<Integer>();
		v1.add(1);
		v1.add(2);
		v1.add(3);
		Vector<Integer> v2= new Vector<Integer>();
		v2.add(4);
		v2.add(8);
		Vector<Vector<Integer>> v=new Vector<Vector<Integer>>();
		v.add(v1);
		v.add(v2);
		
		//Vector<Integer> res=flatten2DVector(v);
	}
}
