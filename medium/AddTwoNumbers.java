package leetcode.medium;
/*
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
class ListNode {
    int val;
	ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode res=null;
        if(l1==null && l2==null) return null;
        res=new ListNode();
        ListNode tail=res, p=res;
        while(l1!=null || l2!=null)
        {
        	int val=carry;
        	val+=(l1==null)?0:l1.val;
        	val+=(l2==null)?0:l2.val;
        	l1=(l1==null)?null:l1.next;
        	l2=(l2==null)?null:l2.next;
        	carry=val/10;
        	val%=10;
        	p.val=val;        	
        	tail=p;
        	p=new ListNode();
        	tail.next=p;
        }
        if(carry==1) {p.val=1; tail=tail.next;}
        tail.next=null;
        return res;
    }
    public static void main(String args[]) {
    	ListNode n1=new ListNode(1);
    	ListNode n2=new ListNode(2);
    	ListNode n3=new ListNode(9);
    	ListNode n4=new ListNode(8);
    	n1.next=n2;
    	n3.next=n4;
    	n2.next=null;
    	n4.next=null;
    	addTwoNumbers(n1,n3);
    }
}