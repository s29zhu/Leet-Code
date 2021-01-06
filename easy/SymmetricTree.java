package leetcode.easy;
import structures.TreeNode;


public class SymmetricTree {
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;
    return (t1.val == t2.val)
        && isMirror(t1.right, t2.left)
        && isMirror(t1.left, t2.right);
    }
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    public static void main(String [] args) {
    	TreeNode root= new TreeNode(1);
    	TreeNode l= new TreeNode(2);
    	TreeNode r= new TreeNode(2);
    	TreeNode l1= new TreeNode(3);
    	TreeNode l2= new TreeNode(4);
    	TreeNode r1= new TreeNode(4);
    	TreeNode r2= new TreeNode(3);
    	root.left=l;
    	root.right=r;
    	l.left=l1;
    	l.right=r1;
    	r.left=l2;
    	r.right=r2;
    	l1.left=null;
    	l1.right=null;
    	r1.left=null;
    	r1.right=null;
    	l2.left=null;
    	l2.right=null;
    	r2.left=null;
    	r2.right=null;
    	System.out.print(isSymmetric(root));
    }
}
