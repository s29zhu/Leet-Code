package leetcode.easy;
import java.util.Stack;

public class removeOuterParentheses {
    public static String removeOuterParentheses(String S) {
        int l=S.length();
        String res="";
        Stack<Character> s=new Stack<Character>();
        Stack<Integer> s1=new Stack<Integer>();
        for(int i=0; i<l; i++){
            char c=S.charAt(i);
            if(c=='('){
                s.push(c);
                s1.push(i);
            }else{
                s.pop();
                int index=s1.pop();

                if(s.isEmpty()) { res+=S.substring(index+1,i); }
            }
            
        }
        return res;
    }
    
    public static void main(String args[]) {
    	System.out.println(removeOuterParentheses("((()(())))"));
    }
}
