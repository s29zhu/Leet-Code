package leetcode.hard;
/*
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeKSortedList {
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head=null, cur=null, min=null;
        boolean flag=true;
        int k=lists.length;
    	min=new ListNode(Integer.MAX_VALUE,null);
    	int i=0,j=0;
    	//Get Head Node
    	for(i=0;i<k;i++) {
    		if(lists[i]!=null && min.val > lists[i].val) {
    			min.val=lists[i].val;
    			j=i;
    		}
    	}	
    	if(min.val==Integer.MAX_VALUE) return head;
    	else {
    		head=cur=min;
    		lists[j]=lists[j].next;
    	}
    	//Handle the rest of the Nodes
        while(flag) {
        	for(i=0,j=0;i<k;i++) {
        		if(lists[i]==null) j++;
        		if(j==k) return head;
        	}
        	min=new ListNode(Integer.MAX_VALUE,null);
        	if(head==null) head=cur;
        	for(i=0;i<k;i++) {
        		if(lists[i]!=null && min.val > lists[i].val) {
        			min.val=lists[i].val;
        			j=i;
        		}
        	}
        	lists[j]=lists[j].next;
        	cur.next=min;
        	cur=min;
        	
        }
        return head;
    }
    public static void main(String args[]) {
    	ListNode n1=new ListNode(8,null);
    	ListNode n2=new ListNode(6,n1);
    	ListNode n3=new ListNode(6,null);
    	ListNode n4=new ListNode(7,null);

    	ListNode [] lists= {n2, null, n3, n4,null};
    	ListNode head=mergeKLists(lists);
    	while(head!=null) {
    		System.out.print(head.val);
    		head=head.next;
    	}
    }
}