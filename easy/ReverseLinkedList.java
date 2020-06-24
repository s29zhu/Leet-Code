package leetcode.easy;
/*
 * https://leetcode.com/problems/reverse-linked-list/
 * Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
public class ReverseLinkedList {
	public ListNode helper(ListNode l1, ListNode l2) {
		if(l2==null) return l1;
		ListNode new_head=l2.next;
		l2.next=l1;
		l1=l2;
		return helper(l1, new_head);
	}
	public ListNode reverseListRecursively(ListNode head) {
		return helper(null, head);
	}
    public ListNode reverseListIteratively(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode p1=head, p2=head.next,p0=null,temp=null;
        while(p2!=null) {
        	p1.next=p0;
        	p0=p1;
        	temp=p2.next;
        	p2.next=p1;
        	p1=p2;
        	p2=temp;
        }
        return p1;
    }
    
    public static void main(String args[]) {
    	
    }
}
