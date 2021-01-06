package leetcode.medium;

import java.util.List;
import java.util.ArrayList;

/*
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.
 */
public class AllFullBT {
	/*
	 * intuition
	 * N has to be odd number
	 * given N-1, build the left tree and right tree
	 */
    public static List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> list=new ArrayList<TreeNode>();
        if(N%2==0) return list;
        if(N==1) {
            TreeNode node=new TreeNode(0);
        	list.add(node);
        	return list;
        }
        //N-1 is even 
        for(int i=1; i<=(N-1)/2; i+=2) {
        	if(i==(N-1)/2 && i%2==1) {
        		List<TreeNode> l1=allPossibleFBT(i); 
        		int size=l1.size();
        		for(int j=0; j<size; j++) {
        			for(int k=0; k<size; k++) {
                    	TreeNode node=new TreeNode(0);
                    	node.left=l1.get(k);
                    	node.right=l1.get(j);//TODO: copy the left tree;
                    	list.add(node);
        			}
        		}
        	}else {
        		List<TreeNode> l1=allPossibleFBT(i);
        		List<TreeNode> l2=allPossibleFBT(N-1-i);
        		for(TreeNode tn1: l1) {
        			for(TreeNode tn2: l2) {
                		TreeNode n1=new TreeNode(0);
                		TreeNode n2=new TreeNode(0);
        				n1.left=tn1;
        				n1.right=tn2;
        				n2.left=tn2;
        				n2.right=tn1;
        				list.add(n1);
        				list.add(n2);
        			}
        		}
        	}
        }
        return list;
    }
    
    public static void main(String args[]) {
    	allPossibleFBT(7);
    }
}
