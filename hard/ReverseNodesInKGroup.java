package leetcode.hard;
/*
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroup {
	/*
	 * intuition:
	 * use a header and tail pointer 
	 */
	public static void reverseList(ListNode h, ListNode t) {
		if(h==t) return;
		ListNode h1=h, h2=h.next;
		while(h2!=t) {
			ListNode temp=h2.next;
			h2.next=h1;
			h1=h2;
			h2=temp;
		}
		t.next=h1;
	}
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode p=head, t=null, h=null, res=head, pre_tail=null;
        boolean flag=true;
        int i=0;
        while(p!=null) {
        	i++;
        	if(i==1) h=p;
        	if(i==k) {
        		i=0;
        		t=p;
        		if(flag) {res=t;flag=false;}
        		ListNode save=t.next;
        		reverseList(h,t);
        		if(pre_tail!=null) pre_tail.next=t;
        		pre_tail=h;
        		pre_tail.next=save;
        		p=pre_tail;
        	}
        	p=p.next;
        }
        return res;
    }
    public static void main(String args[]) {
    	ListNode n1=new ListNode(1);
    	ListNode n2=new ListNode(2);
    	ListNode n3=new ListNode(3);
    	ListNode n4=new ListNode(4);
    	ListNode n5=new ListNode(5);

    	n1.next=n2;
    	n2.next=n3;
    	n3.next=n4;
    	n4.next=n5;
    	n5.next=null;
    	reverseKGroup(n1,2);
    }
}
