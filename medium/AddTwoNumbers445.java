/*
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The most significant digit comes first and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list. 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
package leetcode.medium;
/*
 * 1. get the length of l1, length1, and l2, length2, make sure l1 always point to the longer list,
 * length1 is the length of l1
 * 2. add l2 part to l1 and save the carry
 * 3. add carry to the higher positions to l1, 
 */
public class AddTwoNumbers445 {
	public static int helper1(ListNode l1, ListNode l2) {
		if(l1==null || l2==null) return 0;
		int carry=helper1(l1.next, l2.next);
		int sum=l1.val+l2.val+carry;
		carry=sum/10;
		l1.val=sum%10;
		return carry;
	}
	
	public static int helper2(ListNode l1, ListNode l2, int c) {
		
		if(l1==l2) {
			int sum=l1.val+c;
			c=sum/10;
			l1.val=sum%10;
		}else {
			c=helper2(l1.next,l2,c);
			int sum=l1.val+c;
			c=sum/10;
			l1.val=sum%10;
		}		
		return c;
	}
	
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0, length1=0, length2=0;
        ListNode h1=l1, h2=l2, retval=null;
        
        while(l1!=null) {
        	l1=l1.next;
        	length1++;
        }
        while(l2!=null) {
        	l2=l2.next;
        	length2++;
        }       
        if(length1<length2) {
        	int temp=length1;
        	ListNode temp_n=h1;
        	length1=length2;
        	h1=h2;
        	length2=temp;
        	h2=temp_n;
        }
        
        int diff=length1-length2;
        if(diff == 0 ) {
            carry=helper1(retval, h2);
        }else {	        	
	        retval=h1;
	        while(diff>1) {
	        	retval=retval.next;
	        	diff--;
	        }       
	        carry=helper1(retval.next, h2);
	        carry=helper2(h1,retval, carry);
        }
        if (carry == 1) {
        	ListNode head=new ListNode(1);
        	head.next=h1;
        	h1=head;
        }
        return h1;
    }
    
    public static void main(String [] args) 
    {
    	ListNode l1=new ListNode(1);
    	ListNode ln=new ListNode(2);
    	l1.next=ln;
    	ln.next=null;
    	ListNode l2=new ListNode(1);
    	ListNode ln2=new ListNode(3);
    	l2.next=ln2;
    	ln2.next=null;
    	ListNode retval=addTwoNumbers(l1, l2);
    }
}
