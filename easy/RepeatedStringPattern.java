package leetcode.easy;

public class RepeatedStringPattern {
    public static boolean repeatedSubstringPattern(String s) {
        boolean res=false;
        int l=s.length();
        StringBuilder str=new StringBuilder("");
        for(int i=0; i<l/2; i++) {
        	str=str.append(s.charAt(i));
        	if((l%(i+1))!=0) continue;
        	for(int j=2*i+2;j<=l;j+=(i+1)) {
        		StringBuilder temp=new StringBuilder(s.substring(j-i-1,j));
        		if(!temp.toString().equals(str.toString())) j=l; 
        		if(temp.toString().equals(str.toString())&&j==l) res=true;
        	}
        }
        return res;
    }
    public static void main(String args[]) {
    	StringBuilder s=new StringBuilder("abcdabcabc");
    	//System.out.println(s.toString().equals(t.toString()));
    	System.out.print(repeatedSubstringPattern(s.toString()));
    }
}
