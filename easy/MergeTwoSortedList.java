package leetcode.easy;
import structures.ListNode;
/*
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
 */

public class MergeTwoSortedList {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode res=null, cur=null;
        if(l1.val<=l2.val) {
        	res=l1;
        	l1=l1.next;
        }
        else {
        	res=l2;
        	l2=l2.next;
        }
        cur=res;
        while(l1!=null || l2!=null){
            int v1=(l1==null)?Integer.MAX_VALUE:l1.val;
            int v2=(l2==null)?Integer.MAX_VALUE:l2.val;
            if(v1<=v2){
            	cur.next=l1;
                l1=l1.next;
            }else{
                cur.next=l2;
                l2=l2.next;
            }
        	cur=cur.next;
        }
        cur.next=null;
        return res;
    }
    
    /*
     * LeetCode solution
     * 
     */
    public ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
    public static void main(String args[]) {
    	ListNode n1=new ListNode(1);
    	ListNode n2=new ListNode(2);
    	ListNode n3=new ListNode(3);
    	ListNode n11=new ListNode(1);
    	ListNode n12=new ListNode(2);
    	
    	n1.next=n2;
    	n2.next=n3;
    	n3.next=null;
    	n11.next=n12;
    	n12.next=null;
    	mergeTwoLists(n1, n11);
    }
}
