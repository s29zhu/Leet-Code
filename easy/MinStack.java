package leetcode.easy;

import java.util.Stack;

/*
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:

Methods pop, top and getMin operations will always be called on non-empty stacks.
 */
public class MinStack {
    /** initialize your data structure here. */
	Stack<Long> stack;
	long min;
    public MinStack() {
    	stack=new Stack<Long>();
    }
    
    public void push(int x) {
        if(stack.isEmpty()) {
        	stack.push(0L);
        	min=x;
        }else {
        	stack.push(x-min);
        	if(x<min) min=x;
        }
    }
    
    public void pop() {
    	if(stack.isEmpty()) return;
    	long pop=stack.pop();
    	if(pop<0) min=min-pop;
    }
    
    public int top() {
    	long top=stack.peek();
    	if(top<0) return (int)min;
    	else return (int)(min+top);
    }
    
    public int getMin() {
        return (int)min;
    }
    public static void main(String []args) {
    	MinStack minStack = new MinStack();
    	minStack.push(-2);
    	minStack.push(0);
    	minStack.push(-3);
    	minStack.getMin(); // return -3
    	minStack.pop();
    	minStack.top();    // return 0
    	minStack.getMin(); // return -2
    }
}
