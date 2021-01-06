package leetcode.hard;

import java.util.Stack;

/*
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 */
public class BasicCalculatorIII {
	public static String helper(String s) {
    	Stack<Integer> stack=new Stack<Integer>();
    	Integer res=0, num=0;
    	char lastSign='+';
    	char []cArray=s.toCharArray();
    	
    	for(int i=0; i<cArray.length; i++) {
    		char c=cArray[i];
    		if(c<='9' && c>='0') num=num*10+c-'0';
    		if(c=='+' || c=='-' || c=='*' || c=='/' || i==cArray.length-1) {
    			if(lastSign=='+' || lastSign=='-') {
    				int temp=(lastSign=='+')?num:-num;
    				stack.push(temp);
    				res+=temp; //No matter what, add into the res
    			}
    			
    			if(lastSign=='*' || lastSign=='/') {
    				res-=stack.peek(); // minus the last added number, because * or / has higher priority
    				int temp=(lastSign=='*')?stack.pop()*num:stack.pop()/num;
    				stack.push(temp);
    				res+=temp;
    			}
    			lastSign=c;
    			num=0;
    		}
    	}
		return res.toString();
	}
    public static int calculate(String s) {
    	s.replaceAll(" ", "");
        int index1=s.indexOf('(');
        int index2=s.indexOf(')');
        while(index1 != -1 && index2 != -1) {
	        String sub="\\("+s.substring(index1+1, index2)+"\\)";
	        String replacement=helper(s.substring(index1+1, index2));
	        s=s.replaceAll(sub, replacement);
	        index1=s.indexOf('(');
	        index2=s.indexOf(')');
        }
        String res=helper(s);
        return Integer.valueOf(res);
    }
    
    public static void main(String args[]) {
    	String s="1-(4-3)*2";
		/*
		 * String sub="4-3"; s=s.replaceAll("\\("+sub+ "\\)", "1");
		 * System.out.println(s);
		 */
    	System.out.println(calculate(s));
    }
}
