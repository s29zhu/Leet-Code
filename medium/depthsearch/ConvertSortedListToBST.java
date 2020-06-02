package leetcode.medium.depthsearch;
import java.util.ArrayList;
import leetcode.medium.depthsearch.ListNode;
import leetcode.medium.depthsearch.TreeNode;


/*
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
Given the sorted linked list: [-10,-3,0,5,9],
One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
      0
     / \
   -3   9
   /   /
 -10  5
 */
public class ConvertSortedListToBST {
    public static TreeNode sortedListToBST(ListNode head) {
        ArrayList<Integer> array=listToArray(head);
        if(array.isEmpty()) return null;
        else return arrayToBST(0,array.size()-1,array);
    }
    public static ArrayList<Integer> listToArray(ListNode head) {
    	ArrayList<Integer> array= new ArrayList<Integer>();
    	while(head!=null) {
    		array.add(head.val);
    		head=head.next;
    	}
    	return array;
    }
    public static TreeNode arrayToBST(int start, int end, ArrayList<Integer> array) {
    	TreeNode node=null;
    	if(start==end) {
    		node=new TreeNode(array.get(start));
    		node.left=null;
    		node.right=null;
    		return node;
    		
    	}else if (start > end) {
    		return node;
    	} else {
    		node=new TreeNode(array.get((start+end)/2));
    		node.left=arrayToBST(start,(end+start)/2-1,array);
    		node.right=arrayToBST((end+start)/2+1,end,array);
    		return node;
    	}
    };    
    public static void main(String []args) { 	
    	ListNode n1= new ListNode(1), n2= new ListNode(2),n3= new ListNode(3),
    			n4=new ListNode(4), n5=new ListNode(5);
    	n1.next=n2; n2.next=n3; n3.next=n4; n4.next=n5; n5.next=null;
    	sortedListToBST(n1);    	
    }
}
