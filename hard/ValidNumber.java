package leetcode.hard;
/*
 * Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one. 
However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.
 */
public class ValidNumber {
    public static boolean isNumber(String s) {
        boolean dot_flag=false, e_flag=false;
        while(!s.isEmpty()&&s.charAt(0)==' ')
        	s=s.substring(1);
        while(!s.isEmpty()&&s.charAt(s.length()-1)==' ')
        	s=s.strip();
        
        if(s.isEmpty()) return false;		
        for(int i=0;i<s.length();i++) {
        	char c=s.charAt(i);
        	if(c==' ') return false;
        	if((c>'9'||c<'0')&&!(c=='e'||c=='+'||c=='-'||c=='.')) return false;
        	if(c=='+'&&i!=0) return false;
        	if(c=='-'&&((i>0&&s.charAt(i-1)!='e')||i==s.length()-1)) return false;
        	if(c=='.'&&(dot_flag||e_flag||(i==0&&i==s.length()-1))) return false;
        	else if(c=='.') dot_flag=true;
        	if(s.contentEquals("+.")) return false;
        	if(s.contentEquals("-.")) return false;
            if(c=='.'&&i>0&&i<s.length()-1&&s.charAt(i+1)=='e'&&(s.charAt(i-1)>'9'||s.charAt(i-1)<'0')) return false;
            if(c=='.'&&i==0&&i<s.length()-1&&s.charAt(i+1)=='e') return false;
        	if(c=='e'&&(i==s.length()-1||e_flag||i==0||(i>0&&(s.charAt(i-1)>'9'||s.charAt(i-1)<'0')&&s.charAt(i-1)!='.'))) return false;
        	else if(c=='e') e_flag=true;
        }
        return true;
    }
    public static void main(String args[]) {
    	String s="+.e3";
    	System.out.println(isNumber(s));
    }
}
